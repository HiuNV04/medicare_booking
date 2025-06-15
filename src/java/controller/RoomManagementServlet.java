package controller;

import dal.RoomDAO;
import dal.DoctorDAO;
import model.Room;
import model.Doctor;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="RoomManagementServlet", urlPatterns={"/manager/rooms"})
public class RoomManagementServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RoomDAO roomDAO = new RoomDAO();
        DoctorDAO doctorDAO = new DoctorDAO();
        List<Room> roomList = roomDAO.getAllRooms();
        String roomIdParam = request.getParameter("roomId");
        int roomId = -1;
        if (roomIdParam != null) {
            try { roomId = Integer.parseInt(roomIdParam); } catch (Exception ignored) {}
        }
        List<Doctor> doctorsInRoom = null;
        List<Doctor> availableDoctors = null;
        if (roomId > 0) {
            doctorsInRoom = roomDAO.getDoctorsByRoomId(roomId);
            List<Doctor> allDoctors = doctorDAO.getAllDoctors();
            if (doctorsInRoom != null && !doctorsInRoom.isEmpty()) {
                int specializationId = doctorsInRoom.get(0).getSpecializationId();
                availableDoctors = allDoctors.stream()
                    .filter(d -> d.getRoomId() == 0 && d.getSpecializationId() == specializationId)
                    .collect(Collectors.toList());
            } else {
                availableDoctors = allDoctors.stream()
                    .filter(d -> d.getRoomId() == 0)
                    .collect(Collectors.toList());
            }
        }
        request.setAttribute("roomList", roomList);
        request.setAttribute("selectedRoomId", roomId);
        request.setAttribute("doctorsInRoom", doctorsInRoom);
        request.setAttribute("availableDoctors", availableDoctors);
        request.getRequestDispatcher("/auth/room_management.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RoomDAO roomDAO = new RoomDAO();
        String action = request.getParameter("action");
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        if ("add".equals(action)) {
            roomDAO.addDoctorToRoom(doctorId, roomId);
        } else if ("remove".equals(action)) {
            roomDAO.removeDoctorFromRoom(doctorId);
        }
        response.sendRedirect("rooms?roomId=" + roomId);
    }
} 