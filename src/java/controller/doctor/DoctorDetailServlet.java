package controller.doctor;

import dal.DoctorDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Doctor;

@MultipartConfig(
        maxFileSize = 2 * 1024 * 1024, // 2MB
        maxRequestSize = 5 * 1024 * 1024 // 5MB
)
@WebServlet(name = "DoctorDetailServlet", urlPatterns = {"/DoctorDetailServlet"}) 
public class DoctorDetailServlet extends HttpServlet {

    DoctorDAO dao = new DoctorDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Doctor sessionDoctor = (Doctor) request.getSession().getAttribute("doctor");

        // Nếu chưa đăng nhập → chuyển về trang login
        if (sessionDoctor == null) {
            response.sendRedirect(request.getContextPath() + "/doctor/login_doctor.jsp");
            return;
        }

        // Lấy dữ liệu bác sĩ mới nhất từ DB
        Doctor latestDoctor = dao.getDoctorById(sessionDoctor.getId());
        request.setAttribute("doctor", latestDoctor);

        // Kiểm tra quyền (xem có đúng là chủ tài khoản không)
        boolean isOwner = sessionDoctor.getId() == latestDoctor.getId();
        request.setAttribute("isOwner", isOwner);

        // Forward đến JSP
        request.getRequestDispatcher("/doctor/profile/detail_profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Không cần xử lý gì trong POST
    }

    @Override
    public String getServletInfo() {
        return "Doctor detail profile viewer";
    }
}
