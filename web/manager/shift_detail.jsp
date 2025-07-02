<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chi tiáº¿t ca khÃ¡m</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2 class="mb-4">Chi tiết ca khám</h2>
    <a href="shift-list" class="btn btn-secondary mb-3">Quay lại danh sách</a>
    <div class="card mb-4">
        <div class="card-body">
            <h5 class="card-title">Thông tin ca khám</h5>
            <p><strong>ID:</strong> ${shift.id}</p>
            <p><strong>Bác sí:</strong> ${doctor.fullName}</p>
            <p><strong>Ngày:</strong> <fmt:formatDate value="${shift.date}" pattern="yyyy-MM-dd"/></p>
            <p><strong>Giờ bắt đầu:</strong> ${shift.slotStartTime}</p>
            <p><strong>Giờ kết thúc:</strong> ${shift.slotEndTime}</p>
            <p><strong>Trạng thái:</strong> <c:choose><c:when test="${shift.booked}">Đã đặt lịch</c:when><c:otherwise>Trống</c:otherwise></c:choose></p>
        </div>
    </div>
    <h5>Lịch hẹn liên quan</h5>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>ID</th>
                <th>Bệnh nhân</th>
                <th>Phòng</th>
                <th>Trạng thái</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="a" items="${appointments}">
                <tr>
                    <td>${a.id}</td>
                    <td>${a.patientId}</td>
                    <td>${a.roomId}</td>
                    <td>${a.confirmationStatus}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty appointments}">
                <tr><td colspan="4" class="text-center">Không có danh sách nào</td></tr>
            </c:if>
        </tbody>
    </table>
</div>
</body>
</html> 