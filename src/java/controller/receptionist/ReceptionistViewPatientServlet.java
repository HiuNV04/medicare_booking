package controller.receptionist;

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
import model.Patient;

@WebServlet(name = "ReceptionistViewPatientServlet", urlPatterns = {"/ReceptionistViewPatientServlet"})

public class ReceptionistViewPatientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idRaw = request.getParameter("id");
        if (idRaw == null) {
            response.sendRedirect("error.jsp");
            return;
        }

        try {
            int patientId = Integer.parseInt(idRaw);
            HttpSession session = request.getSession();
            String role = "";

            // Chỉ cho phép lễ tân
            if (session.getAttribute("receptionist") == null) {
                response.sendRedirect("unauthorized.jsp");
                return;
            }

            // TODO: Nếu là doctor → kiểm tra có slot khám bệnh nhân này chưa (bổ sung sau)
            // Nếu không có slot thì redirect hoặc báo lỗi
            PatientDAO dao = new PatientDAO();
            Patient patient = dao.getPatientById(patientId);

            request.setAttribute("patient", patient);
            request.setAttribute("role", role);
            request.getRequestDispatcher("/patient/profile_patient.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect("error.jsp");
        }
    }

    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
