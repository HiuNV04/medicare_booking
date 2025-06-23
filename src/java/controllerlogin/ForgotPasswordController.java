package controllerlogin;

import dal.AccountDAO;
import dal.PatientDAO;
import model.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import java.util.Calendar;
import model.Patient;
import utils.EmailUtils;

@WebServlet(name = "ForgotPasswordController", urlPatterns = {"/forgot-password"})
public class ForgotPasswordController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Mặc định GET → mở form forgot-password.jsp
        request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
    }

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");

        PatientDAO dao = new PatientDAO();
         Patient acc = dao.getAccountByEmail1(email);

        if (acc != null) {
            String token = UUID.randomUUID().toString();

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MINUTE, 30); // 30 phút
            Date expiry = cal.getTime();

            dao.updateResetToken(acc.getId(), token, expiry);

            // URL động
            String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
            String resetLink = appUrl + "/reset-password?token=" + token;

            // Gửi email
            utils.EmailUtils.sendForgotPasswordEmail(email, resetLink);

            request.setAttribute("message", "Đã gửi link đặt lại mật khẩu tới email của bạn.");
        } else {
            request.setAttribute("message", "Email không tồn tại trong hệ thống.");
        }

        request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
    }
}
