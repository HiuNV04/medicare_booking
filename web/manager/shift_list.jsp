<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Danh sách ca khám</title>
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
    <div class="sidebar flex-shrink-0" style="min-height:100vh;background:#343a40;color:#fff;padding:30px 10px 10px 10px;">
        <h4 class="mb-4">Quản lí</h4>
        <a href="${pageContext.request.contextPath}/manager/home">Trang chủ</a>
        <a href="${pageContext.request.contextPath}/manager/rooms">Quản lí phòng</a>
        <a href="${pageContext.request.contextPath}/manager/schedule-management">Quản lí lịch</a>
        <a href="${pageContext.request.contextPath}/manager/shift-list" class="active">Quản lí ca khám</a>
        <a href="#">Báo cáo</a>
        <a href="#">Cài đặt</a>
    </div>
    <!-- Main Content -->
    <div class="main-content flex-grow-1 p-4">
        <div class="container mt-4">
            <h2 class="mb-4">Danh sách ca khám</h2>
            <form method="get" class="row g-3 align-items-end mb-4">
                <div class="col-md-4">
                    <label for="doctorId" class="form-label">Bác sĩ</label>
                    <select name="doctorId" id="doctorId" class="form-select">
                        <option value="">-- Tất cả --</option>
                        <c:forEach var="d" items="${doctorList}">
                            <option value="${d.id}" ${d.id == selectedDoctorId ? 'selected' : ''}>${d.fullName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-3">
                    <label for="date" class="form-label">Ngày</label>
                    <input type="date" name="date" id="date" class="form-control" value="${selectedDate}"/>
                </div>
                <div class="col-md-2">
                    <button type="submit" class="btn btn-primary">Lọc</button>
                </div>
            </form>
            <table class="table table-bordered table-hover">
                <thead class="table-light">
                    <tr>
                        <th>ID</th>
                        <th>Bác sĩ</th>
                        <th>Ngày</th>
                        <th>Giờ bắt đầu</th>
                        <th>Giờ kết thúc</th>
                        <th>Trạng thái</th>
                        <th>Chi tiết</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="shift" items="${shiftList}">
                        <tr>
                            <td>${shift.id}</td>
                            <td>
                                <c:forEach var="d" items="${doctorList}">
                                    <c:if test="${d.id == shift.doctorId}">${d.fullName}</c:if>
                                </c:forEach>
                            </td>
                            <td><fmt:formatDate value="${shift.date}" pattern="yyyy-MM-dd"/></td>
                            <td>${shift.slotStartTime}</td>
                            <td>${shift.slotEndTime}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${shift.booked}">Đã đặt lịch</c:when>
                                    <c:otherwise>Trống</c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <a href="shift-detail?shiftId=${shift.id}" class="btn btn-sm btn-info">Xem chi tiết</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <!-- Pagination -->
            <nav>
                <ul class="pagination justify-content-center">
                    <c:forEach var="i" begin="1" end="${totalPages}">
                        <li class="page-item ${i == currentPage ? 'active' : ''}">
                            <a class="page-link"
                               href="?page=${i}
                               <c:if test='${not empty selectedDoctorId}'> &doctorId=${selectedDoctorId}</c:if>
                               <c:if test='${not empty selectedDate}'> &date=${selectedDate}</c:if>">
                                ${i}
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </nav>
        </div>
    </div>
</div>
</body>
</html> 