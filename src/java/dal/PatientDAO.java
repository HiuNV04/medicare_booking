package dal;

import model.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PatientDAO extends DBContext {

    //cật nhật lại mật khẩu cá nhân 
    public void updatePass(String username, String newPass) {
        String sql = "UPDATE patient SET password = ? WHERE username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newPass);
            st.setString(2, username);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    //thêm mới 1 bệnh nhân
    public void insertPatient(Patient p) {
        String sql = "Insert into patient (role, full_name, date_of_birth, gender, identity_number, insurance_number, phone_number, address) values\n"
                + "(? ,?, ? , ? , ? ,?, ? ,?)";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = sdf.parse(p.getDob());
            st.setString(1, p.getRole());
            st.setString(2, p.getFullname());
            st.setDate(3, new java.sql.Date(parsedDate.getTime()));
            st.setString(4, p.getGender());
            st.setString(5, p.getIdentity());
            st.setString(6, p.getInsurance());
            st.setString(7, p.getPhone());
            st.setString(8, p.getAddress());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }

    public Patient getAPatientByEmail(String email) {
        String sql = "select p.id, p.email, p.username, p.password, p.full_name, \n"
                + "p.gender, p.date_of_birth,  p.identity_number, p.insurance_number, p.phone_number,\n"
                + "p.address, p.image_url from patient p where p.email = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
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
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12));
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return null;
    }
    
    //hàm lấy 1 bệnh nhân bằng username
    public Patient getAPatientByUsername(String username) {
        String sql = "select p.id, p.email, p.username, p.password, p.full_name, \n"
                + "p.gender, p.date_of_birth,  p.identity_number, p.insurance_number, p.phone_number,\n"
                + "p.address, p.image_url from patient p where p.username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
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
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12));
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return null;
    }
    
    //hàm cập nhật thông tin của chính mình
    public void updatePatient(Patient p, int id) throws ParseException {
        String sql = "UPDATE [dbo].[patient]\n"
                + "SET  [date_of_birth] = ?,\n"
                + "    [gender] = ?,\n"
                + "    [identity_number] = ?,\n"
                + "    [insurance_number] = ?,\n"
                + "    [phone_number] = ?,\n"
                + "    [address] = ?,\n"
                + "    [image_url] = ?\n"
                + "WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = sdf.parse(p.getDob());
            st.setDate(1, new java.sql.Date(parsedDate.getTime()));
            st.setString(2, p.getGender());
            st.setString(3, p.getIdentity());
            st.setString(4, p.getInsurance());
            st.setString(5, p.getPhone());
            st.setString(6, p.getAddress());
            st.setString(7, p.getImg());
            st.setInt(8, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //hàm lấy id bệnh nhân bằng cccd
    public int getIdFromIdentity(String identity) {
        String sql = "SELECT id FROM patient WHERE identity_number = ?";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            st.setString(1, identity);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
        }
        return -1;
    }
    
    //hàm lấy tên bệnh nhân bằng id
    public String getNameFromId(int id) {
        String sql = "SELECT full_name FROM patient WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
        }
        return null;
    }
}
