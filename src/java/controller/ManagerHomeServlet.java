/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.Normalizer;
import dal.StaffDAO;
import model.Staff;
import dal.DoctorDAO;
import model.Doctor;

@WebServlet(name="ManagerHomeServlet", urlPatterns={"/manager/home"})
public class ManagerHomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        dal.StaffDAO staffDAO = new dal.StaffDAO();
        dal.DoctorDAO doctorDAO = new dal.DoctorDAO();
        List<model.Staff> managerList = staffDAO.getManagers();
        List<model.Staff> receptionistList = staffDAO.getReceptionists();
        List<model.Doctor> doctorList = doctorDAO.getAllDoctors();
        List<Object[]> peopleList = new java.util.ArrayList<>();
        for (model.Staff m : managerList) {
            peopleList.add(new Object[]{m.getFullName(), m.getRole(), m.getDateOfBirth(), m.getGender(), m.getAddress(), m.getPhoneNumber(), m.getImageUrl()});
        }
        for (model.Staff r : receptionistList) {
            peopleList.add(new Object[]{r.getFullName(), r.getRole(), r.getDateOfBirth(), r.getGender(), r.getAddress(), r.getPhoneNumber(), r.getImageUrl()});
        }
        for (model.Doctor d : doctorList) {
            peopleList.add(new Object[]{d.getFullName(), d.getRole(), d.getDateOfBirth(), d.getGender(), d.getAddress(), d.getPhoneNumber(), d.getImageUrl()});
        }
        // Tìm kiếm
        String search = request.getParameter("search");
        if (search != null && !search.trim().isEmpty()) {
            String searchNormalized = normalizeString(search);
            peopleList = peopleList.stream().filter(p -> normalizeString((String)p[0]).contains(searchNormalized)).collect(java.util.stream.Collectors.toList());
        }
        // Sort theo vai trò
        String sortRole = request.getParameter("sortRole");
        if (sortRole != null && !sortRole.isEmpty() && !sortRole.equals("all")) {
            peopleList = peopleList.stream().filter(p -> ((String)p[1]).equalsIgnoreCase(sortRole)).collect(java.util.stream.Collectors.toList());
        }
        // Phân trang
        int page = 1;
        int pageSize = 10;
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            try { page = Integer.parseInt(pageParam); } catch (Exception ignored) {}
        }
        int total = peopleList.size();
        int totalPages = (int)Math.ceil((double)total / pageSize);
        int from = (page-1)*pageSize;
        int to = Math.min(from+pageSize, total);
        List<Object[]> pageList = new java.util.ArrayList<>();
        if (from < to && from < total) pageList = peopleList.subList(from, to);
        request.setAttribute("peopleList", pageList);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("search", search == null ? "" : search);
        request.setAttribute("sortRole", sortRole == null ? "all" : sortRole);
        request.getRequestDispatcher("/auth/manager_home.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ManagerHomeServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManagerHomeServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    private String normalizeString(String input) {
        if (input == null) return "";
        String s = Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .replaceAll("[^a-zA-Z0-9]", "")
                .toLowerCase();
        return s;
    }
}   