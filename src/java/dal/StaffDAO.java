package dal;
import model.Staff;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDAO extends MyDAO {
    public Staff getStaffByAccountId(int accountId) {
        Staff s = null;
        xSql = "SELECT * FROM staffs WHERE account_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, accountId);
            rs = ps.executeQuery();
            if (rs.next()) {
                s = new Staff();
                s.setId(rs.getInt("id"));
                s.setAccountId(rs.getInt("account_id"));
                s.setImageURL(rs.getString("imageURL"));
                s.setAddress(rs.getString("address"));
                s.setDateOfBirth(rs.getDate("date_of_birth"));
                s.setFullName(rs.getString("full_name"));
                s.setGender(rs.getString("gender"));
                s.setPhoneNumber(rs.getString("phone_number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public void updateStaff(Staff s) {
        xSql = "UPDATE staffs SET imageURL=?, address=?, date_of_birth=?, full_name=?, gender=?, phone_number=? WHERE id=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, s.getImageURL());
            ps.setString(2, s.getAddress());
            ps.setDate(3, s.getDateOfBirth());
            ps.setString(4, s.getFullName());
            ps.setString(5, s.getGender());
            ps.setString(6, s.getPhoneNumber());
            ps.setInt(7, s.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Staff> getAllStaffExceptAdminAndManager() {
        List<Staff> list = new ArrayList<>();
        xSql = "SELECT s.* FROM staffs s JOIN accounts a ON s.account_id = a.id WHERE a.role_id != 1 AND a.role_id != 2";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Staff s = new Staff();
                s.setId(rs.getInt("id"));
                s.setAccountId(rs.getInt("account_id"));
                s.setImageURL(rs.getString("imageURL"));
                s.setAddress(rs.getString("address"));
                s.setDateOfBirth(rs.getDate("date_of_birth"));
                s.setFullName(rs.getString("full_name"));
                s.setGender(rs.getString("gender"));
                s.setPhoneNumber(rs.getString("phone_number"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
} 