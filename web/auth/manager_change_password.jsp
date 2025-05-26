<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Đổi mật khẩu</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5" style="max-width: 500px;">
    <h2 class="mb-4">Đổi mật khẩu</h2>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
    </c:if>
    <form method="post" action="${pageContext.request.contextPath}/manager/change-password">
        <div class="mb-3">
            <label class="form-label">Mật khẩu cũ</label>
            <input type="password" class="form-control" name="oldPassword" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Mật khẩu mới</label>
            <input type="password" class="form-control" name="newPassword" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Xác nhận mật khẩu mới</label>
            <input type="password" class="form-control" name="confirmPassword" required>
        </div>
        <button type="submit" class="btn btn-primary">Đổi mật khẩu</button>
        <a href="${pageContext.request.contextPath}/manager/home" class="btn btn-secondary ms-2">Quay lại</a>
    </form>
</div>
</body>
</html> 