/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Account;
import model.Receptionist;
import java.sql.*;
import java.util.*;
import model.Patient;

/**
 *
 * @author ptson
 */
public class ReceptionistDAO extends DBContext {

    public Receptionist getAReceptionist(Account a) {
        String sql = "select s.id, a.username, a.email, a.password,\n"
                + "s.full_name, s.gender, s.date_of_birth,\n"
                + "s.phone_number, s.address, s.imageURL\n"
                + "from staffs s join accounts a on s.account_id = a.id where username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, a.getUsername());
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
                        rs.getString(10));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Patient> getListPatient() {
        List<Patient> list = new ArrayList<>();
        String sql = "select p.id, p.full_name, p.gender, p.identity_number, p.insurance_number, p.phone_number, p.address, p.imageURL from patients p";
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
        String sql = "select count(*) from patients";
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

    public static void main(String[] args) {
        ReceptionistDAO rdao = new ReceptionistDAO();
        List<Patient> list = rdao.getListPatient();
        for (Patient patient : list) {
            System.out.println(patient.toString1());
        }
    }

}
