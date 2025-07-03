package controller.receptionist;

import dal.AppointmentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Appointment;

@WebServlet(name = "AppointmentDetailServlet", urlPatterns = {"/AppointmentDetailServlet"})

public class AppointmentDetailServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idRaw = request.getParameter("id");
            int id = Integer.parseInt(idRaw);

            AppointmentDAO dao = new AppointmentDAO();
            Appointment detail = dao.getAppointmentDetailById(id);

            // B3: Gửi dữ liệu sang JSP
            request.setAttribute("detail", detail);
            request.getRequestDispatcher("receptionist/detail_appointment.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Lịch hẹn không tồn tại hoặc có lỗi.");
        }
    }

    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
