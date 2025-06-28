package controller.manager;

import dal.StaffDAO;
import dal.DoctorDAO;
import model.Staff;
import model.Doctor;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@MultipartConfig
@WebServlet(name="StaffDetailServlet", urlPatterns={"/manager/staff-detail"})
public class StaffDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Redirect manager to their own profile page if they click "detail" on themselves
        Staff loggedInManager = (Staff) request.getSession().getAttribute("user");
        String idStr = request.getParameter("id");
        if (loggedInManager != null && idStr != null && String.valueOf(loggedInManager.getId()).equals(idStr)) {
            response.sendRedirect(request.getContextPath() + "/manager/profile");
            return;
        }

        String role = request.getParameter("role");
        if (idStr == null || role == null) {
            request.getRequestDispatcher("/manager/staff_detail.jsp").forward(request, response);
            return;
        }
        int id = Integer.parseInt(idStr);
        if (role.equalsIgnoreCase("Doctor")) {
            DoctorDAO doctorDAO = new DoctorDAO();
            Doctor doctor = doctorDAO.getDoctorById(id);
            request.setAttribute("doctor", doctor);
        } else {
            StaffDAO staffDAO = new StaffDAO();
            Staff staff = staffDAO.getManagerById(id);
            if (staff == null) staff = staffDAO.getReceptionistById(id);
            request.setAttribute("staff", staff);
        }
        request.getRequestDispatcher("/manager/staff_detail.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String role = request.getParameter("role");
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String phone = request.getParameter("phoneNumber");
        String gender = request.getParameter("gender");
        java.sql.Date dob = java.sql.Date.valueOf(request.getParameter("dateOfBirth"));
        String existingImageUrl = request.getParameter("existingImageUrl");

        Part filePart = request.getPart("imageFile");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String imageUrl;

        if (fileName != null && !fileName.isEmpty()) {
            String uploadPath = getServletContext().getRealPath("") + File.separator + "img";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();

            File file = new File(uploadPath + File.separator + fileName);
            try (InputStream fileContent = filePart.getInputStream()) {
                Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
            imageUrl = "img/" + fileName;
        } else {
            imageUrl = existingImageUrl;
        }

        if (role.equalsIgnoreCase("Doctor")) {
            DoctorDAO doctorDAO = new DoctorDAO();
            Doctor doctor = doctorDAO.getDoctorById(id);
            doctor.setFullName(fullName);
            doctor.setAddress(address);
            doctor.setPhoneNumber(phone);
            doctor.setGender(gender);
            doctor.setDateOfBirth(dob);
            doctor.setImageUrl(imageUrl);
            doctorDAO.updateDoctor(doctor);
        } else { // Manager or Receptionist
            StaffDAO staffDAO = new StaffDAO();
            Staff staff = staffDAO.getStaffById(id); // A new method that gets any staff by ID
            staff.setFullName(fullName);
            staff.setAddress(address);
            staff.setPhoneNumber(phone);
            staff.setGender(gender);
            staff.setDateOfBirth(dob);
            staff.setImageUrl(imageUrl);
            staffDAO.updateStaff(staff);
        }

        response.sendRedirect("home");
    }
} 