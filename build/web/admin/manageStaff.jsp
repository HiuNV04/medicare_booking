<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="java.util.List"%>
<%@page import="model.Staff"%>
<%
    List<Staff> staffs = (List<Staff>) request.getAttribute("staffs");
%>
<html>
    <head>
        <meta charset="UTF-8">
        <!-- Bootstrap 5 CDN -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <style>
            body {
                min-height: 100vh;
                background: #f8f9fa;
            }
            .sidebar {
                min-height: 100vh;
                background: #222a35;
            }
            .sidebar .nav-link {
                color: #adb5bd;
                font-weight: 500;
                margin-bottom: 7px;
                transition: background 0.15s, color 0.15s;
            }
            .sidebar .nav-link.active,
            .sidebar .nav-link:hover,
            .sidebar .nav-link:focus {
                background: #30425d;
                color: #fff !important;
                border-radius: 6px;
            }
            /* Dropdown menu for sidebar */
            .sidebar .dropdown-menu {
                background: #263143;
                border-radius: 8px;
                border: none;
                margin-left: 10px;
                margin-top: 3px;
                min-width: 210px;
                box-shadow: 0 3px 15px rgba(0,0,0,0.10);
            }
            .sidebar .dropdown-menu .dropdown-item {
                color: #adb5bd;
                font-weight: 500;
                border-radius: 5px;
                transition: background 0.15s, color 0.15s;
            }
            .sidebar .dropdown-menu .dropdown-item:hover,
            .sidebar .dropdown-menu .dropdown-item.active {
                background: #355581;
                color: #fff;
            }
            .sidebar .dropdown-toggle::after {
                float: right;
                margin-top: 9px;
            }
        </style>
    </head>
    <body>

        <div class="container-fluid">
            <div class="row">
                <!-- Sidebar -->
                <nav class="col-md-2 d-none d-md-block sidebar py-4 px-2 position-fixed">
                    <h4 class="ps-2 pb-2 border-bottom border-secondary mb-4 mt-1" style="color:#6cc0e6; letter-spacing:1px;">
                        <i class="bi bi-hospital"></i> MEDICARE
                    </h4>
                    <ul class="nav flex-column">
                        <li class="nav-item">
                            <a class="nav-link"  href="<%=request.getContextPath()%>/admin/dashboard.jsp"><i class="bi bi-house"></i> Dashboard</a>
                        </li>

                        <!-- Dropdown Menu Sidebar -->
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle active" href="#" id="accountDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <i class="bi bi-people"></i> Quản lí tài khoản
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="accountDropdown">
                                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/manageStaff"><i class="bi bi-person-badge"></i> Quản lí nhân viên</a></li>
                                <li><a class="dropdown-item" href="manageDoctor"><i class="bi bi-person-vcard"></i> Quản lí bác sĩ</a></li>
                                <li><a class="dropdown-item" href="managePatient"><i class="bi bi-person"></i> Quản lí bệnh nhân</a></li>
                            </ul>
                        </li>
                        <li class="nav-item mt-3">
                            <a class="nav-link" href="logout"><i class="bi bi-box-arrow-right"></i> Đăng xuất</a>
                        </li>
                    </ul>
                </nav>


                <!-- Main content -->
                <div class="col-md-10 offset-md-2">
                    <div class="main-content">
                        <!-- Dòng 1: Button Thêm nhân viên, căn trái -->
                        <div class="mb-3">
                            <a href="<%=request.getContextPath()%>/addStaff" class="btn btn-success">
                                <i class="bi bi-person-plus"></i> Thêm nhân viên
                            </a>

                        </div>
                        <!-- Dòng 2: Search + Filter, chung 1 hàng, căn trái -->
                        <form class="row g-2 mb-4 align-items-center" method="get" action="manageStaff">
                            <div class="col-md-4">
                                <input type="text" name="search" class="form-control"
                                       placeholder="Tìm kiếm theo email, username"
                                       value="${param.search != null ? param.search : ''}">
                            </div>
                            <div class="col-md-3">
                                <select class="form-select" name="role">
                                    <option value="">Tất cả vai trò</option>
                                    <option value="Manager" ${param.role == 'Manager' ? 'selected' : ''}>Manager</option>
                                    <option value="Receptionist" ${param.role == 'Receptionist' ? 'selected' : ''}>Receptionist</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <select class="form-select" name="status">
                                    <option value="">Tất cả trạng thái</option>
                                    <option value="1" ${param.status == '1' ? 'selected' : ''}>Hoạt động</option>
                                    <option value="0" ${param.status == '0' ? 'selected' : ''}>Đã khóa</option>
                                </select>
                            </div>
                            <div class="col-md-2">
                                <button class="btn btn-outline-primary w-100" type="submit">
                                    <i class="bi bi-search"></i> Tìm kiếm/Lọc
                                </button>
                            </div>
                        </form>

                        <div class="table-responsive">
                            <table class="table table-bordered table-hover align-middle bg-white shadow-sm">
                                <thead>
                                    <tr>
                                        <th>Id</th>
                                        <th>Email</th>
                                        <th>Username</th>
                                        <th>Vai trò</th>
                                        <th>Trạng thái</th>
                                        <th>Control</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="staff" items="${staffs}">
                                        <tr>
                                            <td>${staff.id}</td>
                                            <td>${staff.email}</td>
                                            <td>${staff.username}</td>
                                            <td>${staff.role}</td>
                                            <td>
                                                <span class="badge bg-${staff.status ? 'success' : 'secondary'}">
                                                    <c:choose>
                                                        <c:when test="${staff.status}">Hoạt động</c:when>
                                                        <c:otherwise>Đã khóa</c:otherwise>
                                                    </c:choose>
                                                </span>
                                            </td>
                                            <td>
                                                <a href="viewStaffDetail?id=${staff.id}" class="btn btn-sm btn-info text-white">
                                                    <i class="bi bi-eye"></i> Xem chi tiết
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <c:if test="${empty staffs}">
                                        <tr>
                                            <td colspan="6" class="text-center text-muted">Không có dữ liệu</td>
                                        </tr>
                                    </c:if>
                                </tbody>

                            </table>
                        </div>
                        <!-- PHÂN TRANG (JSTL) -->
                        <nav aria-label="Page navigation" class="mt-3">
                            <ul class="pagination justify-content-center">
                                <c:forEach var="i" begin="1" end="${totalPage}">
                                    <li class="page-item ${i == currentPage ? 'active' : ''}">
                                        <a class="page-link"
                                           href="manageStaff?page=${i}&search=${param.search}&role=${param.role}&status=${param.status}">
                                            ${i}
                                        </a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </nav>

                    </div>
                </div>



            </div>
        </div>
    </div>
</div>
</div>
<!-- Bootstrap JS and Icons -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

