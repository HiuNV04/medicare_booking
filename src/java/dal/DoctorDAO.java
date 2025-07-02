/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.DoctorLevel;
import model.Specialization;
import dal.ValidateDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.Doctor;

/**
 *
 * @author ADMIN
 */
public class DoctorDAO extends MyDAO {
    
    public DoctorDAO() {
        super();
        if (con == null) {
            System.out.println("❌ DoctorDAO: Không kết nối được tới database");
        } else {
            System.out.println("✅ DoctorDAO: Kết nối database thành công");
        }
    }

   
    

    // 1. Lấy toàn bộ bác sĩ
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT d.id, d.image_url, d.email, d.username, d.password, d.role, d.full_name, "
                + "d.date_of_birth, d.gender, d.address, d.phone_number, d.doctor_level_id, d.specialization_id, d.status, d.note, "
                + "dl.name AS level_name, s.name AS specialization_name "
                + "FROM doctor d "
                + "LEFT JOIN doctor_level dl ON d.doctor_level_id = dl.id "
                + "LEFT JOIN specialization s ON d.specialization_id = s.id";

        try (PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
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
                doctor.setLevelName(rs.getString("level_name"));
                doctor.setSpecialization(rs.getString("specialization_name"));
                doctor.setNote(rs.getString("note"));
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error in getAllDoctors(): " + e.getMessage());
        }
        return doctors;
    }

    // 2. Rút gọn bác sĩ 
    public List<Doctor> getListDoctor() {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT d.id, d.email, d.role, d.full_name, d.date_of_birth, d.gender, d.address, d.phone_number, "
                + "d.doctor_level_id, d.specialization_id, dl.name AS level_name, s.name AS specialization_name "
                + "FROM doctor d "
                + "LEFT JOIN doctor_level dl ON d.doctor_level_id = dl.id "
                + "LEFT JOIN specialization s ON d.specialization_id = s.id";
        try (PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("id"));
                doctor.setEmail(rs.getString("email"));
                doctor.setRole(rs.getString("role"));
                doctor.setFullName(rs.getString("full_name"));
                doctor.setDateOfBirth(rs.getDate("date_of_birth"));
                doctor.setGender(rs.getString("gender"));
                doctor.setAddress(rs.getString("address"));
                doctor.setPhoneNumber(rs.getString("phone_number"));
                doctor.setDoctorLevelId(rs.getInt("doctor_level_id"));
                doctor.setSpecializationId(rs.getInt("specialization_id"));
                doctor.setLevelName(rs.getString("level_name"));
                doctor.setSpecialization(rs.getString("specialization_name"));
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error in getListDoctor(): " + e.getMessage());
        }
        return doctors;
    }

    // 3. Lấy chi tiết theo id
    public Doctor getDoctorById(int id) {
        String sql = "SELECT d.*, dl.name AS level_name, s.name AS specialization_name "
                + "FROM doctor d "
                + "LEFT JOIN doctor_level dl ON d.doctor_level_id = dl.id "
                + "LEFT JOIN specialization s ON d.specialization_id = s.id "
                + "WHERE d.id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("id"));
                doctor.setUsername(rs.getString("username"));
                doctor.setPassword(rs.getString("password"));
                doctor.setFullName(rs.getString("full_name"));
                doctor.setImageUrl(rs.getString("image_url"));
                doctor.setEmail(rs.getString("email"));
                doctor.setGender(rs.getString("gender"));
                doctor.setAddress(rs.getString("address"));
                doctor.setPhoneNumber(rs.getString("phone_number"));
                doctor.setDateOfBirth(rs.getDate("date_of_birth"));
                doctor.setNote(rs.getString("note"));
                doctor.setLevelName(rs.getString("level_name"));
                doctor.setSpecialization(rs.getString("specialization_name"));
                return doctor;
            }
        } catch (SQLException e) {
            System.out.println("SQL Error in getDoctorById(): " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void updateDoctor(Doctor doctor) {
        String sql = "UPDATE doctor SET full_name=?, image_url=? , address=?, date_of_birth=?, gender=?, "
                + "phone_number=?, email=?, note=? WHERE id=?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, doctor.getFullName());
            stmt.setString(2, doctor.getImageUrl());
            stmt.setString(3, doctor.getAddress());
            stmt.setDate(4, new java.sql.Date(doctor.getDateOfBirth().getTime()));
            stmt.setString(5, doctor.getGender());
            stmt.setString(6, doctor.getPhoneNumber());
            stmt.setString(7, doctor.getEmail());
            stmt.setString(8, doctor.getNote());
            stmt.setInt(9, doctor.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL Error in updateDoctor(): " + e.getMessage());
        }
    }

    // 6. Lấy tất cả chuyên khoa để lọc
    public List<String[]> getAllSpecializations() {
        List<String[]> list = new ArrayList<>();
        String sql = "SELECT id, name FROM specialization";
        try (PreparedStatement st = con.prepareStatement(sql); ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                list.add(new String[]{String.valueOf(rs.getInt("id")), rs.getString("name")});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 7. Lấy tất cả trình độ để lọc
    public List<String[]> getAllLevels() {
        List<String[]> list = new ArrayList<>();
        String sql = "SELECT id, name FROM doctor_level";
        try (PreparedStatement st = con.prepareStatement(sql); ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                list.add(new String[]{String.valueOf(rs.getInt("id")), rs.getString("name")});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 8. Tìm kiếm và lọc bác sĩ 
    public List<Doctor> searchDoctors(String name, String gender, Integer levelId, Integer specializationId) {
        List<Doctor> list = new ArrayList<>();
        List<Object> params = new ArrayList<>();

        StringBuilder xSql = new StringBuilder(
                "SELECT d.*, s.name AS specialization_name, l.name AS level_name "
                + "FROM doctor d "
                + "JOIN specialization s ON d.specialization_id = s.id "
                + "JOIN doctor_level l ON d.doctor_level_id = l.id "
                + "WHERE 1 = 1 "
        );

        if (gender != null && !gender.trim().isEmpty()) {
            xSql.append("AND d.gender = ? ");
            params.add(gender);
        }

        if (levelId != null) {
            xSql.append("AND d.doctor_level_id = ? ");
            params.add(levelId);
        }

        if (specializationId != null) {
            xSql.append("AND d.specialization_id = ? ");
            params.add(specializationId);
        }

        try (PreparedStatement st = con.prepareStatement(xSql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                st.setObject(i + 1, params.get(i));
            }

            ResultSet rs = st.executeQuery();
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
                d.setSpecialization(rs.getString("specialization_name"));
                d.setLevelName(rs.getString("level_name"));
                list.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ✅ Lọc theo tên bằng Java (dùng Normalize)
        if (name != null && !name.trim().isEmpty()) {
            list = list.stream()
                    .filter(d -> ValidateDAO.fuzzyMatchAllWords(d.getFullName(), name))
                    .collect(Collectors.toList());
        }

        return list;
    }

    // 9. Cập nhật mật khẩu
    public boolean updateDoctorPassword(int doctorId, String newPassword) {
        String sql = "UPDATE doctor SET password = ? WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, newPassword);
            stmt.setInt(2, doctorId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ SQL Error in updateDoctorPassword(): " + e.getMessage());
            return false;
        }
    }

    // 10. phân trang
    public List<Doctor> getDoctorsPage(String name, String gender, Integer levelId, Integer specializationId, int offset, int limit) {
        List<Doctor> doctors = new ArrayList<>();

        StringBuilder sql = new StringBuilder(
                "SELECT d.id, d.image_url, d.email, d.username, d.password, d.role, d.full_name, "
                + "d.date_of_birth, d.gender, d.address, d.phone_number, d.doctor_level_id, d.specialization_id, d.status, d.note, "
                + "dl.name AS level_name, s.name AS specialization_name "
                + "FROM doctor d "
                + "LEFT JOIN doctor_level dl ON d.doctor_level_id = dl.id "
                + "LEFT JOIN specialization s ON d.specialization_id = s.id "
                + "WHERE 1=1"
        );

        List<Object> params = new ArrayList<>();

        if (name != null && !name.trim().isEmpty()) {
            sql.append(" AND d.full_name LIKE ?");
            params.add("%" + name.trim() + "%");
        }
        if (gender != null && !gender.trim().isEmpty()) {
            sql.append(" AND d.gender = ?");
            params.add(gender.trim());
        }
        if (levelId != null) {
            sql.append(" AND d.doctor_level_id = ?");
            params.add(levelId);
        }
        if (specializationId != null) {
            sql.append(" AND d.specialization_id = ?");
            params.add(specializationId);
        }

        // Phân trang
        sql.append(" ORDER BY d.id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");
        params.add(offset);
        params.add(limit);

        try (PreparedStatement stmt = con.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Doctor d = new Doctor();
                d.setId(rs.getInt("id"));
                d.setFullName(rs.getString("full_name"));
                d.setImageUrl(rs.getString("image_url"));
                d.setEmail(rs.getString("email"));
                d.setUsername(rs.getString("username"));
                d.setPassword(rs.getString("password"));
                d.setRole(rs.getString("role"));
                d.setDateOfBirth(rs.getDate("date_of_birth"));
                d.setGender(rs.getString("gender"));
                d.setAddress(rs.getString("address"));
                d.setPhoneNumber(rs.getString("phone_number"));
                d.setDoctorLevelId(rs.getInt("doctor_level_id"));
                d.setSpecializationId(rs.getInt("specialization_id"));
                d.setStatus(rs.getBoolean("status"));
                d.setNote(rs.getString("note"));
                d.setLevelName(rs.getString("level_name"));
                d.setSpecialization(rs.getString("specialization_name"));
                doctors.add(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Phân trang trong Java
        int toIndex = Math.min(offset + limit, doctors.size());
        if (offset > doctors.size()) {
            return new ArrayList<>();
        }
        return doctors.subList(offset, toIndex);
    }

    // 11. Hàm đếm bác sĩ
    public int countDoctors(String name, String gender, Integer levelId, Integer specializationId) {
        return getDoctorsPage(name, gender, levelId, specializationId, 0, Integer.MAX_VALUE).size();
    }

    public boolean isUsernameTaken(String username) {
        String sql = "SELECT 1 FROM doctor WHERE username = ?";
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
    public Doctor findByEmail(String email) {
        String sql = "SELECT * FROM doctor WHERE email = ?";
        try (Connection conn = connection; PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Doctor d = new Doctor();
                d.setUsername(rs.getString("username"));
                d.setEmail(rs.getString("email"));
                return d;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // cập nhật mật khẩu mới nhận từ mail để mã hóa rồi login 
    public boolean updatePasswordByEmail(String email, String hashedPassword) {
        String sql = "UPDATE doctor SET password = ? WHERE email = ?";
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
