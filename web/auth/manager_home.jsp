<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Trang chủ Quản lý</title>
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
        <a href="#" class="active">Trang chủ</a>
        <a href="${pageContext.request.contextPath}/manager/rooms">Quản lý phòng</a>
        <a href="#">Quản lý lịch</a>
        <a href="#">Báo cáo</a>
        <a href="#">Cài đặt</a>
        <!-- Thêm các mục khác sau -->
    </div>
    <!-- Main Content -->
    <div class="main-content flex-grow-1 p-4">
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h2 class="mb-0">Danh sách nhân sự</h2>
            <div>
                <a href="${pageContext.request.contextPath}/manager/profile" class="btn btn-outline-primary me-2">Chỉnh sửa thông tin</a>
                <a href="${pageContext.request.contextPath}/manager/change-password" class="btn btn-outline-warning">Đổi mật khẩu</a>
            </div>
        </div>
        <!-- Search & Sort -->
        <form class="row g-2 mb-3" method="get" action="">
            <div class="col-md-4">
                <input type="text" class="form-control" name="search" placeholder="Tìm kiếm theo tên..." value="${search}">
            </div>
            <div class="col-md-3">
                <select class="form-select" name="sortRole">
                    <option value="all" ${sortRole == 'all' ? 'selected' : ''}>Tất cả vai trò</option>
                    <option value="Manager" ${sortRole == 'Manager' ? 'selected' : ''}>Quản lý</option>
                    <option value="Receptionist" ${sortRole == 'Receptionist' ? 'selected' : ''}>Nhân viên lễ tân</option>
                    <option value="Doctor" ${sortRole == 'Doctor' ? 'selected' : ''}>Bác sĩ</option>
                </select>
            </div>
            <div class="col-md-2">
                <button type="submit" class="btn btn-primary w-100">Tìm kiếm</button>
            </div>
        </form>
        <!-- Table -->
        <div class="table-responsive">
        <table class="table table-bordered table-hover bg-white">
            <thead class="table-light">
                <tr>
                    <th>STT</th>
                    <th>Họ tên</th>
                    <th>Vai trò</th>
                    <th>Ngày sinh</th>
                    <th>Giới tính</th>
                    <th>Địa chỉ</th>
                    <th>Số điện thoại</th>
                    <th>Ảnh đại diện</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="p" items="${peopleList}" varStatus="loop">
                <tr>
                    <td>${(currentPage-1)*10 + loop.index + 1}</td>
                    <td>${p[0]}</td>
                    <td>${p[1]}</td>
                    <td>${p[2]}</td>
                    <td>${p[3]}</td>
                    <td>${p[4]}</td>
                    <td>${p[5]}</td>
                    <td><img src="${p[6]}" alt="avatar" width="50" height="50"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </div>
        <!-- Pagination -->
        <nav>
            <ul class="pagination justify-content-center">
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <li class="page-item ${i == currentPage ? 'active' : ''}">
                        <a class="page-link" href="?page=${i}&search=${search}&sortRole=${sortRole}">${i}</a>
                    </li>
                </c:forEach>
            </ul>
        </nav>
    </div>
</div>
</body>
</html> 