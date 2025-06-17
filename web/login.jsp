<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Đăng nhập</title>
    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body {
            background-color: #f0f2f5;
            font-family: 'Segoe UI', sans-serif;
        }
        .login-container {
            max-width: 450px;
            margin: 60px auto;
            padding: 30px;
            background-color: #ffffff;
            border-radius: 12px;
            box-shadow: 0 8px 20px rgba(0,0,0,0.1);
        }
        .form-label {
            font-weight: 600;
        }
        .g-divider {
            text-align: center;
            margin: 20px 0;
            position: relative;
        }
        .g-divider::before, .g-divider::after {
            content: "";
            position: absolute;
            top: 50%;
            width: 40%;
            height: 1px;
            background-color: #ccc;
        }
        .g-divider::before {
            left: 0;
        }
        .g-divider::after {
            right: 0;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="login-container">
        <h2 class="text-center mb-4">Đăng nhập</h2>

        <!-- Thông báo lỗi -->
        <c:if test="${not empty error}">
            <div class="alert alert-danger text-center">${error}</div>
        </c:if>

        <!-- Thông báo thành công -->
        <c:if test="${not empty success}">
            <div class="alert alert-success text-center">${success}</div>
        </c:if>

        <!-- Form đăng nhập bằng tài khoản hệ thống -->
        <form method="post" action="login">
            <div class="mb-3">
                <label class="form-label">Email hoặc Tên đăng nhập:</label>
                <input type="text" class="form-control" name="loginId" placeholder="Nhập email hoặc tên đăng nhập" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Mật khẩu:</label>
                <input type="password" class="form-control" name="password" placeholder="Nhập mật khẩu" required>
            </div>

            <div class="d-grid mb-3">
                <button type="submit" class="btn btn-primary">Đăng nhập</button>
            </div>
            <!-- Link Quên mật khẩu -->
<div class="text-center mb-3">
    <a href="forgot-password" class="text-decoration-none">Quên mật khẩu?</a>
</div>
        </form>

        <div class="g-divider">hoặc</div>

        <!-- Google Sign-In JS -->
        <script src="https://accounts.google.com/gsi/client" async defer></script>

        <!-- Google Sign-In button -->
        <div id="g_id_onload"
             data-client_id="20495276859-asgm8cn4636ehlrsktoc6klk7ldujrp5.apps.googleusercontent.com"
             data-context="signin"
             data-ux_mode="redirect"
             data-login_uri="http://localhost:9999/MediCare_Booking/oauth2handler"
             data-auto_prompt="false">
        </div>

        <div class="g_id_signin"
             data-type="standard"
             data-size="large"
             data-theme="outline"
             data-text="sign_in_with"
             data-shape="rectangular"
             data-logo_alignment="left">
        </div>

        <!-- Nút chuyển đến trang đăng ký -->
        <div class="text-center mt-4">
            <span>Chưa có tài khoản?</span>
            <a href="register" class="text-decoration-none">Đăng ký</a>
        </div>
    </div>
</div>

<!-- Bootstrap Bundle JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
