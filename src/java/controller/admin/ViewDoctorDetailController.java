/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.DoctorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Doctor;
import model.DoctorLevel;
import model.Specialization;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ViewDoctorDetailController", urlPatterns = {"/viewDoctorDetail"})
public class ViewDoctorDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idRaw = request.getParameter("id");
        if (idRaw == null || idRaw.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/manageStaff");
            return;
        }

        try {
            int id = Integer.parseInt(idRaw);
            DoctorDAO dao = new DoctorDAO();
            List<DoctorLevel> doctorLevel = dao.getAllDoctorLevel();
            List<Specialization> specialization = dao.getAllSpecialization();
            Doctor doctor = dao.getDoctorById(id);
            if (doctor == null) {
                request.setAttribute("msgError", "Không tìm thấy Doctor vs ID nay.");
                request.getRequestDispatcher("/admin/viewDoctorDetail.jsp").forward(request, response);
                return;
            }
            request.setAttribute("doctor", doctor);
            request.setAttribute("doctorLevel", doctorLevel);
            request.setAttribute("specialization", specialization);
            request.getRequestDispatcher("/admin/viewDoctorDetail.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/manageDoctor");
        }
    }
}
