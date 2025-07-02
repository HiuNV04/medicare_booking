package controller.manager;

import dal.DoctorDAO;
import dal.DoctorShiftSlotDAO;
import model.Doctor;
import model.DoctorShiftSlot;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "ShiftListServlet", urlPatterns = {"/manager/shift-list"})
public class ShiftListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DoctorShiftSlotDAO shiftDAO = new DoctorShiftSlotDAO();
        DoctorDAO doctorDAO = new DoctorDAO();
        
        String doctorIdStr = request.getParameter("doctorId");
        String dateStr = request.getParameter("date");
        String pageStr = request.getParameter("page");
        
        Integer doctorId = null;
        if (doctorIdStr != null && !doctorIdStr.isEmpty()) {
            try { doctorId = Integer.parseInt(doctorIdStr); } catch(NumberFormatException e) { doctorId = null; }
        }
        Date date = null;
        if (dateStr != null && !dateStr.isEmpty()) {
            try { date = Date.valueOf(dateStr); } catch(IllegalArgumentException e) { date = null; }
        }
        int page = 1;
        if (pageStr != null && !pageStr.isEmpty()) {
            try { page = Integer.parseInt(pageStr); } catch (Exception e) { page = 1; }
        }
        int pageSize = 10;

        List<Doctor> doctorList = doctorDAO.getAllDoctors();
        int totalShifts = shiftDAO.countShifts(doctorId, date);
        int totalPages = (int) Math.ceil((double) totalShifts / pageSize);
        if (page < 1) page = 1;
        if (page > totalPages && totalPages > 0) page = totalPages;
        int offset = (page - 1) * pageSize;

        List<DoctorShiftSlot> shiftList = shiftDAO.getShiftsPaged(doctorId, date, offset, pageSize);

        request.setAttribute("doctorList", doctorList);
        request.setAttribute("shiftList", shiftList);
        request.setAttribute("selectedDoctorId", doctorId);
        request.setAttribute("selectedDate", dateStr);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        // --- DEBUGGING INFO ---
        request.setAttribute("debug_doctorIdStr", doctorIdStr);
        request.setAttribute("debug_dateStr", dateStr);
        request.setAttribute("debug_pageStr", pageStr);
        request.setAttribute("debug_parsedDoctorId", doctorId);
        request.setAttribute("debug_parsedDate", date);
        request.setAttribute("debug_totalShifts", totalShifts);
        request.setAttribute("debug_shiftListSize", shiftList.size());
        request.setAttribute("debug_offset", offset);
        // --- END DEBUGGING ---
        request.getRequestDispatcher("/manager/shift_list.jsp").forward(request, response);
    }
} 