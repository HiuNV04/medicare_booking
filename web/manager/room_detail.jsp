<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chi tiết phòng chuyên ngành</title>
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
        <h2 class="mb-4">Chi tiết phòng chuyên ngành</h2>
        <c:if test="${selectedRoom != null}">
            <div class="card mb-4">
                <div class="card-header">
                    <h3>Phòng: ${selectedRoom.name}</h3>
                </div>
                <div class="card-body">
                    <p><strong>Chuyên khoa:</strong>
                        <c:forEach var="s" items="${specializationList}">
                            <c:if test="${s.id == selectedRoom.specializationId}">${s.name}</c:if>
                        </c:forEach>
                    </p>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-7 mb-4 mb-lg-0">
                    <h4>Bác sĩ trong phòng (${doctorsInRoom.size()}/2)</h4>
                    <c:if test="${empty doctorsInRoom}">
                        <div class="alert alert-info">Chưa có bác sĩ nào trong phòng này.</div>
                    </c:if>
                    <c:if test="${not empty doctorsInRoom}">
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
                                    <td>
                                        <c:forEach var="s" items="${specializationList}">
                                            <c:if test="${s.id == d.specializationId}">${s.name}</c:if>
                                        </c:forEach>
                                    </td>
                                    <td>${d.dateOfBirth}</td>
                                    <td>${d.gender}</td>
                                    <td><img src="${pageContext.request.contextPath}/${d.imageUrl}" width="40" height="40" alt="Doctor Image"/></td>
                                    <td>
                                        <form method="post" style="display:inline">
                                            <input type="hidden" name="action" value="removeDoctor"/>
                                            <input type="hidden" name="roomId" value="${selectedRoomId}"/>
                                            <input type="hidden" name="doctorId" value="${d.id}"/>
                                            <button type="submit" class="btn btn-danger btn-sm">Xóa</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </div>
                <div class="col-lg-5">
                    <h5>Phân công bác sĩ vào phòng (tối đa 2)</h5>
                    <c:if test="${canAddDoctor}">
                    <form method="post" class="row g-2 align-items-center">
                        <input type="hidden" name="action" value="addDoctor"/>
                        <input type="hidden" name="roomId" value="${selectedRoomId}"/>
                        <div class="col-12 mb-2">
                            <select name="doctorId" class="form-select" required>
                                <option value="">-- Chọn bác sĩ --</option>
                                <c:forEach var="d" items="${availableDoctors}">
                                    <option value="${d.id}">${d.fullName} (Chuyên ngành: 
                                        <c:forEach var="s" items="${specializationList}">
                                            <c:if test="${s.id == d.specializationId}">${s.name}</c:if>
                                        </c:forEach>)
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-12">
                            <button type="submit" class="btn btn-success w-100">Phân công</button>
                        </div>
                    </form>
                    </c:if>
                    <c:if test="${!canAddDoctor}">
                        <div class="alert alert-warning mt-2">Đã đủ 2 bác sĩ trong phòng này.</div>
                    </c:if>
                    <c:if test="${empty availableDoctors && canAddDoctor}">
                        <div class="alert alert-info mt-2">Không có bác sĩ khả dụng nào để phân công.</div>
                    </c:if>
                </div>
            </div>
        </c:if>
        <c:if test="${selectedRoom == null}">
            <div class="alert alert-warning">Không tìm thấy phòng này hoặc phòng chưa được chọn.</div>
        </c:if>
        <div class="mt-4">
            <a href="${pageContext.request.contextPath}/manager/rooms" class="btn btn-secondary">Quay lại quản lý phòng</a>
        </div>
    </div>
</div>
</body>
</html> 