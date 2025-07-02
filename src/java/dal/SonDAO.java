/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.text.SimpleDateFormat;
import model.Patient;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import model.Appointment;
import model.Doctor1;
import model.Patient1;
import model.Receptionist1;

/**
 *
 * @author ADMIN
 */
public class SonDAO extends DBContext {
    //PatientDAO
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
    public void insertPatient(Patient1 p) {
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

    //đếm số lượng patient trong db
    public int getTotalPatient() {
        String sql = "select count(*) from patient";
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

    public Patient1 getAPatientByEmail(String email) {
        String sql = "select p.id, p.email, p.username, p.password, p.full_name, \n"
                + "p.gender, p.date_of_birth,  p.identity_number, p.insurance_number, p.phone_number,\n"
                + "p.address, p.image_url from patient p where p.email = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Patient1(rs.getInt(1),
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
    public Patient1 getAPatientByUsername(String username) {
        String sql = "select p.id, p.email, p.username, p.password, p.full_name, \n"
                + "p.gender, p.date_of_birth,  p.identity_number, p.insurance_number, p.phone_number,\n"
                + "p.address, p.image_url from patient p where p.username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Patient1(rs.getInt(1),
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
    public void updatePatient(Patient1 p, int id) throws ParseException {
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

    //hàm lễ tân cập nhật thông tin của Patient
    public void reUpdatePatient(Patient1 p, int id) throws ParseException {
        String sql = "UPDATE [dbo].[patient]\n"
                + "SET [full_name] = ?,\n" // SỬA Ở ĐÂY
                + "    [date_of_birth] = ?,\n"
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
            st.setString(1, p.getFullname());
            st.setDate(2, new java.sql.Date(parsedDate.getTime()));
            st.setString(3, p.getGender());
            st.setString(4, p.getIdentity());
            st.setString(5, p.getInsurance());
            st.setString(6, p.getPhone());
            st.setString(7, p.getAddress());
            st.setString(8, p.getImg());
            st.setInt(9, id);
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

    //hàm này lấy bệnh nhân theo id(Update bệnh nhân của Lễ tân)
    public Patient1 getAPatientById(int id) {
        String sql = "select p.id, p.full_name,p.gender,\n"
                + "p.date_of_birth,  p.identity_number, p.insurance_number,\n"
                + "p.phone_number,p.address, p.image_url from patient p where p.id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Patient1(rs.getInt(1),
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
            e.printStackTrace();
        }
        return null;
    }

    //hàm xóa 1 bệnh nhân
    public void reDeletePatient(int id) {
        String sql = "Delete from patient where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Patient1> pagingPatient(int index) {
        List<Patient1> list = new ArrayList();
        String sql = "select p.id, p.full_name,p.gender,\n"
                + "p.date_of_birth,  p.identity_number, p.insurance_number,\n"
                + "p.phone_number,p.address, p.image_url from patient p\n"
                + "ORDER BY p.id OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY;";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            st.setInt(1, (index - 1) * 8);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Patient1 p = new Patient1(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9));
                list.add(p);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Patient1> getSortedPatientsWithPaging(String field, String order, int index) {
        List<Patient1> list = new ArrayList<>();
        String sql = "SELECT p.id, p.full_name,p.gender,\n"
                + "p.date_of_birth,  p.identity_number, p.insurance_number,\n"
                + "p.phone_number,p.address, p.image_url FROM patient p \n"
                + "ORDER BY " + field + " " + order + " OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 8);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Patient1 p = new Patient1(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //hàm thêm 1 cuộc hẹn (CHƯA XONG VẪN ĐANG LỖI)
    public List<Doctor1> getListDoctor() {
        List<Doctor1> list = new ArrayList();
        String sql = "select d.id, d.full_name, s.name, s.description, r.name\n"
                + "                from doctor d join specialization s on d.specialization_id = s.id\n"
                + "                  join room r on d.id = r.doctor_id";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Doctor1 d = new Doctor1(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
                list.add(d);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static void main(String[] args) {
        SonDAO dao = new SonDAO();
        List<Doctor1> lst = dao.getListDoctor();
        for (Doctor1 z : lst) {
            System.out.println(z);
        }
    }

    //hàm lấy danh sách cuộc hẹn
    public List<Appointment> getListAppointment(int id) {
        List<Appointment> list = new ArrayList();
        String sql = "select a.id ,d.full_name, s.name, s.description ,dss.date,\n"
                + "CONVERT(varchar(8), dss.slot_start_time, 108) as startTime, CONVERT(varchar(8), dss.slot_end_time, 108) as endTime,\n"
                + "r.name, a.confirmation_status from \n"
                + "appointment_schedule a join doctor d on a.doctor_id = d.id\n"
                + "join room r on a.room_id = r.id \n"
                + "join doctor_shift_slot dss on a.doctor_shift_id = dss.id\n"
                + "join specialization s on d.specialization_id = s.id\n"
                + "where a.patient_id = ?";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Appointment a = new Appointment(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9));
                list.add(a);
            }
        } catch (Exception e) {
        }

        return list;
    }
    //lấy danh sách bệnh nhân

    public List<Patient1> getListPatient() {
        List<Patient1> list = new ArrayList<>();
        String sql = "select p.id, p.full_name, p.gender, p.identity_number, p.insurance_number, p.phone_number, p.address, p.image_url from patient p";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Patient1 p = new Patient1(rs.getInt(1),
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

    public Patient1 getAPatientByIdentity(String identity) {
        String sql = "select p.id, p.full_name, \n"
                + " p.gender, p.date_of_birth ,p.identity_number, \n"
                + "p.insurance_number, p.phone_number, p.address, p.image_url\n"
                + "from patient p where p.identity_number = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, identity);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Patient1(rs.getInt(1),
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
    //lấy thông tin 1 lễ tân

    public Receptionist1 getAReceptionistByEmail(String email) {
        String sql = "select r.id, r.role, r.email, r.username, r.password, r.full_name, r.date_of_birth,\n"
                + "r.gender, r.phone_number, r.address, r.image_url \n"
                + "from staff r where r.email = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Receptionist1(rs.getInt(1),
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
    //hàm cập nhật thông tin cá nhân của lễ tân

    public void update(Receptionist1 r, int id) {
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
}
