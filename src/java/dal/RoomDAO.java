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
                r.setSpecializationId(rs.getInt("specialization_id"));
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