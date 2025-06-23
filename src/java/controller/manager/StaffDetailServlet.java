package controller.manager;

import dal.StaffDAO;
import dal.DoctorDAO;
import model.Staff;
import model.Doctor;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="StaffDetailServlet", urlPatterns={"/manager/staff-detail"})
public class StaffDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String role = request.getParameter("role");
        if (idStr == null || role == null) {
            request.getRequestDispatcher("/manager/staff_detail.jsp").forward(request, response);
            return;
        }
        int id = Integer.parseInt(idStr);
        if (role.equalsIgnoreCase("Doctor")) {
            DoctorDAO doctorDAO = new DoctorDAO();
            Doctor doctor = doctorDAO.getDoctorById(id);
            request.setAttribute("doctor", doctor);
        } else {
            StaffDAO staffDAO = new StaffDAO();
            Staff staff = staffDAO.getManagerById(id);
            if (staff == null) staff = staffDAO.getReceptionistById(id);
            request.setAttribute("staff", staff);
        }
        request.getRequestDispatcher("/manager/staff_detail.jsp").forward(request, response);
    }
} 