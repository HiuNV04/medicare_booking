/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author ptson
 */
public class PatientDAO extends DBContext {

    public boolean checkAccount(String username, String password) {
        String sql = "SELECT * FROM accounts WHERE username = ? AND password = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password); 
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updatePass(Account a, String newPass) {
        String sql = "UPDATE accounts SET password = ?, password_hash = ? WHERE username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newPass);
            st.setString(2, util.Exception.hashPassword(newPass));
            st.setString(3, a.getUsername());
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public Patient getAPatient(Account a) {
        String sql = "select p.id, a.username, a.email, a.password, p.full_name,\n"
                + "p.date_of_birth, p.gender, p.identity_number, \n"
                + "p.insurance_number, p.phone_number, p.address, p.job, p.imageURL\n"
                + "from accounts a join patients p on a.id = p.account_id\n"
                + "where a.username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, a.getUsername());
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
                        rs.getString(12),
                        rs.getString(13));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void updatePatient(Patient p, int id) throws ParseException {
        String sql = "UPDATE [dbo].[patients]\n"
                + "SET [full_name] = ?,\n"
                + "    [date_of_birth] = ?,\n"
                + "    [gender] = ?,\n"
                + "    [identity_number] = ?,\n"
                + "    [insurance_number] = ?,\n"
                + "    [phone_number] = ?,\n"
                + "    [address] = ?,\n"
                + "    [job] = ?,\n"
                + "    [imageURL] = ?\n"
                + "WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, p.getFullname());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = sdf.parse(p.getDob());
            st.setDate(2, new java.sql.Date(parsedDate.getTime()));
            st.setString(3, p.getGender());
            st.setString(4, p.getIdentity());
            st.setString(5, p.getInsurance());
            st.setString(6, p.getPhone());
            st.setString(7, p.getAddress());
            st.setString(8, p.getJob());
            st.setString(9, p.getImg());
            st.setInt(10, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PatientDAO pdao = new PatientDAO();
        Patient p = pdao.getAPatient(new Account("patient1"));
        System.out.println(p.toString());
    }
}
