package dal;
import model.User;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class UserDAO extends MyDAO {
    public User getUserById(int id) {
        User u = null;
        xSql = "SELECT * FROM users WHERE id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                u = extractUser(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    public List<User> getAllManagersAndReceptionists() {
        List<User> list = new ArrayList<>();
        xSql = "SELECT * FROM users WHERE role IN ('Manager', 'Receptionist')";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(extractUser(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private User extractUser(ResultSet rs) throws SQLException {
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setImageUrl(rs.getString("image_url"));
        u.setEmail(rs.getString("email"));
        u.setUsername(rs.getString("username"));
        u.setPassword(rs.getString("password"));
        u.setRole(rs.getString("role"));
        u.setFullName(rs.getString("full_name"));
        u.setDateOfBirth(rs.getDate("date_of_birth"));
        u.setGender(rs.getString("gender"));
        u.setAddress(rs.getString("address"));
        u.setPhoneNumber(rs.getString("phone_number"));
        u.setStatus(rs.getBoolean("status"));
        return u;
    }
} 