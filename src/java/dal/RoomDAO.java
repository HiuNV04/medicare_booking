package dal;
import model.Room;
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
} 