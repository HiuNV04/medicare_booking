package dal;
import model.Doctor;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class DoctorDAO extends MyDAO {
    public Doctor getDoctorById(int id) {
        Doctor d = null;
        xSql = "SELECT * FROM doctor WHERE id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                d = extractDoctor(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM doctor";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Doctor d = new Doctor();
                d.setId(rs.getInt("id"));
                d.setFullName(rs.getString("full_name"));
                d.setDateOfBirth(rs.getDate("date_of_birth"));
                d.setGender(rs.getString("gender"));
                d.setAddress(rs.getString("address"));
                d.setPhoneNumber(rs.getString("phone_number"));
                d.setImageUrl(rs.getString("image_url"));
                d.setSpecializationId(rs.getInt("specialization_id"));
                d.setDoctorLevelId(rs.getInt("doctor_level_id"));
                d.setRole("Doctor");
                doctors.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    private Doctor extractDoctor(ResultSet rs) throws SQLException {
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
        return d;
    }

    public void updateDoctor(Doctor doctor) {
        String query = "UPDATE doctor SET full_name = ?, date_of_birth = ?, gender = ?, address = ?, phone_number = ?, image_url = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, doctor.getFullName());
            ps.setDate(2, new java.sql.Date(doctor.getDateOfBirth().getTime()));
            ps.setString(3, doctor.getGender());
            ps.setString(4, doctor.getAddress());
            ps.setString(5, doctor.getPhoneNumber());
            ps.setString(6, doctor.getImageUrl());
            ps.setInt(7, doctor.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
} 