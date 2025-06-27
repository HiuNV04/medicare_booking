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
import model.Staff;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ViewStaffDetailController", urlPatterns = {"/viewStaffDetail"})
public class ViewStaffDetailController extends HttpServlet {

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
            StaffDAO dao = new StaffDAO();
            Staff staff = dao.getStaffById(id);
            if (staff == null) {
                request.setAttribute("msgError", "Không tìm thấy nhân viên.");
                request.getRequestDispatcher("/admin/viewStaffDetail.jsp").forward(request, response);
                return;
            }
            request.setAttribute("staff", staff);
            request.getRequestDispatcher("/admin/viewStaffDetail.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/manageStaff");
        }
    }
}
