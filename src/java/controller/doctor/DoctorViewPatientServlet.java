package controller.doctor;

import dal.AppointmentDAO;
import dal.PatientDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Doctor;
import model.Patient;

@WebServlet(name = "DoctorViewPatientServlet", urlPatterns = {"/DoctorViewPatientServlet"})

public class DoctorViewPatientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Lấy session & bác sĩ đang đăng nhập
        HttpSession session = request.getSession();
        Doctor doctor = (Doctor) session.getAttribute("doctor");

        if (doctor == null) {
            System.out.println("❌ Chưa đăng nhập -> redirect");
            response.sendRedirect(request.getContextPath() + "/doctor/login_doctor.jsp");
            return;
        }

        // 2. Lấy patientId từ URL
        String idRaw = request.getParameter("id");
        if (idRaw == null) {
            System.out.println("❌ Không có id bệnh nhân -> redirect error");
            response.sendRedirect("error.jsp");
            return;
        }

        try {
            int patientId = Integer.parseInt(idRaw);
            int doctorId = doctor.getId();

            // 3. Gọi DAO để kiểm tra quyền truy cập
            AppointmentDAO appDao = new AppointmentDAO();
            boolean hasAccess = appDao.doctorViewPatientProfile(doctorId, patientId);

            System.out.println("🩺 Bác sĩ ID: " + doctorId);
            System.out.println("🧑‍🤝‍🧑 Bệnh nhân ID: " + patientId);
            System.out.println("✅ Có quyền truy cập không? " + hasAccess);

            if (!hasAccess) {
                System.out.println("🚫 Bác sĩ không có quyền xem hồ sơ bệnh nhân này");
                response.sendRedirect("unauthorized.jsp");
                return;
            }

            // 4. Lấy thông tin bệnh nhân nếu được phép
            PatientDAO patientDAO = new PatientDAO();
            Patient patient = patientDAO.getPatientById(patientId);

            if (patient == null) {
                System.out.println("❌ Không tìm thấy bệnh nhân trong DB");
                response.sendRedirect("error.jsp");
                return;
            }

            // 5. Gửi dữ liệu sang JSP
            request.setAttribute("patient", patient);
            request.setAttribute("role", "doctor");

            request.getRequestDispatcher("/doctor/patient/booked_patient.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            System.out.println("❌ Sai định dạng id bệnh nhân: " + idRaw);
            response.sendRedirect("error.jsp");
        } catch (Exception e) {
            System.out.println("❌ Lỗi trong DoctorViewPatientServlet: " + e.getMessage());
            response.sendRedirect("error.jsp");
        }
    }

    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
