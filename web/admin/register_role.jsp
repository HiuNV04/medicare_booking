<%-- 
    Document   : register_role
    Created on : Jun 26, 2025, 9:06:57 PM
    Author     : Admin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/check/check_login.jsp" %>

<%
    String selectedRole = request.getParameter("role");
    if (selectedRole == null || selectedRole.isEmpty()
            || (!selectedRole.equals("doctor") && !selectedRole.equals("receptionist") && !selectedRole.equals("manager"))) {
        response.sendRedirect(request.getContextPath() + "/admin/home_admin.jsp");
        return;
    }

    String roleDisplay = "Nhân viên";
    if ("doctor".equals(selectedRole)) {
        roleDisplay = "Bác sĩ";
    } else if ("receptionist".equals(selectedRole)) {
        roleDisplay = "Lễ tân";
    } else if ("manager".equals(selectedRole)) {
        roleDisplay = "Quản lý";
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Tạo tài khoản dành cho <%= roleDisplay %></title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/auth/style.css">
        <style>
            body {
                background: linear-gradient(to right, #d3cce3, #e9e4f0);
                font-family: 'Segoe UI', sans-serif;
                margin: 0;
                padding: 0;
            }

            .form-box {
                width: 420px;
                margin: 80px auto;
                padding: 30px;
                background: white;
                border-radius: 10px;
                box-shadow: 0 8px 18px rgba(0, 0, 0, 0.1);
            }

            h2 {
                text-align: center;
                color: #4a00e0;
                margin-bottom: 20px;
            }

            label {
                display: block;
                margin-top: 12px;
                margin-bottom: 4px;
                font-weight: bold;
            }

            input[type="text"], input[type="email"], input[type="password"] {
                width: 100%;
                padding: 10px;
                border-radius: 6px;
                border: 1px solid #ccc;
                margin-bottom: 10px;
                box-sizing: border-box;
            }

            input[type="submit"] {
                width: 100%;
                background-color: #4a00e0;
                color: white;
                padding: 10px;
                border: none;
                border-radius: 6px;
                cursor: pointer;
                font-size: 16px;
            }

            input[type="submit"]:hover {
                background-color: #3a00b3;
            }

            .message {
                text-align: center;
                color: red;
                margin-top: 10px;
            }

            .success {
                color: green;
            }

            .back-link {
                display: inline-block;
                margin-top: 20px;
                text-decoration: none;
                color: #4a00e0;
                font-weight: bold;
            }

            .back-link:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <div class="form-box">
            <h2>Tạo tài khoản dành cho <%= roleDisplay %></h2>
            <form action="${pageContext.request.contextPath}/RegisterRoleServlet" method="post">
                <!-- GIỮ LẠI GIÁ TRỊ ROLE ĐỂ GỬI VỀ SERVLET -->
                <input type="hidden" name="role" value="<%= selectedRole %>">

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

                <input type="submit" value="Tạo tài khoản">
            </form>


            <c:if test="${not empty error}">
                <p class="message">${error}</p>
            </c:if>
            <c:if test="${not empty success}">
                <p class="message success">${success}</p>
            </c:if>

            <div style="text-align: center;">
                <a href="${pageContext.request.contextPath}/admin/home_admin.jsp" class="back-link">← Quay lại trang quản trị</a>
            </div>
        </div>
    </body>
</html>
