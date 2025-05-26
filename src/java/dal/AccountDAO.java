package dal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import java.sql.*;

public class AccountDAO extends MyDAO {
    public Account getAccountById(int id) {
        Account acc = null;
        xSql = "SELECT * FROM accounts WHERE id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                acc = new Account();
                acc.setId(rs.getInt("id"));
                acc.setEmail(rs.getString("email"));
                acc.setUsername(rs.getString("username"));
                acc.setPassword(rs.getString("password"));
                acc.setPasswordHash(rs.getString("password_hash"));
                acc.setRoleId(rs.getInt("role_id"));
                acc.setStatus(rs.getBoolean("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return acc;
    }

    public void updatePassword(int id, String newHash) {
        xSql = "UPDATE accounts SET password_hash=? WHERE id=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, newHash);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
