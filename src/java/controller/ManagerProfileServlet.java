/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.StaffDAO;
import model.Staff;
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

        StaffDAO staffDAO = new StaffDAO();
        Staff manager = staffDAO.getManagerById(2);
        request.setAttribute("user", manager);
        request.getSession().setAttribute("user", manager);
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

        StaffDAO staffDAO = new StaffDAO();
        Staff manager = staffDAO.getManagerById(userId);
        manager.setFullName(fullName);
        manager.setAddress(address);
        manager.setPhoneNumber(phone);
        manager.setGender(gender);
        manager.setImageUrl(imageUrl);
        manager.setDateOfBirth(dob);
        staffDAO.updateManager(manager);
        request.getSession().setAttribute("user", manager);
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