package dal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Doctor;

public class RoomDoctorDAO extends MyDAO {
    public List<Doctor> getDoctorsByRoomId(int roomId) {
        List<Doctor> list = new ArrayList<>();
        xSql = "SELECT d.* FROM room_doctor rd JOIN doctor d ON rd.doctor_id = d.id WHERE rd.room_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, roomId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Doctor d = new Doctor();
                d.setId(rs.getInt("id"));
                d.setFullName(rs.getString("full_name"));
                d.setSpecializationId(rs.getInt("specialization_id"));
                d.setImageUrl(rs.getString("image_url"));
                d.setDateOfBirth(rs.getDate("date_of_birth"));
                d.setGender(rs.getString("gender"));
                d.setAddress(rs.getString("address"));
                d.setPhoneNumber(rs.getString("phone_number"));
                d.setStatus(rs.getBoolean("status"));
                list.add(d);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public int countDoctorsInRoom(int roomId) {
        xSql = "SELECT COUNT(*) FROM room_doctor WHERE room_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, roomId);
            rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    public void addDoctorToRoom(int roomId, int doctorId) {
        xSql = "INSERT INTO room_doctor(room_id, doctor_id) VALUES (?, ?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, roomId);
            ps.setInt(2, doctorId);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void removeDoctorFromRoom(int roomId, int doctorId) {
        xSql = "DELETE FROM room_doctor WHERE room_id = ? AND doctor_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, roomId);
            ps.setInt(2, doctorId);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
} 