<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Đặt lại mật khẩu</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background-color: #f7f9fc;
                font-family: 'Segoe UI', sans-serif;
            }
            .reset-container {
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
        </style>
    </head>
    <body>
        <div class="container">
            <div class="reset-container">
                <h2 class="text-center mb-4">Đặt lại mật khẩu</h2>

                <!-- Thông báo -->
                <c:if test="${not empty message}">
                    <div class="alert alert-info text-center">${message}</div>
                </c:if>

                <!-- Form đặt lại mật khẩu -->
                <form action="reset-password" method="post">
                    <!-- Ẩn token -->
                    <input hidden="" name="token" value="${param.token}">

                    <div class="mb-3">
                        <label class="form-label">Mật khẩu mới:</label>
                        <input type="password" class="form-control" name="password" placeholder="Nhập mật khẩu mới" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Nhập lại mật khẩu mới:</label>
                        <input type="password" class="form-control" name="confirmPassword" placeholder="Nhập lại mật khẩu mới" required>
                    </div>

                    <div class="d-grid mb-3">
                        <button type="submit" class="btn btn-success">Cập nhật mật khẩu</button>
                    </div>
                </form>

                <div class="text-center mt-3">
                    <a href="login" class="text-decoration-none">Quay về đăng nhập</a>
                </div>
            </div>
        </div>
    </body>
</html>
