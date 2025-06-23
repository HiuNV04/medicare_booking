/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Patient;

/**
 *
 * @author ADMIN
 */
public class PatientDAO extends MyDAO {

    public List<Patient> getAllPatient() {
        List<Patient> list = new ArrayList<>();
        xSql = "select * from patient";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Patient p = new Patient();
                p.setId(rs.getInt("id"));
                p.setImageUrl(rs.getString("image_url"));
                p.setEmail(rs.getString("email"));
                p.setUsername(rs.getString("username"));
                p.setPassword(rs.getString("password"));
                p.setRole(rs.getString("role"));
                p.setFullName(rs.getString("full_name"));
                p.setDateOfBirth(rs.getDate("date_of_birth"));
                p.setGender(rs.getString("gender"));
                p.setAddress(rs.getString("address"));
                p.setPhoneNumber(rs.getString("phone_number"));
                p.setIdentityNumber(rs.getString("identity_number"));
                p.setInsuranceNumber(rs.getString("insurance_number"));
                p.setStatus(rs.getBoolean("status"));
                list.add(p);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Patient> getPatientsByPage(int page, int pageSize, String search, String role, String status) {
        List<Patient> list = new ArrayList<>();
        xSql = "SELECT * FROM patient WHERE 1=1";
        if (search != null && !search.isEmpty()) {
            xSql += " AND (email LIKE ? OR username LIKE ?)";
        }
        if (role != null && !role.isEmpty()) {
            xSql += " AND role = ?";
        }
        if (status != null && !status.isEmpty()) {
            xSql += " AND status = ?";
        }
        xSql += " ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            int idx = 1;
            ps = con.prepareStatement(xSql);
            if (search != null && !search.isEmpty()) {
                ps.setString(idx++, "%" + search + "%");
                ps.setString(idx++, "%" + search + "%");
            }
            if (role != null && !role.isEmpty()) {
                ps.setString(idx++, role);
            }
            if (status != null && !status.isEmpty()) {
                ps.setString(idx++, status);
            }
            ps.setInt(idx++, (page - 1) * pageSize);
            ps.setInt(idx++, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                Patient p = new Patient();
                p.setId(rs.getInt("id"));
                p.setImageUrl(rs.getString("image_url"));
                p.setEmail(rs.getString("email"));
                p.setUsername(rs.getString("username"));
                p.setPassword(rs.getString("password"));
                p.setRole(rs.getString("role"));
                p.setFullName(rs.getString("full_name"));
                p.setDateOfBirth(rs.getDate("date_of_birth"));
                p.setGender(rs.getString("gender"));
                p.setAddress(rs.getString("address"));
                p.setPhoneNumber(rs.getString("phone_number"));
                p.setIdentityNumber(rs.getString("identity_number"));
                p.setInsuranceNumber(rs.getString("insurance_number"));
                p.setStatus(rs.getBoolean("status"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int countPatient(String search, String role, String status) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM patient WHERE 1=1";
        if (search != null && !search.isEmpty()) {
            sql += " AND (email LIKE ? OR username LIKE ?)";
        }
        if (role != null && !role.isEmpty()) {
            sql += " AND role = ?";
        }
        if (status != null && !status.isEmpty()) {
            sql += " AND status = ?";
        }
        try {
            int idx = 1;
            ps = con.prepareStatement(sql);
            if (search != null && !search.isEmpty()) {
                ps.setString(idx++, "%" + search + "%");
                ps.setString(idx++, "%" + search + "%");
            }
            if (role != null && !role.isEmpty()) {
                ps.setString(idx++, role);
            }
            if (status != null && !status.isEmpty()) {
                ps.setString(idx++, status);
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public Patient viewPatientDetail(int id) {
        xSql = "select * from patient where id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Patient p = new Patient();
                p.setId(rs.getInt("id"));
                p.setImageUrl(rs.getString("image_url"));
                p.setEmail(rs.getString("email"));
                p.setUsername(rs.getString("username"));
                p.setPassword(rs.getString("password"));
                p.setRole(rs.getString("role"));
                p.setFullName(rs.getString("full_name"));
                p.setDateOfBirth(rs.getDate("date_of_birth"));
                p.setGender(rs.getString("gender"));
                p.setAddress(rs.getString("address"));
                p.setPhoneNumber(rs.getString("phone_number"));
                p.setIdentityNumber(rs.getString("identity_number"));
                p.setInsuranceNumber(rs.getString("insurance_number"));
                p.setStatus(rs.getBoolean("status"));
                return p;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void changePatientStatus(int id, int status) {
        xSql = "UPDATE patient SET status=? WHERE id=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setBoolean(1, status == 1);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        PatientDAO dao  =new PatientDAO();
        Patient p = dao.viewPatientDetail(1);
        System.out.println(p);
    }
}
