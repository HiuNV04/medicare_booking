/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.DoctorDAO;
import dal.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Doctor;
import model.Staff;

/**
 *
 * @author ADMIN
 */
@WebServlet(urlPatterns = {"/manageDoctor"})
public class ManageDoctorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String search = request.getParameter("search");
        String role = request.getParameter("role");
        String status = request.getParameter("status");
        String pageRaw = request.getParameter("page");
        int page = 1;
        int pageSize = 2;
        if (pageRaw != null) {
            try {
                page = Integer.parseInt(pageRaw);
            } catch (NumberFormatException e) {
                page = 1;
            }
        }
        DoctorDAO dao = new DoctorDAO();
        int totalDoctor = dao.countDoctor(search, role, status);
        int totalPage = (int) Math.ceil((double) totalDoctor / pageSize);

        List<Doctor> doctors = dao.getDoctorsByPage(page, pageSize, search, role, status);
      
        request.setAttribute("doctors", doctors);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("search", search);
        request.setAttribute("role", role);
        request.setAttribute("status", status);
        request.getRequestDispatcher("/admin/manageDoctor.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
