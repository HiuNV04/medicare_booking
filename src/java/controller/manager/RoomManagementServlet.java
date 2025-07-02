package controller.manager;

import dal.RoomDAO;
import dal.DoctorDAO;
import dal.ManagerDAO;
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
         ManagerDAO dao = new ManagerDAO();
        List<Room> roomList = dao.getAllRooms();
        List<Specialization> specializationList = dao.getAllSpecializations();
        List<Doctor> doctorList = dao.getAllDoctors();

        // Lấy danh sách ID bác sĩ đã được gán phòng
        java.util.Set<Integer> assignedDoctorIds = roomList.stream()
                .map(Room::getDoctorId)
                .filter(id -> id > 0)
                .collect(Collectors.toSet());

        // Lọc ra danh sách bác sĩ chưa được gán phòng
        List<Doctor> availableDoctors = doctorList.stream()
                .filter(d -> !assignedDoctorIds.contains(d.getId()))
                .collect(Collectors.toList());

        String search = request.getParameter("search");
        if (search != null && !search.trim().isEmpty()) {
            String normalizedSearch = normalize(search);
            roomList = roomList.stream().filter(r -> normalize(r.getName()).contains(normalizedSearch)).collect(Collectors.toList());
        }

        String specializationIdStr = request.getParameter("specializationId");
        if (specializationIdStr != null && !specializationIdStr.isEmpty()) {
            int specializationId = Integer.parseInt(specializationIdStr);
            List<Integer> doctorIds = doctorList.stream()
                .filter(d -> d.getSpecializationId() == specializationId)
                .map(Doctor::getId)
                .collect(Collectors.toList());
            roomList = roomList.stream()
                .filter(r -> doctorIds.contains(r.getDoctorId()))
                .collect(Collectors.toList());
        }

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
        request.setAttribute("doctorList", doctorList);
        request.setAttribute("availableDoctors", availableDoctors);
        request.setAttribute("pageSize", pageSize);

        Object successMsg = request.getSession().getAttribute("success");
        if (successMsg != null) {
            request.setAttribute("success", successMsg);
            request.getSession().removeAttribute("success");
        }
        Object errorMsg = request.getSession().getAttribute("error");
        if (errorMsg != null) {
            request.setAttribute("error", errorMsg);
            request.getSession().removeAttribute("error");
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
        ManagerDAO dao = new ManagerDAO();
        String action = request.getParameter("action");
        if ("addRoom".equals(action)) {
            String name = request.getParameter("name");
            int doctorId = Integer.parseInt(request.getParameter("doctorId"));

            List<Room> allRooms = dao.getAllRooms();
            String normalizedNewName = normalize(name);
            boolean nameExists = allRooms.stream().anyMatch(r -> normalize(r.getName()).equals(normalizedNewName));

            if (nameExists) {
                request.getSession().setAttribute("error", "Tên phòng đã tồn tại!");
            } else {
                dao.addRoom(name, doctorId);
                request.getSession().setAttribute("success", "Thêm phòng thành công!");
            }
        } else if ("deleteRoom".equals(action)) {
            int roomId = Integer.parseInt(request.getParameter("roomId"));
            Room room = dao.getRoomById(roomId);
            if (room != null && room.getDoctorId() > 0) {
                request.getSession().setAttribute("error", "Không thể xóa phòng vì vẫn còn bác sĩ trực thuộc!");
            } else {
                dao.deleteRoom(roomId);
                request.getSession().setAttribute("success", "Xóa phòng thành công!");
            }
        }
        response.sendRedirect(request.getContextPath() + "/manager/rooms");
    }
} 