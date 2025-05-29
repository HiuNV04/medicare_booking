<%-- 
    Document   : about
    Created on : May 21, 2025, 9:48:31 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>TEAM3HANDSOME - Trang chủ khám chữa bệnh</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">
        <!--        <meta name="keywords" content="khám bệnh, chăm sóc sức khỏe, bác sĩ, dịch vụ y tế">
                <meta name="description" content="Trang web khám chữa bệnh chất lượng cao với đội ngũ bác sĩ chuyên môn, dịch vụ đặt lịch và tư vấn sức khỏe.">-->

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
    </head>
    <body>



        <!-- Topbar Start -->
        <div class="container-fluid py-2 border-bottom d-none d-lg-block">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 text-center text-lg-start mb-2 mb-lg-0">
                        <div class="d-inline-flex align-items-center">
                            <a class="text-decoration-none text-body pe-3" href=""><i class="bi bi-telephone me-2"></i>0948803382</a>
                            <span class="text-body">|</span>
                            <a class="text-decoration-none text-body px-3" href=""><i class="bi bi-envelope me-2"></i>khiconst4@gmail.com</a>
                        </div>
                    </div>
                    <div class="col-md-6 text-center text-lg-end">
                        <div class="d-inline-flex align-items-center">
                            <a class="text-body px-2" href="">
                                <i class="fab fa-facebook-f"></i>
                            </a>
                            <a class="text-body px-2" href="">
                                <i class="fab fa-twitter"></i>
                            </a>
                            <a class="text-body px-2" href="">
                                <i class="fab fa-linkedin-in"></i>
                            </a>
                            <a class="text-body px-2" href="">
                                <i class="fab fa-instagram"></i>
                            </a>
                            <a class="text-body ps-2" href="">
                                <i class="fab fa-youtube"></i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Topbar End -->



        <!-- Navbar Start -->
        <div class="container-fluid sticky-top bg-white shadow-sm">
            <div class="container">
                <nav class="navbar navbar-expand-lg bg-white navbar-light py-3 py-lg-0">
                    <a href="index.html" class="navbar-brand">
                        <h1 class="m-0 text-uppercase text-primary"><i class="fa fa-clinic-medical me-2"></i>TEAM3HANDSOME</h1>
                    </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarCollapse">
                        <div class="navbar-nav ms-auto py-0">
                            <a href="home.jsp" class="nav-item nav-link active">Trang chủ</a>
                            <a href="<%= request.getContextPath() %>/auth/about.jsp" class="nav-item nav-link">Giới thiệu</a>
                            <a href="<%= request.getContextPath() %>/auth/service.jsp" class="nav-item nav-link">Dịch vụ</a>
                            <a href="<%= request.getContextPath() %>/auth/price.jsp" class="nav-item nav-link">Bảng giá</a>
                            <div class="nav-item dropdown">
                                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Chi tiết</a>
                                <div class="dropdown-menu m-0">
                                    <a href="${pageContext.request.contextPath}/auth/blog.jsp" class="dropdown-item">Bài viết</a>
                                    <a href="${pageContext.request.contextPath}/auth/detail.jsp" class="dropdown-item">Chi tiết bài viết</a>
                                    <a href="${pageContext.request.contextPath}/auth/team.jsp" class="dropdown-item">Đội ngũ nhân viên</a>
                                    <a href="${pageContext.request.contextPath}/auth/testimonial.jsp" class="dropdown-item">Đánh giá</a>
                                    <a href="${pageContext.request.contextPath}/auth/appointment.jsp" class="dropdown-item">Đặt lịch hẹn</a>
                                    <a href="${pageContext.request.contextPath}/auth/search.jsp" class="dropdown-item">Tìm kiếm</a>
                                </div>
                            </div>
                            <a href="${pageContext.request.contextPath}/auth/contact.jsp" class="nav-item nav-link">Liên hệ</a>

                        </div>
                    </div>
                </nav>
            </div>
        </div>
        <!-- Navbar End -->

        <!-- About Start -->
        <div class="container-fluid py-5">
            <div class="container">
                <div class="row gx-5">
                    <div class="col-lg-5 mb-5 mb-lg-0" style="min-height: 500px;">
                        <div class="position-relative h-100">
                            <img src="${pageContext.request.contextPath}/img/about.jpg"
                                 class="position-absolute w-100 h-100 rounded"
                                 style="object-fit: cover;">


                        </div>
                    </div>
                    <div class="col-lg-7">
                        <div class="mb-4">
                            <h5 class="d-inline-block text-primary text-uppercase border-bottom border-5">Giới thiệu</h5>
                            <h1 class="display-4">Chăm sóc y tế tốt nhất cho bạn và gia đình bạn</h1>
                        </div>
                        <p>Đội ngũ nhân viên y tế chăm sóc tận tình, dịch vụ khám chữa bệnh đạt tiêu chuẩn cao và chất lượng.
                            Nâng cao chất lượng trải nghiệm và niềm tin dành cho mọi người.</p>
                        <div class="row g-3 pt-3">
                            <div class="col-sm-3 col-6">
                                <div class="bg-light text-center rounded-circle py-4">
                                    <i class="fa fa-3x fa-user-md text-primary mb-3"></i>
                                    <h6 class="mb-0">Đạt tiêu chuẩn<small class="d-block text-primary">Bác sĩ</small></h6>
                                </div>
                            </div>
                            <div class="col-sm-3 col-6">
                                <div class="bg-light text-center rounded-circle py-4">
                                    <i class="fa fa-3x fa-procedures text-primary mb-3"></i>
                                    <h6 class="mb-0">Khẩn cấp<small class="d-block text-primary">Dịch vụ</small></h6>
                                </div>
                            </div>
                            <div class="col-sm-3 col-6">
                                <div class="bg-light text-center rounded-circle py-4">
                                    <i class="fa fa-3x fa-microscope text-primary mb-3"></i>
                                    <h6 class="mb-0">Chính xác<small class="d-block text-primary">Xét nghiệm</small></h6>
                                </div>
                            </div>
                            <div class="col-sm-3 col-6">
                                <div class="bg-light text-center rounded-circle py-4">
                                    <i class="fa fa-3x fa-ambulance text-primary mb-3"></i>
                                    <h6 class="mb-0">Miễn phí<small class="d-block text-primary">Xe cứu thương</small></h6>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- About End -->


        <!-- Search Start -->
        <div class="container-fluid bg-primary my-5 py-5">
            <div class="container py-5">
                <div class="text-center mx-auto mb-5" style="max-width: 500px;">
                    <h5 class="d-inline-block text-white text-uppercase border-bottom border-5">Tìm kiếm một bác sĩ</h5>
                    <h1 class="display-4 mb-4">Tìm chuyên gia chăm sóc sức khỏe</h1>
                    <h5 class="text-white fw-normal">Duo ipsum erat stet dolor sea ut nonumy tempor. Tempor duo lorem eos sit sed ipsum takimata ipsum sit est. Ipsum ea voluptua ipsum sit justo</h5>
                </div>
                <div class="mx-auto" style="width: 100%; max-width: 600px;">
                    <div class="input-group">
                        <select class="form-select border-primary w-25" style="height: 60px;">
                            <option selected>Chuyên khoa</option>
                            <option value="1">Chuyên khoa 1</option>
                            <option value="2">Chuyên khoa 2</option>
                            <option value="3">Chuyên khoa 3</option>
                        </select>
                        <!--                        <input type="text" class="form-control border-primary w-50" placeholder="Keyword">-->
                        <input type="text" name="keyword" class="form-control border-primary w-50" placeholder="Từ khóa">

                        <button type="submit" class="btn btn-dark border-0 w-25">Tìm kiếm</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- Search End -->

        <!-- Team Start -->
        <div class="container-fluid py-5">
            <div class="container">
                <div class="text-center mx-auto mb-5" style="max-width: 500px;">
                    <h5 class="d-inline-block text-primary text-uppercase border-bottom border-5">Các bác sĩ của chúng tôi</h5>
                    <h1 class="display-4">Chuyên gia chăm sóc sức khỏe có trình độ</h1>
                </div>
                <div class="owl-carousel team-carousel position-relative">
                    <div class="team-item">
                        <div class="row g-0 bg-light rounded overflow-hidden">
                            <div class="col-12 col-sm-5 h-100">


                                <img src="${pageContext.request.contextPath}/img/team-1.jpg"
                                     class="position-absolute w-100 h-100 rounded"
                                     style="object-fit: cover;">

                            </div>
                            <div class="col-12 col-sm-7 h-100 d-flex flex-column">
                                <div class="mt-auto p-4">
                                    <h3>Tên bác sĩ</h3>
                                    <h6 class="fw-normal fst-italic text-primary mb-4">Chuyên gia tim mạch</h6>
                                    <p class="m-0">Dolor lorem eos dolor duo eirmod sea. Dolor sit magna rebum clita rebum dolor</p>
                                </div>
                                <div class="d-flex mt-auto border-top p-4">
                                    <a class="btn btn-lg btn-primary btn-lg-square rounded-circle me-3" href="#"><i class="fab fa-twitter"></i></a>
                                    <a class="btn btn-lg btn-primary btn-lg-square rounded-circle me-3" href="#"><i class="fab fa-facebook-f"></i></a>
                                    <a class="btn btn-lg btn-primary btn-lg-square rounded-circle" href="#"><i class="fab fa-linkedin-in"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="team-item">
                        <div class="row g-0 bg-light rounded overflow-hidden">
                            <div class="col-12 col-sm-5 h-100">
                                <img src="${pageContext.request.contextPath}/img/team-2.jpg"
                                     class="position-absolute w-100 h-100 rounded"
                                     style="object-fit: cover;">
                            </div>
                            <div class="col-12 col-sm-7 h-100 d-flex flex-column">
                                <div class="mt-auto p-4">
                                    <h3>Tên bác sĩ</h3>
                                    <h6 class="fw-normal fst-italic text-primary mb-4">Chuyên gia tim mạch</h6>
                                    <p class="m-0">Dolor lorem eos dolor duo eirmod sea. Dolor sit magna rebum clita rebum dolor</p>
                                </div>
                                <div class="d-flex mt-auto border-top p-4">
                                    <a class="btn btn-lg btn-primary btn-lg-square rounded-circle me-3" href="#"><i class="fab fa-twitter"></i></a>
                                    <a class="btn btn-lg btn-primary btn-lg-square rounded-circle me-3" href="#"><i class="fab fa-facebook-f"></i></a>
                                    <a class="btn btn-lg btn-primary btn-lg-square rounded-circle" href="#"><i class="fab fa-linkedin-in"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="team-item">
                        <div class="row g-0 bg-light rounded overflow-hidden">
                            <div class="col-12 col-sm-5 h-100">
                                <img src="${pageContext.request.contextPath}/img/team-3.jpg"
                                     class="position-absolute w-100 h-100 rounded"
                                     style="object-fit: cover;">


                            </div>
                            <div class="col-12 col-sm-7 h-100 d-flex flex-column">
                                <div class="mt-auto p-4">
                                    <h3>Tên bác sĩ</h3>
                                    <h6 class="fw-normal fst-italic text-primary mb-4">Chuyên gia tim mạch</h6>
                                    <p class="m-0">Dolor lorem eos dolor duo eirmod sea. Dolor sit magna rebum clita rebum dolor</p>
                                </div>
                                <div class="d-flex mt-auto border-top p-4">
                                    <a class="btn btn-lg btn-primary btn-lg-square rounded-circle me-3" href="#"><i class="fab fa-twitter"></i></a>
                                    <a class="btn btn-lg btn-primary btn-lg-square rounded-circle me-3" href="#"><i class="fab fa-facebook-f"></i></a>
                                    <a class="btn btn-lg btn-primary btn-lg-square rounded-circle" href="#"><i class="fab fa-linkedin-in"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Team End -->







        <!-- Footer Start -->
        <jsp:include page="footer.jsp"></jsp:include>
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

    </body>
</html>
