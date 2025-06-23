package controller.admin;

import dal.DoctorDAO;
import dal.DoctorLevelDAO;
import dal.SpecializationDAO;
import java.io.IOException;
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

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 3, // 3MB
        maxFileSize = 1024 * 1024 * 40, // 40MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
@WebServlet(name = "AddDoctorController", urlPatterns = {"/addDoctor"})
public class AddDoctorController extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "uploads";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DoctorLevelDAO doc = new DoctorLevelDAO();
        SpecializationDAO spec = new SpecializationDAO();
        request.setAttribute("doctorLevel", doc.getDoctorLevel());
        request.setAttribute("specialization", spec.getSpecialization());
        request.getRequestDispatcher("/admin/addDoctor.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        String dateOfBirthStr = request.getParameter("dateOfBirth");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String imageUrl = null;
        int specializationId = Integer.parseInt(request.getParameter("specializationId"));
        int doctorLevelId = Integer.parseInt(request.getParameter("doctorLevelId"));

        // Validate ảnh đại diện (optional, không bắt buộc phải chọn)
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

        // Các trường để forward lại dữ liệu input khi có lỗi
        request.setAttribute("email", email);

        request.setAttribute("imageUpload", imageUrl);
        request.setAttribute("username", username);
        request.setAttribute("password", password);

        request.setAttribute("fullName", fullName);

        request.setAttribute("dateOfBirth", dateOfBirthStr);
        request.setAttribute("gender", gender);
        request.setAttribute("address", address);
        request.setAttribute("phoneNumber", phoneNumber);
        request.setAttribute("specializationId", specializationId);
        request.setAttribute("doctorLevelId", doctorLevelId);

        boolean hasError = false;

        // Validate từng trường, nếu sai thì set error riêng lẻ
        if (email == null || email.trim().isEmpty()) {
            request.setAttribute("emailError", "Không được để trống");
            hasError = true;
        } else if (!email.contains("@")) {
            request.setAttribute("emailError", "Email phải chứa ký tự @");
            hasError = true;
        } else if (new DoctorDAO().checkEmailExists(email)) {
            request.setAttribute("emailError", "Email đã tồn tại");
            hasError = true;
            request.setAttribute("email", ""); // clear input nếu trùng
        }

        if (username == null || username.trim().isEmpty()) {
            request.setAttribute("usernameError", "Không được để trống");
            hasError = true;
        } else if (!username.matches("^[A-Za-z0-9]{5,}$")) {
            request.setAttribute("usernameError", "Username từ 5 ký tự, chỉ chữ/số, không khoảng trắng");
            hasError = true;
        } else if (new DoctorDAO().checkUsernameExists(username)) {
            request.setAttribute("usernameError", "Username đã tồn tại");
            hasError = true;
            request.setAttribute("username", ""); // clear input nếu trùng
        }

        if (password == null || password.isEmpty()) {
            request.setAttribute("passwordError", "Không được để trống");
            hasError = true;
        } else if (!password.matches("^(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>])(?=\\S+$).{8,}$")) {
            request.setAttribute("passwordError", "Mật khẩu 8 ký tự, 1 in hoa, 1 ký tự đặc biệt, không dấu cách");
            hasError = true;
        }
        // Không set lại password để tránh lộ thông tin

        if (fullName == null || fullName.trim().isEmpty()) {
            request.setAttribute("fullNameError", "Không được để trống");
            hasError = true;
        }

        if (dateOfBirthStr == null || dateOfBirthStr.trim().isEmpty()) {
            request.setAttribute("dateOfBirthError", "Không được để trống");
            hasError = true;
        } else {
            try {
                LocalDate dob = LocalDate.parse(dateOfBirthStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                if (dob.isAfter(LocalDate.now())) {
                    request.setAttribute("dateOfBirthError", "Không được chọn ngày trong tương lai");
                    hasError = true;
                }
            } catch (Exception ex) {
                request.setAttribute("dateOfBirthError", "Ngày sinh không hợp lệ");
                hasError = true;
            }
        }

        if (address == null || address.trim().isEmpty()) {
            request.setAttribute("addressError", "Không được để trống");
            hasError = true;
        }

        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            request.setAttribute("phoneNumberError", "Không được để trống");
            hasError = true;
        } else if (!phoneNumber.matches("^[0-9]{9,12}$")) {
            request.setAttribute("phoneNumberError", "Số điện thoại chỉ chứa số, từ 9-12 ký tự");
            hasError = true;
        }

        if (hasError) {
            DoctorLevelDAO doc = new DoctorLevelDAO();
            SpecializationDAO spec = new SpecializationDAO();
            request.setAttribute("doctorLevel", doc.getDoctorLevel());
            request.setAttribute("specialization", spec.getSpecialization());
            request.getRequestDispatcher("/admin/addDoctor.jsp").forward(request, response);
            return;

        }

        // Nếu không lỗi, insert vào DB
        LocalDate dateOfBirth = null;
        if (dateOfBirthStr != null && !dateOfBirthStr.trim().isEmpty()) {
            dateOfBirth = LocalDate.parse(dateOfBirthStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        boolean add = new DoctorDAO().addDoctor(
                imageUrl, email, username, password, "Doctor", fullName,
                dateOfBirth, gender, address, phoneNumber, doctorLevelId, specializationId
        );

        if (add) {
            String encodedMsg = java.net.URLEncoder.encode("Added successfully", "UTF-8");
            response.sendRedirect(request.getContextPath() + "/addDoctor?message=" + encodedMsg);
        } else {
            String encodedMsg = java.net.URLEncoder.encode("Added failed", "UTF-8");
            response.sendRedirect(request.getContextPath() + "/addDoctor?message=" + encodedMsg);
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
