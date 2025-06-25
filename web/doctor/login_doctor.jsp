<%-- 
    Document   : login_doctor
    Created on : Jun 12, 2025, 8:15:43 PM
    Author     : Admin
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Đăng nhập bác sĩ</title>
        <meta charset="UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background: #f0f2f5;
                font-family: 'Segoe UI', sans-serif;
            }
            .login-container {
                max-width: 400px;
                margin: 80px auto;
                padding: 30px;
                background: white;
                border-radius: 12px;
                box-shadow: 0 0 12px rgba(0,0,0,0.1);
            }
            .login-title {
                text-align: center;
                margin-bottom: 24px;
                color: #007bff;
            }
        </style>
    </head>
    <body>

        <div class="login-container">
            <h3 class="login-title">Đăng nhập bác sĩ</h3>

            <form method="post" action="${pageContext.request.contextPath}/DoctorLoginServlet">
                <div class="mb-3">
                    <label class="form-label">Tên đăng nhập:</label>
                    <input type="text" name="username" class="form-control" value="${param.username}" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Mật khẩu:</label>
                    <input type="password" name="password" class="form-control" required>
                </div>

                <c:if test="${not empty error}">
                    <div class="alert alert-danger text-center">${error}</div>
                    </c:if>

                <div class="d-grid">
                    <button type="submit" class="btn btn-primary">Đăng nhập</button>
                </div>
            </form>
        </div>

    </body>
</html>

