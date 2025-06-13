<%-- 
    Document   : addNewStaff
    Created on : Jun 14, 2025, 2:44:44 AM
    Author     : ADMIN
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
        <script>
            function previewImage(event) {
                const file = event.target.files[0];
                const preview = document.getElementById('imagePreview');
                if (file) {
                    const reader = new FileReader();
                    reader.onload = function (e) {
                        preview.src = e.target.result;
                        preview.style.display = 'block';
                    }
                    reader.readAsDataURL(file);
                }
            }
        </script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <!-- Sidebar giữ nguyên -->
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
                <div class="col-md-10 offset-md-2 main-content">
                    <div class="d-flex justify-content-center align-items-center" style="min-height:100vh;">
                        <div class="card shadow-sm border-0 w-100" style="max-width:540px;">
                            <div class="card-body">
                                <h4 class="card-title mb-4" style="color: #30425d;">
                                    <i class="bi bi-person-plus"></i> Thêm nhân viên mới
                                </h4>
                                <%
                                 String message = request.getParameter("message");
                                 if (message != null && !message.isEmpty()) {
                                     boolean success = "Added successfully".equals(message);
                                %>
                                <div class="alert alert-<%= success ? "success" : "danger" %> mt-2"><%= message %></div>
                                <%
                                    }
                                %>
                                <!-- Form Thêm nhân viên -->
                                <form action="addStaff" method="post" enctype="multipart/form-data">
                                    <div class="mb-3 text-center">
                                        <img id="imagePreview" src="#" alt="Preview Image" style="display:none; width:100px; height:100px; object-fit:cover; border-radius:50%;">
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Ảnh đại diện</label>
                                        <input type="file" name="imageUpload" class="form-control" accept="image/*" onchange="previewImage(event)">
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Email</label>
                                        <input name="email" class="form-control" type="email" required>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Username</label>
                                        <input name="username" class="form-control" required>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Password</label>
                                        <input name="password" class="form-control" type="password" required>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Họ tên</label>
                                        <input name="fullName" class="form-control" required>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Ngày sinh</label>
                                        <input name="dateOfBirth" class="form-control" type="date">
                                    </div>
                                    <div class="mb-3">
                                        <select name="gender" class="form-select">
                                            <option value="Nam"   ${staff.gender eq 'Nam'   ? 'selected' : ''}>Nam</option>
                                            <option value="Nữ"    ${staff.gender eq 'Nữ'    ? 'selected' : ''}>Nữ</option>
                                            <option value="Khác"  ${staff.gender eq 'Khác'  ? 'selected' : ''}>Khác</option>
                                        </select>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Địa chỉ</label>
                                        <input name="address" class="form-control">
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Số điện thoại</label>
                                        <input name="phoneNumber" class="form-control">
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Vai trò</label>
                                        <select name="role" class="form-select" required>
                                            <option value="">-- Chọn vai trò --</option>
                                            <option value="Receptionist">Receptionist</option>
                                            <option value="Manager">Manager</option>
                                        </select>
                                    </div>
                                    <button type="submit" class="btn btn-primary px-4">
                                        <i class="bi bi-person-plus"></i> Thêm mới
                                    </button>
                                    <a href="manageStaff" class="btn btn-link">Quay lại danh sách</a>
                                </form>
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
