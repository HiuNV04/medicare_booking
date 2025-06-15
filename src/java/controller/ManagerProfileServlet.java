/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.UserDAO;
import model.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet(name="ManagerProfileServlet", urlPatterns={"/manager/profile"})
public class ManagerProfileServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        Account acc = (Account) session.getAttribute("account");
//        if (acc == null || acc.getRoleId() != 2) {
//            response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
//            return;
//        }

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserById(2);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/auth/manager_profile.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String phone = request.getParameter("phoneNumber");
        String gender = request.getParameter("gender");
        String imageUrl = request.getParameter("imageUrl");
        java.sql.Date dob = java.sql.Date.valueOf(request.getParameter("dateOfBirth"));

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserById(userId);
        user.setFullName(fullName);
        user.setAddress(address);
        user.setPhoneNumber(phone);
        user.setGender(gender);
        user.setImageUrl(imageUrl);
        user.setDateOfBirth(dob);
        // TODO: Thêm hàm updateUser(user) trong UserDAO để cập nhật DB
        // userDAO.updateUser(user);
        response.sendRedirect("home");
    }
    
    
    protected void processRequest(HttpServletRequest requestuest, HttpServletResponse responseonse)
    throws ServletException, IOException {
        responseonse.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = responseonse.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ManagerProfileServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManagerProfileServlet at " + requestuest.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 
} 