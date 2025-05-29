<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chỉnh sửa thông tin cá nhân</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>Chỉnh sửa thông tin cá nhân</h2>
    <form method="post" action="${pageContext.request.contextPath}/manager/profile">
        <input type="hidden" name="id" value="${staff.id}"/>
        <div class="mb-3">
            <label class="form-label">Họ tên</label>
            <input type="text" class="form-control" name="fullName" value="${staff.fullName}" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Ngày sinh</label>
            <input type="date" class="form-control" name="dateOfBirth" value="${staff.dateOfBirth}" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Địa chỉ</label>
            <input type="text" class="form-control" name="address" value="${staff.address}">
        </div>
        <div class="mb-3">
            <label class="form-label">Số điện thoại</label>
            <input type="text" class="form-control" name="phoneNumber" value="${staff.phoneNumber}">
        </div>
        <div class="mb-3">
            <label class="form-label">Giới tính</label>
            <select class="form-control" name="gender">
                <option value="Nam" ${staff.gender == 'Nam' ? 'selected' : ''}>Nam</option>
                <option value="Nữ" ${staff.gender == 'Nữ' ? 'selected' : ''}>Nữ</option>
                <option value="Khác" ${staff.gender == 'Khác' ? 'selected' : ''}>Khác</option>
            </select>
        </div>
        <div class="mb-3">
            <label class="form-label">Ảnh đại diện (URL)</label>
            <input type="text" class="form-control" name="imageURL" value="${staff.imageURL}">
        </div>
        <button type="submit" class="btn btn-success">Lưu thay đổi</button>
        <a href="${pageContext.request.contextPath}/manager/home" class="btn btn-secondary ms-2">Quay lại</a>
    </form>
</div>
</body>
</html> 