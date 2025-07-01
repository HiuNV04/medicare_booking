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
        <title>Lễ Tân</title>
    </head>
    <body>
        <!-- Navbar Start -->
        <div class="container-fluid sticky-top bg-white shadow-sm">
            <div class="container">
                <nav class="navbar navbar-expand-lg bg-white navbar-light py-3 py-lg-0">
                    <a href="${pageContext.request.contextPath}/ReceptionistHomeServlet" class="navbar-brand">
                        <h1 class="m-0 text-uppercase text-primary"><i class="fa fa-clinic-medical me-2"></i>Lễ tân</h1>
                    </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarCollapse">
                        <div class="navbar-nav ms-auto py-0">
                            <a href="${pageContext.request.contextPath}/receptionist/home_receptionist.jsp" class="nav-item nav-link">Trang chủ</a>
                            <div class="nav-item dropdown">
                                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
                                    Thông tin lễ tân
                                </a>
                                <div class="dropdown-menu m-0">
                                    <a href="${pageContext.request.contextPath}/ReceptionistDetailServlet" class="dropdown-item">
                                        <i class="bi-person-circle rounded-circle me-2 text-warning"></i>Hồ sơ cá nhân 
                                    </a>
                                    <a href="${pageContext.request.contextPath}/ReceptionistListServlet" class="dropdown-item">
                                        <i class="bi bi-person-lines-fill me-2 text-success"></i>Danh sách lễ tân
                                    </a>
                                </div>
                            </div>


                            <a href="#" class="nav-item nav-link">
                                Hồ sơ khám bệnh
                            </a>
                            <a href="${pageContext.request.contextPath}/AppointmentListServlet" class="nav-item nav-link">
                                Lịch hẹn khám bệnh
                            </a>
                            <a href="${pageContext.request.contextPath}/BillListServlet" class="nav-item nav-link">
                                Hóa đơn khám bệnh
                            </a> 

                            <a href="${pageContext.request.contextPath}/auth/login.jsp" class="nav-item nav-link">Đăng xuất</a>
                        </div>
                    </div>
                </nav>
            </div>
        </div>
        <!-- Navbar End -->
    </body>
</html>

