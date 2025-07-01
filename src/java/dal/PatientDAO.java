/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
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

    public List<Patient> getPatientsByFilter(String search, String role, Boolean status, int page, int pageSize) {
        List<Patient> list = new ArrayList<>();
        int offset = (page - 1) * pageSize;
        StringBuilder sql = new StringBuilder(
                "SELECT * FROM patient WHERE 1=1"
        );
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
                Patient s = new Patient();
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
    public int countPatients(String search, String role, Boolean status) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM patient WHERE 1=1");
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

    public Patient getPatientById(int id) {
        Patient s = null;
        String sql = "SELECT * FROM patient WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                s = new Patient();
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
                s.setInsuranceNumber(rs.getString("insurance_number"));
                s.setIdentityNumber(rs.getString("identity_number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public boolean updatePatientStatus(int id, boolean status) {
        String sql = "UPDATE patient SET status=? WHERE id=?";
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
