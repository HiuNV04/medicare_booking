/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.admin;

import dal.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import java.io.File;
import java.sql.Date;
import model.Staff;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="UpdateStaffController", urlPatterns={"/updateStaff"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 3, // 3MB
        maxFileSize = 1024 * 1024 * 40, // 40MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class UpdateStaffController extends HttpServlet {
       private static final String UPLOAD_DIRECTORY = "uploads";
  @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);

        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        String dateOfBirthStr = request.getParameter("dateOfBirth");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String role = request.getParameter("role");
        String currentImageUrl = request.getParameter("currentImageUrl");

        // Xử lý file upload (nếu có)
        String imageUrl = currentImageUrl;
        Part filePart = request.getPart("imageUpload");
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = extractFileName(filePart);
            if (fileName != null && !fileName.isEmpty()) {
                String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdirs();
                String filePath = uploadPath + File.separator + fileName;
                filePart.write(filePath);
                imageUrl = UPLOAD_DIRECTORY + "/" + fileName;
            }
        }

        // Xử lý ngày sinh
        Date dateOfBirth = null;
        if (dateOfBirthStr != null && !dateOfBirthStr.isEmpty()) {
            dateOfBirth = java.sql.Date.valueOf(dateOfBirthStr);
        }
        // Update staff object
        Staff s = new Staff();
        s.setId(id);
        s.setImageUrl(imageUrl);
        s.setEmail(email);
        s.setUsername(username);
        s.setPassword(password);
        s.setRole(role);
        s.setFullName(fullName);
        s.setDateOfBirth(dateOfBirth);
        s.setGender(gender);
        s.setAddress(address);
        s.setPhoneNumber(phoneNumber);
        // Trạng thái giữ nguyên, không update ở đây

        boolean updateSuccess = new StaffDAO().updateStaff(s);

        String message = updateSuccess ? "Update successfully" : "Update failed";
        // Trả về lại trang detail
        response.sendRedirect(request.getContextPath() + "/viewStaffDetail?id=" + id + "&message=" + java.net.URLEncoder.encode(message, "UTF-8"));
    }
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        if (contentDisp != null && contentDisp.contains("filename=")) {
            return contentDisp.substring(contentDisp.indexOf("filename=") + 10, contentDisp.length() - 1).replace("\"", "");
        }
        return null;
    }
}
