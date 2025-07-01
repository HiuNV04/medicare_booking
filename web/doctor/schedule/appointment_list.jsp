<%-- 
    Document   : appointment_list
    Created on : May 27, 2025, 1:21:01 PM
    Author     : Admin
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>TEAM3 - Danh sách lịch hẹn</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">

        <!-- Favicon -->
        <link href="${pageContext.request.contextPath}/img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Roboto+Condensed:wght@400;700&family=Roboto:wght@400;700&display=swap" rel="stylesheet">  

        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="${pageContext.request.contextPath}/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

        <!-- Customized Bootstrap Stylesheet -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">




        <!-- Owl Carousel CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.min.css">

    </head>

    <body>
        <!-- topbar Start -->
        <jsp:include page="/doctor/topbar/topbar.jsp" />
        <!-- topbar End -->


        <!-- navbar Start -->
        <jsp:include page="/doctor/navbar/navbar.jsp" />
        <!-- navbar End -->



        <div class="container mt-5">
            <h2 class="text-center mb-4">Danh sách lịch hẹn theo chuyên khoa</h2>

            <!-- Bộ lọc -->
            <form class="row g-3 mb-4" method="get" action="appointmentlistservlet">
                <div class="col-md-3">
                    <label class="form-label">Từ ngày</label>
                    <input type="date" class="form-control" name="fromDate">
                </div>
                <div class="col-md-3">
                    <label class="form-label">Đến ngày</label>
                    <input type="date" class="form-control" name="toDate">
                </div>
                <div class="col-md-3">
                    <label class="form-label">Trạng thái</label>
                    <select class="form-select" name="status">
                        <option value="">Tất cả</option>
                        <option value="Pending">Chờ xác nhận</option>
                        <option value="Accepted">Đã xác nhận</option>
                        <option value="Canceled">Đã hủy</option>
                    </select>
                </div>
                <div class="col-md-3">
                    <label class="form-label">Chuyên khoa</label>
                    <select class="form-select" name="specialty">
                        <option value="">Tất cả</option>
                        <option value="Da liễu">Da liễu</option>
                        <option value="Tim mạch">Tim mạch</option>
                        <option value="Nội tổng quát">Nội tổng quát</option>
                        <option value="Răng Hàm Mặt">Răng Hàm Mặt</option>
                    </select>
                </div>
                <div class="col-12 text-end">
                    <button type="submit" class="btn btn-primary">Lọc</button>
                </div>
            </form>


            <!-- Bảng danh sách -->
            <table class="table table-bordered table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>STT</th>
                        <th>Tên bệnh nhân</th>
                        <th>Ngày khám</th>
                        <th>Giờ</th>
                        <th>Chuyên khoa</th>
                        <th>Trạng thái</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="a" items="${appointments}">
                        <tr>
                            <td>${a.id}</td>
                            <td>${a.patientName}</td>
                            <td>${a.appointmentDate}</td>
                            <td>${a.timeRange}</td>
                            <td>${a.specialty}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${a.status == 'Pending'}">
                                        <span class="badge bg-warning text-dark">Chờ xác nhận</span>
                                    </c:when>
                                    <c:when test="${a.status == 'Confirmed'}">
                                        <span class="badge bg-success">Đã xác nhận</span>
                                    </c:when>
                                    <c:when test="${a.status == 'Cancelled'}">
                                        <span class="badge bg-danger">Đã hủy</span>
                                    </c:when>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>

            </table>
        </div>

        <!-- Footer Start -->
        <jsp:include page="/doctor/footer/footer.jsp" />
        <!-- Footer End -->










        <!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>


        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/waypoints/waypoints.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>
        <script src="lib/tempusdominus/js/moment.min.js"></script>
        <script src="lib/tempusdominus/js/moment-timezone.min.js"></script>
        <script src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

        <!-- Template Javascript -->
        <script src="js/main.js"></script>
        <!-- jQuery (Owl Carousel cần jQuery) -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

        <!-- Owl Carousel JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>
    </body>
</html>


