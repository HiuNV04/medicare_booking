<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Quản lý Bác sĩ</title>
        <jsp:include page="/frontend/head/head.jsp" />
        <!-- Font Awesome Icon -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <style>
            body {
                background: linear-gradient(to right, #e0f7fa, #f1f8ff);
                font-family: 'Segoe UI', sans-serif;
            }

            .card-custom {
                background: rgba(255, 255, 255, 0.95);
                backdrop-filter: blur(10px);
                border-radius: 20px;
                box-shadow: 0 8px 30px rgba(0, 0, 0, 0.1);
                padding: 35px;
            }

            h2.section-title {
                font-weight: 700;
                font-size: 2rem;
                background: linear-gradient(90deg, #1e88e5, #42a5f5);
                -webkit-background-clip: text;
                -webkit-text-fill-color: transparent;
            }

            .form-control, .form-select {
                border-radius: 12px;
                box-shadow: inset 0 1px 3px rgba(0,0,0,0.05);
            }

            .btn-gradient {
                background: linear-gradient(to right, #42a5f5, #1e88e5);
                color: white;
                border: none;
                border-radius: 10px;
                transition: 0.3s ease-in-out;
            }

            .btn-gradient:hover {
                box-shadow: 0 0 15px rgba(66, 165, 245, 0.6);
                transform: translateY(-2px);
            }

            .table-hover tbody tr:hover {
                background-color: #e3f2fd !important;
            }

            .table th {
                background-color: #1e88e5;
                color: white;
                text-align: center;
                vertical-align: middle;
            }

            .table td {
                vertical-align: middle;
            }

            .highlight-row {
                background-color: #bbdefb !important;
                font-weight: bold;
            }

            .no-result {
                font-size: 1.1rem;
                color: #888;
            }

            .table td i {
                margin-right: 6px;
                color: #1e88e5;
            }

            .action-btn {
                display: flex;
                justify-content: center;
                gap: 6px;
            }

            .action-btn a {
                transition: 0.2s;
            }

            .action-btn a:hover {
                transform: scale(1.1);
            }
        </style>
    </head>
    <body>
        <jsp:include page="/frontend/topbar/topbar.jsp" />
        <jsp:include page="/doctor/navbar/navbar.jsp" />

        <div class="container mt-5 mb-5">
            <div class="card card-custom">
                <h2 class="text-center section-title mb-4">Quản lý <span class="text-warning">Bác sĩ</span></h2>

                <!-- Form tìm kiếm -->
                <form action="DoctorListServlet" method="get" class="row g-3 align-items-end mb-4">
                    <div class="col-md-3">
                        <label class="form-label">Tên bác sĩ</label>
                        <input type="text" name="name" class="form-control" placeholder="Nhập tên bác sĩ..." value="${param.name}" />
                    </div>

                    <div class="col-md-2">
                        <label class="form-label">Giới tính</label>
                        <select name="gender" class="form-select">
                            <option value="">-- Giới tính --</option>
                            <option value="Nam" <c:if test="${param.gender == 'Nam'}">selected</c:if>>Nam</option>
                            <option value="Nữ" <c:if test="${param.gender == 'Nữ'}">selected</c:if>>Nữ</option>
                            </select>
                        </div>

                        <div class="col-md-3">
                            <label class="form-label">Chuyên khoa</label>
                            <select name="specializationId" class="form-select">
                                <option value="">-- Chuyên khoa --</option>
                            <c:forEach var="special" items="${specializations}">
                                <option value="${special[0]}" <c:if test="${param.specializationId == special[0]}">selected</c:if>>${special[1]}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-md-3">
                        <label class="form-label">Trình độ</label>
                        <select name="levelId" class="form-select">
                            <option value="">-- Trình độ --</option>
                            <c:forEach var="level" items="${levels}">
                                <option value="${level[0]}" <c:if test="${param.levelId == level[0]}">selected</c:if>>${level[1]}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-md-1 d-grid">
                        <button type="submit" class="btn btn-gradient">
                            <i class="fas fa-filter"></i> Lọc
                        </button>
                    </div>
                </form>

                <!-- Bảng danh sách -->
                <c:if test="${not empty doctors}">
                    <div class="table-responsive">
                        <table class="table table-hover table-bordered align-middle">
                            <thead>
                                <tr>
                                    <th><i class="fas fa-id-badge"></i> ID</th>
                                    <th><i class="fas fa-user-md"></i> Họ tên</th>
                                    <th><i class="fas fa-envelope"></i> Email</th>
                                    <th><i class="fas fa-venus-mars"></i> Giới tính</th>
                                    <th><i class="fas fa-birthday-cake"></i> Ngày sinh</th>
                                    <th><i class="fas fa-phone"></i> SĐT</th>
                                    <th><i class="fas fa-map-marker-alt"></i> Địa chỉ</th>
                                    <th><i class="fas fa-stethoscope"></i> Chuyên khoa</th>
                                    <th><i class="fas fa-graduation-cap"></i> Trình độ</th>
                                    <th><i class="fas fa-cogs"></i> Thao tác</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="doctor" items="${doctors}">
                                    <tr class="<c:if test='${sessionScope.doctor != null && sessionScope.doctor.id == doctor.id}'>highlight-row</c:if>">
                                        <td>${doctor.id}</td>
                                        <td>${doctor.fullName}</td>
                                        <td>${doctor.email}</td>
                                        <td>${doctor.gender}</td>
                                        <td><fmt:formatDate value="${doctor.dateOfBirth}" pattern="dd/MM/yyyy" /></td>
                                        <td>${doctor.phoneNumber}</td>
                                        <td>${doctor.address}</td>
                                        <td>${doctor.specialization}</td>
                                        <td>${doctor.levelName}</td>
                                        <td class="action-btn">
                                            <c:choose>
                                                <c:when test="${sessionScope.doctor != null && sessionScope.doctor.id == doctor.id}">
                                                    <a href="${pageContext.request.contextPath}/DoctorUpdateServlet?id=${doctor.id}" 
                                                       class="btn btn-sm btn-info" title="Chỉnh sửa thông tin của bạn">
                                                        <i class="fas fa-user-edit"></i>
                                                    </a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="${pageContext.request.contextPath}/DoctorUpdateServlet?id=${doctor.id}" 
                                                       class="btn btn-sm btn-secondary" title="Xem chi tiết bác sĩ">
                                                        <i class="fas fa-eye"></i>
                                                    </a>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>

                <c:if test="${empty doctors}">
                    <div class="text-center no-result mt-4">Không tìm thấy bác sĩ nào.</div>
                </c:if>

                <!-- Phân trang -->
                <jsp:include page="/pages.jsp"/>
            </div>
        </div>

        <jsp:include page="/frontend/footer/footer.jsp" />
        <jsp:include page="/frontend/script/script.jsp" />
    </body>
</html>
