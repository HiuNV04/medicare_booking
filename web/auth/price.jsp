<%-- 
    Document   : price
    Created on : May 20, 2025, 3:34:27 PM
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
        <div class="container-fluid py-2 border-bottom d-none d-lg-block">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 text-center text-lg-start mb-2 mb-lg-0">
                        <div class="d-inline-flex align-items-center">
                            <a class="text-decoration-none text-body pe-3" href=""><i class="bi bi-telephone me-2"></i>0948803382</a>
                            <span class="text-body">|</span>
                            <a class="text-decoration-none text-body px-3" href=""><i class="bi bi-envelope me-2"></i>khiconst@example.com</a>
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
                            <a href="<%= request.getContextPath() %>/auth/home.jsp" class="nav-item nav-link active">Trang chủ</a>
                            <a href="<%= request.getContextPath() %>/auth/about.jsp" class="nav-item nav-link">Giới thiệu</a>
                            <a href="<%= request.getContextPath() %>/auth/service.jsp" class="nav-item nav-link">Dịch vụ</a>
                            <a href="<%= request.getContextPath() %>/auth/price.jsp" class="nav-item nav-link">Bảng giá</a>
                            <div class="nav-item dropdown">
                                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Các trang phụ</a>
                                <div class="dropdown-menu m-0">
                                    <a href="blog.html" class="dropdown-item">Bài viết</a>
                                    <a href="detail.html" class="dropdown-item">Chi tiết bài viết</a>
                                    <a href="team.html" class="dropdown-item">Đội ngũ nhân viên</a>
                                    <a href="testimonial.html" class="dropdown-item">Đánh giá</a>
                                    <a href="appointment.html" class="dropdown-item">Đặt lịch hẹn</a>
                                    <a href="search.html" class="dropdown-item">Tìm kiếm</a>
                                </div>
                            </div>
                            <a href="contact.html" class="nav-item nav-link">Liên hệ</a>
                        </div>
                    </div>
                </nav>
            </div>
        </div>
        <!-- Navbar End -->
        
        
        
        <!-- Price Start -->
        <div class="container-fluid py-5">
            <div class="container">
                <div class="text-center mx-auto mb-5" style="max-width: 500px;">
                    <h5 class="d-inline-block text-primary text-uppercase border-bottom border-5">Dịch vụ y tế</h5>
                    <h1 class="display-4">Dịch vụ khám chất lượng cao</h1>
                </div>
                <div class="owl-carousel price-carousel position-relative" style="padding: 0 45px 45px 45px;">
                    <div class="bg-light rounded text-center">
                        <div class="position-relative">
                            <img class="img-fluid rounded-top" src="img/price-1.jpg" alt="">
                            <div class="position-absolute w-100 h-100 top-50 start-50 translate-middle rounded-top d-flex flex-column align-items-center justify-content-center" style="background: rgba(29, 42, 77, .8);">
                                <h3 class="text-white">Pregnancy Care</h3>
                                <h1 class="display-4 text-white mb-0">
                                    <small class="align-top fw-normal" style="font-size: 22px; line-height: 45px;">$</small>49<small class="align-bottom fw-normal" style="font-size: 16px; line-height: 40px;">/ Year</small>
                                </h1>
                            </div>
                        </div>
                        <div class="text-center py-5">
                            <p>Emergency Medical Treatment</p>
                            <p>Highly Experienced Doctors</p>
                            <p>Highest Success Rate</p>
                            <p>Telephone Service</p>
                            <a href="" class="btn btn-primary rounded-pill py-3 px-5 my-2">Apply Now</a>
                        </div>
                    </div>
                    <div class="bg-light rounded text-center">
                        <div class="position-relative">
                            <img class="img-fluid rounded-top" src="img/price-2.jpg" alt="">
                            <div class="position-absolute w-100 h-100 top-50 start-50 translate-middle rounded-top d-flex flex-column align-items-center justify-content-center" style="background: rgba(29, 42, 77, .8);">
                                <h3 class="text-white">Health Checkup</h3>
                                <h1 class="display-4 text-white mb-0">
                                    <small class="align-top fw-normal" style="font-size: 22px; line-height: 45px;">$</small>99<small class="align-bottom fw-normal" style="font-size: 16px; line-height: 40px;">/ Year</small>
                                </h1>
                            </div>
                        </div>
                        <div class="text-center py-5">
                            <p>Emergency Medical Treatment</p>
                            <p>Highly Experienced Doctors</p>
                            <p>Highest Success Rate</p>
                            <p>Telephone Service</p>
                            <a href="" class="btn btn-primary rounded-pill py-3 px-5 my-2">Apply Now</a>
                        </div>
                    </div>
                    <div class="bg-light rounded text-center">
                        <div class="position-relative">
                            <img class="img-fluid rounded-top" src="img/price-3.jpg" alt="">
                            <div class="position-absolute w-100 h-100 top-50 start-50 translate-middle rounded-top d-flex flex-column align-items-center justify-content-center" style="background: rgba(29, 42, 77, .8);">
                                <h3 class="text-white">Dental Care</h3>
                                <h1 class="display-4 text-white mb-0">
                                    <small class="align-top fw-normal" style="font-size: 22px; line-height: 45px;">$</small>149<small class="align-bottom fw-normal" style="font-size: 16px; line-height: 40px;">/ Year</small>
                                </h1>
                            </div>
                        </div>
                        <div class="text-center py-5">
                            <p>Emergency Medical Treatment</p>
                            <p>Highly Experienced Doctors</p>
                            <p>Highest Success Rate</p>
                            <p>Telephone Service</p>
                            <a href="" class="btn btn-primary rounded-pill py-3 px-5 my-2">Apply Now</a>
                        </div>
                    </div>
                    <div class="bg-light rounded text-center">
                        <div class="position-relative">
                            <img class="img-fluid rounded-top" src="img/price-4.jpg" alt="">
                            <div class="position-absolute w-100 h-100 top-50 start-50 translate-middle rounded-top d-flex flex-column align-items-center justify-content-center" style="background: rgba(29, 42, 77, .8);">
                                <h3 class="text-white">Operation & Surgery</h3>
                                <h1 class="display-4 text-white mb-0">
                                    <small class="align-top fw-normal" style="font-size: 22px; line-height: 45px;">$</small>199<small class="align-bottom fw-normal" style="font-size: 16px; line-height: 40px;">/ Year</small>
                                </h1>
                            </div>
                        </div>
                        <div class="text-center py-5">
                            <p>Emergency Medical Treatment</p>
                            <p>Highly Experienced Doctors</p>
                            <p>Highest Success Rate</p>
                            <p>Telephone Service</p>
                            <a href="" class="btn btn-primary rounded-pill py-3 px-5 my-2">Apply Now</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Price Plan End -->
        
        
        
        <!-- Appointment Start -->
        <div class="container-fluid bg-primary my-5 py-5">
            <div class="container py-5">
                <div class="row gx-5">
                    <div class="col-lg-6 mb-5 mb-lg-0">
                        <div class="mb-4">
                            <h5 class="d-inline-block text-white text-uppercase border-bottom border-5">Appointment</h5>
                            <h1 class="display-4">Make An Appointment For Your Family</h1>
                        </div>
                        <p class="text-white mb-5">Eirmod sed tempor lorem ut dolores. Aliquyam sit sadipscing kasd ipsum. Dolor ea et dolore et at sea ea at dolor, justo ipsum duo rebum sea invidunt voluptua. Eos vero eos vero ea et dolore eirmod et. Dolores diam duo invidunt lorem. Elitr ut dolores magna sit. Sea dolore sanctus sed et. Takimata takimata sanctus sed.</p>
                        <a class="btn btn-dark rounded-pill py-3 px-5 me-3" href="">Find Doctor</a>
                        <a class="btn btn-outline-dark rounded-pill py-3 px-5" href="">Read More</a>
                    </div>
                    <div class="col-lg-6">
                        <div class="bg-white text-center rounded p-5">
                            <h1 class="mb-4">Book An Appointment</h1>
                            <form>
                                <div class="row g-3">
                                    <div class="col-12 col-sm-6">
                                        <select class="form-select bg-light border-0" style="height: 55px;">
                                            <option selected>Choose Department</option>
                                            <option value="1">Department 1</option>
                                            <option value="2">Department 2</option>
                                            <option value="3">Department 3</option>
                                        </select>
                                    </div>
                                    <div class="col-12 col-sm-6">
                                        <select class="form-select bg-light border-0" style="height: 55px;">
                                            <option selected>Select Doctor</option>
                                            <option value="1">Doctor 1</option>
                                            <option value="2">Doctor 2</option>
                                            <option value="3">Doctor 3</option>
                                        </select>
                                    </div>
                                    <div class="col-12 col-sm-6">
                                        <input type="text" class="form-control bg-light border-0" placeholder="Your Name" style="height: 55px;">
                                    </div>
                                    <div class="col-12 col-sm-6">
                                        <input type="email" class="form-control bg-light border-0" placeholder="Your Email" style="height: 55px;">
                                    </div>
                                    <div class="col-12 col-sm-6">
                                        <div class="date" id="date" data-target-input="nearest">
                                            <input type="text"
                                                   class="form-control bg-light border-0 datetimepicker-input"
                                                   placeholder="Date" data-target="#date" data-toggle="datetimepicker" style="height: 55px;">
                                        </div>
                                    </div>
                                    <div class="col-12 col-sm-6">
                                        <div class="time" id="time" data-target-input="nearest">
                                            <input type="text"
                                                   class="form-control bg-light border-0 datetimepicker-input"
                                                   placeholder="Time" data-target="#time" data-toggle="datetimepicker" style="height: 55px;">
                                        </div>
                                    </div>
                                    <div class="col-12">
                                        <button class="btn btn-primary w-100 py-3" type="submit">Make An Appointment</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
