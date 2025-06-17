package utils;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailUtils {

    private static final String FROM_EMAIL = "trungkien2981412@gmail.com";
    private static final String APP_PASSWORD = "jssa nrmt rbwm jphj";

    // Hàm gửi email chung
    public static boolean sendEmail(String to, String subject, String content) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, APP_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL, "Medicio"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(MimeUtility.encodeText(subject, "UTF-8", "B"));
            message.setContent(content, "text/html; charset=UTF-8");

            Transport.send(message);
            System.out.println("✅ Gửi email thành công tới: " + to);
            return true;

        } catch (Exception e) {
            System.err.println("❌ Gửi mail thất bại: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private static String buildEmailTemplate(String bodyContent) {
        return "<!DOCTYPE html>"
                + "<html lang='vi'>"
                + "<head>"
                + "    <meta charset='UTF-8'>"
                + "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                + "    <style>"
                + "        body { font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; background-color: #f7f9fc; margin: 0; padding: 0; }"
                + "        .email-wrapper { max-width: 600px; margin: 40px auto; background-color: #ffffff; border-radius: 10px; overflow: hidden; box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05); }"
                + "        .email-header { background-color: #ffffff; border-bottom: 1px solid #eaeaea; padding: 24px 32px; text-align: center; }"
                + "        .email-header h1 { color: #2c3e50; font-size: 24px; margin: 0; font-weight: bold; }"
                + "        .email-body { padding: 32px; color: #333333; font-size: 15px; line-height: 1.7; }"
                + "        .email-body p { margin: 0 0 16px; }"
                + "        .email-footer { text-align: center; padding: 24px; font-size: 13px; color: #999999; border-top: 1px solid #eaeaea; background-color: #fafafa; }"
                + "        .email-footer a { color: #3FBBC0; text-decoration: none; }"
                + "        .btn { display: inline-block; padding: 12px 28px; background-color: #3FBBC0; color: #ffffff !important; text-decoration: none; border-radius: 50px; font-size: 15px; font-weight: bold; margin: 24px 0; }"
                + "        .btn:hover { background-color: #36a7ab; }"
                + "    </style>"
                + "</head>"
                + "<body>"
                + "    <div class='email-wrapper'>"
                + "        <div class='email-header'>"
                + "            <h1>MediCare Booking</h1>"
                + "        </div>"
                + "        <div class='email-body'>"
                + bodyContent
                + "        </div>"
                + "        <div class='email-footer'>"
                + "            <p>© 2025 MediCare Booking</p>"
                + "            <p>Website: <a href='https://medicare-booking.vn'>medicare-booking.vn</a></p>"
                + "            <p>Hotline: <a href='tel:+84901234567'>0901 234 567</a></p>"
                + "        </div>"
                + "    </div>"
                + "</body>"
                + "</html>";
    }

    public static boolean sendForgotPasswordEmail(String to, String resetLink) {
        String subject = "Yêu cầu đặt lại mật khẩu - MediCare Booking";
        String bodyContent = "<p>Xin chào,</p>"
                + "<p>Chúng tôi nhận được yêu cầu đặt lại mật khẩu cho tài khoản của bạn trên <strong>MediCare Booking</strong>.</p>"
                + "<p>Vui lòng nhấn vào nút bên dưới để đặt lại mật khẩu mới:</p>"
                + "<p style='text-align: center;'>"
                + "    <a href='" + resetLink + "' class='btn'>Đặt lại mật khẩu</a>"
                + "</p>"
                + "<p>Liên kết có hiệu lực trong <strong>30 phút</strong>. Nếu bạn không yêu cầu, vui lòng bỏ qua email này.</p>"
                + "<p>Trân trọng,<br>Đội ngũ MediCare Booking</p>";
        return sendEmail(to, subject, buildEmailTemplate(bodyContent));
    }

    public static boolean sendPasswordChangedEmail(String to) {
        String subject = "Xác nhận thay đổi mật khẩu thành công - MediCare Booking";
        String bodyContent = "<p>Xin chào,</p>"
                + "<p>Bạn vừa thay đổi mật khẩu thành công cho tài khoản trên <strong>MediCare Booking</strong>.</p>"
                + "<p>Nếu bạn KHÔNG thực hiện thao tác này, vui lòng liên hệ với chúng tôi ngay lập tức để đảm bảo an toàn tài khoản.</p>"
                + "<p>Trân trọng,<br>Đội ngũ MediCare Booking</p>";
        return sendEmail(to, subject, buildEmailTemplate(bodyContent));
    }

}
