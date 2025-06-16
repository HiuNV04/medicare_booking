package controller;

import dal.RoomDAO;
import dal.DoctorDAO;
import dal.RoomDoctorDAO;
import model.Room;
import model.Doctor;
import model.Specialization;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
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
        RoomDAO roomDAO = new RoomDAO();
        DoctorDAO doctorDAO = new DoctorDAO();
        RoomDoctorDAO roomDoctorDAO = new RoomDoctorDAO();
        
        List<Specialization> specializationList = roomDAO.getAllSpecializations();
        request.setAttribute("specializationList", specializationList);

        String roomIdParam = request.getParameter("roomId");
        int roomId = -1;
        if (roomIdParam != null) {
            try { roomId = Integer.parseInt(roomIdParam); } catch (Exception ignored) {}
        }

        Room selectedRoom = null;
        List<Doctor> doctorsInRoom = null;
        List<Doctor> availableDoctors = null;
        
        if (roomId > 0) {
            selectedRoom = roomDAO.getRoomById(roomId);
            doctorsInRoom = roomDoctorDAO.getDoctorsByRoomId(roomId);
            int count = roomDoctorDAO.countDoctorsInRoom(roomId);
            
            // Make doctorsInRoom effectively final for lambda
            final List<Doctor> finalDoctorsInRoom = doctorsInRoom;
            final int finalSpecializationId = selectedRoom != null ? selectedRoom.getSpecializationId() : -1; 

            availableDoctors = doctorDAO.getAllDoctors().stream()
                .filter(d -> d.getSpecializationId() == finalSpecializationId &&
                             finalDoctorsInRoom.stream().noneMatch(doc -> doc.getId() == d.getId()))
                .collect(Collectors.toList());
            request.setAttribute("canAddDoctor", count < 2);
        }

        request.setAttribute("selectedRoomId", roomId);
        request.setAttribute("selectedRoom", selectedRoom);
        request.setAttribute("doctorsInRoom", doctorsInRoom);
        request.setAttribute("availableDoctors", availableDoctors);
        request.getRequestDispatcher("/auth/room_detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RoomDoctorDAO roomDoctorDAO = new RoomDoctorDAO();
        String action = request.getParameter("action");
        int roomId = Integer.parseInt(request.getParameter("roomId"));

        if ("addDoctor".equals(action)) {
            int doctorId = Integer.parseInt(request.getParameter("doctorId"));
            if (roomDoctorDAO.countDoctorsInRoom(roomId) < 2) {
                roomDoctorDAO.addDoctorToRoom(roomId, doctorId);
            }
        } else if ("removeDoctor".equals(action)) {
            int doctorId = Integer.parseInt(request.getParameter("doctorId"));
            roomDoctorDAO.removeDoctorFromRoom(roomId, doctorId);
        }
        response.sendRedirect("room_detail?roomId=" + roomId);
    }
} 