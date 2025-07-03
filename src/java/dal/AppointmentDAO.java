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

    // lấy toàn bộ danh sách lịch hẹn khám
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

    // lấy toàn bộ danh sách lịch hẹn khám để phân trang
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

    // lấy chi tiết lịch hẹn theo id bệnh nhân 
    public Appointment getAppointmentDetailById(int id) {
        Appointment detail = null;

        xSql = "SELECT "
                + "a.id AS appointment_id, a.note, a.confirmation_status, "
                + "p.id AS patient_id, p.full_name AS patient_name, p.date_of_birth, p.gender, "
                + "d.id AS doctor_id, d.full_name AS doctor_name, "
                + "sp.name AS specialization, "
                + "dl.name AS doctor_level, "
                + "s.date, s.slot_start_time, s.slot_end_time, "
                + "r.id AS room_id, r.name AS room_name "
                + "FROM appointment_schedule a "
                + "JOIN patient p ON a.patient_id = p.id "
                + "JOIN doctor_shift_slot s ON a.doctor_shift_id = s.id "
                + "JOIN doctor d ON s.doctor_id = d.id "
                + "JOIN doctor_level dl ON d.doctor_level_id = dl.id "
                + "JOIN specialization sp ON d.specialization_id = sp.id "
                + "JOIN room r ON a.room_id = r.id "
                + "WHERE a.id = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                detail = new Appointment();
                detail.setId(rs.getInt("appointment_id"));
                detail.setNote(rs.getString("note"));
                detail.setConfirmationStatus(rs.getString("confirmation_status"));

                detail.setPatientId(rs.getInt("patient_id"));
                detail.setPatientName(rs.getString("patient_name"));
                detail.setPatientDob(rs.getDate("date_of_birth"));
                detail.setPatientGender(rs.getString("gender"));

                detail.setDoctorId(rs.getInt("doctor_id"));
                detail.setDoctorName(rs.getString("doctor_name"));
                detail.setSpecialization(rs.getString("specialization"));
                detail.setDoctorLevel(rs.getString("doctor_level"));

                detail.setDate(rs.getDate("date"));
                detail.setSlotStartTime(rs.getTime("slot_start_time"));
                detail.setSlotEndTime(rs.getTime("slot_end_time"));

                detail.setRoomId(rs.getInt("room_id"));
                detail.setRoomName(rs.getString("room_name"));
            }

        } catch (Exception e) {
            System.out.println("Lỗi khi lấy chi tiết lịch hẹn: " + e.getMessage());
        }

        return detail;
    }

    // xác nhận lịch hẹn cho bệnh nhân bởi receptionist
    public void confirmStatus(int appointmentId, String status) {
        xSql = "UPDATE appointment_schedule SET confirmation_status = ? WHERE id = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, status);
            ps.setInt(2, appointmentId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("❌ Lỗi updateConfirmationStatus: " + e.getMessage());
        }
    }

    // xác nhận lịch hẹn đã được đặt tránh trùng
    public void updateSlotBooked(int appointmentId, boolean isBooked) {
        xSql = "UPDATE doctor_shift_slot "
                + "SET is_booked = 1 "
                + "WHERE id = (SELECT doctor_shift_id FROM appointment_schedule WHERE id = ?)";

        try {
            ps = con.prepareStatement(xSql);
            ps.setBoolean(1, isBooked);
            ps.setInt(2, appointmentId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Lỗi markSlotAsBookedByAppointment: " + e.getMessage());
        }
    }

    public boolean doctorViewPatientProfile(int doctorId, int patientId) {
        xSql = "SELECT 1 FROM appointment_schedule a "
                + "JOIN doctor_shift_slot s ON a.doctor_shift_id = s.id "
                + "WHERE s.doctor_id = ? AND a.patient_id = ? AND a.confirmation_status = 'Approved'";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, doctorId);
            ps.setInt(2, patientId);
            rs = ps.executeQuery();
            return rs.next();  // Nếu có dòng nào → bác sĩ có quyền xem
        } catch (Exception e) {
            System.out.println("Lỗi canDoctorViewPatientProfile: " + e.getMessage());
        }
        return false;
    }

    public static void main(String[] args) {
        AppointmentDAO dao = new AppointmentDAO();
        int testId = 1; // ID của lịch hẹn muốn test, bạn sửa ID cho đúng với DB

        Appointment detail = dao.getAppointmentDetailById(testId);

        if (detail != null) {
            System.out.println("📋 Chi tiết lịch hẹn:");
            System.out.println("ID: " + detail.getId());
            System.out.println("Bệnh nhân: " + detail.getPatientName() + " - " + detail.getPatientGender() + " - " + detail.getPatientDob());
            System.out.println("Bác sĩ: " + detail.getDoctorName() + " (" + detail.getSpecialization() + " - " + detail.getDoctorLevel() + ")");
            System.out.println("Thời gian: " + detail.getDate() + " từ " + detail.getSlotStartTime() + " đến " + detail.getSlotEndTime());
            System.out.println("Phòng: " + detail.getRoomName());
            System.out.println("Trạng thái: " + detail.getConfirmationStatus());
            System.out.println("Ghi chú: " + detail.getNote());
        } else {
            System.out.println("❌ Không tìm thấy lịch hẹn với ID: " + testId);
        }
    }

}
