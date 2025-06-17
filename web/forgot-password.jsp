<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quên mật khẩu</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5" style="max-width: 500px;">
    <h2 class="mb-4 text-center">Quên mật khẩu</h2>

    <c:if test="${not empty message}">
        <div class="alert alert-info text-center">${message}</div>
    </c:if>

    <form action="forgot-password" method="post">
        <div class="mb-3">
            <label class="form-label">Nhập email của bạn:</label>
            <input type="email" class="form-control" name="email" required>
        </div>
        <div class="d-grid">
            <button type="submit" class="btn btn-primary">Gửi link đặt lại mật khẩu</button>
        </div>
    </form>

    <div class="text-center mt-3">
        <a href="login">Quay về đăng nhập</a>
    </div>
</div>
</body>
</html>
