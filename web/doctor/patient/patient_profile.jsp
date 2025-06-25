<%-- 
    Document   : view_profile
    Created on : May 27, 2025, 10:12:40 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>TEAM3 - Hồ sơ bệnh nhân</title>
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

        <!-- Content Start -->
        <div class="container py-5">
            <h3 class="mb-4">Hồ sơ bệnh nhân</h3>

            <!-- Tabs -->
            <ul class="nav nav-tabs mb-3" id="patientTab" role="tablist">
                <li class="nav-item" role="presentation">
                    <button class="nav-link active" id="waiting-tab" data-bs-toggle="tab" data-bs-target="#waiting" type="button" role="tab">
                        Bệnh nhân chờ khám
                    </button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="examined-tab" data-bs-toggle="tab" data-bs-target="#examined" type="button" role="tab">
                        Bệnh nhân đã khám
                    </button>
                </li>
            </ul>

            <div class="tab-content" id="patientTabContent">

                <!-- Tab 1: Bệnh nhân chờ khám -->
                <div class="tab-pane fade show active" id="waiting" role="tabpanel">
                    <div class="table-responsive">
                        <table class="table table-bordered align-middle">
                            <thead class="table-light">
                                <tr>
                                    <th>Họ tên</th>
                                    <th>Tuổi</th>
                                    <th>Triệu chứng</th>
                                    <th>Nhóm máu</th>
                                    <th>Ngày đăng ký</th>
                                    <th>Thao tác</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- Dữ liệu mẫu -->
                                <tr>
                                    <td>Nguyễn Văn A</td>
                                    <td>30</td>
                                    <td>Sốt cao, đau đầu</td>
                                    <td>A</td>
                                    <td>17-03-2025</td>

                                    <td>
                                        <a href="${pageContext.request.contextPath}/doctor/prescription/create_prescription.jsp" class="btn btn-sm btn-primary">
                                            Chuẩn đoán
                                        </a>
                                    </td>
                                </tr>
                                <!-- Lặp theo dữ liệu thật sau này -->
                            </tbody>
                        </table>
                    </div>
                </div>

                <!-- Tab 2: Bệnh nhân đã khám -->
                <div class="tab-pane fade" id="examined" role="tabpanel">
                    <div class="table-responsive">
                        <table class="table table-bordered align-middle">
                            <thead class="table-light">
                                <tr>
                                    <th>Họ tên</th>
                                    <th>Tuổi</th>
                                    <th>Nhóm máu</th>
                                    <th>Ngày đăng ký</th>
                                    <th>Ngày khám</th>
                                    <th>Chuẩn đoán</th>
                                    <th>Thao tác</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- Dữ liệu mẫu -->
                                <tr>
                                    <td>Trần Thị B</td>
                                    <td>30</td>
                                    <td>A</td>
                                    <td>14-02-2025</td>
                                    <td>19-05-2025</td>
                                    <td>Viêm da liễu</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/doctor/prescription/create_prescription.jsp" class="btn btn-sm btn-info">
                                            Tạo đơn thuốc
                                        </a>
                                    </td>
                                </tr>
                                <!-- Lặp theo dữ liệu thật sau này -->
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
        </div>
        <!-- Content End -->

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
