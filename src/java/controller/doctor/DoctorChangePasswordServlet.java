package controller.doctor;

import dal.DoctorDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Doctor;

@WebServlet(name = "DoctorChangePasswordServlet", urlPatterns = {"/DoctorChangePasswordServlet"}) 
public class DoctorChangePasswordServlet extends HttpServlet {

    private final DoctorDAO dao = new DoctorDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 🔒 Bước 5: Kiểm tra bác sĩ đã đăng nhập chưa
        Doctor sessionDoctor = (Doctor) request.getSession().getAttribute("doctor");
        if (sessionDoctor == null) {
            response.sendRedirect("login_doctor.jsp"); // Chưa login thì về trang đăng nhập
            return;
        }

        int id = sessionDoctor.getId(); // lấy id từ session luôn cho an toàn
        DoctorDAO dao = new DoctorDAO();
        Doctor doctor = dao.getDoctorById(id);

        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!doctor.getPassword().equals(currentPassword)) {
            request.setAttribute("error", "Mật khẩu hiện tại không đúng.");
        } else if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "Mật khẩu mới không khớp.");
        } else {
            dao.updateDoctorPassword(id, newPassword);
            request.setAttribute("success", "Đổi mật khẩu thành công!");
            doctor = dao.getDoctorById(id); // load lại thông tin mới
        }

        request.setAttribute("doctor", doctor);
        request.getRequestDispatcher("/doctor/profile/change_password.jsp").forward(request, response);
    }

    public String getServletInfo() {
        return "Short description";
    }
}
