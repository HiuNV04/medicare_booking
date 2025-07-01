/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.DoctorShiftSlot;

/**
 *
 * @author ADMIN
 */
public class DoctorShiftSlotDAO extends MyDAO {

    public DoctorShiftSlotDAO() {
        super();
        if (con == null) {
            System.out.println(" SlotShiftDAO: Không kết nối được  tới database");
        } else {
            System.out.println(" SlotShiftDAO: Kết nối database thành công");
        }
    }

    public List<DoctorShiftSlot> getAllSlotShift() {
        List<DoctorShiftSlot> list = new ArrayList<>();
        String sql = "SELECT dss.id AS slot_id, dss.slot_start_time, dss.slot_end_time, dss.date, dss.is_booked, "
                + "d.id AS doctor_id, d.full_name AS doctor_name, "
                + "s.id AS specialization_id, s.name AS specialization_name, "
                + "dl.id AS level_id, dl.name AS level_name "
                + "FROM doctor_shift_slot dss "
                + "JOIN doctor d ON dss.doctor_id = d.id "
                + "JOIN specialization s ON d.specialization_id = s.id "
                + "JOIN doctor_level dl ON d.doctor_level_id = dl.id";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DoctorShiftSlot s = new DoctorShiftSlot();
                s.setSlotId(rs.getInt("slot_id"));
                s.setDoctorId(rs.getInt("doctor_id"));
                s.setStart(rs.getTime("slot_start_time"));
                s.setEnd(rs.getTime("slot_end_time"));
                s.setSlotDate(rs.getDate("date"));
                s.setBooked(rs.getBoolean("is_booked"));
                s.setDoctorName(rs.getString("doctor_name"));
                s.setSpecializationId(rs.getInt("specialization_id"));
                s.setSpecializationName(rs.getString("specialization_name"));
                s.setLevelId(rs.getInt("level_id"));
                s.setLevelName(rs.getString("level_name"));

                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<DoctorShiftSlot> getTodaySlotByDoctorId(int doctorId) {
        List<DoctorShiftSlot> list = new ArrayList<>();

        String sql = "SELECT dss.id AS slot_id, dss.slot_start_time, dss.slot_end_time, dss.date, dss.is_booked, "
                + "d.full_name AS doctor_name, dl.name AS level_name, s.name AS specialization_name "
                + "FROM doctor_shift_slot dss "
                + "JOIN doctor d ON dss.doctor_id = d.id "
                + "LEFT JOIN doctor_level dl ON d.doctor_level_id = dl.id "
                + "LEFT JOIN specialization s ON d.specialization_id = s.id "
                + "WHERE d.id = ? AND dss.date = CAST(GETDATE() AS DATE)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, doctorId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DoctorShiftSlot s = new DoctorShiftSlot();
                s.setSlotId(rs.getInt("slot_id"));
                s.setStart(rs.getTime("slot_start_time"));
                s.setEnd(rs.getTime("slot_end_time"));
                s.setSlotDate(rs.getDate("date"));
                s.setBooked(rs.getBoolean("is_booked"));
                s.setDoctorName(rs.getString("doctor_name"));
                s.setLevelName(rs.getString("level_name"));
                s.setSpecializationName(rs.getString("specialization_name"));

                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // phân trang slot ca khám
    public List<DoctorShiftSlot> getSlotPage(int offset, int limit) {
        List<DoctorShiftSlot> list = new ArrayList<>();
        String sql = "SELECT dss.id AS slot_id, dss.slot_start_time, dss.slot_end_time, dss.date, dss.is_booked, "
                + "d.id AS doctor_id, d.full_name AS doctor_name, "
                + "s.id AS specialization_id, s.name AS specialization_name, "
                + "dl.id AS level_id, dl.name AS level_name "
                + "FROM doctor_shift_slot dss "
                + "JOIN doctor d ON dss.doctor_id = d.id "
                + "JOIN specialization s ON d.specialization_id = s.id "
                + "JOIN doctor_level dl ON d.doctor_level_id = dl.id "
                + "ORDER BY dss.date, dss.slot_start_time";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DoctorShiftSlot s = new DoctorShiftSlot();
                s.setSlotId(rs.getInt("slot_id"));
                s.setDoctorId(rs.getInt("doctor_id"));
                s.setStart(rs.getTime("slot_start_time"));
                s.setEnd(rs.getTime("slot_end_time"));
                s.setSlotDate(rs.getDate("date"));
                s.setBooked(rs.getBoolean("is_booked"));
                s.setDoctorName(rs.getString("doctor_name"));
                s.setSpecializationId(rs.getInt("specialization_id"));
                s.setSpecializationName(rs.getString("specialization_name"));
                s.setLevelId(rs.getInt("level_id"));
                s.setLevelName(rs.getString("level_name"));

                list.add(s);
            }

            int toIndex = Math.min(offset + limit, list.size());
            if (offset > list.size()) {
                return new ArrayList<>();
            }

            return list.subList(offset, toIndex);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    // đếm tổng số slot
    public int countAllSlots() {
        String sql = "SELECT COUNT(*) FROM doctor_shift_slot";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        DoctorShiftSlotDAO dao = new DoctorShiftSlotDAO();
        List<DoctorShiftSlot> slots = dao.getAllSlotShift();
        System.out.println("Slot: " + slots.size());
    }

}
