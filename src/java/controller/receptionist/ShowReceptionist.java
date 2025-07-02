/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.receptionist;

 import dal.SonDAO;
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
import model.Receptionist;
import model.Receptionist1;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB Kích thước file tối đa được giữ trong RAM, nếu vượt thì lưu vào file tạm
        maxFileSize = 1024 * 1024 * 20, // 20MB Kích thước tối đa của 1 file được upload
        maxRequestSize = 1024 * 1024 * 25 // 25MB Tổng kích thước tối đa của toàn bộ request (nếu upload nhiều file)
)

@WebServlet(name = "ShowReceptionist", urlPatterns = {"/showReceptionist"})
public class ShowReceptionist extends HttpServlet {

    SonDAO dao = new SonDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        String username = (String) request.getSession().getAttribute("username");
//        Receptionist r = rdao.getAReceptionistByEmail(username);
        Receptionist1 r = dao.getAReceptionistByEmail("receptionist1@medicare.com");
        HttpSession session = request.getSession();
        int totalPatients = dao.getTotalPatients();
        int totalAppointments = dao.getTotalAppoinment();
        session.setAttribute("receptionist", r);
        session.setAttribute("id", String.valueOf(r.getId()));
        session.setAttribute("totalPatients", totalPatients);
        session.setAttribute("totalAppointments", totalAppointments);
        request.getRequestDispatcher("/receptionist/receptionistProfile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String id_raw = (String) request.getSession().getAttribute("id");
            String fullname = request.getParameter("fullname");
            String gender = request.getParameter("gender");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");

            String error = util.Exception.checkPhoneNumber(phone);
            if (error != null) {
                request.setAttribute("error", error);
                request.getRequestDispatcher("/receptionist/receptionistProfile.jsp").forward(request, response);
            }

            Receptionist1 current = (Receptionist1) request.getSession().getAttribute("receptionist");
            String imageUrl = current.getImg(); //lấy ảnh cũ để cho vào 

            Part filePart = request.getPart("image");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            
            if (fileName != null && !fileName.isEmpty()) {
                if (!util.Exception.checkImage(filePart)) {
                    request.setAttribute("error", "Chỉ được tải lên file ảnh (.jpg, .jpeg, .png, .gif, .webp)");
                    request.getRequestDispatcher("/receptionist/receptionistProfile.jsp").forward(request, response);
                    return;
                }
                // Lưu ảnh mới
                String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
                System.out.println("Upload path: " + uploadPath);
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                String filePath = uploadPath + File.separator + fileName;
                filePart.write(filePath);
                imageUrl = "uploads/" + fileName;

            }

            Receptionist1 rUpdate = new Receptionist1(fullname, gender, phone, address, imageUrl);

            int id = Integer.parseInt(id_raw);
            dao.update(rUpdate, id);
            response.sendRedirect("showReceptionist");
        } catch (Exception e) {
            System.out.println("bug : " + e.getMessage());
        }
    }
}
