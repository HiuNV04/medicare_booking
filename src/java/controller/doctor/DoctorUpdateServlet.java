package controller.doctor;

import dal.DoctorDAO;
import java.io.File;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import model.Doctor;

@MultipartConfig(
        maxFileSize = 2 * 1024 * 1024, // 2MB
        maxRequestSize = 5 * 1024 * 1024 // 5MB
)

//@WebServlet(name = "DoctorUpdateServlet", urlPatterns = {"/DoctorUpdateServlet"})
public class DoctorUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        DoctorDAO dao = new DoctorDAO();
        Doctor doctor = dao.getDoctorById(id);

        Doctor sessionDoctor = (Doctor) request.getSession().getAttribute("doctor");

        if (doctor == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Bác sĩ không tồn tại.");
            return;
        }

        boolean isOwner = sessionDoctor != null && sessionDoctor.getId() == id;

        request.setAttribute("doctor", doctor);
        request.setAttribute("isOwner", isOwner);
        request.getRequestDispatcher("/doctor/profile/detail_profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Doctor sessionDoctor = (Doctor) request.getSession().getAttribute("doctor");
        int id = Integer.parseInt(request.getParameter("id"));

        // 🔐 Kiểm tra quyền cập nhật
        if (sessionDoctor == null || sessionDoctor.getId() != id) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền cập nhật hồ sơ này.");
            return;
        }

        DoctorDAO dao = new DoctorDAO();
        Doctor doctor = dao.getDoctorById(id);
        if (doctor == null) {
            response.sendRedirect("login_doctor.jsp");
            return;
        }

        // ✅ Cập nhật thông tin hồ sơ
        doctor.setFullName(request.getParameter("fullName"));
        doctor.setAddress(request.getParameter("address"));
        doctor.setGender(request.getParameter("gender"));
        doctor.setPhoneNumber(request.getParameter("phoneNumber"));
        doctor.setEmail(request.getParameter("email"));
        doctor.setDateOfBirth(java.sql.Date.valueOf(request.getParameter("dateOfBirth")));
        doctor.setNote(request.getParameter("note"));

        try {
            Part filePart = request.getPart("imageFile");
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = "doctor" + id + ".jpg";

                // 📁 Ghi vào thư mục thật của project
                String projectPath = System.getProperty("user.dir");
                String hardPath = projectPath + "/web/doctor/img";
                File hardDir = new File(hardPath);
                if (!hardDir.exists()) {
                    hardDir.mkdirs();
                }

                File savedFile = new File(hardDir, fileName);
                filePart.write(savedFile.getAbsolutePath()); // ✅ Ghi đúng 1 lần

                // 📁 Copy sang thư mục Tomcat (deploy)
                String deployPath = getServletContext().getRealPath("/doctor/img");
                File deployDir = new File(deployPath);
                if (!deployDir.exists()) {
                    deployDir.mkdirs();
                }

                Files.copy(
                        savedFile.toPath(),
                        new File(deployDir, fileName).toPath(),
                        StandardCopyOption.REPLACE_EXISTING
                );

                doctor.setImageUrl("doctor/img/" + fileName);
            }

            dao.updateDoctor(doctor);
            request.setAttribute("message", "Cập nhật thành công!");
        } catch (IllegalStateException e) {
            request.setAttribute("error", "Dữ liệu gửi vượt quá giới hạn!");
        }

        Doctor updatedDoctor = dao.getDoctorById(id);
        request.setAttribute("doctor", updatedDoctor);
        request.setAttribute("isOwner", true);
        request.getRequestDispatcher("/doctor/profile/detail_profile.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Handles update for doctor profile";
    }
}
