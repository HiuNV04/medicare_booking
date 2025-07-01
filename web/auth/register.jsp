<%-- 
    Document   : register
    Created on : Jun 24, 2025, 2:09:50 PM
    Author     : ADMIN
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Đăng ký</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/auth/style.css">
    </head>
    <body>
        <div class="form-box">
            <h2>Đăng ký tài khoản bệnh nhân</h2>
            <form action="${pageContext.request.contextPath}/PatientRegisterServlet" method="post">

                <label for="fullName">Họ và tên:</label>
                <input type="text" name="fullName" id="fullName" required>

                <label for="email">Email:</label>
                <input type="email" name="email" id="email" required>

                <label for="username">Tên tài khoản:</label>
                <input type="text" name="username" id="username" required>

                <label for="password">Mật khẩu:</label>
                <input type="password" name="password" id="password" required>

                <label for="confirmPassword">Nhập lại mật khẩu:</label>
                <input type="password" name="confirmPassword" id="confirmPassword" required>

                <!-- Checkbox gửi mail -->
                <div style="margin: 15px 0;">
                    <label style="display: inline;">
                        <input type="checkbox" name="sendEmail" value="true">
                        Gửi xác nhận qua email
                    </label>
                </div>

                <input type="submit" value="Đăng ký">
            </form>

            <div style="text-align: center; margin-top: 15px;">
                <a href="${pageContext.request.contextPath}/auth/login.jsp">Đã có tài khoản? Đăng nhập</a>
            </div>

            <c:if test="${not empty error}">
                <p class="message">${error}</p>
            </c:if>
            <c:if test="${not empty success}">
                <p class="message success">${success}</p>
            </c:if>
        </div>
    </body>
</html>
