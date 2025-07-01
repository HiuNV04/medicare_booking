package controller.doctor;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import dal.DoctorDAO;
import jakarta.servlet.annotation.WebServlet;
import model.Doctor;

//@WebServlet(name = "DoctorProfileServlet", urlPatterns = {"/DoctorProfileServlet"})
public class DoctorProfileServlet extends HttpServlet {

    private DoctorDAO dao = new DoctorDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy tối đa 5 bác sĩ có cấp bậc là giáo sư (doctorLevelId = 1)
        List<Doctor> doctors = dao.getAllDoctors().stream()
                .filter(d -> d.getDoctorLevelId() == 1)
                .limit(5)
                .collect(Collectors.toList());

        request.setAttribute("doctors", doctors);
        request.getRequestDispatcher("/doctor/doctor_home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Tạm thời chưa xử lý gì ở POST vì trang chỉ để hiển thị
    }

    @Override
    public String getServletInfo() {
        return "Hiển thị trang chính của bác sĩ với thanh trượt các giáo sư";
    }

}
