/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Receptionist;
import java.sql.*;
import java.util.*;
import model.Patient;

/**
 *
 * @author ptson
 */
public class ReceptionistDAO extends DBContext {

    //lấy thông tin 1 lễ tân
    public Receptionist getAReceptionistByEmail(String email) {
        String sql = "select r.id, r.role, r.email, r.username, r.password, r.full_name, r.date_of_birth,\n"
                + "r.gender, r.phone_number, r.address, r.image_url \n"
                + "from staff r where r.email = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Receptionist(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Receptionist getAReceptionistByUsername(String username) {
        String sql = "select r.id, r.role, r.email, r.username, r.password, r.full_name, r.date_of_birth,\n"
                + "r.gender, r.phone_number, r.address, r.image_url \n"
                + "from staff r where r.username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Receptionist(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11));
            }
        } catch (Exception e) {
        }
        return null;
    }

    //lấy danh sách bệnh nhân
    public List<Patient> getListPatient() {
        List<Patient> list = new ArrayList<>();
        String sql = "select p.id, p.full_name, p.gender, p.identity_number, p.insurance_number, p.phone_number, p.address, p.image_url from patient p";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Patient p = new Patient(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8));
                list.add(p);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public int getTotalPatients() {
        String sql = "select count(*) from patient";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    // hàm sắp xếp bệnh nhân theo id hoặc name
    public List<Patient> getListPatientSorted(String field, String order) {
        List<Patient> list = new ArrayList<>();
        String column;
        switch (field.toLowerCase()) {
            case "name":
                column = "full_name";
                break;
            case "identity":
                column = "identity_number";
                break;
            default:
                column = "id";
                break;
        }
        String sortOrder = order.equalsIgnoreCase("Desc") ? "DESC" : "ASC";
        String sql = "SELECT p.id, p.full_name, p.gender, p.identity_number, "
                + "p.insurance_number, p.phone_number, p.address, p.image_url "
                + "FROM patient p ORDER BY " + column + " " + sortOrder;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Patient(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //hàm tìm kiếm bệnh nhân theo cccd
    public Patient getAPatientByIdentity(String identity) {
        String sql = "select p.id, p.full_name, \n"
                + " p.gender, p.date_of_birth ,p.identity_number, \n"
                + "p.insurance_number, p.phone_number, p.address, p.image_url\n"
                + "from patient p where p.identity_number = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, identity);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Patient(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9));
            }
        } catch (SQLException e) {
        }
        return null;
    }

    //hàm cập nhật thông tin cá nhân của lễ tân
    public void update(Receptionist r, int id) {
        String sql = "UPDATE [dbo].[staff]\n"
                + "SET [full_name] = ?,\n"
                + "    [gender] = ?,\n"
                + "    [phone_number] = ?,\n"
                + "    [address] = ?,\n"
                + "    [image_url] = ?\n"
                + "WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, r.getFullname());
            st.setString(2, r.getGender());
            st.setString(3, r.getPhone());
            st.setString(4, r.getAddress());
            st.setString(5, r.getImg());
            st.setInt(6, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //hàm thay đổi mật khẩu của chính receptionist đó
    public void updatePass(String username, String newPass) {
        String sql = "UPDATE accounts SET password = ? WHERE username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newPass);
            st.setString(2, username);
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    //hàm checkAccount xem đã tồn tại trong database chưa 
    public boolean checkAccount(String username, String password) {
        String sql = "SELECT * FROM accounts WHERE username = ? AND password = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //hàm lấy tổng số lịch hẹn
    public int getTotalAppoinment() {
        String sql = "select count(*) from appointment_schedule";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }
}
