/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

/**
 *
 * @author ADMIN
 */
@WebServlet(urlPatterns = {"/updateStaff"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 3, // 3MB
        maxFileSize = 1024 * 1024 * 40, // 40MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)

public class UpdateStaffController extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "uploads";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String fullName = request.getParameter("fullName");
        String dateOfBirthStr = request.getParameter("dateOfBirth");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        LocalDate dateOfBirth = null;
        String imageUrl = null;

        if (dateOfBirthStr != null && !dateOfBirthStr.isEmpty()) {
            dateOfBirth = LocalDate.parse(dateOfBirthStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        // Xử lý file upload
        Collection<Part> parts = request.getParts();
        for (Part part : parts) {
            if (part.getName().equals("imageUpload") && part.getSize() > 0) {
                String fileName = extractFileName(part); // Trích xuất tên file
                if (fileName != null && !fileName.isEmpty()) {
                    if (!fileName.matches("(?i).*\\.(jpg|jpeg|png|gif)$")) { // Thêm (?i) để không phân biệt hoa thường
                        String encodedMsg = java.net.URLEncoder.encode("Only image files (jpg, jpeg, png, gif) are allowed.", "UTF-8");
                        response.sendRedirect("viewStaffDetail?id=" + id + "&message=" + encodedMsg);

                        return;
                    }
                    String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdirs(); // Tạo thư mục nếu không tồn tại
                    }
                    String filePath = uploadPath + File.separator + fileName;
                    File storeFile = new File(filePath);
                    part.write(storeFile.getAbsolutePath());
                    imageUrl = UPLOAD_DIRECTORY + "/" + fileName;
                }
            }
        }
        if (imageUrl == null || imageUrl.isEmpty()) {
            imageUrl = request.getParameter("currentImageUrl");
        }

        StaffDAO dao = new StaffDAO();
        boolean updated = dao.updateStaff(id, email, imageUrl, username, fullName, address, dateOfBirth, gender, address, phoneNumber);
        if (updated) {
            String encodedMsg = java.net.URLEncoder.encode("Update successfully", "UTF-8");
            response.sendRedirect("viewStaffDetail?id=" + id + "&message=" + encodedMsg);
        } else {
            String encodedMsg = java.net.URLEncoder.encode("Update failed", "UTF-8");
            response.sendRedirect("viewStaffDetail?id=" + id + "&message=" + encodedMsg);
        }

    }

    // Phương thức hỗ trợ trích xuất tên file từ Part
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        if (contentDisp != null && contentDisp.contains("filename=")) {
            return contentDisp.substring(contentDisp.indexOf("filename=") + 10, contentDisp.length() - 1).replace("\"", "");
        }
        return null;
    }
}
