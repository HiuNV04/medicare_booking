/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.AdminDAO;
import dal.DoctorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ChangeDoctorStatusController", urlPatterns = {"/changeDoctorStatus"})
public class ChangeDoctorStatusController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idRaw = request.getParameter("id");
        String statusRaw = request.getParameter("status");
        if (idRaw == null || statusRaw == null) {
            response.sendRedirect(request.getContextPath() + "/manageDoctor");
            return;
        }
        try {
            int id = Integer.parseInt(idRaw);
            boolean status = "1".equals(statusRaw) || "true".equalsIgnoreCase(statusRaw);

            boolean success = new AdminDAO().updateDoctorStatus(id, status);
            String message = success ? "Update status successfully" : "Update status failed";
            response.sendRedirect(request.getContextPath() + "/viewDoctorDetail?id=" + id + "&message=" + java.net.URLEncoder.encode(message, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/manageDoctor");
        }
    }
}
