/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Patient;

/**
 *
 * @author ADMIN
 */
public class PatientDAO extends MyDAO {

    public Patient checkLogin(String username, String password) {
        Patient patient = null;

        String sql = "SELECT * FROM patient WHERE username = ? AND password = ? AND status = 1";

        try (Connection conn = connection; PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);  // Đã mã hóa SHA-256 trước khi gọi

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                patient = new Patient();
                patient.setId(rs.getInt("id"));
                patient.setImageUrl(rs.getString("image_url"));
                patient.setEmail(rs.getString("email"));
                patient.setUsername(rs.getString("username"));
                patient.setPassword(rs.getString("password"));
                patient.setRole(rs.getString("role"));
                patient.setFullName(rs.getString("full_name"));
                patient.setDateOfBirth(rs.getDate("date_of_birth"));
                patient.setGender(rs.getString("gender"));
                patient.setAddress(rs.getString("address"));
                patient.setPhoneNumber(rs.getString("phone_number"));
                patient.setStatus(rs.getBoolean("status"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return patient;
    }

    public void insert(Patient patient) {
        String sql = "INSERT INTO patient (full_name, email, username, password, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = connection; PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, patient.getFullName());
            ps.setString(2, patient.getEmail());
            ps.setString(3, patient.getUsername());
            ps.setString(4, patient.getPassword());
            ps.setBoolean(5, patient.isStatus());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

// check trùng tài khoản khi đăng kí
    public boolean isUsernameTaken(String username) {
        String sql = "SELECT 1 FROM patient WHERE username = ?";
        try (Connection con = connection; PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            return rs.next(); // Nếu có dòng => đã tồn tại
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // xác định email có tồn tại không rồi gửi mã
    public Patient findByEmail(String email) {
        String sql = "SELECT * FROM patient WHERE email = ?";
        try (Connection conn = connection; PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Patient p = new Patient();
                p.setUsername(rs.getString("username"));
                p.setEmail(rs.getString("email"));
                return p;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // cập nhật mật khẩu mới nhận từ mail để mã hóa rồi login 
    public boolean updatePasswordByEmail(String email, String hashedPassword) {
        String sql = "UPDATE patient SET password = ? WHERE email = ?";
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
