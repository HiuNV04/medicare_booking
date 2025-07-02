/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dal.MyDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Bill;

/**
 *
 * @author ADDMIN
 */
public class BillDAO extends MyDAO {

    public BillDAO() {
        super();
        if (con == null) {
            System.out.println("❌ DoctorDAO: Không kết nối được tới database");
        } else {
            System.out.println("✅ DoctorDAO: Kết nối database thành công");
        }
    }

    public List<Bill> getAllBills() {
        List<Bill> list = new ArrayList<>();

        xSql = "SELECT "
                + "m.id AS medical_record_id, "
                + "p.full_name AS patient_name, "
                + "m.time, "
                + "m.payment_method, "
                + "m.payment_status, "
                + "(SELECT SUM(total_price) FROM medical_test WHERE medical_record_id = m.id) AS total_amount "
                + "FROM medical_record m "
                + "JOIN appointment_schedule a ON m.appointment_schedule_id = a.id "
                + "JOIN patient p ON a.patient_id = p.id "
                + "ORDER BY m.time ASC";

        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Bill bill = new Bill();
                bill.setId(rs.getInt("medical_record_id"));
                bill.setPatientName(rs.getString("patient_name"));
                bill.setAppointmentTime(rs.getTimestamp("time"));
                bill.setPaymentMethod(rs.getString("payment_method"));
                bill.setPaymentStatus(rs.getString("payment_status"));

                double total = rs.getDouble("total_amount");
                if (rs.wasNull()) {
                    total = 0.0;
                }
                bill.setAmount(total);

                list.add(bill);
            }
        } catch (Exception e) {
            System.out.println("❌ Lỗi getAllBills: " + e.getMessage());
        }

        return list;
    }

    public List<Bill> getBillsPage(int offset, int limit) {
        List<Bill> list = new ArrayList<>();

        xSql = "SELECT "
                + "m.id AS medical_record_id, "
                + "p.full_name AS patient_name, "
                + "m.time, "
                + "m.payment_method, "
                + "m.payment_status, "
                + "(SELECT SUM(total_price) FROM medical_test WHERE medical_record_id = m.id) AS total_amount "
                + "FROM medical_record m "
                + "JOIN appointment_schedule a ON m.appointment_schedule_id = a.id "
                + "JOIN patient p ON a.patient_id = p.id "
                + "ORDER BY m.time ASC "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, offset);
            ps.setInt(2, limit);
            rs = ps.executeQuery();

            while (rs.next()) {
                Bill bill = new Bill();
                bill.setId(rs.getInt("medical_record_id"));
                bill.setPatientName(rs.getString("patient_name"));
                bill.setAppointmentTime(rs.getTimestamp("time"));
                bill.setPaymentMethod(rs.getString("payment_method"));
                bill.setPaymentStatus(rs.getString("payment_status"));

                double total = rs.getDouble("total_amount");
                if (rs.wasNull()) {
                    total = 0.0;
                }
                bill.setAmount(total);

                list.add(bill);
            }
        } catch (Exception e) {
            System.out.println("❌ Lỗi getBillsPage: " + e.getMessage());
        }

        return list;
    }

    public List<Bill> getBillDetailById(int billId) {
        List<Bill> list = new ArrayList<>();

        xSql = "SELECT mr.id AS medical_record_id, "
                + "p.full_name AS patient_name, "
                + "mr.time, "
                + "mr.payment_method, "
                + "mr.payment_status, "
                + "s.name AS test_name, "
                + "smt.quantity, "
                + "s.price AS unit_price, "
                + "(s.price * smt.quantity) AS total_test_price "
                + "FROM medical_record mr "
                + "JOIN appointment_schedule a ON mr.appointment_schedule_id = a.id "
                + "JOIN patient p ON a.patient_id = p.id "
                + "JOIN medical_test mt ON mt.medical_record_id = mr.id "
                + "JOIN services_of_medical_test smt ON smt.medical_test_id = mt.id "
                + "JOIN service s ON smt.service_id = s.id "
                + "WHERE mr.id = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, billId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Bill b = new Bill();
                b.setId(rs.getInt("medical_record_id"));
                b.setPatientName(rs.getString("patient_name"));
                b.setAppointmentTime(rs.getTimestamp("time"));
                b.setPaymentMethod(rs.getString("payment_method"));
                b.setPaymentStatus(rs.getString("payment_status"));

                b.setTestName(rs.getString("test_name"));
                b.setQuantity(rs.getInt("quantity"));
                b.setUnitPrice(rs.getDouble("unit_price"));
                b.setTotalTest(rs.getDouble("total_test_price"));

                list.add(b);
            }

            // Tính tổng tiền thủ công nếu cần
            if (!list.isEmpty()) {
                double total = 0;
                for (Bill b : list) {
                    total += b.getTotalTest();
                }
                for (Bill b : list) {
                    b.setAmount(total);
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Lỗi getBillDetailById: " + e.getMessage());
        }

        return list;
    }

    public int countAllBills() {
        xSql = "SELECT COUNT(*) FROM medical_record";

        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("❌ Lỗi countAllBills: " + e.getMessage());
        }

        return 0;
    }

    public static void main(String[] args) {
        BillDAO dao = new BillDAO();
        int billId = 5; // sửa ID tùy hóa đơn có thật
        List<Bill> billDetails = dao.getBillDetailById(billId);

        if (billDetails == null || billDetails.isEmpty()) {
            System.out.println("❌ Không tìm thấy hóa đơn ID: " + billId);
            return;
        }

        Bill info = billDetails.get(0);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy");

        System.out.println("===== CHI TIẾT HÓA ĐƠN =====");
        System.out.println("👤 Bệnh nhân: " + info.getPatientName());
        System.out.println("⏰ Thời gian khám: " + sdf.format(info.getAppointmentTime()));
        System.out.println("💳 Phương thức thanh toán: " + info.getPaymentMethod());
        System.out.println("💰 Trạng thái thanh toán: " + info.getPaymentStatus());
        System.out.println();

        System.out.println("📄 Danh sách xét nghiệm:");
        System.out.printf("%-30s %-10s %-15s %-15s\n", "Tên xét nghiệm", "SL", "Đơn giá", "Thành tiền");

        for (Bill b : billDetails) {
            System.out.printf("%-30s %-10d %-15.0f %-15.0f\n",
                    b.getTestName(), b.getQuantity(), b.getUnitPrice(), b.getTotalTest());

        }

        System.out.println();
        System.out.printf("👉 Tổng tiền: %,.0f ₫\n", info.getAmount());
    }

}
