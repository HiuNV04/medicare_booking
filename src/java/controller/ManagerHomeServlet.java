/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.StaffDAO;
import model.Account;
import model.Staff;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet(name="ManagerHomeServlet", urlPatterns={"/manager/home"})
public class ManagerHomeServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        Account acc = (Account) session.getAttribute("account");
//        if (acc == null || acc.getRoleId() != 2) {
//            response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
//            return;
//        }

        Account acc = new Account();
        acc.setId(2); // id của manager mẫu trong DB
        acc.setEmail("manager1@medicare.com");
        acc.setRoleId(2);
        
        StaffDAO staffDAO = new StaffDAO();
        List<Staff> staffList = staffDAO.getAllStaffExceptAdminAndManager();
        request.setAttribute("staffList", staffList);
        request.setAttribute("staff", staffDAO.getStaffByAccountId(acc.getId()));
        request.setAttribute("account", acc);
        request.getRequestDispatcher("/auth/manager_home.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ManagerHomeServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManagerHomeServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 
}   