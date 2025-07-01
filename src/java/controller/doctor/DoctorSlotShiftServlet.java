package controller.doctor;

import dal.DoctorShiftSlotDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;
import model.DoctorShiftSlot;

@WebServlet(name = "DoctorSlotShiftServlet", urlPatterns = {"/DoctorSlotShiftServlet"}) 
public class DoctorSlotShiftServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int page = 1;
        int pageSize = 5;
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        int offset = (page - 1) * pageSize;

        DoctorShiftSlotDAO slotDAO = new DoctorShiftSlotDAO();
        List<DoctorShiftSlot> slotList = slotDAO.getSlotPage(offset, pageSize);
        int totalRecords = slotDAO.countAllSlots();
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

        request.setAttribute("slotList", slotList);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("baseUrl", "DoctorSlotShiftServlet");

        request.getRequestDispatcher("/doctor/schedule/slot_list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
