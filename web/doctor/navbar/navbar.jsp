<%-- 
    Document   : navbar
    Created on : Jun 3, 2025, 7:51:35 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    </head>
    <body>
        <!-- Navbar Start -->
        <div class="container-fluid sticky-top bg-white shadow-sm">
            <div class="container">
                <nav class="navbar navbar-expand-lg bg-white navbar-light py-3 py-lg-0">
                    <a href="${pageContext.request.contextPath}/DoctorProfileServlet" class="navbar-brand">
                        <h1 class="m-0 text-uppercase text-primary"><i class="fa fa-clinic-medical me-2"></i>Bác sĩ</h1>
                    </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarCollapse">
                        <div class="navbar-nav ms-auto py-0">
                            <a href="${pageContext.request.contextPath}/DoctorProfileServlet" class="nav-item nav-link">Trang chủ</a>
                            <div class="nav-item dropdown">
                                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
                                    Thông tin bác sĩ
                                </a>
                                <div class="dropdown-menu m-0">
                                    <a href="${pageContext.request.contextPath}/DoctorDetailServlet" class="dropdown-item">
                                        <i class="bi-person-circle rounded-circle me-2 text-warning"></i>Hồ sơ cá nhân 
                                    </a>
                                    <a href="${pageContext.request.contextPath}/DoctorListServlet" class="dropdown-item">
                                        <i class="bi bi-person-lines-fill me-2 text-success"></i>Danh sách bác sĩ
                                    </a>
                                </div>
                            </div>

                            <a href="<%= request.getContextPath() %>/doctor/patient/patient_profile.jsp" class="nav-item nav-link">Hồ sơ bệnh nhân</a>

                            <div class="nav-item dropdown">
                                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
                                    Chi tiết lịch hẹn
                                </a>
                                <div class="dropdown-menu m-0">
                                    <a href="${pageContext.request.contextPath}/doctor/schedule/appointment_list.jsp" class="dropdown-item">
                                        <i class="fa fa-list-alt me-2 text-success"></i> Danh sách lịch khám
                                    </a>
                                    <a href="${pageContext.request.contextPath}/doctor/schedule/appointment_pending.jsp" class="dropdown-item">
                                        <i class="fa fa-check-circle me-2 text-warning"></i> Trạng thái xác nhận
                                    </a>
                                    <a href="${pageContext.request.contextPath}/doctor/schedule/appointment_history.jsp" class="dropdown-item">
                                        <i class="fa fa-history me-2 text-info"></i> Lịch sử lịch khám
                                    </a>
                                </div>
                            </div>

                            <div class="nav-item dropdown">
                                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Đơn thuốc</a>
                                <div class="dropdown-menu m-0">

                                    <a href="${pageContext.request.contextPath}/doctor/prescription/create_prescription.jsp" class="dropdown-item">
                                        <i class="fa fa-plus-circle me-2 text-success"></i> Tạo đơn thuốc
                                    </a>
                                    <a href="${pageContext.request.contextPath}/doctor/prescription/view_prescription.jsp" class="dropdown-item">
                                        <i class="fa fa-file-medical-alt me-2 text-info"></i> Xem đơn thuốc
                                    </a>
                                    <a href="${pageContext.request.contextPath}/doctor/prescription/history_prescription.jsp" class="dropdown-item">
                                        <i class="fa fa-clipboard-list me-2 text-warning"></i> Danh sách đơn thuốc
                                    </a>    
                                </div>
                            </div>
                            <a href="${pageContext.request.contextPath}/doctor/login_doctor.jsp" class="nav-item nav-link">Đăng xuất</a>
                        </div>
                    </div>
                </nav>
            </div>
        </div>
        <!-- Navbar End -->
    </body>
</html>
