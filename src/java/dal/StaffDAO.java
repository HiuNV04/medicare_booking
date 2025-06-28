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
} 