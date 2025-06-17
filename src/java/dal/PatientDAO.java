package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import model.Patient;

public class PatientDAO extends DBContext {

    public Patient checkLogin(String loginId, String password) {
        String sql = "SELECT * FROM patient WHERE (username = ? OR email = ?) AND password = ? AND status = 1";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, loginId);
            ps.setString(2, loginId);
            ps.setString(3, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Patient p = new Patient();
                    p.setId(rs.getInt("id"));
                    p.setImageUrl(rs.getString("image_url"));
                    p.setEmail(rs.getString("email"));
                    p.setUsername(rs.getString("username"));
                    p.setPassword(rs.getString("password"));
                    p.setRole(rs.getString("role"));
                    p.setFullName(rs.getString("full_name"));
                    java.sql.Date dob = rs.getDate("date_of_birth");
                    if (dob != null) {
                        p.setDateOfBirth(dob.toLocalDate());
                    }
                    p.setGender(rs.getString("gender"));
                    p.setAddress(rs.getString("address"));
                    p.setPhoneNumber(rs.getString("phone_number"));
                    p.setIdentityNumber(rs.getString("identity_number"));
                    p.setInsuranceNumber(rs.getString("insurance_number"));
                    p.setStatus(rs.getBoolean("status"));
                    return p;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Patient getPatientByEmail(String email) {
        String sql = "SELECT * FROM patient WHERE email = ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Patient p = new Patient();
                    p.setId(rs.getInt("id"));
                    p.setImageUrl(rs.getString("image_url"));
                    p.setEmail(rs.getString("email"));
                    p.setUsername(rs.getString("username"));
                    p.setPassword(rs.getString("password"));
                    p.setRole(rs.getString("role"));
                    p.setFullName(rs.getString("full_name"));
                    java.sql.Date dob = rs.getDate("date_of_birth");
                    if (dob != null) {
                        p.setDateOfBirth(dob.toLocalDate());
                    }
                    p.setGender(rs.getString("gender"));
                    p.setAddress(rs.getString("address"));
                    p.setPhoneNumber(rs.getString("phone_number"));
                    p.setIdentityNumber(rs.getString("identity_number"));
                    p.setInsuranceNumber(rs.getString("insurance_number"));
                    p.setStatus(rs.getBoolean("status"));
                    return p;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Patient getPatientByUsername(String username) {
        String sql = "SELECT * FROM patient WHERE username = ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Patient p = new Patient();
                    p.setId(rs.getInt("id"));
                    p.setImageUrl(rs.getString("image_url"));
                    p.setEmail(rs.getString("email"));
                    p.setUsername(rs.getString("username"));
                    p.setPassword(rs.getString("password"));
                    p.setRole(rs.getString("role"));
                    p.setFullName(rs.getString("full_name"));
                    java.sql.Date dob = rs.getDate("date_of_birth");
                    if (dob != null) {
                        p.setDateOfBirth(dob.toLocalDate());
                    }
                    p.setGender(rs.getString("gender"));
                    p.setAddress(rs.getString("address"));
                    p.setPhoneNumber(rs.getString("phone_number"));
                    p.setIdentityNumber(rs.getString("identity_number"));
                    p.setInsuranceNumber(rs.getString("insurance_number"));
                    p.setStatus(rs.getBoolean("status"));
                    return p;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addPatient(Patient patient) {
        String sql = "INSERT INTO patient (image_url, email, username, password, role, full_name, date_of_birth, gender, address, phone_number, identity_number, insurance_number, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, patient.getImageUrl());
            ps.setString(2, patient.getEmail());
            ps.setString(3, patient.getUsername());
            ps.setString(4, patient.getPassword());
            ps.setString(5, patient.getRole());
            ps.setString(6, patient.getFullName());
            if (patient.getDateOfBirth() != null) {
                ps.setDate(7, java.sql.Date.valueOf(patient.getDateOfBirth()));
            } else {
                ps.setNull(7, java.sql.Types.DATE);
            }
            ps.setString(8, patient.getGender());
            ps.setString(9, patient.getAddress());
            ps.setString(10, patient.getPhoneNumber());
            ps.setString(11, patient.getIdentityNumber());
            ps.setString(12, patient.getInsuranceNumber());
            ps.setBoolean(13, patient.isStatus());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi SQL: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    public Patient getAccountByEmail1(String email) {
        String sql = "SELECT * FROM patient WHERE email = ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Patient p = new Patient();
                    p.setId(rs.getInt("id"));
                    p.setImageUrl(rs.getString("image_url"));
                    p.setEmail(rs.getString("email"));
                    p.setUsername(rs.getString("username"));
                    p.setPassword(rs.getString("password"));
                    p.setRole(rs.getString("role"));
                    p.setFullName(rs.getString("full_name"));
                    java.sql.Date dob = rs.getDate("date_of_birth");
                    if (dob != null) {
                        p.setDateOfBirth(dob.toLocalDate());
                    }
                    p.setGender(rs.getString("gender"));
                    p.setAddress(rs.getString("address"));
                    p.setPhoneNumber(rs.getString("phone_number"));
                    p.setIdentityNumber(rs.getString("identity_number"));
                    p.setInsuranceNumber(rs.getString("insurance_number"));
                    p.setStatus(rs.getBoolean("status"));
                    p.setResetToken(rs.getString("resetToken"));
                    p.setResetTokenExpiry(rs.getTimestamp("resetTokenExpiry"));
                    return p;
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi SQL getAccountByEmail1: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void updateResetToken(int id, String token, Date expiry) {
        String sql = "UPDATE patient SET resetToken = ?, resetTokenExpiry = ? WHERE id = ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, token);
            if (expiry != null) {
                ps.setTimestamp(2, new java.sql.Timestamp(expiry.getTime()));
            } else {
                ps.setNull(2, java.sql.Types.TIMESTAMP);
            }
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi SQL updateResetToken: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean isValidResetToken(String token) {
        String sql = "SELECT resetTokenExpiry FROM patient WHERE resetToken = ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, token);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    java.sql.Timestamp expiry = rs.getTimestamp("resetTokenExpiry");
                    if (expiry != null) {
                        long now = System.currentTimeMillis();
                        return now < expiry.getTime();
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi SQL isValidResetToken: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePasswordByToken(String token, String password) {
        String sql = "UPDATE patient SET password = ?, resetToken = NULL, resetTokenExpiry = NULL WHERE resetToken = ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, password);
            ps.setString(2, token);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi SQL updatePasswordByToken: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public String getEmailByToken(String token) {
        String sql = "SELECT email FROM patient WHERE resetToken = ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, token);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("email");
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi SQL getEmailByToken: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public boolean isEmailExist(String email) {
        String sql = "SELECT 1 FROM patient WHERE email = ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println("Lỗi SQL isEmailExist: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean isUsernameExist(String generatedUsername) {
        String sql = "SELECT 1 FROM patient WHERE username = ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, generatedUsername);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println("Lỗi SQL isUsernameExist: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public void insertAccount(String email, String generatedUsername, String password, int status) {
        String sql = "INSERT INTO patient (email, username, password, status, role) VALUES (?, ?, ?, ?, 'Patient')";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, generatedUsername);
            ps.setString(3, password);
            ps.setInt(4, status);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi SQL insertAccount: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Patient checkLogingg(String email) {
        String sql = "SELECT * FROM patient WHERE email = ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Patient p = new Patient();
                    p.setId(rs.getInt("id"));
                    p.setImageUrl(rs.getString("image_url"));
                    p.setEmail(rs.getString("email"));
                    p.setUsername(rs.getString("username"));
                    p.setPassword(rs.getString("password"));
                    p.setRole(rs.getString("role"));
                    p.setFullName(rs.getString("full_name"));
                    java.sql.Date dob = rs.getDate("date_of_birth");
                    if (dob != null) {
                        p.setDateOfBirth(dob.toLocalDate());
                    }
                    p.setGender(rs.getString("gender"));
                    p.setAddress(rs.getString("address"));
                    p.setPhoneNumber(rs.getString("phone_number"));
                    p.setIdentityNumber(rs.getString("identity_number"));
                    p.setInsuranceNumber(rs.getString("insurance_number"));
                    p.setStatus(rs.getBoolean("status"));
                    // Nếu có trường token thì gán nốt luôn
                    try {
                        p.setResetToken(rs.getString("resetToken"));
                        p.setResetTokenExpiry(rs.getTimestamp("resetTokenExpiry"));
                    } catch (Exception ignore) {
                    }
                    return p;
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi SQL checkLogingg: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}
