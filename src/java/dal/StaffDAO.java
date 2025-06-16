/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import model.Staff;

/**
 *
 * @author ADMIN
 */
public class StaffDAO extends MyDAO {

    public List<Staff> getAllStaff() {
        List<Staff> list = new ArrayList<>();
        xSql = "select * from Staff where role != 'Admin'";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Staff s = new Staff();
                s.setId(rs.getInt("id"));
                s.setImageUrl(rs.getString("image_url"));
                s.setEmail(rs.getString("email"));
                s.setUsername(rs.getString("username"));
                s.setPassword(rs.getString("password"));
                s.setRole(rs.getString("role"));
                s.setFullName(rs.getString("full_name"));
                s.setDateOfBirth(rs.getDate("date_of_birth"));
                s.setGender(rs.getString("gender"));
                s.setAddress(rs.getString("address"));
                s.setPhoneNumber(rs.getString("phone_number"));
                s.setStatus(rs.getBoolean("status"));
                list.add(s);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Staff viewStaffDetail(int id) {
        xSql = "select * from Staff where id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Staff s = new Staff();
                s.setId(rs.getInt("id"));
                s.setImageUrl(rs.getString("image_url"));
                s.setEmail(rs.getString("email"));
                s.setUsername(rs.getString("username"));
                s.setPassword(rs.getString("password"));
                s.setRole(rs.getString("role"));
                s.setFullName(rs.getString("full_name"));
                s.setDateOfBirth(rs.getDate("date_of_birth"));
                s.setGender(rs.getString("gender"));
                s.setAddress(rs.getString("address"));
                s.setPhoneNumber(rs.getString("phone_number"));
                s.setStatus(rs.getBoolean("status"));
                return s;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateStaff(int id, String email, String imageUrl, String username, String fullName,
            String password, LocalDate dateOfBirth, String gender, String address, String phoneNumber) {
        xSql = "UPDATE staff SET email=?, image_url=?, username=?, password=?, full_name=?, date_of_birth=?, gender=?, address=?, phone_number=? WHERE id=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, email);
            ps.setString(2, imageUrl);
            ps.setString(3, username);
            ps.setString(4, password);
            ps.setString(5, fullName);
            ps.setObject(6, dateOfBirth);
            ps.setString(7, gender);
            ps.setString(8, address);
            ps.setString(9, phoneNumber);
            ps.setInt(10, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        }
    }

    public void changeStaffStatus(int id, int status) {
        xSql = "UPDATE staff SET status=? WHERE id=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setBoolean(1, status == 1);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Staff> getStaffsByPage(int page, int pageSize, String search, String role, String status) {
        List<Staff> list = new ArrayList<>();
        xSql = "SELECT * FROM staff WHERE 1=1 and role != 'Admin'";
        if (search != null && !search.isEmpty()) {
            xSql += " AND (email LIKE ? OR username LIKE ?)";
        }
        if (role != null && !role.isEmpty()) {
            xSql += " AND role = ?";
        }
        if (status != null && !status.isEmpty()) {
            xSql += " AND status = ?";
        }
        xSql += " ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            int idx = 1;
            ps = con.prepareStatement(xSql);
            if (search != null && !search.isEmpty()) {
                ps.setString(idx++, "%" + search + "%");
                ps.setString(idx++, "%" + search + "%");
            }
            if (role != null && !role.isEmpty()) {
                ps.setString(idx++, role);
            }
            if (status != null && !status.isEmpty()) {
                ps.setString(idx++, status);
            }
            ps.setInt(idx++, (page - 1) * pageSize);
            ps.setInt(idx++, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                Staff staff = new Staff();
                staff.setId(rs.getInt("id"));
                staff.setImageUrl(rs.getString("image_url"));
                staff.setEmail(rs.getString("email"));
                staff.setUsername(rs.getString("username"));
                staff.setPassword(rs.getString("password"));
                staff.setRole(rs.getString("role"));
                staff.setFullName(rs.getString("full_name"));
                staff.setDateOfBirth(rs.getDate("date_of_birth"));
                staff.setGender(rs.getString("gender"));
                staff.setAddress(rs.getString("address"));
                staff.setPhoneNumber(rs.getString("phone_number"));
                staff.setStatus(rs.getBoolean("status"));
                list.add(staff);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int countStaff(String search, String role, String status) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM staff WHERE 1=1 and role != 'Admin'";
        if (search != null && !search.isEmpty()) {
            sql += " AND (email LIKE ? OR username LIKE ?)";
        }
        if (role != null && !role.isEmpty()) {
            sql += " AND role = ?";
        }
        if (status != null && !status.isEmpty()) {
            sql += " AND status = ?";
        }
        try {
            int idx = 1;
            ps = con.prepareStatement(sql);
            if (search != null && !search.isEmpty()) {
                ps.setString(idx++, "%" + search + "%");
                ps.setString(idx++, "%" + search + "%");
            }
            if (role != null && !role.isEmpty()) {
                ps.setString(idx++, role);
            }
            if (status != null && !status.isEmpty()) {
                ps.setString(idx++, status);
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public boolean addStaff(String imageUrl, String email, String username, String password,
            String role, String fullName, LocalDate dateOfBirth, String gender,
            String address, String phoneNumber) {
        String sql = "INSERT INTO staff (image_url, email, username, password, role, full_name, date_of_birth, gender, address, phone_number) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, imageUrl);
            ps.setString(2, email);
            ps.setString(3, username);
            ps.setString(4, password);
            ps.setString(5, role);
            ps.setString(6, fullName);
            ps.setDate(7, java.sql.Date.valueOf(dateOfBirth)); // KHÔNG check null
            ps.setString(8, gender);
            ps.setString(9, address);
            ps.setString(10, phoneNumber);
            int affected = ps.executeUpdate();
            return affected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

   

    public boolean checkEmailExists(String email) {
        xSql = "SELECT 1 FROM Staff WHERE email = ?";
        try {
        ps = con.prepareStatement(xSql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

   public boolean checkUsernameExists(String username) {
        xSql = "SELECT 1 FROM Staff WHERE username = ?";
        try {
        ps = con.prepareStatement(xSql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        StaffDAO staff = new StaffDAO();
        boolean isExisted = staff.checkUsernameExists("manager2");
        System.out.println(isExisted);
    }
}
