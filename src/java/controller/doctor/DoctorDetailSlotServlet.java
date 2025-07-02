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

        int doctorId = sessionDoctor.getId();

        // Lấy offset từ request
        int offset = 0;
        try {
            offset = Integer.parseInt(request.getParameter("offset"));
        } catch (Exception e) {
            offset = 0;
        }

        // Tính ngày từ offset (6 ngày)
        LocalDate fromDate = LocalDate.now().plusDays(offset);
        LocalDate toDate = fromDate.plusDays(5);

        // Gọi DAO
        DoctorShiftSlotDAO slotDAO = new DoctorShiftSlotDAO();
        List<DoctorShiftSlot> allSlots = slotDAO.getSlotByDoctorTodayId(doctorId); // đã JOIN để có patientName

        // Group theo ngày
        Map<String, List<DoctorShiftSlot>> grouped = new LinkedHashMap<>();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate cur = fromDate;

        while (!cur.isAfter(toDate)) {
            String key = cur.format(fmt);
            List<DoctorShiftSlot> slotsInDay = new ArrayList<>();

            for (DoctorShiftSlot s : allSlots) {
                if (s.getSlotDate().toLocalDate().isEqual(cur)) {
                    slotsInDay.add(s);
                }
            }

            grouped.put(key, slotsInDay);
            cur = cur.plusDays(1);
        }

        request.setAttribute("slotDetail", allSlots);
        request.setAttribute("offset", offset);
        request.getRequestDispatcher("/doctor/schedule/appointment_detail.jsp").forward(request, response);
    }

    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
