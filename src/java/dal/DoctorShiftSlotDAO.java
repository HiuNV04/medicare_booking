package dal;

import model.DoctorShiftSlot;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DoctorShiftSlotDAO extends MyDAO {

    public List<DoctorShiftSlot> getShiftsByDoctorForWeek(int doctorId, LocalDate startDate, LocalDate endDate) {
        List<DoctorShiftSlot> list = new ArrayList<>();
        xSql = "SELECT * FROM doctor_shift_slot WHERE doctor_id = ? AND date >= ? AND date <= ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, doctorId);
            ps.setDate(2, Date.valueOf(startDate));
            ps.setDate(3, Date.valueOf(endDate));
            rs = ps.executeQuery();
            while (rs.next()) {
                DoctorShiftSlot slot = new DoctorShiftSlot();
                slot.setId(rs.getInt("id"));
                slot.setDoctorId(rs.getInt("doctor_id"));
                slot.setSlotStartTime(rs.getTime("slot_start_time"));
                slot.setSlotEndTime(rs.getTime("slot_end_time"));
                slot.setDate(rs.getDate("date"));
                slot.setBooked(rs.getBoolean("is_booked"));
                list.add(slot);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addShift(int doctorId, Date date, Time startTime, Time endTime) {
        xSql = "INSERT INTO doctor_shift_slot (doctor_id, date, slot_start_time, slot_end_time, is_booked) VALUES (?, ?, ?, ?, 0)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, doctorId);
            ps.setDate(2, date);
            ps.setTime(3, startTime);
            ps.setTime(4, endTime);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteShift(int slotId) {
        xSql = "DELETE FROM doctor_shift_slot WHERE id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, slotId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Kiểm tra đã tồn tại ca chưa (theo doctorId, date, hour)
    public boolean isShiftExists(int doctorId, Date date, int hour) {
        xSql = "SELECT COUNT(*) FROM doctor_shift_slot WHERE doctor_id = ? AND date = ? AND HOUR(slot_start_time) = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, doctorId);
            ps.setDate(2, date);
            ps.setInt(3, hour);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Kiểm tra ca đã được đặt chưa (dựa vào bảng appointment_schedule)
    public boolean isShiftBooked(int shiftId) {
        xSql = "SELECT COUNT(*) FROM appointment_schedule WHERE doctor_shift_id = ? AND (confirmation_status = 'Pending' OR confirmation_status = 'Approved')";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, shiftId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lấy tất cả ca khám
    public List<DoctorShiftSlot> getAllShifts() {
        List<DoctorShiftSlot> list = new ArrayList<>();
        xSql = "SELECT * FROM doctor_shift_slot";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DoctorShiftSlot slot = new DoctorShiftSlot();
                slot.setId(rs.getInt("id"));
                slot.setDoctorId(rs.getInt("doctor_id"));
                slot.setSlotStartTime(rs.getTime("slot_start_time"));
                slot.setSlotEndTime(rs.getTime("slot_end_time"));
                slot.setDate(rs.getDate("date"));
                slot.setBooked(rs.getBoolean("is_booked"));
                list.add(slot);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy ca khám theo bác sĩ
    public List<DoctorShiftSlot> getShiftsByDoctor(int doctorId) {
        List<DoctorShiftSlot> list = new ArrayList<>();
        xSql = "SELECT * FROM doctor_shift_slot WHERE doctor_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, doctorId);
            rs = ps.executeQuery();
            while (rs.next()) {
                DoctorShiftSlot slot = new DoctorShiftSlot();
                slot.setId(rs.getInt("id"));
                slot.setDoctorId(rs.getInt("doctor_id"));
                slot.setSlotStartTime(rs.getTime("slot_start_time"));
                slot.setSlotEndTime(rs.getTime("slot_end_time"));
                slot.setDate(rs.getDate("date"));
                slot.setBooked(rs.getBoolean("is_booked"));
                list.add(slot);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy ca khám theo ngày
    public List<DoctorShiftSlot> getShiftsByDate(Date date) {
        List<DoctorShiftSlot> list = new ArrayList<>();
        xSql = "SELECT * FROM doctor_shift_slot WHERE date = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setDate(1, date);
            rs = ps.executeQuery();
            while (rs.next()) {
                DoctorShiftSlot slot = new DoctorShiftSlot();
                slot.setId(rs.getInt("id"));
                slot.setDoctorId(rs.getInt("doctor_id"));
                slot.setSlotStartTime(rs.getTime("slot_start_time"));
                slot.setSlotEndTime(rs.getTime("slot_end_time"));
                slot.setDate(rs.getDate("date"));
                slot.setBooked(rs.getBoolean("is_booked"));
                list.add(slot);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy ca khám theo bác sĩ và ngày
    public List<DoctorShiftSlot> getShiftsByDoctorAndDate(int doctorId, Date date) {
        List<DoctorShiftSlot> list = new ArrayList<>();
        xSql = "SELECT * FROM doctor_shift_slot WHERE doctor_id = ? AND date = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, doctorId);
            ps.setDate(2, date);
            rs = ps.executeQuery();
            while (rs.next()) {
                DoctorShiftSlot slot = new DoctorShiftSlot();
                slot.setId(rs.getInt("id"));
                slot.setDoctorId(rs.getInt("doctor_id"));
                slot.setSlotStartTime(rs.getTime("slot_start_time"));
                slot.setSlotEndTime(rs.getTime("slot_end_time"));
                slot.setDate(rs.getDate("date"));
                slot.setBooked(rs.getBoolean("is_booked"));
                list.add(slot);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy 1 ca khám theo id
    public DoctorShiftSlot getShiftById(int id) {
        DoctorShiftSlot slot = null;
        xSql = "SELECT * FROM doctor_shift_slot WHERE id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                slot = new DoctorShiftSlot();
                slot.setId(rs.getInt("id"));
                slot.setDoctorId(rs.getInt("doctor_id"));
                slot.setSlotStartTime(rs.getTime("slot_start_time"));
                slot.setSlotEndTime(rs.getTime("slot_end_time"));
                slot.setDate(rs.getDate("date"));
                slot.setBooked(rs.getBoolean("is_booked"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return slot;
    }

    // Đếm tổng số ca khám theo filter (phiên bản an toàn)
    public int countShifts(Integer doctorId, java.sql.Date date) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM doctor_shift_slot WHERE 1=1");
        List<Object> params = new ArrayList<>();
        if (doctorId != null) {
            sql.append(" AND doctor_id = ?");
            params.add(doctorId);
        }
        if (date != null) {
            sql.append(" AND date = ?");
            params.add(date);
        }
        try {
            ps = con.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Lấy ca khám theo filter và phân trang (mặc định sort theo date DESC, slot_start_time ASC, không nhận sortField/sortDir)
    public List<DoctorShiftSlot> getShiftsPaged(Integer doctorId, java.sql.Date date, int offset, int limit) {
        List<DoctorShiftSlot> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM doctor_shift_slot WHERE 1=1");
        List<Object> params = new ArrayList<>();
        if (doctorId != null) {
            sql.append(" AND doctor_id = ?");
            params.add(doctorId);
        }
        if (date != null) {
            sql.append(" AND date = ?");
            params.add(date);
        }
        sql.append(" ORDER BY id ASC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");
        params.add(offset);
        params.add(limit);
        try {
            ps = con.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                DoctorShiftSlot slot = new DoctorShiftSlot();
                slot.setId(rs.getInt("id"));
                slot.setDoctorId(rs.getInt("doctor_id"));
                slot.setSlotStartTime(rs.getTime("slot_start_time"));
                slot.setSlotEndTime(rs.getTime("slot_end_time"));
                slot.setDate(rs.getDate("date"));
                slot.setBooked(rs.getBoolean("is_booked"));
                list.add(slot);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
