/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import util.PasswordUtil;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import dal.StaffDAO;
import model.Staff;

@WebServlet(name="ManagerChangePasswordServlet", urlPatterns={"/manager/change-password"})
public class ManagerChangePasswordServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            dal.StaffDAO staffDAO = new dal.StaffDAO();
            model.Staff manager = staffDAO.getManagerById(2); // Hardcode id=2
            request.getSession().setAttribute("user", manager);
        }
        request.getRequestDispatcher("/auth/manager_change_password.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
        jakarta.servlet.http.HttpSession session = request.getSession();
        model.Staff manager = (model.Staff) session.getAttribute("user");
        String oldPass = request.getParameter("oldPassword");
        String newPass = request.getParameter("newPassword");
        String confirmPass = request.getParameter("confirmPassword");

        if (!util.PasswordUtil.hashSHA256(oldPass).equals(manager.getPassword())) {
            request.setAttribute("error", "Mật khẩu cũ không đúng");
            request.getRequestDispatcher("/auth/manager_change_password.jsp").forward(request, response);
            return;
        }
        if (!newPass.equals(confirmPass)) {
            request.setAttribute("error", "Mật khẩu mới không khớp");
            request.getRequestDispatcher("/auth/manager_change_password.jsp").forward(request, response);
            return;
        }
        String newHash = util.PasswordUtil.hashSHA256(newPass);
        manager.setPassword(newHash);
        dal.StaffDAO staffDAO = new dal.StaffDAO();
        staffDAO.updateManagerPassword(manager.getId(), newHash); // Cập nhật mật khẩu vào staff
        session.setAttribute("user", manager);
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