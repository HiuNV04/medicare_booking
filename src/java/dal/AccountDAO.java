package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

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

    // Đăng nhập bằng Google - chỉ cần email tồn tại, không cần mật khẩu
    public Account checkLogingg(String loginId) {
        String sql = "SELECT * FROM accounts WHERE email = ? AND status = 1";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, loginId);
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

    // Lấy account theo email
    public Account getAccountByEmail(String email) {
        String sql = "SELECT * FROM accounts WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
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

    public Account getAccountByEmail1(String email) {
        String sql = "SELECT id, email, username, password, role_id, status, reset_token, reset_token_expiry FROM accounts WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Account acc = new Account();
                    acc.setId(rs.getInt("id"));
                    acc.setEmail(rs.getString("email"));
                    acc.setUsername(rs.getString("username"));
                    acc.setPassword(rs.getString("password"));
                    acc.setRoleId(rs.getInt("role_id"));
                    acc.setStatus(rs.getBoolean("status"));
                    acc.setResetToken(rs.getString("reset_token"));

                    if (rs.getTimestamp("reset_token_expiry") != null) {
                        acc.setResetTokenExpiry(rs.getTimestamp("reset_token_expiry"));
                    } else {
                        acc.setResetTokenExpiry(null);
                    }

                    return acc;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Kiểm tra token hợp lệ
    public boolean isValidResetToken(String token) {
        String sql = "SELECT reset_token_expiry FROM accounts WHERE reset_token = ? AND reset_token_expiry > GETDATE()";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, token);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // Nếu có bản ghi → token hợp lệ
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

public boolean updatePasswordByToken(String token, String newPassword) {
    String sql = "UPDATE accounts SET password = ?, reset_token = NULL, reset_token_expiry = NULL "
               + "WHERE reset_token = ?";

    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, newPassword);
        ps.setString(2, token);

        int rows = ps.executeUpdate();
        System.out.println("Rows updated: " + rows);

        return rows > 0;
    } catch (SQLException e) {
        System.err.println("SQL Exception: " + e.getMessage());
        e.printStackTrace();
    }
    return false;
}


// Lấy email theo token

    public String getEmailByToken(String token) {
        String sql = "SELECT email FROM accounts WHERE reset_token = ? AND reset_token_expiry > GETDATE()";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, token);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("email");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
// Cập nhật reset_token + expiry

    public void updateResetToken(int accountId, String token, Date expiry) {
        String sql = "UPDATE accounts SET reset_token = ?, reset_token_expiry = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, token);
            ps.setTimestamp(2, new java.sql.Timestamp(expiry.getTime()));
            ps.setInt(3, accountId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   public static void main(String[] args) {
    AccountDAO dao = new AccountDAO();

    // Token bạn muốn test:
    String testToken = "4f21f8b1-34d6-4ddc-bab4-91c24889ce68";

    // Password mới bạn muốn set:
    String newPassword = "newpassword123";

    // Gọi hàm update:
    boolean success = dao.updatePasswordByToken(testToken, newPassword);

    // In kết quả:
    if (success) {
        System.out.println("✅ Cập nhật password thành công cho token: " + testToken);
    } else {
        System.out.println("❌ Không cập nhật được password (token không hợp lệ hoặc đã hết hạn): " + testToken);
    }
}


}
