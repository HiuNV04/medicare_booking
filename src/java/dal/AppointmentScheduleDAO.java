package dal;

import model.AppointmentSchedule;
import java.sql.*;

public class AppointmentScheduleDAO extends MyDAO {

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
} 