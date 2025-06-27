/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import model.Patient;

/**
 *
 * @author ADMIN
 */
public class PatientDAO extends MyDAO{
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
                    s.setDateOfBirth(dob.toLocalDate());
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
}
