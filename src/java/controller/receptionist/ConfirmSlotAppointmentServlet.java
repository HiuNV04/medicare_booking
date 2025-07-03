package controller.receptionist;

import dal.AppointmentDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Appointment;

@WebServlet(name = "ConfirmSlotAppointmentServlet", urlPatterns = {"/ConfirmSlotAppointmentServlet"})

public class ConfirmSlotAppointmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
            String action = request.getParameter("action");

            AppointmentDAO dao = new AppointmentDAO();

            String message = "";

            if ("approve".equals(action)) {
                dao.confirmStatus(appointmentId, "Approved");
                dao.updateSlotBooked(appointmentId, true);
                message = "✅ Lịch hẹn đã được xác nhận thành công!";
            } else if ("cancel".equals(action)) {
                dao.confirmStatus(appointmentId, "Canceled");
                message = "❌ Lịch hẹn đã bị từ chối!";
            }

            // Lấy lại thông tin chi tiết lịch hẹn
            Appointment detail = dao.getAppointmentDetailById(appointmentId);
            request.setAttribute("detail", detail);
            request.setAttribute("message", message);

            request.getRequestDispatcher("/receptionist/detail_appointment.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra khi xử lý yêu cầu.");
        }
    }

}
