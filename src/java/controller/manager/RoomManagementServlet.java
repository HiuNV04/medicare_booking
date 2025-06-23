package controller.manager;

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

@WebServlet(name="RoomManagementServlet", urlPatterns={"/manager/rooms"})
public class RoomManagementServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RoomDAO roomDAO = new RoomDAO();
        DoctorDAO doctorDAO = new DoctorDAO();
        RoomDoctorDAO roomDoctorDAO = new RoomDoctorDAO();
        List<Room> roomList = roomDAO.getAllRooms();
        List<Specialization> specializationList = roomDAO.getAllSpecializations();
        // Lọc theo tên phòng
        String search = request.getParameter("search");
        if (search != null && !search.trim().isEmpty()) {
            String normalizedSearch = normalize(search);
            roomList = roomList.stream().filter(r -> normalize(r.getName()).contains(normalizedSearch)).collect(Collectors.toList());
        }
        // Lọc theo chuyên khoa
        String specializationIdStr = request.getParameter("specializationId");
        if (specializationIdStr != null && !specializationIdStr.isEmpty()) {
            int specializationId = Integer.parseInt(specializationIdStr);
            roomList = roomList.stream().filter(r -> r.getSpecializationId() == specializationId).collect(Collectors.toList());
        }
        // Phân trang
        int pageSize = 10;
        int currentPage = 1;
        String pageStr = request.getParameter("page");
        if (pageStr != null) {
            try { currentPage = Integer.parseInt(pageStr); } catch (Exception ignored) {}
        }
        int totalRooms = roomList.size();
        int totalPages = (int) Math.ceil((double) totalRooms / pageSize);
        int fromIndex = (currentPage - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, totalRooms);
        List<Room> pagedRoomList = roomList.subList(fromIndex, toIndex);
        request.setAttribute("pagedRoomList", pagedRoomList);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("specializationList", specializationList);
        request.setAttribute("pageSize", pageSize);
        // Handle success message from session
        Object successMsg = request.getSession().getAttribute("success");
        if (successMsg != null) {
            request.setAttribute("success", successMsg);
            request.getSession().removeAttribute("success");
        }
        request.getRequestDispatcher("/manager/room_management.jsp").forward(request, response);
    }

    private String normalize(String input) {
        if (input == null) return "";
        return java.text.Normalizer.normalize(input, java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .replaceAll("[ _]", "")
                .toLowerCase();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RoomDAO roomDAO = new RoomDAO();
        RoomDoctorDAO roomDoctorDAO = new RoomDoctorDAO();
        String action = request.getParameter("action");
        if ("addRoom".equals(action)) {
            int specializationId = Integer.parseInt(request.getParameter("specializationId"));
            String name = request.getParameter("name");
            // Kiểm tra trùng tên phòng (không phân biệt hoa thường, loại bỏ khoảng trắng)
            List<Room> allRooms = roomDAO.getAllRooms();
            String normalizedNewName = normalize(name);
            boolean exists = allRooms.stream().anyMatch(r -> normalize(r.getName()).equals(normalizedNewName));
            if (exists) {
                request.setAttribute("error", "Tên phòng đã tồn tại!");
                // Truyền lại các list cần thiết để hiển thị lại trang
                List<Room> roomList = roomDAO.getAllRooms();
                List<Specialization> specializationList = roomDAO.getAllSpecializations();
                request.setAttribute("pagedRoomList", roomList);
                request.setAttribute("currentPage", 1);
                request.setAttribute("totalPages", 1);
                request.setAttribute("specializationList", specializationList);
                request.setAttribute("pageSize", roomList.size());
                request.getRequestDispatcher("/manager/room_management.jsp").forward(request, response);
                return;
            }
            roomDAO.addRoom(specializationId, name);
            // Set success message in session
            request.getSession().setAttribute("success", "Thêm phòng thành công!");
        } else if ("editRoom".equals(action)) {
            int roomId = Integer.parseInt(request.getParameter("roomId"));
            int specializationId = Integer.parseInt(request.getParameter("specializationId"));
            String name = request.getParameter("name");
            roomDAO.updateRoom(roomId, specializationId, name);
        } else if ("deleteRoom".equals(action)) {
            int roomId = Integer.parseInt(request.getParameter("roomId"));
            roomDAO.deleteRoom(roomId);
        } else if ("addDoctor".equals(action)) {
            int roomId = Integer.parseInt(request.getParameter("roomId"));
            int doctorId = Integer.parseInt(request.getParameter("doctorId"));
            if (roomDoctorDAO.countDoctorsInRoom(roomId) < 2) {
                roomDoctorDAO.addDoctorToRoom(roomId, doctorId);
            }
        } else if ("removeDoctor".equals(action)) {
            int roomId = Integer.parseInt(request.getParameter("roomId"));
            int doctorId = Integer.parseInt(request.getParameter("doctorId"));
            roomDoctorDAO.removeDoctorFromRoom(roomId, doctorId);
        }
        response.sendRedirect("rooms" + (request.getParameter("roomId") != null ? ("?roomId=" + request.getParameter("roomId")) : ""));
    }
} 