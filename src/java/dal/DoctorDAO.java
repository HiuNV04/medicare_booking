/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Doctor;
import java.time.LocalDate;
import java.sql.Date;
import model.Doctor;
import model.DoctorLevel;
import model.Specialization;
import model.Doctor;

/**
 *
 * @author ADMIN
 */
public class DoctorDAO extends MyDAO {

    // Lấy danh sách nhân viên với filter, phân trang
    public List<Doctor> getDoctorsByFilter(String search, String role, Boolean status, int page, int pageSize) {
        List<Doctor> list = new ArrayList<>();
        int offset = (page - 1) * pageSize;
        StringBuilder sql = new StringBuilder(
                "SELECT * FROM doctor WHERE 1=1 and role != 'Admin'"
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
                Doctor s = new Doctor();
                s.setId(rs.getInt("id"));
                s.setEmail(rs.getString("email"));
                s.setUsername(rs.getString("username"));
                s.setRole(rs.getString("role"));
                s.setStatus(rs.getBoolean("status"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Đếm số lượng staff với filter (phục vụ phân trang)
    public int countDoctors(String search, String role, Boolean status) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM doctor WHERE 1=1 and role != 'Admin' ");
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

    public Doctor getDoctorById(int id) {
        Doctor s = null;
        String sql = "SELECT * FROM doctor WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                s = new Doctor();
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
                s.setDoctorLevelId(rs.getInt("doctor_level_id"));
                s.setSpecializationId(rs.getInt("specialization_id"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public List<DoctorLevel> getAllDoctorLevel() {
        List<DoctorLevel> list = new ArrayList<>();
        String sql = "SELECT id, name, examination_fee FROM doctor_level";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DoctorLevel dl = new DoctorLevel();
                dl.setId(rs.getInt("id"));
                dl.setName(rs.getString("name"));
                dl.setExaminationFee(rs.getFloat("examination_fee"));
                list.add(dl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Specialization> getAllSpecialization() {
        List<Specialization> list = new ArrayList<>();
        String sql = "SELECT id, name, description FROM specialization";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Specialization sp = new Specialization();
                sp.setId(rs.getInt("id"));
                sp.setName(rs.getString("name"));
                sp.setDescription(rs.getString("description"));
                list.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateDoctor(Doctor s) {
        String sql = "UPDATE doctor SET image_url=?, email=?, username=?, password=?, full_name=?, date_of_birth=?, gender=?, address=?, phone_number=?, doctor_level_id=?, specialization_id=? WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, s.getImageUrl());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getUsername());
            ps.setString(4, s.getPassword());
            ps.setString(5, s.getFullName());
            ps.setDate(6, s.getDateOfBirth() == null ? null : java.sql.Date.valueOf(s.getDateOfBirth()));
            ps.setString(7, s.getGender());
            ps.setString(8, s.getAddress());
            ps.setString(9, s.getPhoneNumber());
            ps.setInt(10, s.getDoctorLevelId());
            ps.setInt(11, s.getSpecializationId());
            ps.setInt(12, s.getId());
            int count = ps.executeUpdate();
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
     public boolean updateDoctorStatus(int id, boolean status) {
        String sql = "UPDATE doctor SET status=? WHERE id=?";
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
        public boolean checkEmailExists(String email) {
        String sql = "SELECT 1 FROM doctor WHERE email=?";
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
        String sql = "SELECT 1 FROM doctor WHERE username=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            return false;
        }
    }
     public boolean addDoctor(String imageUrl, String email, String username, String password,
                             String role, String fullName, LocalDate dateOfBirth, String gender,
                             String address, String phoneNumber, int doctorLevelId,
                             int specializationId ) {
        String sql = "INSERT INTO doctor (image_url, email, username, password, role, full_name, date_of_birth, " +
                "gender, address, phone_number, doctor_level_id, specialization_id, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,1)";  //  
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, imageUrl);
            ps.setString(2, email);
            ps.setString(3, username);
            ps.setString(4, password);
            ps.setString(5, role);
            ps.setString(6, fullName);
            if (dateOfBirth != null) {
                ps.setDate(7, Date.valueOf(dateOfBirth));
            }
            ps.setString(8, gender);
            ps.setString(9, address);
            ps.setString(10, phoneNumber);
            ps.setInt(11, doctorLevelId);
            ps.setInt(12, specializationId);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }  
    }

}
