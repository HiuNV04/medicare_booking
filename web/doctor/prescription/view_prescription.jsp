<%-- 
    Document   : view_prescription
    Created on : May 27, 2025, 1:21:46 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>TEAM3 - Xem đơn thuốc</title>
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

        <!-- View_Prescription Form Start -->
        <div class="container mt-5 mb-5">
            <h2 class="text-center mb-4">Chi tiết đơn thuốc</h2>

            <!-- Thông tin cơ bản -->
            <div class="row mb-3">
                <div class="col-md-6">
                    <label class="form-label fw-bold">Bệnh nhân</label>
                    <div class="border p-2 rounded">Nguyễn Văn A</div>
                </div>
                <div class="col-md-6">
                    <label class="form-label fw-bold">Ngày tạo</label>
                    <div class="border p-2 rounded">2025-05-28</div>
                </div>
            </div>

            <div class="mb-3">
                <label class="form-label fw-bold">Triệu chứng</label>
                <div class="border p-2 rounded">Đau đầu, sốt nhẹ, buồn nôn</div>
            </div>

            <div class="mb-3">
                <label class="form-label fw-bold">Chẩn đoán</label>
                <div class="border p-2 rounded">Viêm xoang cấp</div>
            </div>

            <div class="mb-3">
                <label class="form-label fw-bold">Ghi chú thêm</label>
                <div class="border p-2 rounded">Bệnh nhân cần nghỉ ngơi và uống nhiều nước</div>
            </div>

            <!-- Danh sách thuốc -->
            <div class="mb-3">
                <label class="form-label fw-bold">Danh sách thuốc:</label>
                <table class="table table-bordered text-center align-middle">
                    <thead class="table-light">
                        <tr>
                            <th>Tên thuốc</th>
                            <th>Cách dùng</th>
                            <th>Số lượng viên</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Paracetamol</td>
                            <td>Uống sau khi ăn, 3 viên/ngày</td>
                            <td>9 viên</td>
                        </tr>
                        <tr>
                            <td>Amoxicillin</td>
                            <td>Uống sáng - tối</td>
                            <td>6 viên</td>
                        </tr>
                        <tr>
                            <td>Loratadine</td>
                            <td>1 viên trước khi ngủ</td>
                            <td>3 viên</td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <div class="text-center mt-4">
                <a href="create_prescription.jsp" class="btn btn-primary">Tạo đơn mới</a>
                <a href="<%= request.getContextPath() %>/doctor/doctor_home.jsp" class="btn btn-secondary">Về trang chủ</a>
            </div>
        </div>

        <!-- View_Prescription Form End -->





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
