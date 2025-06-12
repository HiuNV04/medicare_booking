/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import model.UserAccount;

/**
 *
 * @author ADMIN
 */
public class UserAccountDAO extends MyDAO {

    public List<UserAccount> getAllAccounts() {
        List<UserAccount> list = new ArrayList<>();
        try {
            xSql
                    = "SELECT image_url, email, username, password, role, full_name, date_of_birth, gender, address, phone_number, status, NULL AS doctor_level_id, NULL AS specialization_id, NULL AS identity_number, NULL AS insurance_number "
                    + "FROM users "
                    + "UNION ALL "
                    + "SELECT image_url, email, username, password, role, full_name, date_of_birth, gender, address, phone_number, status, doctor_level_id, specialization_id, NULL AS identity_number, NULL AS insurance_number "
                    + "FROM doctor "
                    + "UNION ALL "
                    + "SELECT image_url, email, username, password, role, full_name, date_of_birth, gender, address, phone_number, status, NULL AS doctor_level_id, NULL AS specialization_id, identity_number, insurance_number "
                    + "FROM patient";
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                UserAccount ua = new UserAccount();
                ua.setImageUrl(rs.getString("image_url"));
                ua.setEmail(rs.getString("email"));
                ua.setUsername(rs.getString("username"));
                ua.setPassword(rs.getString("password"));
                ua.setRole(rs.getString("role"));
                ua.setFullName(rs.getString("full_name"));
                ua.setDateOfBirth(rs.getDate("date_of_birth"));
                ua.setGender(rs.getString("gender"));
                ua.setAddress(rs.getString("address"));
                ua.setPhoneNumber(rs.getString("phone_number"));
                ua.setStatus(rs.getBoolean("status"));
                ua.setDoctorLevelId((Integer) rs.getObject("doctor_level_id"));
                ua.setSpecializationId((Integer) rs.getObject("specialization_id"));
                ua.setIdentityNumber(rs.getString("identity_number"));
                ua.setInsuranceNumber(rs.getString("insurance_number"));
                list.add(ua);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public UserAccount viewDetailAccount(String username, String role) {
        try {
            xSql = " SELECT * from (select id, image_url, email, username, password, role, full_name, date_of_birth, gender, address, phone_number, status, NULL AS doctor_level_id, NULL AS specialization_id, NULL AS identity_number, NULL AS insurance_number \n"
                    + "                FROM users \n"
                    + "                UNION ALL \n"
                    + "                SELECT id, image_url, email, username, password, role, full_name, date_of_birth, gender, address, phone_number, status, doctor_level_id, specialization_id, NULL AS identity_number, NULL AS insurance_number \n"
                    + "                FROM doctor \n"
                    + "                UNION ALL \n"
                    + "                SELECT id, image_url, email, username, password, role, full_name, date_of_birth, gender, address, phone_number, status, NULL AS doctor_level_id, NULL AS specialization_id, identity_number, insurance_number \n"
                    + "                FROM patient) as all_accounts\n"
                    + "\n"
                    + "where username = ? and role= ?";

            ps = con.prepareStatement(xSql);
            ps.setString(1, username);
            ps.setString(2, role);
            rs = ps.executeQuery();
            while (rs.next()) {
                UserAccount ua = new UserAccount();
                ua.setImageUrl(rs.getString("image_url"));
                ua.setEmail(rs.getString("email"));
                ua.setUsername(rs.getString("username"));
                ua.setPassword(rs.getString("password"));
                ua.setRole(rs.getString("role"));
                ua.setFullName(rs.getString("full_name"));
                ua.setDateOfBirth(rs.getDate("date_of_birth"));
                ua.setGender(rs.getString("gender"));
                ua.setAddress(rs.getString("address"));
                ua.setPhoneNumber(rs.getString("phone_number"));
                ua.setStatus(rs.getBoolean("status"));
                ua.setDoctorLevelId((Integer) rs.getObject("doctor_level_id"));
                ua.setSpecializationId((Integer) rs.getObject("specialization_id"));
                ua.setIdentityNumber(rs.getString("identity_number"));
                ua.setInsuranceNumber(rs.getString("insurance_number"));
                return ua;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        UserAccountDAO d = new UserAccountDAO();
        UserAccount x = d.viewDetailAccount("doc2", "Doctor");
        System.out.println(x);
    }

}
