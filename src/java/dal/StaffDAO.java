/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import model.Staff;
import dal.DBContext;

import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class StaffDAO extends MyDAO {
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
