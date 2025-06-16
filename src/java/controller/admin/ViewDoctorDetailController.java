/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.DoctorDAO;
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
import model.Doctor;
import model.DoctorLevel;
import model.Specialization;
 
/**
 *
 * @author ADMIN
 */
@WebServlet(urlPatterns = {"/viewDoctorDetail"})
public class ViewDoctorDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DoctorDAO dao = new DoctorDAO();
        String idString = request.getParameter("id").trim();
        int id = Integer.parseInt(idString);
        Doctor doctor = dao.viewDoctorDetail(id);
        request.setAttribute("doctor", doctor);
         DoctorLevelDAO doc = new DoctorLevelDAO();
        SpecializationDAO spec = new SpecializationDAO();
        request.setAttribute("doctorLevel", doc.getDoctorLevel());
        request.setAttribute("specialization", spec.getSpecialization());
         request.getRequestDispatcher("/admin/viewDoctorDetail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
