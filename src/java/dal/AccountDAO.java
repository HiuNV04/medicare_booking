package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;

public class AccountDAO extends DBContext {

    // Kiểm tra email đã tồn tại hay chưa
    public boolean isEmailExist(String email) {
        String sql = "SELECT 1 FROM accounts WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // nếu có kết quả -> email đã tồn tại
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Kiểm tra username đã tồn tại hay chưa
    public boolean isUsernameExist(String username) {
        String sql = "SELECT 1 FROM accounts WHERE username = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // nếu có kết quả -> username đã tồn tại
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Thêm tài khoản mới
    public void insertAccount(String email, String username, String password, int roleId) {
        String sql = "INSERT INTO accounts (email, username, password, password_hash, role_id, status) VALUES (?, ?, ?, ?, ?, 1)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setString(4, password); // Giả sử bạn chưa mã hóa password, nếu có thì thay bằng hash
            ps.setInt(5, roleId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Đăng nhập thông thường bằng email hoặc username + password
    public Account checkLogin(String loginId, String password) {
        String sql = "SELECT * FROM accounts WHERE (email = ? OR username = ?) AND password = ? AND status = 1";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, loginId);
            ps.setString(2, loginId);
            ps.setString(3, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return extractAccountFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

  

    // Hàm tiện ích trích xuất dữ liệu từ ResultSet sang đối tượng Account
    private Account extractAccountFromResultSet(ResultSet rs) throws SQLException {
        Account acc = new Account();
        acc.setId(rs.getInt("id"));
        acc.setEmail(rs.getString("email"));
        acc.setUsername(rs.getString("username"));
        acc.setPassword(rs.getString("password"));
        acc.setPasswordHash(rs.getString("password_hash"));
        acc.setRoleId(rs.getInt("role_id"));
        acc.setResetToken(rs.getString("reset_token"));
        if (rs.getTimestamp("reset_token_expiry") != null) {
            acc.setResetTokenExpiry(rs.getDate("reset_token_expiry"));
        }
        acc.setStatus(rs.getBoolean("status"));
        return acc;
    }
}
