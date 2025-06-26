<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Chi tiết tài khoản</title>
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
                        <div class="row g-4">
                            <!-- Card: Cập nhật thông tin -->
                            <div class="col-lg-7">
                                <div class="card shadow-sm border-0">
                                    <div class="card-body">
                                        <h5 class="card-title mb-4"><i class="bi bi-person-lines-fill"></i> Cập nhật thông tin</h5>
                                        <div class="mb-3">
                                            <label class="form-label">Id</label>
                                            <input name="id" class="form-control" value="${patient.id}" readonly="">
                                        </div>

                                        <div class="mb-3">
                                            <label class="form-label">Image:</label>
                                            <c:if test="${not empty patient.imageUrl}">
                                                <img class="form-control" src="${patient.imageUrl}" alt="Current Image" class="image-preview" id="imagePreview">
                                                <input type="hidden" name="currentImageUrl" value="${patient.imageUrl}">

                                            </c:if>
                                            <c:if test="${empty patient.imageUrl}">
                                                <img src="" alt="Preview Image" class="image-preview" id="imagePreview" style="display: none;">
                                            </c:if>
                                            <input type="file" name="imageUpload" class="form-control" accept="image/*" onchange="previewImage(event)">
                                        </div>

                                        <div class="mb-3">
                                            <label class="form-label">Email</label>
                                            <div class="d-flex align-items-center">
                                                <input id="email" name="email" class="form-control" value="${patient.email}">
                                            </div>

                                        </div>

                                        <div class="mb-3">
                                            <label class="form-label">Username</label>
                                            <div class="d-flex align-items-center">
                                                <input id="username" name="username" class="form-control" value="${patient.username}">
                                            </div>

                                        </div>

                                        <div class="mb-3">
                                            <label class="form-label">Password</label>
                                            <div class="d-flex align-items-center">
                                                <input id="password" name="password" type="text"  class="form-control" value="${patient.password}">
                                            </div>

                                        </div>

                                        <div class="mb-3">
                                            <label class="form-label">Họ tên</label>
                                            <div class="d-flex align-items-center">
                                                <input id="fullName" name="fullName" class="form-control" value="${patient.fullName}">
                                            </div>
                                        </div>

                                        <div class="mb-3">
                                            <label class="form-label">Ngày sinh</label>
                                            <div class="d-flex align-items-center">
                                                <input id= "dateOfBirth" name="dateOfBirth" class="form-control" type="date" value="${patient.dateOfBirth != null ? patient.dateOfBirth.toString() : ""}">
                                            </div>
                                        </div>

                                        <div class="mb-3">
                                            <select name="gender" class="form-select">
                                                <option value="Nam"   ${patient.gender eq 'Nam'   ? 'selected' : ''}>Nam</option>
                                                <option value="Nữ"    ${patient.gender eq 'Nữ'    ? 'selected' : ''}>Nữ</option>
                                                <option value="Khác"  ${patient.gender eq 'Khác'  ? 'selected' : ''}>Khác</option>
                                            </select>
                                            <span class="error-message" id="gender-error"></span>
                                        </div>

                                        <div class="mb-3">
                                            <label class="form-label">Địa chỉ</label>
                                            <input id="address" name="address" class="form-control" value="${patient.address}">
                                        </div> 


                                        <div class="mb-3">
                                            <label class="form-label">Số điện thoại</label>
                                            <div class="d-flex align-items-center">
                                                <input id="phoneNumber" name="phoneNumber" class="form-control"  value="${patient.phoneNumber}">
                                            </div>
                                        </div>


                                        <div class="mb-3">
                                            <label class="form-label">Vai trò</label>
                                            <input name="role" class="form-control" value="${patient.role}" readonly>
                                        </div>



                                        <div class="mb-3">
                                            <label class="form-label">Căn cước công dân</label>
                                            <div class="d-flex align-items-center">
                                                <input id="identityNumber" name="identityNumber" class="form-control" value="${patient.identityNumber}">
                                            </div>
                                        </div>
                                        <div class="mb-3">
                                            <label class="form-label">Bảo hiểm Y tế</label>
                                            <div class="d-flex align-items-center">
                                                <input id="insuranceNumber" name="insuranceNumber" class="form-control" value="${patient.insuranceNumber}">
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                            <!-- Card: Đổi trạng thái -->
                            <div class="col-lg-5">
                                <div class="card shadow-sm border-0">
                                    <div class="card-body">
                                        <h5 class="card-title mb-4"><i class="bi bi-shield-lock"></i> Trạng thái tài khoản</h5>
                                        <form action="changePatientStatus" method="post">
                                            <input type="hidden" name="id" value="${patient.id}">
                                            <div class="mb-4">
                                                <label class="form-label">Trạng thái hiện tại:</label><br>
                                                <c:choose>
                                                    <c:when test="${patient.status}">
                                                        <span class="badge bg-success fs-6">Đang hoạt động</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="badge bg-danger fs-6">Đã khóa</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                            <button type="submit" name="status"
                                                    value="${patient.status ? 0 : 1}"
                                                    class="btn ${patient.status ? 'btn-danger' : 'btn-success'} px-4">
                                                <i class="bi ${patient.status ? 'bi-person-x-fill' : 'bi-person-check-fill'}"></i>
                                                ${patient.status ? 'Deactivate' : 'Active'}
                                            </button>
                                        </form>
                                    </div>
                                </div>

                            </div>

                            <!-- Back button -->
                            <a href="<%=request.getContextPath()%>/managePatient" class="btn btn-link mt-4">
                                <i class="bi bi-arrow-left"></i> Quay lại danh sách
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
