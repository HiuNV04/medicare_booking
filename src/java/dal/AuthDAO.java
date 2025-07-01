/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Doctor;
import model.Patient;
import model.Staff;

/**
 *
 * @author Admin
 */
public class AuthDAO extends MyDAO {

    public Object login(String username, String encryptedPassword) {
        try {
            System.out.println("Check loggin tài khoản: " + username);

            // 1. Doctor login
            String sqlDoctor = "SELECT * FROM doctor WHERE username = ? AND password = ? AND status = 1";
            try (Connection conn = connection; PreparedStatement psDoctor = conn.prepareStatement(sqlDoctor)) {
                psDoctor.setString(1, username);
                psDoctor.setString(2, encryptedPassword);
                try (ResultSet rs = psDoctor.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("Đăng nhập thành công dưới vai trò bác sĩ");
                        Doctor d = new Doctor();
                        d.setId(rs.getInt("id"));
                        d.setUsername(username);
                        d.setRole(rs.getString("role").trim().toLowerCase());
                        return d;
                    }
                }
            }

            // 2. Patient login
            String sqlPatient = "SELECT * FROM patient WHERE username = ? AND password = ? AND status = 1";
            try (Connection conn = connection; PreparedStatement psPatient = conn.prepareStatement(sqlPatient)) {
                psPatient.setString(1, username);
                psPatient.setString(2, encryptedPassword);
                try (ResultSet rs = psPatient.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("Đăng nhập thành công dưới vai trò bệnh nhân");
                        Patient p = new Patient();
                        p.setId(rs.getInt("id"));
                        p.setUsername(username);
                        p.setRole(rs.getString("role").trim().toLowerCase());
                        return p;
                    }
                }
            }

            // 3. Staff login
            String sqlStaff = "SELECT * FROM staff WHERE username = ? AND password = ? AND status = 1";
            try (Connection conn = connection; PreparedStatement psStaff = conn.prepareStatement(sqlStaff)) {
                psStaff.setString(1, username);
                psStaff.setString(2, encryptedPassword);
                try (ResultSet rs = psStaff.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("Đăng nhập thành công dưới vai trò staff: " + rs.getString("role"));
                        Staff s = new Staff();
                        s.setId(rs.getInt("id"));
                        s.setUsername(rs.getString("username"));
                        s.setRole(rs.getString("role").trim().toLowerCase());
                        return s;
                    }
                }
            }

            System.out.println("Không tìm thấy tài khoản hợp lệ");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // hàm check trùng username và email khi đăng kí tài khoản
    public boolean isUsernameOrEmailTaken(String username, String email) {
        String[] tables = {"doctor", "staff", "patient"};
        try (Connection conn = connection) {
            for (String table : tables) {
                String sql = "SELECT 1 FROM " + table + " WHERE username = ? OR email = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, username);
                    ps.setString(2, email);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertPatient(Patient p) {
        String sql = "INSERT INTO patient (image_url, email, username, password, role, full_name, date_of_birth, gender, address, phone_number, identity_number, insurance_number, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = connection; PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getImageUrl() == null ? "" : p.getImageUrl());
            ps.setString(2, p.getEmail());
            ps.setString(3, p.getUsername());
            ps.setString(4, p.getPassword());
            ps.setString(5, p.getRole());
            ps.setString(6, p.getFullName());
            ps.setDate(7, (Date) p.getDateOfBirth());
            ps.setString(8, p.getGender());
            ps.setString(9, p.getAddress());
            ps.setString(10, p.getPhoneNumber());
            ps.setString(11, p.getIdentityNumber());
            ps.setString(12, p.getInsuranceNumber());
            ps.setBoolean(13, p.isStatus());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertDoctor(Doctor d) {
        String sql = "INSERT INTO doctor (image_url, email, username, password, role, full_name, date_of_birth, gender, address, phone_number, doctor_level_id, specialization_id, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = connection; PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, d.getImageUrl());
            ps.setString(2, d.getEmail());
            ps.setString(3, d.getUsername());
            ps.setString(4, d.getPassword());
            ps.setString(5, d.getRole());
            ps.setString(6, d.getFullName());
            ps.setDate(7, new java.sql.Date(d.getDateOfBirth().getTime()));
            ps.setString(8, d.getGender());
            ps.setString(9, d.getAddress());
            ps.setString(10, d.getPhoneNumber());
            ps.setInt(11, d.getDoctorLevelId());
            ps.setInt(12, d.getSpecializationId());
            ps.setBoolean(13, d.isStatus());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertStaff(Staff s) {
        String sql = "INSERT INTO staff (image_url, email, username, password, role, full_name, date_of_birth, gender, address, phone_number, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = connection; PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getImageUrl());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getUsername());
            ps.setString(4, s.getPassword());
            ps.setString(5, s.getRole());
            ps.setString(6, s.getFullName());
            ps.setDate(7, s.getDateOfBirth());
            ps.setString(8, s.getGender());
            ps.setString(9, s.getAddress());
            ps.setString(10, s.getPhoneNumber());
            ps.setBoolean(11, s.isStatus());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // kiếm tra email có tồn tại không
    public boolean checkEmailExists(String email, String role) {
        String table = role.equals("doctor") ? "doctor" : role.equals("patient") ? "patient" : "staff";
        String sql = "SELECT 1 FROM " + table + " WHERE email = ?";
        try (Connection conn = connection; PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // mã hóa mật khẩu
    public boolean updatePasswordByUsername(String username, String newEncryptedPassword) {
        String[] tables = {"doctor", "patient", "staff"};
        for (String table : tables) {
            String sql = "UPDATE " + table + " SET password = ? WHERE username = ?";
            try (Connection conn = connection; PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, newEncryptedPassword);
                ps.setString(2, username);
                if (ps.executeUpdate() > 0) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
