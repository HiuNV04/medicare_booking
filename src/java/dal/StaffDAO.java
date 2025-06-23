package dal;

import model.Staff;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDAO extends MyDAO {
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

    public List<Staff> getReceptionists() {
        List<Staff> list = new ArrayList<>();
        String sql = "SELECT * FROM staff WHERE role = 'Receptionist' AND status = 1";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(extractStaff(rs));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
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

    private Staff extractStaff(ResultSet rs) throws SQLException {
        Staff s = new Staff();
        s.setId(rs.getInt("id"));
        s.setImageUrl(rs.getString("image_url"));
        s.setEmail(rs.getString("email"));
        s.setUsername(rs.getString("username"));
        s.setPassword(rs.getString("password"));
        s.setRole(rs.getString("role"));
        s.setFullName(rs.getString("full_name"));
        s.setDateOfBirth(rs.getDate("date_of_birth"));
        s.setGender(rs.getString("gender"));
        s.setAddress(rs.getString("address"));
        s.setPhoneNumber(rs.getString("phone_number"));
        s.setStatus(rs.getInt("status"));
        return s;
    }
} 