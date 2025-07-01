/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.StaffDAO;
import model.Staff;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
 import java.sql.Date;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "AddStaffController", urlPatterns = {"/addStaff"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 3,
        maxFileSize = 1024 * 1024 * 40,
        maxRequestSize = 1024 * 1024 * 50
)
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
   
            request.setCharacterEncoding("UTF-8");
            String imageUrl = null;
            Part filePart = request.getPart("imageUpload");
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = extractFileName(filePart);
                if (fileName != null && !fileName.isEmpty()) {
                    String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdirs();
                    }
                    String filePath = uploadPath + File.separator + fileName;
                    filePart.write(filePath);
                    imageUrl = UPLOAD_DIRECTORY + "/" + fileName;
                }
            }

            String email = request.getParameter("email");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String fullName = request.getParameter("fullName");
            String dateOfBirthStr = request.getParameter("dateOfBirth");
            String gender = request.getParameter("gender");
            String address = request.getParameter("address");
            String phoneNumber = request.getParameter("phoneNumber");
            String role = request.getParameter("role");

            boolean hasError = false;
            // Simple server validation (bạn có thể mở rộng thêm như ở trên)
            if (email == null || email.trim().isEmpty()) {
                request.setAttribute("emailError", "Không được để trống");
                hasError = true;
            } else if (new StaffDAO().checkEmailExists(email)) {
                request.setAttribute("emailError", "Email đã tồn tại");
                hasError = true;
            }

            if (username == null || username.trim().isEmpty()) {
                request.setAttribute("usernameError", "Không được để trống");
                hasError = true;
            } else if (new StaffDAO().checkUsernameExists(username)) {
                request.setAttribute("usernameError", "Username đã tồn tại");
                hasError = true;
            }

            if (password == null || password.length() < 6) {
                request.setAttribute("passwordError", "Mật khẩu ít nhất 6 ký tự");
                hasError = true;
            }
            if (fullName == null || fullName.trim().isEmpty()) {
                request.setAttribute("fullNameError", "Không được để trống");
                hasError = true;
            }
            if (dateOfBirthStr == null || dateOfBirthStr.trim().isEmpty()) {
                request.setAttribute("dateOfBirthError", "Không được để trống");
                hasError = true;
            }
            if (address == null || address.trim().isEmpty()) {
                request.setAttribute("addressError", "Không được để trống");
                hasError = true;
            }
            if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
                request.setAttribute("phoneNumberError", "Không được để trống");
                hasError = true;
            }
            if (role == null || role.trim().isEmpty()) {
                request.setAttribute("roleError", "Vui lòng chọn vai trò");
                hasError = true;
            }

            // Forward lại input nếu có lỗi
            request.setAttribute("email", email);
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("fullName", fullName);
            request.setAttribute("dateOfBirth", dateOfBirthStr);
            request.setAttribute("gender", gender);
            request.setAttribute("address", address);
            request.setAttribute("phoneNumber", phoneNumber);
            request.setAttribute("role", role);

            if (hasError) {
                request.getRequestDispatcher("/admin/addStaff.jsp").forward(request, response);
                return;
            }

            Date dateOfBirth = null;
            if (dateOfBirthStr != null && !dateOfBirthStr.isEmpty()) {
               dateOfBirth = java.sql.Date.valueOf(dateOfBirthStr);
            }

            Staff staff = new Staff(0, imageUrl, email, username, password, role, fullName, dateOfBirth, gender, address, phoneNumber, false);

            boolean success = new StaffDAO().addStaff(staff);
            String message = success ? "Thêm thành công" : "Thêm thất bại";
            request.setAttribute("message", message);
            if (success) {
                response.sendRedirect(request.getContextPath() + "/manageStaff?message=" + java.net.URLEncoder.encode(message, "UTF-8"));
            } else {
                request.getRequestDispatcher("/admin/addStaff.jsp").forward(request, response);
            }
        }
    
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        if (contentDisp != null && contentDisp.contains("filename=")) {
            return contentDisp.substring(contentDisp.indexOf("filename=") + 10, contentDisp.length() - 1).replace("\"", "");
        }
        return null;
    }

}
