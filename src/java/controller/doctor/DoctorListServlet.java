package controller.doctor;

import dal.DoctorDAO;
import dal.ValidateDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import model.Doctor;

//@WebServlet(name = "DoctorListServlet", urlPatterns = {"/DoctorListServlet"})
public class DoctorListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Doctor sessionDoctor = (Doctor) request.getSession().getAttribute("doctor");
        if (sessionDoctor == null) {
            response.sendRedirect(request.getContextPath() + "/doctor/login_doctor.jsp");
            return;
        }

        try {
            // --- Lấy tham số filter ---
            String rawName = request.getParameter("name");
            String name = (rawName != null) ? URLDecoder.decode(rawName, "UTF-8") : null;

            String gender = request.getParameter("gender");
            String levelRaw = request.getParameter("levelId");
            String specializationRaw = request.getParameter("specializationId");

            Integer levelId = (levelRaw != null && !levelRaw.trim().isEmpty()) ? Integer.parseInt(levelRaw) : null;
            Integer specializationId = (specializationRaw != null && !specializationRaw.trim().isEmpty())
                    ? Integer.parseInt(specializationRaw) : null;

            // --- Xử lý phân trang ---
            int page = 1;
            int pageSize = 5;
            String pageParam = request.getParameter("page");
            if (pageParam != null) {
                try {
                    page = Integer.parseInt(pageParam);
                } catch (NumberFormatException ignored) {
                }
            }
            if (page < 1) {
                page = 1;
            }
            int offset = (page - 1) * pageSize;

            // --- Truy vấn danh sách ---
            DoctorDAO dao = new DoctorDAO();
            List<Doctor> rawList = dao.searchDoctors(null, gender, levelId, specializationId); // không truyền name vào nữa

            // --- Lọc theo tên nếu có ---
            List<Doctor> filtered = (name != null && !name.trim().isEmpty())
                    ? rawList.stream()
                            .filter(doc -> ValidateDAO.fuzzyMatchAllWords(doc.getFullName(), name))
                            .toList()
                    : rawList;

            // --- Tính phân trang ---
            int totalDoctors = filtered.size();
            int fromIndex = Math.min(offset, totalDoctors);
            int toIndex = Math.min(offset + pageSize, totalDoctors);
            List<Doctor> doctors = filtered.subList(fromIndex, toIndex);
            int totalPages = (int) Math.ceil(totalDoctors * 1.0 / pageSize);

            // --- Lấy danh sách chuyên môn, cấp bậc ---
            List<String[]> specializations = dao.getAllSpecializations();
            List<String[]> levels = dao.getAllLevels();

            // --- Giữ lại các tham số lọc để phân trang ---
            StringBuilder queryBuilder = new StringBuilder();
            if (name != null && !name.isEmpty()) {
                queryBuilder.append("&name=").append(URLEncoder.encode(name, "UTF-8"));
            }
            if (gender != null && !gender.isEmpty()) {
                queryBuilder.append("&gender=").append(gender);
            }
            if (levelId != null) {
                queryBuilder.append("&levelId=").append(levelId);
            }
            if (specializationId != null) {
                queryBuilder.append("&specializationId=").append(specializationId);
            }
            String queryString = queryBuilder.toString();

            // --- Truyền dữ liệu ra view ---
            request.setAttribute("doctors", doctors);
            request.setAttribute("specializations", specializations);
            request.setAttribute("levels", levels);
            request.setAttribute("param", request.getParameterMap());
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("baseUrl", "DoctorListServlet");
            request.setAttribute("queryString", queryString);

            request.getRequestDispatcher("/doctor/profile/list_profile.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi lọc danh sách bác sĩ.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
