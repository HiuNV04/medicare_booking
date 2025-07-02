/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.List;
import model.AppointmentSchedule;
import model.Doctor;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import model.DoctorShiftSlot;
import model.Room;
import model.Specialization;
import model.Staff;

/**
 *
 * @author ADMIN
 */
public class ManagerDAO extends MyDAO{
    //AppointmentScheduleDAO
     public AppointmentSchedule getAppointmentByShiftId(int shiftId) {
        AppointmentSchedule schedule = null;
        xSql = "SELECT * FROM appointment_schedule WHERE doctor_shift_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, shiftId);
            rs = ps.executeQuery();
            if (rs.next()) {
                schedule = new AppointmentSchedule();
                schedule.setId(rs.getInt("id"));
                schedule.setPatientId(rs.getInt("patient_id"));
                schedule.setDoctorShiftId(rs.getInt("doctor_shift_id"));
                schedule.setRoomId(rs.getInt("room_id"));
                schedule.setConfirmationStatus(rs.getString("confirmation_status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schedule;
    }

    // Lấy trạng thái lịch hẹn theo shiftId
    public String getAppointmentStatusForShift(int shiftId) {
        xSql = "SELECT confirmation_status FROM appointment_schedule WHERE doctor_shift_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, shiftId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("confirmation_status");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Lấy danh sách lịch hẹn theo shiftId
    public java.util.List<AppointmentSchedule> getAppointmentsByShiftId(int shiftId) {
        java.util.List<AppointmentSchedule> list = new java.util.ArrayList<>();
        xSql = "SELECT * FROM appointment_schedule WHERE doctor_shift_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, shiftId);
            rs = ps.executeQuery();
            while (rs.next()) {
                AppointmentSchedule schedule = new AppointmentSchedule();
                schedule.setId(rs.getInt("id"));
                schedule.setPatientId(rs.getInt("patient_id"));
                schedule.setDoctorShiftId(rs.getInt("doctor_shift_id"));
                schedule.setRoomId(rs.getInt("room_id"));
                schedule.setConfirmationStatus(rs.getString("confirmation_status"));
                list.add(schedule);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    //DoctorDAO
      public Doctor getDoctorById(int id) {
        Doctor d = null;
        xSql = "SELECT * FROM doctor WHERE id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                d = extractDoctor(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM doctor";
        try {
             ps = con.prepareStatement(query);
             rs = ps.executeQuery();
            while (rs.next()) {
                Doctor d = new Doctor();
                d.setId(rs.getInt("id"));
                d.setFullName(rs.getString("full_name"));
                d.setDateOfBirth(rs.getDate("date_of_birth"));
                d.setGender(rs.getString("gender"));
                d.setAddress(rs.getString("address"));
                d.setPhoneNumber(rs.getString("phone_number"));
                d.setImageUrl(rs.getString("image_url"));
                d.setSpecializationId(rs.getInt("specialization_id"));
                d.setDoctorLevelId(rs.getInt("doctor_level_id"));
                d.setRole("Doctor");
                doctors.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    private Doctor extractDoctor(ResultSet rs) throws SQLException {
        Doctor d = new Doctor();
        d.setId(rs.getInt("id"));
        d.setImageUrl(rs.getString("image_url"));
        d.setEmail(rs.getString("email"));
        d.setUsername(rs.getString("username"));
        d.setPassword(rs.getString("password"));
        d.setRole(rs.getString("role"));
        d.setFullName(rs.getString("full_name"));
        d.setDateOfBirth(rs.getDate("date_of_birth"));
        d.setGender(rs.getString("gender"));
        d.setAddress(rs.getString("address"));
        d.setPhoneNumber(rs.getString("phone_number"));
        d.setDoctorLevelId(rs.getInt("doctor_level_id"));
        d.setSpecializationId(rs.getInt("specialization_id"));
        return d;
    }

    public void updateDoctor(Doctor doctor) {
        String query = "UPDATE doctor SET full_name = ?, date_of_birth = ?, gender = ?, address = ?, phone_number = ?, image_url = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, doctor.getFullName());
            ps.setDate(2, new java.sql.Date(doctor.getDateOfBirth().getTime()));
            ps.setString(3, doctor.getGender());
            ps.setString(4, doctor.getAddress());
            ps.setString(5, doctor.getPhoneNumber());
            ps.setString(6, doctor.getImageUrl());
            ps.setInt(7, doctor.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //DoctorShiftSlotDAO
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
                slot.setSlotId(rs.getInt("id"));
                slot.setDoctorId(rs.getInt("doctor_id"));
                slot.setStart(rs.getTime("slot_start_time"));
                slot.setEnd(rs.getTime("slot_end_time"));
                slot.setSlotDate(rs.getDate("date"));
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
                slot.setSlotId(rs.getInt("id"));
                slot.setDoctorId(rs.getInt("doctor_id"));
                slot.setStart(rs.getTime("slot_start_time"));
                slot.setEnd(rs.getTime("slot_end_time"));
                slot.setSlotDate(rs.getDate("date"));
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
                slot.setSlotId(rs.getInt("id"));
                slot.setDoctorId(rs.getInt("doctor_id"));
                slot.setStart(rs.getTime("slot_start_time"));
                slot.setEnd(rs.getTime("slot_end_time"));
                slot.setSlotDate(rs.getDate("date"));
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
                slot.setSlotId(rs.getInt("id"));
                slot.setSlotId(rs.getInt("doctor_id"));
                slot.setStart(rs.getTime("slot_start_time"));
                slot.setEnd(rs.getTime("slot_end_time"));
                slot.setSlotDate(rs.getDate("date"));
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
                slot.setSlotId(rs.getInt("id"));
                slot.setDoctorId(rs.getInt("doctor_id"));
                slot.setStart(rs.getTime("slot_start_time"));
                slot.setEnd(rs.getTime("slot_end_time"));
                slot.setSlotDate(rs.getDate("date"));
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
                slot.setSlotId(rs.getInt("id"));
                slot.setDoctorId(rs.getInt("doctor_id"));
                slot.setStart(rs.getTime("slot_start_time"));
                slot.setEnd(rs.getTime("slot_end_time"));
                slot.setSlotDate(rs.getDate("date"));
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
                slot.setSlotId(rs.getInt("id"));
                slot.setDoctorId(rs.getInt("doctor_id"));
                slot.setStart(rs.getTime("slot_start_time"));
                slot.setEnd(rs.getTime("slot_end_time"));
                slot.setSlotDate(rs.getDate("date"));
                slot.setBooked(rs.getBoolean("is_booked"));
                list.add(slot);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    //RoomDAO
        public List<Room> getAllRooms() {
        List<Room> list = new ArrayList<>();
        xSql = "SELECT * FROM room";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Room r = new Room();
                r.setId(rs.getInt("id"));
                r.setName(rs.getString("name"));
                r.setDoctorId(rs.getInt("doctor_id"));
                list.add(r);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public Room getRoomById(int id) {
        Room r = null;
        xSql = "SELECT * FROM room WHERE id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                r = new Room();
                r.setId(rs.getInt("id"));
                r.setName(rs.getString("name"));
                r.setDoctorId(rs.getInt("doctor_id"));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return r;
    }

    public void addRoom(String name, int doctorId) {
        xSql = "INSERT INTO room(name, doctor_id) VALUES (?, ?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, name);
            ps.setInt(2, doctorId);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void updateRoom(int id, String name) {
        xSql = "UPDATE room SET name = ? WHERE id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, name);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public boolean isDoctorAssigned(int doctorId) {
        xSql = "SELECT COUNT(*) FROM room WHERE doctor_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, doctorId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void deleteRoom(int id) {
        xSql = "DELETE FROM room WHERE id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public List<Specialization> getAllSpecializations() {
        List<Specialization> list = new ArrayList<>();
        xSql = "SELECT id, name FROM specialization";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Specialization s = new Specialization();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                list.add(s);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public void assignDoctorToRoom(int roomId, int doctorId) {
        xSql = "UPDATE room SET doctor_id = ? WHERE id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, doctorId);
            ps.setInt(2, roomId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
      public Staff getManagerById(int id) {
        Staff s = null;
        String sql = "SELECT * FROM staff WHERE id = ? AND role = 'Manager' AND status = 1";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                s = extractStaff(rs);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return s;
    }

    public List<Staff> getManagers() {
        List<Staff> list = new ArrayList<>();
        String sql = "SELECT * FROM staff WHERE role = 'Manager' AND status = 1";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(extractStaff(rs));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public void updateManager(Staff s) {
        String sql = "UPDATE staff SET image_url=?, email=?, username=?, full_name=?, date_of_birth=?, gender=?, address=?, phone_number=? WHERE id=? AND role='Manager'";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, s.getImageUrl());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getUsername());
            ps.setString(4, s.getFullName());
            ps.setDate(5, s.getDateOfBirth());
            ps.setString(6, s.getGender());
            ps.setString(7, s.getAddress());
            ps.setString(8, s.getPhoneNumber());
            ps.setInt(9, s.getId());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void updateManagerPassword(int id, String newPassword) {
        String sql = "UPDATE staff SET password=? WHERE id=? AND role='Manager'";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, newPassword);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    //StaffDAO
    public List<Staff> getReceptionists() {
        List<Staff> receptionists = new ArrayList<>();
        String query = "SELECT * FROM staff WHERE role = 'Receptionist'";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                receptionists.add(extractStaff(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receptionists;
    }

    public Staff getReceptionistById(int id) {
        Staff s = null;
        String sql = "SELECT * FROM staff WHERE id = ? AND role = 'Receptionist' AND status = 1";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                s = extractStaff(rs);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return s;
    }

    public Staff getStaffById(int id) {
        String query = "SELECT * FROM staff WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return extractStaff(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateStaff(Staff staff) {
        String query = "UPDATE staff SET full_name = ?, date_of_birth = ?, gender = ?, address = ?, phone_number = ?, image_url = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, staff.getFullName());
            ps.setDate(2, new java.sql.Date(staff.getDateOfBirth().getTime()));
            ps.setString(3, staff.getGender());
            ps.setString(4, staff.getAddress());
            ps.setString(5, staff.getPhoneNumber());
            ps.setString(6, staff.getImageUrl());
            ps.setInt(7, staff.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Staff extractStaff(ResultSet rs) throws SQLException {
        Staff staff = new Staff();
        staff.setId(rs.getInt("id"));
        staff.setFullName(rs.getString("full_name"));
        staff.setDateOfBirth(rs.getDate("date_of_birth"));
        staff.setGender(rs.getString("gender"));
        staff.setAddress(rs.getString("address"));
        staff.setPhoneNumber(rs.getString("phone_number"));
        staff.setImageUrl(rs.getString("image_url"));
        staff.setRole(rs.getString("role"));
        return staff;
    }
     public static String hashSHA256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
