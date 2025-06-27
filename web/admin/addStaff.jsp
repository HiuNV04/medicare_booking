<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Thêm nhân viên mới</title>
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
            .sidebar .nav-link.active, .sidebar .nav-link:hover, .sidebar .nav-link:focus {
                background: #30425d;
                color: #fff !important;
                border-radius: 6px;
            }
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
            .sidebar .dropdown-menu .dropdown-item:hover, .sidebar .dropdown-menu .dropdown-item.active {
                background: #355581;
                color: #fff;
            }
            .sidebar .dropdown-toggle::after {
                float: right;
                margin-top: 9px;
            }
            .error-message {
                color: red;
                font-size: 0.95em;
                margin-top: 3px;
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
                                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/manageDoctor"><i class="bi bi-person-vcard"></i> Quản lí bác sĩ</a></li>
                                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/managePatient"><i class="bi bi-person"></i> Quản lí bệnh nhân</a></li>
                            </ul>
                        </li>
                    </ul>
                </nav>
                <!-- Main content -->
                <div class="col-md-10 offset-md-2">
                    <div class="main-content">
                        <div class="row justify-content-center">
                            <div class="col-lg-8">
                                <div class="card shadow-sm border-0 mt-5">
                                    <div class="card-body">
                                        <h4 class="card-title mb-4"><i class="bi bi-person-plus"></i> Thêm nhân viên mới</h4>
                                        <!-- Hiển thị thông báo -->
                                        <c:if test="${not empty param.message}">
                                            <div class="alert alert-${param.message eq 'Added successfully' ? 'success' : 'danger'}">${param.message}</div>
                                        </c:if>
                                        <form action="addStaff" method="post" enctype="multipart/form-data" novalidate>
                                            <div class="mb-3">
                                                <label class="form-label">Ảnh đại diện</label>
                                                <input id="imageUpload" type="file" name="imageUpload" class="form-control" accept="image/*" onchange="previewImage(event)">
                                                <img id="imagePreview" src="#" style="display:none; width:100px;height:100px;object-fit:cover;margin-top:8px;border-radius:50%;">
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Email</label>
                                                <input type="email" name="email" class="form-control" value="${email != null ? email : ''}" required>
                                                <span class="error-message">${emailError}</span>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Username</label>
                                                <input type="text" name="username" class="form-control" value="${username != null ? username : ''}" required>
                                                <span class="error-message">${usernameError}</span>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Password</label>
                                                <input type="password" name="password" class="form-control" required>
                                                <span class="error-message">${passwordError}</span>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Họ tên</label>
                                                <input type="text" name="fullName" class="form-control" value="${fullName != null ? fullName : ''}" required>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Ngày sinh</label>
                                                <input type="date" name="dateOfBirth" class="form-control" value="${dateOfBirth != null ? dateOfBirth : ''}" required>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Giới tính</label>
                                                <select name="gender" class="form-select" required>
                                                    <option value="Nam"   ${gender eq 'Nam' ? 'selected' : ''}>Nam</option>
                                                    <option value="Nữ"    ${gender eq 'Nữ' ? 'selected' : ''}>Nữ</option>
                                                    <option value="Khác"  ${gender eq 'Khác' ? 'selected' : ''}>Khác</option>
                                                </select>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Địa chỉ</label>
                                                <input type="text" name="address" class="form-control" value="${address != null ? address : ''}" required>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Số điện thoại</label>
                                                <input type="text" name="phoneNumber" class="form-control" value="${phoneNumber != null ? phoneNumber : ''}" required>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Vai trò</label>
                                                <select name="role" class="form-select" required>
                                                    <option value="Manager"   ${role eq 'Manager' ? 'selected' : ''}>Manager</option>
                                                    <option value="Receptionist"   ${role eq 'Receptionist' ? 'selected' : ''}>Receptionist</option>
                                                </select>
                                            </div>
                                            <button type="submit" class="btn btn-primary px-4"><i class="bi bi-person-plus"></i> Thêm mới</button>
                                            <a href="manageStaff" class="btn btn-link">Quay lại danh sách</a>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
