package controller.doctor;

import dal.DoctorDAO;
import model.Doctor;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "DoctorLoginServlet", urlPatterns = {"/DoctorLoginServlet"}) 
public class DoctorLoginServlet extends HttpServlet {

    private DoctorDAO dao = new DoctorDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Truy cập trực tiếp => điều hướng về trang đăng nhập
        request.getRequestDispatcher("/doctor/login_doctor.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Doctor doctor = dao.getDoctorDetail(username, password);

        if (doctor != null) {
            request.getSession().setAttribute("doctor", doctor);
            response.sendRedirect("DoctorProfileServlet"); // hoặc trang dashboard bác sĩ
        } else {
            request.setAttribute("error", "Tài khoản hoặc mật khẩu sai!");
            request.getRequestDispatcher("/doctor/login_doctor.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Xử lý đăng nhập tài khoản bác sĩ";
    }
}
