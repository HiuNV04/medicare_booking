/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.DoctorLevelDAO;
import dal.SpecializationDAO;
import dal.UserAccountDAO;
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
import model.UserAccount;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ViewUserDetailController", urlPatterns = {"/viewUserDetail"})
public class ViewUserDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserAccountDAO dao = new UserAccountDAO();
        String username = request.getParameter("username");
        String role = request.getParameter("role");

        UserAccount accountDetail = dao.viewDetailAccount(username, role);
        List<Specialization> specializations = new SpecializationDAO().getAll();
        List<DoctorLevel> doctorLevels = new DoctorLevelDAO().getAll();

        request.setAttribute("specializations", specializations);
        request.setAttribute("doctorLevels", doctorLevels);

        request.setAttribute("ua", accountDetail);
        request.getRequestDispatcher("/admin/viewAccountDetail.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
