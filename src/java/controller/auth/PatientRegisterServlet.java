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
import model.Patient;

/**
 *
 * @author ADDMIN
 */
@WebServlet(name = "PatientRegisterServlet", urlPatterns = {"/PatientRegisterServlet"})
public class PatientRegisterServlet extends HttpServlet {

    private AuthDAO dao = new AuthDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/auth/register.jsp").forward(request, response); // điều hướng luôn về trang đki tài khoản bệnh nhân
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String identity = request.getParameter("identity");
        String insurance = request.getParameter("insurance");

        String encryptedPassword = PasswordEncryptor.encrypt(password);

        Patient p = new Patient();
        p.setUsername(username);
        p.setPassword(encryptedPassword);
        p.setEmail(email);
        p.setFullName(fullName);
        p.setGender(gender);
        p.setPhoneNumber(phone);
        p.setDateOfBirth(dob == null || dob.isEmpty() ? null : java.sql.Date.valueOf(dob));
        p.setAddress(address);
        p.setIdentityNumber(identity);
        p.setInsuranceNumber(insurance);
        p.setRole("patient".trim().toLowerCase());
        p.setStatus(true);

        if (dao.isUsernameOrEmailTaken(username, email)) {
            request.setAttribute("error", "Username hoặc Email đã tồn tại!");
            request.getRequestDispatcher("/auth/register.jsp").forward(request, response);
        } else {
//            dao.insertPatient(p);
            dao.insertAccountPatient(p);
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect("auth/login.jsp");
        }
    }

}
