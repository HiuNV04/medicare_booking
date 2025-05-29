/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.PatientDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author ptson
 */
@WebServlet(name = "ChangePassPatient", urlPatterns = {"/changePassPatient"})
public class ChangePassPatient extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/auth/patientChangePass.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("user");
        String oldPass = request.getParameter("oPass");
        String newPass = request.getParameter("nPass");
        String cpw = request.getParameter("cpw");
        HttpSession session = request.getSession();

        PatientDAO pdao = new PatientDAO();

        // Kiểm tra mật khẩu cũ có đúng không
        boolean valid = pdao.checkAccount(username, oldPass);

        if (!valid) {
            session.setAttribute("msg", "Old password is incorrect!");
            request.getRequestDispatcher("/auth/patientChangePass.jsp").forward(request, response);
        } else if (!newPass.equals(cpw)) {
            session.setAttribute("msg", "New password and confirm password do not match!");
            request.getRequestDispatcher("/auth/patientChangePass.jsp").forward(request, response);
        } else {
            Account a = new Account(username);
            pdao.updatePass(a, newPass);
            session.removeAttribute("msg");
            response.sendRedirect("showPatient");
        }
    }
}
