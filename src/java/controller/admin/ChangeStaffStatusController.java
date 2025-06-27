/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.StaffDAO;
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
@WebServlet(name = "ChangeStaffStatusController", urlPatterns = {"/changeStaffStatus"})
public class ChangeStaffStatusController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idRaw = request.getParameter("id");
        String statusRaw = request.getParameter("status");
        if (idRaw == null || statusRaw == null) {
            response.sendRedirect(request.getContextPath() + "/manageStaff");
            return;
        }
        try {
            int id = Integer.parseInt(idRaw);
            boolean status = "1".equals(statusRaw) || "true".equalsIgnoreCase(statusRaw);

            boolean success = new StaffDAO().updateStaffStatus(id, status);
            String message = success ? "Thay đổi trạng thái thành công" : "Thay đổi trạng thái thất bại";
            response.sendRedirect(request.getContextPath() + "/viewStaffDetail?id=" + id + "&message=" + java.net.URLEncoder.encode(message, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/manageStaff");
        }
    }
}
