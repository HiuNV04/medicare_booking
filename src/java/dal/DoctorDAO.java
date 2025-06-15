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
        List<Doctor> list = new ArrayList<>();
        xSql = "SELECT * FROM doctor";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(extractDoctor(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
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
        d.setStatus(rs.getBoolean("status"));
        return d;
    }
} 