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
import model.Patient;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="ViewPatientDetailController", urlPatterns={"/viewPatientDetail"})
public class ViewPatientDetailController extends HttpServlet {
 
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
            PatientDAO dao = new PatientDAO();
            Patient patient = dao.getPatientById(id);
            if (patient == null) {
                request.setAttribute("msgError", "Không tìm thấy nhân viên.");
                request.getRequestDispatcher("/admin/viewPatientDetail.jsp").forward(request, response);
                return;
            }
            request.setAttribute("patient", patient);
            request.getRequestDispatcher("/admin/viewPatientDetail.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/managePatient");
        }
     } 
 
   

}
