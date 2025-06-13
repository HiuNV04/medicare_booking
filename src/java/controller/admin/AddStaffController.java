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
import model.Staff;

/**
 *
 * @author ADMIN
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 3, // 3MB
        maxFileSize = 1024 * 1024 * 40, // 40MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
@WebServlet(name = "AddStaffController", urlPatterns = {"/addStaff"})
public class AddStaffController extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "uploads";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/admin/addStaff.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy dữ liệu từ form
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        String dateOfBirthStr = request.getParameter("dateOfBirth");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String role = request.getParameter("role");
        String imageUrl = null;

        // Xử lý ngày sinh (có thể null)
        LocalDate dateOfBirth = null;
        if (dateOfBirthStr != null && !dateOfBirthStr.trim().isEmpty()) {
            dateOfBirth = LocalDate.parse(dateOfBirthStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        // Xử lý file ảnh
        Collection<Part> parts = request.getParts();
        for (Part part : parts) {
            if ("imageUpload".equals(part.getName()) && part.getSize() > 0) {
                String fileName = extractFileName(part);
                if (fileName != null && !fileName.isEmpty()) {
                    String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdirs();
                    }
                    String filePath = uploadPath + File.separator + fileName;
                    part.write(filePath);
                    imageUrl = UPLOAD_DIRECTORY + "/" + fileName;
                }
            }
        }

        // Thêm vào DB
        StaffDAO dao = new StaffDAO();
        boolean insert = dao.insertStaff(imageUrl, email, username, password, role, fullName, dateOfBirth, gender, address, phoneNumber);

        if (insert) {
            String encodedMsg = java.net.URLEncoder.encode("Added successfully", "UTF-8");
            response.sendRedirect(request.getContextPath() + "/addStaff?message=" + encodedMsg);
        } else {
            String encodedMsg = java.net.URLEncoder.encode("Added failed", "UTF-8");
            response.sendRedirect(request.getContextPath() + "/addStaff?message=" + encodedMsg);
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
