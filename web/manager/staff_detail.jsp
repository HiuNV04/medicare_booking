<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chi tiết nhân sự</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5" style="max-width: 600px;">
    <h2 class="mb-4">Chi tiết nhân sự</h2>
    <c:choose>
        <c:when test="${not empty staff}">
            <div class="card">
                <div class="card-body">
                    <div class="text-center mb-3">
                        <img src="${staff.imageUrl}" alt="avatar" width="100" height="100" class="rounded-circle"/>
                    </div>
                    <p><strong>Họ tên:</strong> ${staff.fullName}</p>
                    <p><strong>Email:</strong> ${staff.email}</p>
                    <p><strong>Tên đăng nhập:</strong> ${staff.username}</p>
                    <p><strong>Vai trò:</strong> ${staff.role}</p>
                    <p><strong>Ngày sinh:</strong> ${staff.dateOfBirth}</p>
                    <p><strong>Giới tính:</strong> ${staff.gender}</p>
                    <p><strong>Địa chỉ:</strong> ${staff.address}</p>
                    <p><strong>Số điện thoại:</strong> ${staff.phoneNumber}</p>
                </div>
            </div>
        </c:when>
        <c:when test="${not empty doctor}">
            <div class="card">
                <div class="card-body">
                    <div class="text-center mb-3">
                        <img src="${doctor.imageUrl}" alt="avatar" width="100" height="100" class="rounded-circle"/>
                    </div>
                    <p><strong>Họ tên:</strong> ${doctor.fullName}</p>
                    <p><strong>Email:</strong> ${doctor.email}</p>
                    <p><strong>Tên đăng nhập:</strong> ${doctor.username}</p>
                    <p><strong>Vai trò:</strong> ${doctor.role}</p>
                    <p><strong>Ngày sinh:</strong> ${doctor.dateOfBirth}</p>
                    <p><strong>Giới tính:</strong> ${doctor.gender}</p>
                    <p><strong>Địa chỉ:</strong> ${doctor.address}</p>
                    <p><strong>Số điện thoại:</strong> ${doctor.phoneNumber}</p>
                    <p><strong>Chuyên khoa:</strong> ${doctor.specializationId}</p>
                    <p><strong>Trình độ:</strong> ${doctor.doctorLevelId}</p>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-danger">Không tìm thấy thông tin nhân sự.</div>
        </c:otherwise>
    </c:choose>
    <div class="mt-3">
        <a href="${pageContext.request.contextPath}/manager/home" class="btn btn-secondary">Quay lại danh sách</a>
    </div>
</div>
</body>
</html> 