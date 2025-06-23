package controllerlogin;

import dal.AccountDAO;
import dal.PatientDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "ResetPasswordController", urlPatterns = {"/reset-password"})
public class ResetPasswordController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String token = request.getParameter("token");

        PatientDAO dao = new PatientDAO();
        boolean valid = dao.isValidResetToken(token);

        if (valid) {
            request.setAttribute("token", token); // Để form hidden
            request.getRequestDispatcher("reset-password.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Link đặt lại mật khẩu không hợp lệ hoặc đã hết hạn.");
            request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String token = request.getParameter("token");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!password.equals(confirmPassword)) {
            request.setAttribute("token", token);
            request.setAttribute("message", "Mật khẩu nhập lại không khớp.");
            request.getRequestDispatcher("reset-password.jsp").forward(request, response);
            return;
        }

        PatientDAO dao = new PatientDAO();
        String toen = token;
        boolean updated = dao.updatePasswordByToken(token, password);

        if (updated) {
            String email = dao.getEmailByToken(token);
            if (email != null && !email.isEmpty()) {
                utils.EmailUtils.sendPasswordChangedEmail(email);
            }

            request.setAttribute("success", "Đặt lại mật khẩu thành công. Vui lòng đăng nhập.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Token không hợp lệ hoặc đã hết hạn.");
            request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
        }
    }
}
