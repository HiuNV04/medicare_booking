/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    // 1. Lấy toàn bộ bác sĩ (dùng cho trang edit)
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
            System.out.println("❌ SQL Error in getAllDoctors(): " + e.getMessage());
        }
        return doctors;
    }

    // 2. Rút gọn bác sĩ (cho trang edit quản lý)
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
            System.out.println(" SQL Error in getListDoctor(): " + e.getMessage());
        }
        return doctors;
    }

    // 3. Lấy chi tiết theo ID
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
            System.out.println("❌ SQL Error in getDoctorById(): " + e.getMessage());
        }
        return null;
    }

    // 4. Đăng nhập (getDetail)
    public Doctor getDoctorDetail(String username, String password) {
        String sql = "SELECT d.*, dl.name AS level_name, s.name AS specialization_name "
                + "FROM doctor d "
                + "LEFT JOIN doctor_level dl ON d.doctor_level_id = dl.id "
                + "LEFT JOIN specialization s ON d.specialization_id = s.id "
                + "WHERE d.username = ? AND d.password = ? AND d.status = 1";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("id"));
                doctor.setFullName(rs.getString("full_name"));
                doctor.setImageUrl(rs.getString("image_url"));
                doctor.setAddress(rs.getString("address"));
                doctor.setDateOfBirth(rs.getDate("date_of_birth"));
                doctor.setGender(rs.getString("gender"));
                doctor.setPhoneNumber(rs.getString("phone_number"));
                doctor.setEmail(rs.getString("email"));
                doctor.setStatus(rs.getInt("status") == 1);
                doctor.setSpecializationId(rs.getInt("specialization_id"));
                doctor.setDoctorLevelId(rs.getInt("doctor_level_id"));
                doctor.setSpecialization(rs.getString("specialization_name"));
                doctor.setLevelName(rs.getString("level_name"));
                doctor.setNote(rs.getString("note"));
                return doctor;
            }
        } catch (SQLException e) {
            System.out.println(" SQL Error in getDoctorDetail(): " + e.getMessage());
        }
        return null;
    }

    // 5. Cập nhật thông tin bác sĩ
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
            System.out.println(" SQL Error in updateDoctor(): " + e.getMessage());
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

        StringBuilder sql = new StringBuilder(
                "SELECT d.*, s.name AS specialization_name, l.name AS level_name "
                + "FROM doctor d "
                + "JOIN specialization s ON d.specialization_id = s.id "
                + "JOIN doctor_level l ON d.doctor_level_id = l.id "
                + "WHERE 1 = 1"
        );

        if (name != null && !name.trim().isEmpty()) {
            sql.append(" AND LOWER(REPLACE(d.full_name, ' ', '')) LIKE LOWER(REPLACE(?, ' ', ''))");
        }

        if (gender != null && !gender.trim().isEmpty()) {
            sql.append(" AND d.gender = ?");
        }

        if (levelId != null) {
            sql.append(" AND d.doctor_level_id = ?");
        }

        if (specializationId != null) {
            sql.append(" AND d.specialization_id = ?");
        }

        try (PreparedStatement st = con.prepareStatement(sql.toString())) {
            int index = 1;

            if (name != null && !name.trim().isEmpty()) {
                st.setString(index++, "%" + name.trim() + "%");
            }

            if (gender != null && !gender.trim().isEmpty()) {
                st.setString(index++, gender);
            }

            if (levelId != null) {
                st.setInt(index++, levelId);
            }

            if (specializationId != null) {
                st.setInt(index++, specializationId);
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
                d.setSpecialization(rs.getString("specialization_name")); // từ JOIN specialization
                d.setLevelName(rs.getString("level_name"));               // từ JOIN level
                list.add(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
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

            try (ResultSet rs = stmt.executeQuery()) {
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
            }

        } catch (SQLException e) {
            System.out.println("❌ SQL Error in searchDoctorsWithPaging(): " + e.getMessage());
        }

        return doctors;
    }

    // 11. Hàm đếm bác sĩ
    public int countDoctors(String name, String gender, Integer levelId, Integer specializationId) {

        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM doctor d WHERE 1=1");
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

        try (PreparedStatement stmt = con.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.out.println("SQL Error in countFilteredDoctors(): " + e.getMessage());
        }

        return 0;
    }

    public static void main(String[] args) {
        DoctorDAO dao = new DoctorDAO();

        String name = "an";
        String gender = "Nam";
        String specialization = "";
        String level = "";

        List<Doctor> doctors = dao.searchDoctors(name, gender, Integer.SIZE, Integer.SIZE);

        if (doctors.isEmpty()) {
            System.out.println("Không tìm thấy bác sĩ nào.");
        } else {
            for (Doctor d : doctors) {
                System.out.println("Họ tên: " + d.getFullName());
                System.out.println("Giới tính: " + d.getGender());
                System.out.println("Trình độ: " + d.getLevelName());
                System.out.println("Chuyên khoa: " + d.getSpecialization());

                System.out.println("name=" + name + ", gender=" + gender + ", level=" + level + ", specialization=" + specialization);

                System.out.println("----------------------------");
            }
        }
    }
}
