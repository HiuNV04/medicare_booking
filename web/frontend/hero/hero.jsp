<%-- 
    Document   : hero
    Created on : Jun 3, 2025, 2:01:47 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
         <!-- Hero Start -->
        <div class="container-fluid bg-primary py-5 mb-5 hero-header">
            <div class="container py-5">
                <div class="row justify-content-start">
                    <div class="col-lg-8 text-center text-lg-start">
                        <h5 class="d-inline-block text-primary text-uppercase border-bottom border-5" style="border-color: rgba(256, 256, 256, .3) !important;">Welcome To Medinova</h5>
                        <h1 class="display-1 text-white mb-md-4">Giải pháp chăm sóc sức khỏe tốt nhất dành cho bạn</h1>
                        <div class="pt-2">
                        <a href="${pageContext.request.contextPath}/DoctorDetailServlet" class="btn btn-light rounded-pill py-md-3 px-md-5 mx-2">Thông tin bác sĩ</a>
                        <a href="" class="btn btn-outline-light rounded-pill py-md-3 px-md-5 mx-2">Appointment</a>
                    </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Hero End -->
    </body>
</html>
