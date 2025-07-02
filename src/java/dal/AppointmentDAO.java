/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;
import model.Appointment;

/**
 *
 * @author ADDMIN
 */
public class AppointmentDAO extends MyDAO {

    public AppointmentDAO() {
        super();
        if (con == null) {
            System.out.println("❌ DoctorDAO: Không kết nối được tới database");
        } else {
            System.out.println("✅ DoctorDAO: Kết nối database thành công");
        }
    }

    public List<Appointment> getAllAppointments() {
        List<Appointment> list = new ArrayList<>();

        xSql = "SELECT "
                + "a.id AS appointment_id, "
                + "p.id AS patient_id, p.full_name AS patient_name, "
                + "d.id AS doctor_id, d.full_name AS doctor_name, "
                + "s.date, s.slot_start_time, s.slot_end_time, "
                + "a.room_id, a.confirmation_status "
                + "FROM appointment_schedule a "
                + "JOIN patient p ON a.patient_id = p.id "
                + "JOIN doctor_shift_slot s ON a.doctor_shift_id = s.id "
                + "JOIN doctor d ON s.doctor_id = d.id "
                + "ORDER BY s.date ASC, s.slot_start_time ASC, a.id ASC";

        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Appointment a = new Appointment();
                a.setId(rs.getInt("appointment_id"));
                a.setDate(rs.getDate("date"));
                a.setSlotStartTime(rs.getTime("slot_start_time"));
                a.setSlotEndTime(rs.getTime("slot_end_time"));
                a.setPatientId(rs.getInt("patient_id"));
                a.setPatientName(rs.getString("patient_name"));
                a.setDoctorId(rs.getInt("doctor_id"));
                a.setDoctorName(rs.getString("doctor_name"));
                a.setRoomId(rs.getInt("room_id"));
                a.setConfirmationStatus(rs.getString("confirmation_status"));

                list.add(a);
            }
        } catch (Exception e) {
            System.out.println("❌ Lỗi khi truy vấn danh sách lịch hẹn: " + e.getMessage());
        }

        return list;
    }

    public List<Appointment> getAppointmentsPage(int offset, int limit) {
        List<Appointment> list = new ArrayList<>();

        xSql = "SELECT "
                + "a.id AS appointment_id, "
                + "p.full_name AS patient_name, "
                + "d.full_name AS doctor_name, "
                + "s.date, s.slot_start_time, s.slot_end_time, "
                + "a.room_id, a.confirmation_status "
                + "FROM appointment_schedule a "
                + "JOIN patient p ON a.patient_id = p.id "
                + "JOIN doctor_shift_slot s ON a.doctor_shift_id = s.id "
                + "JOIN doctor d ON s.doctor_id = d.id "
                + "ORDER BY s.date ASC, s.slot_start_time ASC";

        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Appointment a = new Appointment();
                a.setId(rs.getInt("appointment_id"));
                a.setDate(rs.getDate("date"));
                a.setSlotStartTime(rs.getTime("slot_start_time"));
                a.setSlotEndTime(rs.getTime("slot_end_time"));
                a.setPatientName(rs.getString("patient_name"));
                a.setDoctorName(rs.getString("doctor_name"));
                a.setRoomId(rs.getInt("room_id"));
                a.setConfirmationStatus(rs.getString("confirmation_status"));
                list.add(a);
            }

            // Xử lý phân trang sau khi lấy toàn bộ kết quả
            int toIndex = Math.min(offset + limit, list.size());
            if (offset > list.size()) {
                return new ArrayList<>();
            }
            return list.subList(offset, toIndex);

        } catch (Exception e) {
            System.out.println("Lỗi getAppointmentsPage: " + e.getMessage());
            return new ArrayList<>();
        }
    }

// Đếm tổng số lịch hẹn
    public int countAppointments() {
        xSql = "SELECT COUNT(*) FROM appointment_schedule";

        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("❌ Lỗi countAppointments: " + e.getMessage());
        }

        return 0;
    }

}
