/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import controller.send.EmailSender;
import controller.send.PasswordEncryptor;
import controller.send.RandomPassword;
import dal.DoctorDAO;
import dal.PatientDAO;
import dal.StaffDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author ADDMIN
 */
@WebServlet(name = "ForgetPasswordServlet", urlPatterns = {"/ForgetPasswordServlet"})
public class ForgetPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");

        DoctorDAO doctorDAO = new DoctorDAO();
        StaffDAO staffDAO = new StaffDAO();
        PatientDAO patientDAO = new PatientDAO();

        String username = null;
        String role = null;

        if (doctorDAO.findByEmail(email) != null) {
            username = doctorDAO.findByEmail(email).getUsername();
            role = "doctor";
        } else if (staffDAO.findByEmail(email) != null) {
            username = staffDAO.findByEmail(email).getUsername();
            role = "staff";
        } else if (patientDAO.findByEmail(email) != null) {
            username = patientDAO.findByEmail(email).getUsername();
            role = "patient";
        }

        if (username == null) {
            request.setAttribute("error", "Không tìm thấy tài khoản với email này.");
            request.getRequestDispatcher("/auth/forget.jsp").forward(request, response);
            return;
        }

        // 2. Sinh mật khẩu mới & mã hóa
        String newPassword = RandomPassword.generate();
        String hashed = PasswordEncryptor.encrypt(newPassword);

        // 3. Cập nhật mật khẩu mới
        boolean updated = false;
        switch (role) {
            case "doctor":
                updated = doctorDAO.updatePasswordByEmail(email, hashed);
                break;
            case "staff":
                updated = staffDAO.updatePasswordByEmail(email, hashed);
                break;
            case "patient":
                updated = patientDAO.updatePasswordByEmail(email, hashed);
                break;
            default:
                updated = false;
        }

        if (!updated) {
            request.setAttribute("error", "Không thể cập nhật mật khẩu. Vui lòng thử lại sau.");
            request.getRequestDispatcher("/auth/forget.jsp").forward(request, response);
            return;
        }

        // 4. Gửi mail
        String subject = "Khôi phục mật khẩu - Medicare";
        String message = "Xin chào " + username + ",<br><br>"
                + "Hệ thống đã tạo lại mật khẩu cho tài khoản của bạn.<br>"
                + "<b>Mật khẩu mới:</b> <span style='color:blue'>" + newPassword + "</span><br><br>"
                + "Vui lòng đăng nhập và đổi mật khẩu càng sớm càng tốt.<br><br>"
                + "Trân trọng,<br>MediCare Team";

        try {
            EmailSender.sendEmail(email, subject, message);
            request.setAttribute("success", "Mật khẩu mới đã được gửi đến email của bạn.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi khi gửi email. Vui lòng thử lại sau.");
        }

        request.getRequestDispatcher("/auth/forget.jsp").forward(request, response);
    }
}
