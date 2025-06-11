/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import model.DoctorLevel;

/**
 *
 * @author ADMIN
 */
public class DoctorLevelDAO extends MyDAO {

    public List<DoctorLevel> getAll() {
        List<DoctorLevel> list = new ArrayList<>();
        xSql = "SELECT id, name, examination_fee FROM doctor_level";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DoctorLevel d = new DoctorLevel(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getFloat("examination_fee")
                );
                list.add(d);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
