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
            System.out.println("Check login tài khoản: " + username);

            // 1. Doctor
            xSql = "SELECT * FROM doctor WHERE username = ? AND password = ? AND status = 1";
            ps = con.prepareStatement(xSql);
            ps.setString(1, username);
            ps.setString(2, encryptedPassword);
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Đăng nhập thành công: bác sĩ");
                Doctor d = new Doctor();
                d.setId(rs.getInt("id"));
                d.setUsername(username);
                d.setRole(rs.getString("role").trim().toLowerCase());
                return d;
            }

            // 2. Patient
            xSql = "SELECT * FROM patient WHERE username = ? AND password = ? AND status = 1";
            ps = con.prepareStatement(xSql);
            ps.setString(1, username);
            ps.setString(2, encryptedPassword);
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Đăng nhập thành công: bệnh nhân");
                Patient p = new Patient();
                p.setId(rs.getInt("id"));
                p.setUsername(username);
                p.setRole(rs.getString("role").trim().toLowerCase());
                return p;
            }

            // 3. Staff
            xSql = "SELECT * FROM staff WHERE username = ? AND password = ? AND status = 1";
            ps = con.prepareStatement(xSql);
            ps.setString(1, username);
            ps.setString(2, encryptedPassword);
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Đăng nhập thành công: staff " + rs.getString("role"));
                Staff s = new Staff();
                s.setId(rs.getInt("id"));
                s.setUsername(rs.getString("username"));
                s.setRole(rs.getString("role").trim().toLowerCase());
                return s;
            }

            System.out.println("Không tìm thấy tài khoản hợp lệ.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return null;
    }
    public static void main(String[] args) {
        AuthDAO dao = new AuthDAO();
        Object a = dao.login("doc1", "abc");
    }

    public boolean isUsernameOrEmailTaken(String username, String email) {
        String[] tables = {"doctor", "staff", "patient"};
        try {
            for (String table : tables) {
                xSql = "SELECT 1 FROM " + table + " WHERE username = ? OR email = ?";
                ps = con.prepareStatement(xSql);
                ps.setString(1, username);
                ps.setString(2, email);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(); // ✅ đóng ps, rs sau mỗi vòng lặp
        }
        return false;
    }

    public boolean insertAccountPatient(Patient p) {
        try {
            xSql = "INSERT INTO patient (image_url, email, username, password, role, full_name, date_of_birth, gender, address, phone_number, identity_number, insurance_number, status) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(xSql);
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
        } finally {
            closeResources();
        }
        return false;
    }

    public boolean insertAccountDoctor(Doctor d) {
        try {
            xSql = "INSERT INTO doctor (image_url, email, username, password, role, full_name, date_of_birth, gender, address, phone_number, doctor_level_id, specialization_id, status) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(xSql);
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
        } finally {
            closeResources();
        }
        return false;
    }

    public boolean insertAccountStaff(Staff s) {
        try {
            xSql = "INSERT INTO staff (image_url, email, username, password, role, full_name, date_of_birth, gender, address, phone_number, status) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(xSql);
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
        } finally {
            closeResources();
        }
        return false;
    }

    // kiếm tra email có tồn tại không
    public boolean checkEmailExists(String email, String role) {
        String table = role.equals("doctor") ? "doctor" : role.equals("patient") ? "patient" : "staff";
        try {
            xSql = "SELECT 1 FROM " + table + " WHERE email = ?";
            ps = con.prepareStatement(xSql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return false;
    }

    // mã hóa mật khẩu
    public boolean updatePasswordByUsername(String username, String newEncryptedPassword) {
        String[] tables = {"doctor", "patient", "staff"};
        try {
            for (String table : tables) {
                xSql = "UPDATE " + table + " SET password = ? WHERE username = ?";
                ps = con.prepareStatement(xSql);
                ps.setString(1, newEncryptedPassword);
                ps.setString(2, username);
                if (ps.executeUpdate() > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return false;
    }

}
