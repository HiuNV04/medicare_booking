package controller.doctor;

import dal.DoctorDAO;
import java.io.File;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import model.Doctor;

@MultipartConfig(
        maxFileSize = 2 * 1024 * 1024, // 2MB
        maxRequestSize = 5 * 1024 * 1024 // 5MB
)

public class DoctorUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DoctorDAO dao = new DoctorDAO();

        int id = Integer.parseInt(request.getParameter("id"));
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
        DoctorDAO dao = new DoctorDAO();

        Doctor sessionDoctor = (Doctor) request.getSession().getAttribute("doctor");
        int id = Integer.parseInt(request.getParameter("id"));

        if (sessionDoctor == null || sessionDoctor.getId() != id) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền cập nhật hồ sơ này.");
            return;
        }
        Doctor doctor = dao.getDoctorById(id);
        if (doctor == null) {
            response.sendRedirect("login_doctor.jsp");
            return;
        }

        // cập nhật thông tin hồ sơ
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

                // 1. Thư mục lưu ảnh
                String uploadFolder = "D:/MediCareUploads/doctor/";
                File uploadDir = new File(uploadFolder);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                    System.out.println("? Đã tạo thư mục: " + uploadFolder);
                }

                // 2. Đường dẫn lưu file
                String uploadPath = uploadFolder + fileName;
                System.out.println("? Đang lưu ảnh vào: " + uploadPath);

                // 3. Lưu ảnh vào ổ đĩa
                filePart.write(uploadPath);
                System.out.println(" Upload ảnh thành công: " + fileName);

                // 4. Cập nhật imageUrl để phục vụ từ servlet
                doctor.setImageUrl("ImageServlet?file=" + fileName);
            }
            dao.updateDoctor(doctor);
            request.setAttribute("message", "Cập nhật thành công!");

        } catch (IllegalStateException e) {
            request.setAttribute("error", "Dữ liệu gửi vượt quá giới hạn!");
            e.printStackTrace();
        }

        Doctor updatedDoctor = dao.getDoctorById(id);
        request.setAttribute("doctor", updatedDoctor);
        request.setAttribute("isOwner", true);
        request.getRequestDispatcher("/doctor/profile/detail_profile.jsp").forward(request, response);
    }
}
