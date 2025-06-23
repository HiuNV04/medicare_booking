package controller.receptionist;

import dal.PatientDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Patient;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 1024 * 1024 * 20, // 20MB
        maxRequestSize = 1024 * 1024 * 25 // 25MB
)
@WebServlet(name = "ReUpdatePatient", urlPatterns = {"/reUpdatePatient"})
public class ReUpdatePatient extends HttpServlet {

    PatientDAO pdao = new PatientDAO();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id_raw = request.getParameter("id");
        try {
            if (id_raw == null || id_raw.trim().isEmpty()) {
                request.setAttribute("error", "ID bệnh nhân không hợp lệ.");
                request.getRequestDispatcher("/receptionist/reUpdatePatient.jsp").forward(request, response);
                return;
            }
            int id = Integer.parseInt(id_raw);
            Patient p = pdao.getAPatientById(id);
            if (p == null) {
                request.setAttribute("error", "Không tìm thấy bệnh nhân với ID: " + id);
                request.getRequestDispatcher("/receptionist/reUpdatePatient.jsp").forward(request, response);
                return;
            }
            request.setAttribute("p", p);
            request.getRequestDispatcher("/receptionist/reUpdatePatient.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "ID bệnh nhân không hợp lệ.");
            request.getRequestDispatcher("/receptionist/reUpdatePatient.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id_raw = request.getParameter("id");
            String fullname = request.getParameter("fullname");
            String dob = request.getParameter("dob");
            String gender = request.getParameter("gender");
            String identity = request.getParameter("identity");
            String insurance = request.getParameter("insurance");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");

            // Kiểm tra ID
            if (id_raw == null || id_raw.trim().isEmpty()) {
                request.setAttribute("error", "ID bệnh nhân không hợp lệ.");
                request.getRequestDispatcher("/receptionist/reUpdatePatient.jsp").forward(request, response);
                return;
            }
            int id = Integer.parseInt(id_raw);

            // Kiểm tra các trường bắt buộc
            if (fullname == null || fullname.trim().isEmpty()) {
                request.setAttribute("error", "Họ và tên không được để trống.");
                Patient p = pdao.getAPatientById(id);
                request.setAttribute("p", p);
                request.getRequestDispatcher("/receptionist/reUpdatePatient.jsp").forward(request, response);
                return;
            }

            if (dob == null || dob.trim().isEmpty()) {
                request.setAttribute("error", "Ngày sinh không được để trống.");
                Patient p = pdao.getAPatientById(id);
                request.setAttribute("p", p);
                request.getRequestDispatcher("/receptionist/reUpdatePatient.jsp").forward(request, response);
                return;
            }

            // Kiểm tra số điện thoại
            String error = util.Exception.checkPhoneNumber(phone);
            if (error != null) {
                request.setAttribute("error", error);
                Patient p = pdao.getAPatientById(id);
                request.setAttribute("p", p);
                request.getRequestDispatcher("/receptionist/reUpdatePatient.jsp").forward(request, response);
                return;
            }

            // Kiểm tra ngày sinh
            Date dateOfBirth;
            try {
                dateOfBirth = sdf.parse(dob);
            } catch (Exception e) {
                request.setAttribute("error", "Định dạng ngày sinh không hợp lệ. Vui lòng sử dụng định dạng yyyy-MM-dd.");
                Patient p = pdao.getAPatientById(id);
                request.setAttribute("p", p);
                request.getRequestDispatcher("/receptionist/reUpdatePatient.jsp").forward(request, response);
                return;
            }

            Date today = new Date();
            if (dateOfBirth.after(today)) {
                request.setAttribute("error", "Ngày sinh không được lớn hơn ngày hiện tại.");
                Patient p = pdao.getAPatientById(id);
                request.setAttribute("p", p);
                request.getRequestDispatcher("/receptionist/reUpdatePatient.jsp").forward(request, response);
                return;
            }

            // Xử lý ảnh
            Patient current = pdao.getAPatientById(id);
            if (current == null) {
                request.setAttribute("error", "Không tìm thấy bệnh nhân với ID: " + id);
                request.getRequestDispatcher("/receptionist/reUpdatePatient.jsp").forward(request, response);
                return;
            }
            String imageUrl = current.getImg();

            Part filePart = request.getPart("image");
            String fileName = null;

            if (filePart != null && filePart.getSize() > 0) {
                fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                if (!util.Exception.checkImage(filePart)) {
                    request.setAttribute("error", "Chỉ được tải lên file ảnh (.jpg, .jpeg, .png, .gif, .webp)");
                    request.setAttribute("p", current);
                    request.getRequestDispatcher("/receptionist/reUpdatePatient.jsp").forward(request, response);
                    return;
                }

                String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                String filePath = uploadPath + File.separator + fileName;
                filePart.write(filePath);
                imageUrl = "Uploads/" + fileName;
            }

            // Tạo đối tượng Patient để cập nhật
            Patient pUpdate = new Patient(id, fullname, gender, dob, identity, insurance, phone, address, imageUrl);

            // Cập nhật bệnh nhân
            pdao.reUpdatePatient(pUpdate, id);
            response.sendRedirect("reViewPatient");
        } catch (NumberFormatException e) {
            request.setAttribute("error", "ID bệnh nhân không hợp lệ.");
            request.getRequestDispatcher("/receptionist/reUpdatePatient.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Đã xảy ra lỗi khi cập nhật thông tin: " + e.getMessage());
            request.getRequestDispatcher("/receptionist/reUpdatePatient.jsp").forward(request, response);
        }
    }
}
