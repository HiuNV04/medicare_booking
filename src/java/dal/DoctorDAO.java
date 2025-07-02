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
import controller.send.TextUtils;
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

    public List<Doctor> getDoctors() {
    List<Doctor> doctors = new ArrayList<>();
    String sql = "SELECT d.*, dl.name AS level_name, sp.name AS specialization_name " +
                 "FROM doctor d " +
                 "LEFT JOIN doctor_level dl ON d.doctor_level_id = dl.id " +
                 "LEFT JOIN specialization sp ON d.specialization_id = sp.id";
    try {
         ps = con.prepareStatement(sql);
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
            doctor.setDateOfBirth(rs.getDate("date_of_birth")); // nếu bạn dùng java.sql.Date
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
        e.printStackTrace();
    }
    return doctors;
}

    // Lấy danh sách nhân viên với filter, phân trang
    public List<Doctor> getDoctorsByFilter(String search, String role, Boolean status, int page, int pageSize) {
        List<Doctor> list = new ArrayList<>();
        int offset = (page - 1) * pageSize;
        StringBuilder sql = new StringBuilder(
                "SELECT * FROM doctor WHERE 1=1 and role != 'Admin'");
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
                    s.setDateOfBirth(dob);
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
            ps.setDate(6, new java.sql.Date(s.getDateOfBirth().getTime()));
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
            String role, String fullName, Date dateOfBirth, String gender,
            String address, String phoneNumber, int doctorLevelId,
            int specializationId) {
        String sql = "INSERT INTO doctor (image_url, email, username, password, role, full_name, date_of_birth, "
                + "gender, address, phone_number, doctor_level_id, specialization_id, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,1)";  //  
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, imageUrl);
            ps.setString(2, email);
            ps.setString(3, username);
            ps.setString(4, password);
            ps.setString(5, role);
            ps.setString(6, fullName);
            if (dateOfBirth != null) {
                ps.setDate(7, dateOfBirth);
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
    public Doctor getDoctorById1(int id) {
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

//    // 4. Đăng nhập 
//    public Doctor checkLogin(String username, String password) {
//        String sql = "SELECT d.*, dl.name AS level_name, s.name AS specialization_name "
//                + "FROM doctor d "
//                + "LEFT JOIN doctor_level dl ON d.doctor_level_id = dl.id "
//                + "LEFT JOIN specialization s ON d.specialization_id = s.id "
//                + "WHERE d.username = ? AND d.password = ? AND d.status = 1";
//        try (PreparedStatement stmt = con.prepareStatement(sql)) {
//            stmt.setString(1, username);
//            stmt.setString(2, password);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                Doctor doctor = new Doctor();
//                doctor.setId(rs.getInt("id"));
//                doctor.setFullName(rs.getString("full_name"));
//                doctor.setImageUrl(rs.getString("image_url"));
//                doctor.setAddress(rs.getString("address"));
//                doctor.setDateOfBirth(rs.getDate("date_of_birth"));
//                doctor.setGender(rs.getString("gender"));
//                doctor.setPhoneNumber(rs.getString("phone_number"));
//                doctor.setEmail(rs.getString("email"));
//                doctor.setStatus(rs.getInt("status") == 1);
//                doctor.setSpecializationId(rs.getInt("specialization_id"));
//                doctor.setDoctorLevelId(rs.getInt("doctor_level_id"));
//                doctor.setSpecialization(rs.getString("specialization_name"));
//                doctor.setLevelName(rs.getString("level_name"));
//                doctor.setNote(rs.getString("note"));
//                return doctor;
//            }
//        } catch (SQLException e) {
//            System.out.println("SQL Error in getDoctorDetail(): " + e.getMessage());
//        }
//        return null;
//    }
    public void updateDoctor1(Doctor doctor) {
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
                d.setSpecialization(rs.getString("specialization_name"));
                d.setLevelName(rs.getString("level_name"));
                list.add(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (name != null && !name.trim().isEmpty()) {
            String[] keywords = name.trim().split("\\s+");

            list = list.stream().filter(d -> {
                String fullName = d.getFullName();
                String fullNameNoAccent = TextUtils.normalizeForFuzzySearch(fullName); // bỏ dấu
                String fullNameWithAccent = TextUtils.normalizeStrict(fullName);       // giữ dấu

                for (String keyword : keywords) {
                    String keywordNoAccent = TextUtils.normalizeForFuzzySearch(keyword);
                    String keywordWithAccent = TextUtils.normalizeStrict(keyword);

                    if (!keyword.equalsIgnoreCase(keywordWithAccent)) {
                        if (!fullNameWithAccent.contains(keywordWithAccent)) {
                            return false;
                        }
                    } else {
                        if (!fullNameNoAccent.contains(keywordNoAccent)) {
                            return false;
                        }
                    }
                }

                return true;
            }).collect(Collectors.toList());
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

//    // check kí tự đặc biệt , khoảng trắng
//    public List<Doctor> filterByFuzzyName(List<Doctor> inputList, String rawName) {
//        if (rawName == null || rawName.trim().isEmpty()) {
//            return inputList;
//        }
//
//        String cleanName = rawName.replaceAll("\\s+", "");
//        String nameNormalized = TextUtils.normalizeForFuzzySearch(cleanName);
//        String nameStrict = TextUtils.normalizeStrict(cleanName);
//
//        return inputList.stream().filter(d -> {
//            String fullName = d.getFullName();
//            String normalizedFullName = TextUtils.normalizeForFuzzySearch(fullName);
//            String originalFullName = TextUtils.normalizeStrict(fullName);
//
//            if (!nameNormalized.equals(nameStrict)) {
//                return originalFullName.contains(nameStrict);
//            } else {
//                return normalizedFullName.contains(nameNormalized);
//            }
//        }).collect(Collectors.toList());
//    }

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

//    public void insert(Doctor doctor) {
//        String sql = "INSERT INTO doctor (full_name, email, username, password, role, status, created_at) "
//                + "VALUES (?, ?, ?, ?, ?, ?, GETDATE())";
//
//        try (Connection conn = connection; PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setString(1, doctor.getFullName());
//            ps.setString(2, doctor.getEmail());
//            ps.setString(3, doctor.getUsername());
//            ps.setString(4, doctor.getPassword());
//            String role = doctor.getRole().toUpperCase().charAt(0) + doctor.getRole().substring(1).toLowerCase();
//            ps.setString(5, role);
//            ps.setBoolean(6, doctor.isStatus());
//
//            ps.executeUpdate();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

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
