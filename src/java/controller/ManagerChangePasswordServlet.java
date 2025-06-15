/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.UserDAO;
import model.User;
import util.PasswordUtil;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;

@WebServlet(name="ManagerChangePasswordServlet", urlPatterns={"/manager/change-password"})
public class ManagerChangePasswordServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
        request.getRequestDispatcher("/auth/manager_change_password.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String oldPass = request.getParameter("oldPassword");
        String newPass = request.getParameter("newPassword");
        String confirmPass = request.getParameter("confirmPassword");

        if (!PasswordUtil.hashSHA256(oldPass).equals(user.getPassword())) {
            request.setAttribute("error", "Mật khẩu cũ không đúng");
            request.getRequestDispatcher("/auth/manager_change_password.jsp").forward(request, response);
            return;
        }
        if (!newPass.equals(confirmPass)) {
            request.setAttribute("error", "Mật khẩu mới không khớp");
            request.getRequestDispatcher("/auth/manager_change_password.jsp").forward(request, response);
            return;
        }
        String newHash = PasswordUtil.hashSHA256(newPass);
        UserDAO dao = new UserDAO();
        // TODO: Thêm hàm updatePassword(userId, newHash) trong UserDAO
        // dao.updatePassword(user.getId(), newHash);
        user.setPassword(newHash);
        session.setAttribute("user", user);
        request.setAttribute("success", "Đổi mật khẩu thành công");
        request.getRequestDispatcher("/auth/manager_change_password.jsp").forward(request, response);
    }
    
    
    protected void processRequest(HttpServletRequest requestuest, HttpServletResponse responseonse)
    throws ServletException, IOException {
        responseonse.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = responseonse.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ManagerChangePasswordServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManagerChangePasswordServlet at " + requestuest.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 
} 