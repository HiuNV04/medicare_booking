<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.UserAccount"%>
<%
    List<UserAccount> lstAccount = (List<UserAccount>) request.getAttribute("lstAccount");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý tài khoản</title>
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
        }
        .sidebar .nav-link.active,
        .sidebar .nav-link:hover {
            background: #30425d;
            color: #fff;
            border-radius: 6px;
        }
        .header-bar {
            background: #222a35;
            color: #fff;
            padding: 18px 30px 18px 220px;
            font-size: 1.1rem;
            letter-spacing: 2px;
        }
        .main-content {
            padding: 40px 30px 30px 30px;
        }
        .table thead th {
            background: #30425d !important;
            color: #fff;
            border-bottom: none;
        }
        .table {
            border-radius: 8px;
            overflow: hidden;
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
                    <a class="nav-link" href="dashboard.jsp"><i class="bi bi-house"></i> Dashboard</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="manageAccount"><i class="bi bi-people"></i> Tài khoản</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="manageDoctor.jsp"><i class="bi bi-person-badge"></i> Bác sĩ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="managePatient.jsp"><i class="bi bi-person"></i> Bệnh nhân</a>
                </li>
                <li class="nav-item mt-3">
                    <a class="nav-link" href="logout"><i class="bi bi-box-arrow-right"></i> Đăng xuất</a>
                </li>
            </ul>
        </nav>
        <!-- Main content -->
        <div class="col-md-10 offset-md-2">
            <!-- Header -->
            <div class="header-bar mb-4 d-flex align-items-center justify-content-between">
                <span><i class="bi bi-person-circle me-2"></i>Quản trị hệ thống - Quản lý tài khoản</span>
                <span style="font-size:0.98rem;">Xin chào, Admin</span>
            </div>
            <div class="main-content">
                <h2 class="mb-4" style="color:#30425d; font-weight:600;">Danh sách tài khoản</h2>
                <div class="table-responsive">
                    <table class="table table-bordered table-hover align-middle bg-white shadow-sm">
                        <thead>
                            <tr>
                                <th>Email</th>
                                <th>Username</th>
                                <th>Họ tên</th>
                                <th>Ngày sinh</th>
                                <th>Vai trò</th>
                                <th>Trạng thái</th>
                                <th>Control</th>
                            </tr>
                        </thead>
                        <tbody>
                        <% if (lstAccount != null)
                            for (UserAccount u : lstAccount) { %>
                            <tr>
                                <td><%=u.getEmail()%></td>
                                <td><%=u.getUsername()%></td>
                                <td><%=u.getFullName()%></td>
                                <td><%=u.getDateOfBirth()%></td>
                                <td><%=u.getRole()%></td>
                                <td>
                                    <span class="badge bg-<%= u.isStatus() ? "success" : "secondary" %>">
                                        <%= u.isStatus() ? "Hoạt động" : "Đã khóa" %>
                                    </span>
                                </td>
                                <td>
                                    <a href="viewUserDetail?username=<%=u.getUsername()%>&role=<%=u.getRole()%>" class="btn btn-sm btn-info text-white">
                                        <i class="bi bi-eye"></i> Xem chi tiết
                                    </a>
                                </td>
                            </tr>
                        <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Bootstrap JS and Icons -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
