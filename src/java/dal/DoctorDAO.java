/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.*;

/**
 *
 * @author ADMIN
 */
public class DoctorDAO extends MyDAO {

    public List<Doctor> getAllDoctor() {
        List<Doctor> list = new ArrayList<>();
        xSql = "select * from Doctor";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Doctor d = new Doctor();
                d.setId(rs.getInt("id"));
                d.setImageUrl(rs.getString("image_url"));
                d.setEmail(rs.getString("email"));
                d.setUsername(rs.getString("username"));
                d.setPassword(rs.getString("password"));
                d.setRole(rs.getString("role"));
                d.setFullName(rs.getString("full_name"));
                d.setDateOfBirth(rs.getDate("date_of_birth"));
                d.setGender(rs.getString("gender"));
                d.setAddress(rs.getString("address"));
                d.setPhoneNumber(rs.getString("phone_number"));
                d.setDoctorLevelId(rs.getInt("doctor_level_id"));
                d.setSpecializationId(rs.getInt("specialization_id"));
                d.setStatus(rs.getBoolean("status"));
                list.add(d);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Doctor> getDoctorsByPage(int page, int pageSize, String search, String role, String status) {
        List<Doctor> list = new ArrayList<>();
        xSql = "SELECT * FROM doctor WHERE 1=1";
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
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("id"));
                doctor.setImageUrl(rs.getString("image_url"));
                doctor.setEmail(rs.getString("email"));
                doctor.setUsername(rs.getString("username"));
                doctor.setPassword(rs.getString("password"));
                doctor.setRole(rs.getString("role"));
                doctor.setFullName(rs.getString("full_name"));
                doctor.setDateOfBirth(rs.getDate("date_of_birth"));
                doctor.setGender(rs.getString("gender"));
                doctor.setAddress(rs.getString("address"));
                doctor.setPhoneNumber(rs.getString("phone_number"));
                doctor.setDoctorLevelId(rs.getInt("doctor_level_id"));
                doctor.setSpecializationId(rs.getInt("specialization_id"));
                doctor.setStatus(rs.getBoolean("status"));
                list.add(doctor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int countDoctor(String search, String role, String status) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM doctor WHERE 1=1";
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

    public Doctor viewDoctorDetail(int id) {
        xSql = "select * from doctor where id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Doctor d = new Doctor();
                d.setId(rs.getInt("id"));
                d.setImageUrl(rs.getString("image_url"));
                d.setEmail(rs.getString("email"));
                d.setUsername(rs.getString("username"));
                d.setPassword(rs.getString("password"));
                d.setRole(rs.getString("role"));
                d.setFullName(rs.getString("full_name"));
                d.setDateOfBirth(rs.getDate("date_of_birth"));
                d.setGender(rs.getString("gender"));
                d.setAddress(rs.getString("address"));
                d.setPhoneNumber(rs.getString("phone_number"));
                d.setDoctorLevelId(rs.getInt("doctor_level_id"));
                d.setSpecializationId(rs.getInt("specialization_id"));
                d.setStatus(rs.getBoolean("status"));
                return d;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateDoctor(int id, String email, String imageUrl, String username, String fullName,
            String password, LocalDate dateOfBirth, String gender, String address, String phoneNumber,
            int doctorLevelId, int specializationId) {
        xSql = "UPDATE doctor SET email=?, image_url=?, username=?, password=?, full_name=?, date_of_birth=?, gender=?, address=?, phone_number=?, doctor_level_id=?, specialization_id=? WHERE id=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, email);
            ps.setString(2, imageUrl);
            ps.setString(3, username);
            ps.setString(4, password);
            ps.setString(5, fullName);
            ps.setObject(6, dateOfBirth);
            ps.setString(7, gender);
            ps.setString(8, address);
            ps.setString(9, phoneNumber);
            ps.setInt(10, doctorLevelId);
            ps.setInt(11, specializationId);
            ps.setInt(12, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        }
    }

    public void changeDoctorStatus(int id, int status) {
        xSql = "UPDATE doctor SET status=? WHERE id=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setBoolean(1, status == 1);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkEmailExists(String email) {
        xSql = "SELECT 1 FROM doctor WHERE email = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkUsernameExists(String username) {
        xSql = "SELECT 1 FROM doctor WHERE username = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addDoctor(String imageUrl, String email, String username, String password,
            String role, String fullName, LocalDate dateOfBirth, String gender,
            String address, String phoneNumber, int doctorLevelId, int specializationId) {
        String sql = "INSERT INTO doctor (image_url, email, username, password, role, full_name, date_of_birth, gender, address, phone_number, doctor_level_id, specialization_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, imageUrl);
            ps.setString(2, email);
            ps.setString(3, username);
            ps.setString(4, password);
            ps.setString(5, role);
            ps.setString(6, fullName);
            ps.setDate(7, java.sql.Date.valueOf(dateOfBirth)); // KHÔNG check null
            ps.setString(8, gender);
            ps.setString(9, address);
            ps.setString(10, phoneNumber);
            ps.setInt(11, doctorLevelId);
            ps.setInt(12, specializationId);
            int affected = ps.executeUpdate();
            return affected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        DoctorDAO dao = new DoctorDAO();
      boolean add = dao.addDoctor("a", "b", "a", "a", "a", "a", LocalDate.of(2004, 4, 12), "a", "a", "a", 1, 2);
   if(add){
       System.out.println("oke");
   }else{
       System.out.println("failed");
   }
    }
}
