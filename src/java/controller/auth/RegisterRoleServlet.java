/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import controller.send.EmailSender;
import controller.send.PasswordEncryptor;

import dal.AuthDAO;
import dal.DoctorDAO;
import dal.StaffDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.Doctor;
import model.Staff;


@WebServlet(name = "RegisterRoleServlet", urlPatterns = {"/RegisterRoleServlet"})
public class RegisterRoleServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AuthDAO dao = new AuthDAO();

        request.setCharacterEncoding("UTF-8");

        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String role = request.getParameter("role"); // doctor, manager, receptionist

        if (dao.isUsernameOrEmailTaken(username, email)) {
            request.setAttribute("error", "Tên đăng nhập hoặc Email đã tồn tại!");
            request.setAttribute("role", role);
            request.getRequestDispatcher("/admin/register_role.jsp?role=" + role).forward(request, response);
            return;
        }

        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Mật khẩu không khớp.");
            request.setAttribute("role", role);
            request.getRequestDispatcher("/admin/register_role.jsp?role=" + role).forward(request, response);
            return;
        }

        String encryptedPassword = PasswordEncryptor.encrypt(password);

        // Gửi mail sau khi tạo
        String subject = "Tài khoản nhân viên đã được tạo";
        String message = "Xin chào " + fullName + ",\n\n"
                + "Tài khoản của bạn trên hệ thống đã được tạo.\n"
                + "Tên đăng nhập: " + username + "\n"
                + "Mật khẩu tạm thời: " + password + "\n\n"
                + "Vui lòng đăng nhập và đổi mật khẩu ngay khi có thể.\n\n"
                + "Trân trọng,\nHệ thống Medicare";

        try {
            if ("doctor".equals(role)) {
                Doctor doctor = new Doctor();
                doctor.setFullName(fullName);
                doctor.setEmail(email);
                doctor.setUsername(username);
                doctor.setPassword(encryptedPassword);
                doctor.setRole("doctor");
                doctor.setStatus(true);

                new DoctorDAO().insert(doctor);
            } else if ("manager".equals(role) || "receptionist".equals(role)) {
                Staff staff = new Staff();
                staff.setFullName(fullName);
                staff.setEmail(email);
                staff.setUsername(username);
                staff.setPassword(encryptedPassword);
                staff.setRole(role.toLowerCase());
                staff.setStatus(true);

                new StaffDAO().insert(staff);
            } else {
                request.setAttribute("error", "Vai trò không hợp lệ.");
                request.setAttribute("role", role);
                request.getRequestDispatcher("/admin/register_role.jsp?role=" + role).forward(request, response);
                return;
            }

            EmailSender.sendEmail(email, subject, message);
            request.setAttribute("success", "Tạo tài khoản thành công! Đã gửi thông tin qua email.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi hệ thống hoặc gửi email thất bại.");
        }

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        request.setAttribute("role", role);
        request.getRequestDispatcher("/admin/register_role.jsp?role=" + role).forward(request, response);
    }
}
