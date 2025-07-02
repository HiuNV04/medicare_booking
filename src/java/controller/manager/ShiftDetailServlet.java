package controller.manager;

   import dal.ManagerDAO;
import model.Doctor;
import model.DoctorShiftSlot;
import model.AppointmentSchedule;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShiftDetailServlet", urlPatterns = {"/manager/shift-detail"})
public class ShiftDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String shiftIdStr = request.getParameter("shiftId");
        if (shiftIdStr == null || shiftIdStr.isEmpty()) {
            response.sendRedirect("shift-list");
            return;
        }
        int shiftId = Integer.parseInt(shiftIdStr);
     
        ManagerDAO dao = new ManagerDAO();

        DoctorShiftSlot shift = dao.getShiftById(shiftId);
        Doctor doctor = dao.getDoctorById(shift.getDoctorId());
        List<AppointmentSchedule> appointments = dao.getAppointmentsByShiftId(shiftId);

        request.setAttribute("shift", shift);
        request.setAttribute("doctor", doctor);
        request.setAttribute("appointments", appointments);
        request.getRequestDispatcher("/manager/shift_detail.jsp").forward(request, response);
    }
} 