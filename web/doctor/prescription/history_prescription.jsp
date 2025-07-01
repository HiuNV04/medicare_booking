<%-- 
    Document   : history_prescription
    Created on : May 28, 2025, 10:21:47 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>TEAM3 - Tạo đơn thuốc</title>
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


        <!-- Prescription History Start -->
        <div class="container mt-5 mb-5">
            <h2 class="text-center mb-4">Danh sách đơn thuốc</h2>

            <table class="table table-bordered table-hover text-center align-middle">
                <thead class="table-primary">
                    <tr>
                        <th>STT</th>
                        <th>Tên bệnh nhân</th>
                        <th>Bác sĩ</th>
                        <th>Ngày kê</th>
                        <th>Chi tiết</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Ví dụ dữ liệu tĩnh -->
                    <tr>
                        <td>1</td>
                        <td>Nguyễn Văn A</td>
                        <td>BS. Hùng Đăng</td>
                        <td>11/05/2025</td>
                        <td>
                            <a href="view_prescription.jsp?id=1" class="btn btn-sm btn-info">
                                <i class="fa fa-eye"></i> Xem
                            </a>
                        </td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Lê Thị B</td>
                        <td>BS. Tiến Thành</td>
                        <td>13/05/2025</td>
                        <td>
                            <a href="view_prescription.jsp?id=2" class="btn btn-sm btn-info">
                                <i class="fa fa-eye"></i> Xem
                            </a>
                        </td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>Phạm Thu C</td>
                        <td>BS. Trần Minh</td>
                        <td>15/05/2025</td>
                        <td>
                            <a href="view_prescription.jsp?id=2" class="btn btn-sm btn-info">
                                <i class="fa fa-eye"></i> Xem
                            </a>
                        </td>
                    </tr>
                    <tr>
                        <td>4</td>
                        <td>Trần Văn D</td>
                        <td>BS. Phạm Ân</td>
                        <td>18/05/2025</td>
                        <td>
                            <a href="view_prescription.jsp?id=2" class="btn btn-sm btn-info">
                                <i class="fa fa-eye"></i> Xem
                            </a>
                        </td>
                    </tr>
                    <tr>
                        <td>5</td>
                        <td>Bùi Ngọc E</td>
                        <td>BS. Hồ Hải</td>
                        <td>19/05/2025</td>
                        <td>
                            <a href="view_prescription.jsp?id=2" class="btn btn-sm btn-info">
                                <i class="fa fa-eye"></i> Xem
                            </a>
                        </td>
                    </tr>
                    <!-- Lặp thêm dữ liệu ở đây bằng JSTL hoặc scriptlet nếu có backend -->
                </tbody>
            </table>
        </div>
        <!-- Prescription History End -->











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