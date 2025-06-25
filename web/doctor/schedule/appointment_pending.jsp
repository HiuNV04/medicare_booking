<%-- 
    Document   : appointment_pending
    Created on : May 27, 2025, 8:54:58 PM
    Author     : Admin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>TEAM3 - Trạng thái xác nhận</title>
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

            <h2 class="text-center mb-4">Danh sách lịch hẹn <span class="text-warning">chờ xác nhận</span>
            </h2>


            <!-- Bảng danh sách -->
            <table class="table table-bordered table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>STT</th>
                        <th>Tên bệnh nhân</th>
                        <th>Ngày sinh</th>
                        <th>Giới tính</th>                     
                        <th>Ngày khám</th>
                        <th>Giờ khám</th>
                        <th>Chuyên khoa</th>
                        <th>Ghi chú</th>
                        <th>Thao tác</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Dữ liệu mẫu -->
                    <tr>
                        <td>1</td>
                        <td>Nguyễn Bảo An</td>
                        <td>20-09-2004</td>
                        <td>Nam</td>                 
                        <td>21-05-2025</td>
                        <td>08:00 - 08:30</td>
                        <td>Da liễu</td>
                        <td>Da suốt hiện nhièu vết mần đỏ</td>
                        <td>
                            <form method="post" action="HandleAppointmentServlet" class="d-inline">
                                <input type="hidden" name="appointmentId" value="1">
                                <input type="hidden" name="action" value="accept">
                                <button type="submit" class="btn btn-success btn-sm">Xác nhận</button>
                            </form>
                            <form method="post" action="HandleAppointmentServlet" class="d-inline ms-1">
                                <input type="hidden" name="appointmentId" value="1">
                                <input type="hidden" name="action" value="cancel">
                                <button type="submit" class="btn btn-danger btn-sm">Hủy</button>
                            </form>
                        </td>
                    </tr>

                    <tr>
                        <td>2</td>
                        <td>Nguyễn Văn Giang</td>
                        <td>15-07-1999</td>
                        <td>Nam</td>
                        <td>24-05-2025</td>
                        <td>08:00 - 08:30</td>
                        <td>Tim mạch</td>
                        <td>Gia đình có tiền sử di truyền bệnh tim mạch</td>
                        <td>
                            <form method="post" action="HandleAppointmentServlet" class="d-inline">
                                <input type="hidden" name="appointmentId" value="5">
                                <input type="hidden" name="action" value="accept">
                                <button type="submit" class="btn btn-success btn-sm">Xác nhận</button>
                            </form>
                            <form method="post" action="HandleAppointmentServlet" class="d-inline ms-1">
                                <input type="hidden" name="appointmentId" value="5">
                                <input type="hidden" name="action" value="cancel">
                                <button type="submit" class="btn btn-danger btn-sm">Hủy</button>
                            </form>
                        </td>
                    </tr>

                    <tr>
                        <td>3</td>
                        <td>Trần Đức Minh</td>                       
                        <td>08-12-2001</td>
                        <td>Nam</td>
                        <td>26-05-2025</td>
                        <td>07:30 - 09:30</td>
                        <td>Tai Mũi Họng</td>
                        <td>Đau tai và viêm họng</td>
                        <td>
                            <form method="post" action="HandleAppointmentServlet" class="d-inline">
                                <input type="hidden" name="appointmentId" value="3">
                                <input type="hidden" name="action" value="accept">
                                <button type="submit" class="btn btn-success btn-sm">Xác nhận</button>
                            </form>
                            <form method="post" action="HandleAppointmentServlet" class="d-inline ms-1">
                                <input type="hidden" name="appointmentId" value="3">
                                <input type="hidden" name="action" value="cancel">
                                <button type="submit" class="btn btn-danger btn-sm">Hủy</button>
                            </form>
                        </td>
                    </tr>

                    <!-- Thêm các dòng khác nếu cần -->
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


