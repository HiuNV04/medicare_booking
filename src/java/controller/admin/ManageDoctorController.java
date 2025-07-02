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
import java.util.List;
import model.Doctor;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="ManageDoctorController", urlPatterns={"/manageDoctor"})
public class ManageDoctorController extends HttpServlet {
   
    
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

        AdminDAO dao = new AdminDAO();

        int totalRecord = dao.countDoctors(search, role, status);
        int totalPage = (int) Math.ceil((double) totalRecord / pageSize);

        List<Doctor> doctors = dao.getDoctorsByFilter(search, role, status, page, pageSize);

        request.setAttribute("doctors", doctors);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("currentPage", page);

        request.getRequestDispatcher("/admin/manageDoctor.jsp").forward(request, response);
    }
     } 

   

