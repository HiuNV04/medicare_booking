package controller;

import dal.PatientDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Patient;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB Kích thước file tối đa được giữ trong RAM, nếu vượt thì lưu vào file tạm
        maxFileSize = 1024 * 1024 * 20, // 20MB Kích thước tối đa của 1 file được upload
        maxRequestSize = 1024 * 1024 * 25 // 25MB Tổng kích thước tối đa của toàn bộ request (nếu upload nhiều file)
)

@WebServlet(name = "ShowPatient", urlPatterns = {"/showPatient"})
public class ShowPatient extends HttpServlet {

    PatientDAO pdao = new PatientDAO();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        String username = (String) request.getSession().getAttribute("username");
//        Patient p = pdao.getAPatient(new Account(username));
        Patient p = pdao.getAPatientByEmail("pat1@gmail.com");
        HttpSession session = request.getSession();
        session.setAttribute("patient", p);
        session.setAttribute("id", String.valueOf(p.getId()));
        request.getRequestDispatcher("/patient/patientProfile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        Patient p = (Patient) session.getAttribute("patient");
// Dùng thông tin:
//        int id = p.getId();

        try {
            String id_raw = (String) request.getSession().getAttribute("id");
            String dob = request.getParameter("dob");
            String gender = request.getParameter("gender");
            String identity = request.getParameter("identity");
            String insurance = request.getParameter("insurance");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");

            //xử lí các lỗi nhập kh hợp lệ
            String error = util.Exception.checkPhoneNumber(phone);
            if (error != null) {
                request.setAttribute("error", error);
                request.getRequestDispatcher("/patient/patientProfile.jsp").forward(request, response);
            }

            //xử lí ngày sinh kh vượt quá thời gian hiện tại
            Date dateOfBirth = sdf.parse(dob);
            Date today = new Date();
            if (dateOfBirth.after(today)) {
                request.setAttribute("error", "Ngày sinh không được lớn hơn ngày hiện tại.");
                request.getRequestDispatcher("/patient/patientProfile.jsp").forward(request, response);
                return; 
            }

            //xử lí ảnh tải lên
            Patient current = (Patient) request.getSession().getAttribute("patient");
            String imageUrl = current.getImg(); //lấy ảnh cũ để cho vào 

            Part filePart = request.getPart("image");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            if (fileName != null && !fileName.isEmpty()) {
                if (!util.Exception.checkImage(filePart)) {
                    request.setAttribute("error", "Chỉ được tải lên file ảnh (.jpg, .jpeg, .png, .gif, .webp)");
                    request.getRequestDispatcher("/patient/patientProfile.jsp").forward(request, response);
                    return;
                }
                // Lưu ảnh mới
                String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                String filePath = uploadPath + File.separator + fileName;
                filePart.write(filePath);
                imageUrl = "uploads/" + fileName;
            }

            Patient pUpdate = new Patient(dob, gender, identity, insurance, phone, address, imageUrl);
            int id;

            id = Integer.parseInt(id_raw);
            pdao.updatePatient(pUpdate, id);
            response.sendRedirect("showPatient");
        } catch (ServletException | IOException | NumberFormatException | ParseException e) {
            System.out.println("bug : " + e.getMessage());
        }
    }
}
