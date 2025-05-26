<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Trang chủ Quản lý</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <div class="d-flex justify-content-end align-items-center mb-3">
        <a href="${pageContext.request.contextPath}/manager/profile" class="btn btn-outline-primary me-2">Chỉnh sửa thông tin</a>
        <a href="${pageContext.request.contextPath}/manager/change-password" class="btn btn-outline-warning">Đổi mật khẩu</a>
    </div>
    <h2 class="mb-4">Danh sách bác sĩ và nhân viên</h2>
    <table class="table table-bordered table-hover">
        <thead class="table-light">
            <tr>
                <th>STT</th>
                <th>Họ tên</th>
                <th>Ngày sinh</th>
                <th>Giới tính</th>
                <th>Địa chỉ</th>
                <th>Số điện thoại</th>
                <th>Ảnh đại diện</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="s" items="${staffList}" varStatus="loop">
            <tr>
                <td>${loop.index + 1}</td>
                <td>${s.fullName}</td>
                <td>${s.dateOfBirth}</td>
                <td>${s.gender}</td>
                <td>${s.address}</td>
                <td>${s.phoneNumber}</td>
                <td><img src="${s.imageURL}" alt="avatar" width="50" height="50"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html> 