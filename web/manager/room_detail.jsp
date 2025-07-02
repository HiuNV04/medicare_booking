<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chi tiết phòng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background: #f8f9fa; }
        .sidebar {
            min-height: 100vh;
            background: #343a40;
            color: #fff;
            padding: 30px 10px 10px 10px;
        }
        .sidebar a { color: #fff; text-decoration: none; display: block; margin-bottom: 15px; }
        .sidebar a.active, .sidebar a:hover { color: #ffc107; font-weight: bold; }
        .main-content { margin-left: 220px; }
        @media (max-width: 768px) {
            .main-content { margin-left: 0; }
            .sidebar { min-height: auto; }
        }
    </style>
</head>
<body>
<div class="d-flex">
    <!-- Sidebar -->
    <div class="sidebar flex-shrink-0">
        <h4 class="mb-4">Quản lý</h4>
        <a href="${pageContext.request.contextPath}/manager/home">Trang chủ</a>
        <a href="${pageContext.request.contextPath}/manager/rooms" class="active">Quản lý phòng</a>
        <a href="#">Quản lý lịch</a>
        <a href="#">Báo cáo</a>
        <a href="#">Cài đặt</a>
    </div>
    <!-- Main Content -->
    <div class="main-content flex-grow-1 p-4">
        <h2 class="mb-4">Chi tiết phòng: ${room.name}</h2>

        <c:if test="${not empty success}">
            <div class="alert alert-success">${success}</div>
        </c:if>

        <div class="card mb-4">
            <div class="card-header">Thông tin bác sĩ hiện tại</div>
            <div class="card-body">
                <c:choose>
                    <c:when test="${not empty currentDoctor}">
                        <p><strong>Họ tên:</strong> ${currentDoctor.fullName}</p>
                        <p><strong>Email:</strong> ${currentDoctor.email}</p>
                    </c:when>
                    <c:otherwise>
                        <p class="text-muted">Chưa có bác sĩ nào được gán cho phòng này.</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <div class="card">
            <div class="card-header">Thay đổi bác sĩ</div>
            <div class="card-body">
                <form method="post" action="${pageContext.request.contextPath}/manager/room_detail">
                    <input type="hidden" name="action" value="changeDoctor"/>
                    <input type="hidden" name="roomId" value="${room.id}"/>
                    <div class="mb-3">
                        <label class="form-label">Chọn bác sĩ mới</label>
                        <select name="newDoctorId" class="form-select" required>
                            <c:forEach var="d" items="${availableDoctors}">
                                <option value="${d.id}" ${d.id == currentDoctor.id ? 'selected' : ''}>
                                    ${d.fullName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Cập nhật bác sĩ</button>
                </form>
            </div>
        </div>

        <div class="mt-4">
            <a href="${pageContext.request.contextPath}/manager/rooms" class="btn btn-secondary">Quay lại danh sách phòng</a>
        </div>
    </div>
</div>
</body>
</html> 