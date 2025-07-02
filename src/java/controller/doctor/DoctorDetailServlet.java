package controller.doctor;

import dal.DoctorDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Doctor;

@MultipartConfig(
        maxFileSize = 2 * 1024 * 1024, // 2MB
        maxRequestSize = 5 * 1024 * 1024 // 5MB
)
//@WebServlet(name = "DoctorDetailServlet", urlPatterns = {"/DoctorDetailServlet"}) 
public class DoctorDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DoctorDAO dao = new DoctorDAO();
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
            return;
        }
        Doctor sessionDoctor = (Doctor) session.getAttribute("doctor");
        if (sessionDoctor == null) {
            response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
            return;
        }

        Doctor latestDoctor = dao.getDoctorById(sessionDoctor.getId());
        if (latestDoctor == null) {
            request.setAttribute("error", "Không tìm thấy thông tin bác sĩ.");
            request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
            return;
        }

        request.setAttribute("doctor", latestDoctor);
        boolean isOwner = sessionDoctor.getId() == latestDoctor.getId();
        request.setAttribute("isOwner", isOwner);
        request.getRequestDispatcher("/doctor/profile/detail_profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
