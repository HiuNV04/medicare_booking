/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.DoctorLevelDAO;
import dal.SpecializationDAO;
import dal.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.DoctorLevel;
import model.Specialization;
import model.Staff;

/**
 *
 * @author ADMIN
 */
@WebServlet(urlPatterns = {"/viewStaffDetail"})
public class ViewStaffDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StaffDAO dao = new StaffDAO();
        String idString = request.getParameter("id").trim();
        int id = Integer.parseInt(idString);
        Staff staff = dao.viewStaffDetail(id);
        request.setAttribute("staff", staff);
        request.getRequestDispatcher("/admin/viewStaffDetail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
