<%-- 
    Document   : login
    Created on : Jun 24, 2025, 2:09:43 PM
    Author     : ADMIN
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Đăng nhập</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/auth/style.css">
    </head>
    <body>
        <div class="form-box">
            <h2>Đăng nhập</h2>
            <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
                <label for="username">Tài khoản:</label>
                <input type="text" name="username" id="username" value="${param.username}" required>

                <label for="password">Mật khẩu:</label>
                <input type="password" name="password" id="password" required>

                <input type="submit" value="Đăng nhập">
            </form>

            <div style="text-align: center; margin-top: 15px;">
                <a href="${pageContext.request.contextPath}/auth/forget.jsp">Quên mật khẩu?</a><br>
                <span>Chưa có tài khoản? </span>
                <a href="${pageContext.request.contextPath}/auth/register.jsp">Đăng ký</a>
            </div>

            <c:if test="${not empty error}">
                <p class="message">${error}</p>
            </c:if>
        </div>
    </body>
</html>
