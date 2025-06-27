/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.admin;

import dal.PatientDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Patient;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="ManagePatientController", urlPatterns={"/managePatient"})
public class ManagePatientController extends HttpServlet {
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         String search = request.getParameter("search");
        String role = request.getParameter("role");
        String statusStr = request.getParameter("status");
        String pageStr = request.getParameter("page");
        int page = 1;
        int pageSize = 2; // Số bản ghi mỗi trang, bạn thay đổi tùy ý

        if (pageStr != null && !pageStr.isEmpty()) {
            try {
                page = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        Boolean status = null;
        if (statusStr != null && !statusStr.isEmpty()) {
            status = "1".equals(statusStr);
        }

        PatientDAO staffDAO = new PatientDAO();

        int totalRecord = staffDAO.countPatients(search, role, status);
        int totalPage = (int) Math.ceil((double) totalRecord / pageSize);

        List<Patient> patients = staffDAO.getPatientsByFilter(search, role, status, page, pageSize);

        request.setAttribute("patients", patients);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("currentPage", page);

        request.getRequestDispatcher("/admin/managePatient.jsp").forward(request, response);
    } 
 
    
  
}
