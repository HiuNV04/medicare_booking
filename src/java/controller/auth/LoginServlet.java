/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import controller.send.EmailSender;
import controller.send.PasswordEncryptor;
import controller.send.RandomPassword;
import dal.AuthDAO;
import dal.DoctorDAO;
import dal.PatientDAO;
import dal.StaffDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.Doctor;
import model.Patient;
import model.Staff;

/**
 *
 * @author ADDMIN
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    AuthDAO dao = new AuthDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String encryptedPassword = PasswordEncryptor.encrypt(password);

        Object account = dao.login(username, encryptedPassword);

        if (account == null) {
            account = dao.login(username, password);
        }
        if (account != null) {
            // Xoá session cũ 
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            session = request.getSession(true);

            if (account instanceof Doctor) {
                Doctor d = (Doctor) account;
                Doctor fullDoctor = new DoctorDAO().getDoctorById1(d.getId());

                if (fullDoctor == null) {
                    request.setAttribute("error", "Không tìm thấy thông tin bác sĩ trong hệ thống.");
                    request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
                    return;
                }
                session.invalidate();
                session = request.getSession(true);
                session.setAttribute("doctor", fullDoctor);
                response.sendRedirect(request.getContextPath() + "/DoctorProfileServlet");

                return;

            } else if (account instanceof Patient) {
                session.setAttribute("patient", account);
                response.sendRedirect("patient/home_patient.jsp");
                return;

            } else if (account instanceof Staff) {
                Staff s = (Staff) account;
                session.setAttribute("staff", s);
                String role = s.getRole();

                if (role == null) {
                    request.setAttribute("error", "Tài khoản không có vai trò xác định.");
                    request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
                    return;
                }

                switch (role.trim().toLowerCase()) {
                    case "admin":
                        session.setAttribute("admin", s);
                        response.sendRedirect("admin/dashboard.jsp");
                        break;
                    case "manager":
                        session.setAttribute("manager", s);
                        response.sendRedirect("manager/home.jsp");
                        break;
                    case "receptionist":
                        session.setAttribute("receptionist", s);
                        response.sendRedirect("receptionist/home.jsp");
                        break;
                    default:
                        request.setAttribute("error", "Vai trò không hợp lệ: " + role);
                        request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
                        break;
                }
                return;
            }

        } else {
            // B5: login thất bại
            request.setAttribute("error", "Sai tài khoản hoặc mật khẩu");
            request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
        }
    }
}
