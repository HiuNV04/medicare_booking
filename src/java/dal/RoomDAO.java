package dal;
import model.Room;
import model.Doctor;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import model.Specialization;

public class RoomDAO extends MyDAO {
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
                r.setSpecializationId(rs.getInt("specialization_id"));
                list.add(r);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public List<Doctor> getDoctorsByRoomId(int roomId) {
        List<Doctor> list = new ArrayList<>();
        xSql = "SELECT * FROM doctor WHERE room_id = ?";
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

    public void addDoctorToRoom(int doctorId, int roomId) {
        xSql = "UPDATE doctor SET room_id = ? WHERE id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, roomId);
            ps.setInt(2, doctorId);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void removeDoctorFromRoom(int doctorId) {
        xSql = "UPDATE doctor SET room_id = NULL WHERE id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, doctorId);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
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
                r.setSpecializationId(rs.getInt("specialization_id"));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return r;
    }

    public void addRoom(int specializationId, String name) {
        xSql = "INSERT INTO room(specialization_id, name) VALUES (?, ?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, specializationId);
            ps.setString(2, name);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void updateRoom(int id, int specializationId, String name) {
        xSql = "UPDATE room SET specialization_id = ?, name = ? WHERE id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, specializationId);
            ps.setString(2, name);
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
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
} 