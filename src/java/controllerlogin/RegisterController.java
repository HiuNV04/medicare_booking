package controllerlogin;

import dal.PatientDAO;
import model.Patient;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet(name = "RegisterController", urlPatterns = {"/register"})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,      // 1MB
    maxFileSize = 5 * 1024 * 1024,        // 5MB
    maxRequestSize = 10 * 1024 * 1024     // 10MB
)
public class RegisterController extends HttpServlet {

    private static final String UPLOAD_DIR = "uploads/avatars";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // Hiện trang đăng ký
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Lấy các trường form text
        String imageUrl = request.getParameter("image_url");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String fullName = request.getParameter("full_name");
        String dateOfBirthStr = request.getParameter("date_of_birth");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phone_number");
        String identityNumber = request.getParameter("identity_number");
        String insuranceNumber = request.getParameter("insurance_number");
        String statusStr = request.getParameter("status");
        boolean status = "1".equals(statusStr);

        // Validate cơ bản
        if (email == null || email.isEmpty() ||
            username == null || username.isEmpty() ||
            password == null || password.isEmpty() ||
            confirmPassword == null || confirmPassword.isEmpty() ||
            !password.equals(confirmPassword)) {
            request.setAttribute("error", "Vui lòng nhập đủ thông tin và mật khẩu lặp lại phải khớp.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        PatientDAO dao = new PatientDAO();

        // Kiểm tra trùng email/username
        if (dao.getPatientByEmail(email) != null) {
            request.setAttribute("error", "Email đã được sử dụng!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        if (dao.getPatientByUsername(username) != null) {
            request.setAttribute("error", "Tên đăng nhập đã tồn tại!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Xử lý upload file ảnh nếu có
        String fileUrl = null;
        Part imagePart = request.getPart("image_file");
        if (imagePart != null && imagePart.getSize() > 0) {
            String fileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
            String appPath = getServletContext().getRealPath("");
            String savePath = appPath + File.separator + UPLOAD_DIR;
            File uploadDir = new File(savePath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
            String filePath = savePath + File.separator + uniqueFileName;
            imagePart.write(filePath);

            // Đường dẫn để lưu vào DB
            fileUrl = UPLOAD_DIR + "/" + uniqueFileName;
        }

        // Ưu tiên ảnh upload, nếu không có thì lấy link nhập
        String finalImageUrl = (fileUrl != null) ? fileUrl : imageUrl;

        // Parse ngày sinh
        LocalDate dob = null;
        try {
            if (dateOfBirthStr != null && !dateOfBirthStr.isEmpty())
                dob = LocalDate.parse(dateOfBirthStr);
        } catch (Exception e) {
            request.setAttribute("error", "Ngày sinh không hợp lệ!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Tạo Patient object
        Patient patient = new Patient();
        patient.setImageUrl(finalImageUrl);
        patient.setEmail(email);
        patient.setUsername(username);
        patient.setPassword(password); // (Khuyến nghị mã hóa trước khi lưu)
        patient.setRole("Patient");
        patient.setFullName(fullName);
        patient.setDateOfBirth(dob);
        patient.setGender(gender);
        patient.setAddress(address);
        patient.setPhoneNumber(phoneNumber);
        patient.setIdentityNumber(identityNumber);
        patient.setInsuranceNumber(insuranceNumber);
        patient.setStatus(status);

        // Thêm vào DB
        boolean result = dao.addPatient(patient);
        if (result) {
            request.setAttribute("success", "Đăng ký thành công! Bạn có thể đăng nhập.");
        } else {
            request.setAttribute("error", "Đăng ký thất bại. Vui lòng thử lại.");
        }
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "RegisterController - Xử lý đăng ký bệnh nhân";
    }
}
