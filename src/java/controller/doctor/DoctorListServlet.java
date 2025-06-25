package controller.doctor;

import dal.DoctorDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Doctor;

@WebServlet(name = "DoctorListServlet", urlPatterns = {"/DoctorListServlet"})
public class DoctorListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Kiểm tra đăng nhập
        Doctor sessionDoctor = (Doctor) request.getSession().getAttribute("doctor");
        if (sessionDoctor == null) {
            response.sendRedirect(request.getContextPath() + "/doctor/login_doctor.jsp");
            return;
        }

        try {
            // Lấy tham số lọc
            String name = request.getParameter("name");
            String gender = request.getParameter("gender");
            String levelRaw = request.getParameter("levelId");
            String specializationRaw = request.getParameter("specializationId");

            Integer levelId = null;
            Integer specializationId = null;

            if (levelRaw != null && !levelRaw.trim().isEmpty()) {
                levelId = Integer.parseInt(levelRaw);
            }
            if (specializationRaw != null && !specializationRaw.trim().isEmpty()) {
                specializationId = Integer.parseInt(specializationRaw);
            }

            // Phân trang
            int page = 1;
            int limit = 5; // số bác sĩ mỗi trang

            String pageParam = request.getParameter("page");
            if (pageParam != null) {
                try {
                    page = Integer.parseInt(pageParam);
                } catch (NumberFormatException e) {
                    page = 1;
                }
            }
            if (page < 1) {
                page = 1;
            }

            int offset = (page - 1) * limit;

            // Gọi DAO
            DoctorDAO dao = new DoctorDAO();
            List<Doctor> doctors = dao.getDoctorsPage(name, gender, levelId, specializationId, offset, limit);
            int totalDoctors = dao.countDoctors(name, gender, levelId, specializationId);
            int totalPages = (int) Math.ceil(totalDoctors * 1.0 / limit);

            // Dữ liệu cho dropdown lọc
            List<String[]> specializations = dao.getAllSpecializations();
            List<String[]> levels = dao.getAllLevels();

            // Gửi dữ liệu sang JSP
            request.setAttribute("doctors", doctors);
            request.setAttribute("specializations", specializations);
            request.setAttribute("levels", levels);
            request.setAttribute("param", request.getParameterMap());

            // Thêm thông tin phân trang
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);

            request.getRequestDispatcher("/doctor/profile/list_profile.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi lọc bác sĩ.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Hiển thị danh sách hồ sơ bác sĩ";
    }
}
