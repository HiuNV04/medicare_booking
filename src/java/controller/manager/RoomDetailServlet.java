package controller.manager;

import dal.RoomDAO;
import dal.DoctorDAO;
import dal.ManagerDAO;
import model.Room;
import model.Doctor;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="RoomDetailServlet", urlPatterns={"/manager/room_detail"})
public class RoomDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ManagerDAO dao = new ManagerDAO();
        
        String roomIdParam = request.getParameter("roomId");
        if (roomIdParam == null) {
            response.sendRedirect(request.getContextPath() + "/manager/rooms");
            return;
        }

        int roomId = Integer.parseInt(roomIdParam);
        Room room = dao.getRoomById(roomId);
        
        if (room == null) {
            response.sendRedirect(request.getContextPath() + "/manager/rooms");
            return;
        }

        Doctor currentDoctor = null;
        if (room.getDoctorId() > 0) {
            currentDoctor = dao.getDoctorById(room.getDoctorId());
        }

        List<Room> allRooms = dao.getAllRooms();
        java.util.Set<Integer> assignedDoctorIds = allRooms.stream()
                .filter(r -> r.getId() != roomId) // Exclude current room's doctor
                .map(Room::getDoctorId)
                .filter(id -> id > 0)
                .collect(java.util.stream.Collectors.toSet());

        List<Doctor> availableDoctors = dao.getAllDoctors().stream()
                .filter(d -> !assignedDoctorIds.contains(d.getId()))
                .collect(java.util.stream.Collectors.toList());
        
        request.setAttribute("room", room);
        request.setAttribute("currentDoctor", currentDoctor);
        request.setAttribute("availableDoctors", availableDoctors);
        
        Object successMsg = request.getSession().getAttribute("success");
        if (successMsg != null) {
            request.setAttribute("success", successMsg);
            request.getSession().removeAttribute("success");
        }

        request.getRequestDispatcher("/manager/room_detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ManagerDAO dao = new ManagerDAO();
        String action = request.getParameter("action");
        int roomId = Integer.parseInt(request.getParameter("roomId"));

        if ("changeDoctor".equals(action)) {
            int newDoctorId = Integer.parseInt(request.getParameter("newDoctorId"));
            dao.assignDoctorToRoom(roomId, newDoctorId);
            request.getSession().setAttribute("success", "Thay đổi bác sĩ thành công!");
        }
        response.sendRedirect(request.getContextPath() + "/manager/room_detail?roomId=" + roomId);
    }
} 