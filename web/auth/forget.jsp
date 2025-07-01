<%-- 
    Document   : forget
    Created on : Jul 1, 2025, 7:59:09 PM
    Author     : Admin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Quên mật khẩu</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/auth/style.css">
    </head>
    <body>
        <div class="form-box">
            <h2>Quên mật khẩu</h2>
            <form action="${pageContext.request.contextPath}/ForgetPasswordServlet" method="post">
                <label for="username">Tên đăng nhập:</label>
                <input type="text" name="username" id="username" required>
                <label for="email">Email đã đăng ký:</label>
                <input type="email" name="email" id="email" required>
                <input type="submit" value="Gửi mật khẩu mới qua email">
            </form>
            <div style="text-align: center; margin-top: 15px;">
                <a href="${pageContext.request.contextPath}/auth/login.jsp">← Quay lại đăng nhập</a>
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
