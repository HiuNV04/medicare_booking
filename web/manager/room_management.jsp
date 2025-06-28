<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quản lý phòng chuyên ngành</title>
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
        <a href="${pageContext.request.contextPath}/manager/schedule-management" >Quản lý lịch</a>
        <a href="${pageContext.request.contextPath}/manager/shift-list">Quản lý ca khám</a>
        <a href="#">Báo cáo</a>
        <a href="#">Cài đặt</a>
    </div>
    <!-- Main Content -->
    <div class="main-content flex-grow-1 p-4">
        <c:if test="${not empty success}">
            <div class="alert alert-success">${success}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>
        <h2 class="mb-4">Quản lý phòng chuyên ngành</h2>
        <!-- Form tìm kiếm và filter -->
        <form method="get" class="row g-2 mb-4 align-items-end">
            <div class="col-md-4">
                <input type="text" class="form-control" name="search" placeholder="Tìm theo tên phòng" value="${param.search}">
            </div>
            <div class="col-md-4">
                <select name="specializationId" class="form-select">
                    <option value="">-- Tất cả chuyên khoa --</option>
                    <c:forEach var="s" items="${specializationList}">
                        <option value="${s.id}" ${param.specializationId == s.id ? 'selected' : ''}>${s.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-2">
                <button type="submit" class="btn btn-primary w-100">Tìm kiếm</button>
            </div>
        </form>
        <!-- Form thêm phòng -->
        <form method="post" action="${pageContext.request.contextPath}/manager/rooms" class="row g-2 mb-4 align-items-end">
            <input type="hidden" name="action" value="addRoom"/>
            <div class="col-md-5">
                <label class="form-label">Tên phòng</label>
                <input type="text" class="form-control" name="name" required>
            </div>
            <div class="col-md-5">
                <label class="form-label">Bác sĩ trực</label>
                <select name="doctorId" class="form-select" required>
                    <option value="">-- Chọn bác sĩ --</option>
                    <c:forEach var="d" items="${availableDoctors}">
                        <option value="${d.id}">${d.fullName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-2">
                <button type="submit" class="btn btn-success w-100">Thêm phòng</button>
            </div>
        </form>
        <!-- Danh sách phòng -->
        <table class="table table-bordered table-hover bg-white mb-4">
            <thead class="table-light">
                <tr>
                    <th>STT</th>
                    <th>Tên phòng</th>
                    <th>Chuyên khoa</th>
                    <th>Thao tác</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="r" items="${pagedRoomList}" varStatus="loop">
                <tr>
                    <td>${(currentPage-1)*pageSize + loop.index + 1}</td>
                    <td>${r.name}</td>
                    <td>
                        <c:set var="foundDoctor" value="${false}"/>
                        <c:forEach var="d" items="${doctorList}">
                            <c:if test="${d.id == r.doctorId}">
                                <c:forEach var="s" items="${specializationList}">
                                    <c:if test="${s.id == d.specializationId}">
                                        ${s.name}
                                        <c:set var="foundDoctor" value="${true}"/>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                        <c:if test="${not foundDoctor}">
                            Trống
                        </c:if>
                    </td>
                    <td>
                        <form method="post" style="display:inline">
                            <input type="hidden" name="action" value="deleteRoom"/>
                            <input type="hidden" name="roomId" value="${r.id}"/>
                            <button type="submit" class="btn btn-danger btn-sm">Xóa</button>
                        </form>
                        <a href="room_detail?roomId=${r.id}" class="btn btn-primary btn-sm ms-2">Chi tiết</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <!-- Phân trang -->
        <nav>
            <ul class="pagination">
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <li class="page-item ${i == currentPage ? 'active' : ''}">
                        <a class="page-link" href="?page=${i}&search=${param.search}&specializationId=${param.specializationId}">${i}</a>
                    </li>
                </c:forEach>
            </ul>
        </nav>
    </div>
</div>
</body>
</html> 