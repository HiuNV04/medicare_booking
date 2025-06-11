<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>

<%
    UserAccount ua = (UserAccount) request.getAttribute("ua");
    List<Specialization> specializations = (List<Specialization>) request.getAttribute("specializations");
    List<DoctorLevel> doctorLevels = (List<DoctorLevel>)  request.getAttribute("doctorLevels");
    if (ua == null) {
%>
<div class="alert alert-danger mt-5">Không tìm thấy tài khoản!</div>
<%
        return;
    }
%>
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
            .sidebar .nav-link.active, .sidebar .nav-link:hover {
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
            .card-title {
                font-size: 1.15rem;
                font-weight: 600;
                color: #30425d;
            }
            .form-label {
                font-weight: 500;
                color: #30425d;
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
                            <a class="nav-link" href="manageAccount"><i class="bi bi-people"></i> Tài khoản</a>
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
                        <span><i class="bi bi-person-circle me-2"></i>Quản trị hệ thống - Xem chi tiết tài khoản</span>
                        <span style="font-size:0.98rem;">Xin chào, Admin</span>
                    </div>
                    <div class="main-content">
                        <div class="row g-4">
                            <!-- Card: Cập nhật thông tin -->
                            <div class="col-lg-7">
                                <div class="card shadow-sm border-0">
                                    <div class="card-body">
                                        <h5 class="card-title mb-4"><i class="bi bi-person-lines-fill"></i> Cập nhật thông tin</h5>
                                        <form action="updateAccount" method="get" >
                                            <input type="hidden" name="id" value="<%=ua.getId()%>">
                                            <input type="hidden" name="role" value="<%=ua.getRole()%>">
                                            <div class="mb-3">
                                                <label class="form-label">Email</label>
                                                <input name="email" class="form-control" value="<%=ua.getEmail()%>" required>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Username</label>
                                                <input name="username" class="form-control" value="<%=ua.getUsername()%>" readonly>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Họ tên</label>
                                                <input name="fullName" class="form-control" value="<%=ua.getFullName()%>" required>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Ngày sinh</label>
                                                <input type="date" name="dateOfBirth" class="form-control" value="<%=ua.getDateOfBirth() != null ? ua.getDateOfBirth().toString() : ""%>">
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Giới tính</label>
                                                <input name="gender" class="form-control" value="<%=ua.getGender()%>">
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Địa chỉ</label>
                                                <input name="address" class="form-control" value="<%=ua.getAddress()%>">
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Số điện thoại</label>
                                                <input name="phoneNumber" class="form-control" value="<%=ua.getPhoneNumber()%>">
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Vai trò</label>
                                                <input name="role" class="form-control" value="<%=ua.getRole()%>" readonly>
                                            </div>
                                            <%-- Nếu tài khoản là Doctor thì show select --%>
                                            <% if (ua.getRole() != null && ua.getRole().equalsIgnoreCase("Doctor")) { %>
                                            <div class="mb-3">
                                                <label class="form-label">Mã trình độ Bác sĩ</label>
                                                <select name="doctorLevelId" class="form-select">
                                                    <% for (DoctorLevel dl : doctorLevels) { %>
                                                    <option value="<%=dl.getId()%>" <%= (ua.getDoctorLevelId()!=null && ua.getDoctorLevelId()==dl.getId()) ? "selected" : "" %>>
                                                        <%=dl.getName()%> (Phí khám: <%=dl.getExaminationFee()%>)
                                                    </option>
                                                    <% } %>
                                                </select>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Mã chuyên khoa</label>
                                                <select name="specializationId" class="form-select">
                                                    <% for (Specialization s : specializations) { %>
                                                    <option value="<%=s.getId()%>" <%= (ua.getSpecializationId()!=null && ua.getSpecializationId()==s.getId()) ? "selected" : "" %>>
                                                        <%=s.getName()%> (<%=s.getDescription()%>)
                                                    </option>
                                                    <% } %>
                                                </select>
                                            </div>
                                            <% } %>
                                            
                                            <% if (ua.getIdentityNumber() != null) { %>
                                            <div class="mb-3">
                                                <label class="form-label">CCCD</label>
                                                <input name="identityNumber" class="form-control" value="<%=ua.getIdentityNumber()%>" readonly>
                                            </div>
                                            <% } %>
                                            <% if (ua.getInsuranceNumber() != null) { %>
                                            <div class="mb-3">
                                                <label class="form-label">Số BHYT</label>
                                                <input name="insuranceNumber" class="form-control" value="<%=ua.getInsuranceNumber()%>" readonly>
                                            </div>
                                            <% } %>
                                            <button type="submit" class="btn btn-primary mt-2 px-4">
                                                <i class="bi bi-save"></i> Save changes
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <!-- Card: Đổi trạng thái -->
                            <div class="col-lg-5">
                                <div class="card shadow-sm border-0">
                                    <div class="card-body">
                                        <h5 class="card-title mb-4"><i class="bi bi-shield-lock"></i> Trạng thái tài khoản</h5>
                                        <form action="changeStatus" method="post">
                                            <input type="hidden" name="id" value="<%=ua.getId()%>">
                                            <input type="hidden" name="role" value="<%=ua.getRole()%>">
                                            <div class="mb-4">
                                                <label class="form-label">Trạng thái hiện tại:</label><br>
                                                <% if (ua.isStatus()) { %>
                                                <span class="badge bg-success fs-6">Đang hoạt động</span>
                                                <% } else { %>
                                                <span class="badge bg-danger fs-6">Đã khóa</span>
                                                <% } %>
                                            </div>
                                            <button type="submit" name="status" value="<%=ua.isStatus() ? 0 : 1%>"
                                                    class="btn <%=ua.isStatus() ? "btn-danger" : "btn-success"%> px-4">
                                                <i class="bi <%=ua.isStatus() ? "bi-person-x-fill" : "bi-person-check-fill"%>"></i>
                                                <%=ua.isStatus() ? "Deactivate" : "Active"%>
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Back button -->
                        <a href="manageAccount" class="btn btn-link mt-4">
                            <i class="bi bi-arrow-left"></i> Quay lại danh sách
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
