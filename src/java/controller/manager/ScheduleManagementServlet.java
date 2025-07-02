package controller.manager;

 import dal.DoctorDAO;
import dal.DoctorShiftSlotDAO;
import dal.ManagerDAO;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Doctor;
import model.DoctorShiftSlot;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.stream.Collectors;

@WebServlet(name="ScheduleManagementServlet", urlPatterns={"/manager/schedule-management"})
public class ScheduleManagementServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DoctorDAO doctorDAO = new DoctorDAO();
        List<Doctor> doctors = doctorDAO.getAllDoctors();
        request.setAttribute("doctorList", doctors);

        String doctorIdStr = request.getParameter("doctorId");
        String weekStr = request.getParameter("week");
        String dateStr = request.getParameter("date");
        int totalWeeks = 4;
        int currentWeek = 0;
        LocalDate today = LocalDate.now();
        LocalDate baseDate = today;
        if (dateStr != null && !dateStr.isEmpty()) {
            try { baseDate = LocalDate.parse(dateStr); } catch (Exception e) { baseDate = today; }
        }
        if (weekStr != null && !weekStr.isEmpty()) {
            try { currentWeek = Integer.parseInt(weekStr); } catch (Exception e) { currentWeek = 0; }
        } else if (dateStr != null && !dateStr.isEmpty()) {
            // Nếu chọn ngày, xác định tuần chứa ngày đó so với tuần hiện tại
            LocalDate mondayThisWeek = today.with(DayOfWeek.MONDAY);
            LocalDate mondayTarget = baseDate.with(DayOfWeek.MONDAY);
            currentWeek = (int) java.time.temporal.ChronoUnit.WEEKS.between(mondayThisWeek, mondayTarget);
            if (currentWeek < 0) currentWeek = 0;
            if (currentWeek > totalWeeks-1) currentWeek = totalWeeks-1;
        }
        // Ngày đầu tuần cần hiển thị
        LocalDate monday = today.with(DayOfWeek.MONDAY).plusWeeks(currentWeek);
        List<Date> sqlWeekDates = new ArrayList<>();
        List<String> displayWeekDates = new ArrayList<>();
        SimpleDateFormat sdfDisplay = new SimpleDateFormat("EEE, dd/MM");
        SimpleDateFormat sdfKey = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(java.sql.Date.valueOf(monday));
        for (int i = 0; i < 7; i++) {
            Date sqlDate = new Date(cal.getTimeInMillis());
            sqlWeekDates.add(sqlDate);
            displayWeekDates.add(sdfDisplay.format(sqlDate));
            cal.add(Calendar.DATE, 1);
        }
        request.setAttribute("weekDates", displayWeekDates);
        request.setAttribute("sqlWeekDates", sqlWeekDates);
        request.setAttribute("currentWeek", currentWeek);
        request.setAttribute("totalWeeks", totalWeeks);
        request.setAttribute("selectedDate", dateStr);

        if (doctorIdStr != null && !doctorIdStr.isEmpty()) {
            int doctorId = Integer.parseInt(doctorIdStr);
            request.setAttribute("selectedDoctorId", doctorId);
             ManagerDAO dao = new ManagerDAO();
            List<DoctorShiftSlot> shifts = dao.getShiftsByDoctorForWeek(
                doctorId,
                sqlWeekDates.get(0).toLocalDate(),
                sqlWeekDates.get(6).toLocalDate()
            );
            Map<String, DoctorShiftSlot> shiftMap = new HashMap<>();
            Map<String, String> appointmentMap = new HashMap<>();
            for (DoctorShiftSlot shift : shifts) {
                String dateKey = sdfKey.format(shift.getSlotDate());
                int hour = shift.getStart().toLocalTime().getHour();
                String slotKey = dateKey + "_" + hour;
                shiftMap.put(slotKey, shift);
                String status = dao.getAppointmentStatusForShift(shift.getSlotId());
                if (status != null) {
                    appointmentMap.put(slotKey, status);
                }
            }
            request.setAttribute("shifts", shiftMap);
            request.setAttribute("appointmentMap", appointmentMap);
        }
        request.getRequestDispatcher("/manager/schedule_management.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String doctorIdStr = request.getParameter("doctorId");
        String message = null;
        String messageType = null;

        if (action != null && doctorIdStr != null) {
             ManagerDAO dao = new ManagerDAO();
            if ("create".equals(action)) {
                String dateStr = request.getParameter("date");
                String hourStr = request.getParameter("hour");
                int doctorId = Integer.parseInt(doctorIdStr);
                Date date = Date.valueOf(dateStr);
                int hour = Integer.parseInt(hourStr);
                if (dao.isShiftExists(doctorId, date, hour)) {
                    message = "Ca làm việc đã tồn tại!";
                    messageType = "danger";
                } else {
                    // Tạo ca làm việc mới (6-18h, mỗi ca 1 tiếng)
                    Time startTime = Time.valueOf(String.format("%02d:00:00", hour));
                    Time endTime = Time.valueOf(String.format("%02d:00:00", hour+1));
                    dao.addShift(doctorId, date, startTime, endTime);
                    message = "Tạo ca làm việc thành công!";
                    messageType = "success";
                }
            } else if ("delete".equals(action)) {
                String shiftIdStr = request.getParameter("shiftId");
                int shiftId = Integer.parseInt(shiftIdStr);
                if (dao.isShiftBooked(shiftId)) {
                    message = "Không thể xóa ca đã có lịch hẹn!";
                    messageType = "danger";
                } else {
                    dao.deleteShift(shiftId);
                    message = "Xóa ca làm việc thành công!";
                    messageType = "success";
                }
            }
        }
        // Lưu thông báo vào session để hiển thị sau redirect
        if (message != null) {
            request.getSession().setAttribute("scheduleMessage", message);
            request.getSession().setAttribute("scheduleMessageType", messageType);
        }
        // Redirect back to the schedule page to show the update
        response.sendRedirect(request.getContextPath() + "/manager/schedule-management?doctorId=" + doctorIdStr);
    }
    
    private List<String> getWeekDates(LocalDate startOfWeek) {
        List<String> dates = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, dd/MM");
        for (int i = 0; i < 7; i++) {
            dates.add(startOfWeek.plusDays(i).format(formatter));
        }
        return dates;
    }

    private List<Integer> getTimeSlots() {
        List<Integer> slots = new ArrayList<>();
        for (int i = 6; i < 18; i++) {
            slots.add(i);
        }
        return slots;
    }
} 