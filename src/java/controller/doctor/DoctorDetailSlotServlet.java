package controller.doctor;

import dal.DoctorShiftSlotDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

import jakarta.servlet.http.HttpServlet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import model.Doctor;
import model.DoctorShiftSlot;

//@WebServlet(name = "DoctorDetailSlotServlet", urlPatterns = {"/DoctorDetailSlotServlet"}) 
public class DoctorDetailSlotServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Doctor sessionDoctor = (Doctor) request.getSession().getAttribute("doctor");
        if (sessionDoctor == null) {
            response.sendRedirect(request.getContextPath() + "/doctor/login_doctor.jsp");
            return;
        }

        DoctorShiftSlotDAO slotDAO = new DoctorShiftSlotDAO();
        List<DoctorShiftSlot> allSlots = slotDAO.getAllSlotShift();

        // Lọc theo bác sĩ đang đăng nhập và 8 ngày tới
        LocalDate today = LocalDate.now();
        LocalDate maxDate = today.plusDays(7);

        List<DoctorShiftSlot> filtered = new ArrayList<>();
        for (DoctorShiftSlot s : allSlots) {
            if (s.getDoctorId() == sessionDoctor.getId()) {
                LocalDate slotDate = s.getSlotDate().toLocalDate();
                if (!slotDate.isBefore(today) && !slotDate.isAfter(maxDate)) {
                    filtered.add(s);
                }
            }
        }

        // Gộp theo ngày
        Map<String, List<DoctorShiftSlot>> grouped = new LinkedHashMap<>();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (int i = 0; i < 8; i++) {
            String key = today.plusDays(i).format(fmt);
            grouped.put(key, new ArrayList<>());
        }

        for (DoctorShiftSlot slot : filtered) {
            String key = slot.getSlotDate().toLocalDate().format(fmt);
            grouped.get(key).add(slot);
        }

        request.setAttribute("groupedSlotDetail", grouped);
        request.getRequestDispatcher("/doctor/schedule/appointment_detail.jsp").forward(request, response);
    }

    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
