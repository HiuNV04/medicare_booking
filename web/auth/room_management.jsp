<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quản lý phòng</title>
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
        <a href="#" class="active">Quản lý phòng</a>
        <a href="#">Quản lý lịch</a>
        <a href="#">Báo cáo</a>
        <a href="#">Cài đặt</a>
        <!-- Thêm các mục khác sau -->
    </div>
    <!-- Main Content -->
    <div class="main-content flex-grow-1 p-4">
        <h2 class="mb-4">Quản lý phòng</h2>
        <form method="get" class="mb-4">
            <div class="row g-2 align-items-center">
                <div class="col-auto">
                    <label for="roomId" class="form-label mb-0">Chọn phòng:</label>
                </div>
                <div class="col-auto">
                    <select name="roomId" id="roomId" class="form-select" onchange="this.form.submit()">
                        <option value="">-- Chọn phòng --</option>
                        <c:forEach var="r" items="${roomList}">
                            <option value="${r.id}" ${selectedRoomId == r.id ? 'selected' : ''}>${r.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </form>
        <c:if test="${selectedRoomId > 0}">
            <div class="row">
                <div class="col-lg-7 mb-4 mb-lg-0">
                    <h4>Bác sĩ trong phòng</h4>
                    <table class="table table-bordered table-hover">
                        <thead class="table-light">
                            <tr>
                                <th>STT</th>
                                <th>Họ tên</th>
                                <th>Chuyên ngành</th>
                                <th>Ngày sinh</th>
                                <th>Giới tính</th>
                                <th>Ảnh</th>
                                <th>Thao tác</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="d" items="${doctorsInRoom}" varStatus="loop">
                            <tr>
                                <td>${loop.index + 1}</td>
                                <td>${d.fullName}</td>
                                <td>${d.specializationId}</td>
                                <td>${d.dateOfBirth}</td>
                                <td>${d.gender}</td>
                                <td><img src="${d.imageUrl}" width="40" height="40"/></td>
                                <td>
                                    <form method="post" style="display:inline">
                                        <input type="hidden" name="action" value="remove"/>
                                        <input type="hidden" name="roomId" value="${selectedRoomId}"/>
                                        <input type="hidden" name="doctorId" value="${d.id}"/>
                                        <button type="submit" class="btn btn-danger btn-sm">Xóa</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col-lg-5">
                    <h5>Thêm bác sĩ vào phòng (cùng chuyên ngành)</h5>
                    <form method="post" class="row g-2 align-items-center">
                        <input type="hidden" name="action" value="add"/>
                        <input type="hidden" name="roomId" value="${selectedRoomId}"/>
                        <div class="col-12 mb-2">
                            <select name="doctorId" class="form-select" required>
                                <option value="">-- Chọn bác sĩ --</option>
                                <c:forEach var="d" items="${availableDoctors}">
                                    <option value="${d.id}">${d.fullName} (Chuyên ngành: ${d.specializationId})</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-12">
                            <button type="submit" class="btn btn-success w-100">Thêm vào phòng</button>
                        </div>
                    </form>
                </div>
            </div>
        </c:if>
    </div>
</div>
</body>
</html> 