/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Staff;
import dal.DBContext;

import java.sql.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Staff;

/**
 *
 * @author ADMIN
 */
public class StaffDAO extends MyDAO {
    // Lấy danh sách nhân viên với filter, phân trang

    public List<Staff> getStaffsByFilter(String search, String role, Boolean status, int page, int pageSize) {
        List<Staff> list = new ArrayList<>();
        int offset = (page - 1) * pageSize;
        StringBuilder sql = new StringBuilder(
                "SELECT * FROM staff WHERE 1=1 and role != 'Admin'");
        if (search != null && !search.trim().isEmpty()) {
            sql.append("AND (email LIKE ? OR username LIKE ?) ");
        }
        if (role != null && !role.isEmpty()) {
            sql.append("AND role = ? ");
        }
        if (status != null) {
            sql.append("AND status = ? ");
        }
        sql.append("ORDER BY id DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

        try {
            ps = con.prepareStatement(sql.toString());
            int idx = 1;
            if (search != null && !search.trim().isEmpty()) {
                ps.setString(idx++, "%" + search + "%");
                ps.setString(idx++, "%" + search + "%");
            }
            if (role != null && !role.isEmpty()) {
                ps.setString(idx++, role);
            }
            if (status != null) {
                ps.setBoolean(idx++, status);
            }
            ps.setInt(idx++, offset);
            ps.setInt(idx++, pageSize);

            rs = ps.executeQuery();
            while (rs.next()) {
                Staff s = new Staff();
                s.setId(rs.getInt("id"));
                s.setEmail(rs.getString("email"));
                s.setUsername(rs.getString("username"));
                s.setRole(rs.getString("role"));
                s.setStatus(rs.getBoolean("status"));
                // Nạp thêm các field khác nếu muốn
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Đếm số lượng staff với filter (phục vụ phân trang)
    public int countStaffs(String search, String role, Boolean status) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM staff WHERE 1=1 and role != 'Admin' ");
        if (search != null && !search.trim().isEmpty()) {
            sql.append("AND (email LIKE ? OR username LIKE ?) ");
        }
        if (role != null && !role.isEmpty()) {
            sql.append("AND role = ? ");
        }
        if (status != null) {
            sql.append("AND status = ? ");
        }

        try {
            ps = con.prepareStatement(sql.toString());
            int idx = 1;
            if (search != null && !search.trim().isEmpty()) {
                ps.setString(idx++, "%" + search + "%");
                ps.setString(idx++, "%" + search + "%");
            }
            if (role != null && !role.isEmpty()) {
                ps.setString(idx++, role);
            }
            if (status != null) {
                ps.setBoolean(idx++, status);
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

    public Staff getStaffById(int id) {
        Staff s = null;
        String sql = "SELECT * FROM staff WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                s = new Staff();
                s.setId(rs.getInt("id"));
                s.setImageUrl(rs.getString("image_url"));
                s.setEmail(rs.getString("email"));
                s.setUsername(rs.getString("username"));
                s.setPassword(rs.getString("password"));
                s.setRole(rs.getString("role"));
                s.setFullName(rs.getString("full_name"));
                java.sql.Date dob = rs.getDate("date_of_birth");
                if (dob != null) {
                    s.setDateOfBirth(dob);
                }
                s.setGender(rs.getString("gender"));
                s.setAddress(rs.getString("address"));
                s.setPhoneNumber(rs.getString("phone_number"));
                s.setStatus(rs.getBoolean("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public boolean updateStaff(Staff s) {
        String sql = "UPDATE staff SET image_url=?, email=?, username=?, password=?, full_name=?, date_of_birth=?, gender=?, address=?, phone_number=? WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, s.getImageUrl());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getUsername());
            ps.setString(4, s.getPassword());
            ps.setString(5, s.getFullName());
            ps.setDate(6, new java.sql.Date(s.getDateOfBirth().getTime()));
            ps.setString(7, s.getGender());
            ps.setString(8, s.getAddress());
            ps.setString(9, s.getPhoneNumber());
            ps.setInt(10, s.getId());
            int count = ps.executeUpdate();
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateStaffStatus(int id, boolean status) {
        String sql = "UPDATE staff SET status=? WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setBoolean(1, status);
            ps.setInt(2, id);
            int count = ps.executeUpdate();
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addStaff(Staff s) {
        String sql = "INSERT INTO staff (image_url, email, username, password, role, full_name, date_of_birth, gender, address, phone_number, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, s.getImageUrl());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getUsername());
            ps.setString(4, s.getPassword());
            ps.setString(5, s.getRole());
            ps.setString(6, s.getFullName());
            ps.setDate(7, new java.sql.Date(s.getDateOfBirth().getTime()));
            ps.setString(8, s.getGender());
            ps.setString(9, s.getAddress());
            ps.setString(10, s.getPhoneNumber());
            int n = ps.executeUpdate();
            return n > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkEmailExists(String email) {
        String sql = "SELECT 1 FROM staff WHERE email=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean checkUsernameExists(String username) {
        String sql = "SELECT 1 FROM staff WHERE username=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            return false;
        }
    }

    // Đọc thông tin đăng nhập
    public Staff checkLogin(String email, String password) {
        Staff staff = null;
        String sql = "SELECT * FROM staff WHERE email = ? AND password = ? AND status = 1";

        try (Connection conn = connection; PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                staff = new Staff();
                staff.setId(rs.getInt("id"));
                staff.setImageUrl(rs.getString("image_url"));
                staff.setEmail(rs.getString("email"));
                staff.setUsername(rs.getString("username"));
                staff.setPassword(rs.getString("password"));
                staff.setRole(rs.getString("role"));
                staff.setFullName(rs.getString("full_name"));
                staff.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                staff.setGender(rs.getString("gender"));
                staff.setAddress(rs.getString("address"));
                staff.setPhoneNumber(rs.getString("phone_number"));
                staff.setStatus(rs.getBoolean("status"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return staff;
    }

    // Chèn tài khoản mới (admin tạo)
    public void insert(Staff staff) {
        String sql = "INSERT INTO staff (full_name, email, username, password, role, status) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = connection; PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, staff.getFullName());
            ps.setString(2, staff.getEmail());
            ps.setString(3, staff.getUsername());
            ps.setString(4, staff.getPassword());
            String role = staff.getRole().toUpperCase().charAt(0) + staff.getRole().substring(1).toLowerCase();
            ps.setString(5, role);
            ps.setBoolean(6, staff.isStatus());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // check trùng tài khoản
    public boolean isUsernameTaken(String username) {
        String sql = "SELECT 1 FROM staff WHERE username = ?";
        try (Connection con = connection; PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // xác định email có tồn tại không rồi gửi mã
    public Staff findByEmail(String email) {
        String sql = "SELECT * FROM staff WHERE email = ?";
        try (Connection conn = connection; PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Staff s = new Staff();
                s.setUsername(rs.getString("username"));
                s.setEmail(rs.getString("email"));
                return s;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // cập nhật mật khẩu mới nhận từ mail để mã hóa rồi login
    public boolean updatePasswordByEmail(String email, String hashedPassword) {
        String sql = "UPDATE staff SET password = ? WHERE email = ?";
        try (Connection conn = connection; PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, hashedPassword);
            ps.setString(2, email);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
