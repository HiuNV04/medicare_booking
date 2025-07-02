<%-- 
    Document   : doctor_home
    Created on : May 27, 2025, 10:12:40 AM
    Author     : Admin
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/check/check_doctor.jsp" %>




<%
    long version = System.currentTimeMillis();
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Trang chủ</title>
        <jsp:include page="/frontend/head/head.jsp" />
    </head>
    <body> 
        <jsp:include page="/frontend/topbar/topbar.jsp" />
        <jsp:include page="/doctor/navbar/navbar.jsp" />
        <jsp:include page="/frontend/hero/hero.jsp" />
        <!--        Team Start -->
        <div class="container-fluid py-5">
            <div class="container">
                <div class="text-center mx-auto mb-5" style="max-width: 500px;">
                    <h5 class="d-inline-block text-primary text-uppercase border-bottom border-5">Các bác sĩ của chúng tôi</h5>
                    <h1 class="display-4">Chuyên gia chăm sóc sức khỏe có trình độ</h1>
                </div>   

                <c:if test="${not empty doctors}">
                    <div class="container-fluid py-5">
                        <div class="container">
                            <div class="owl-carousel team-carousel position-relative">
                                <c:forEach var="doctor" items="${doctors}" varStatus="status">
                                    <c:set var="version" value="<%= System.currentTimeMillis() %>" />
                                    <c:set var="imageUrl" value="${doctor.imageUrl}" />
                                    <c:choose>
                                        <c:when test="${fn:startsWith(imageUrl, 'external_images/doctor/')}">
                                            <c:set var="imageUrl" value="ImageServlet?file=${fn:split(imageUrl, '/')[2]}" />
                                        </c:when>
                                    </c:choose>

                                    <div class="team-item">
                                        <div class="row g-0 bg-light rounded overflow-hidden">
                                            <!-- Ảnh bác sĩ -->
                                            <div class="col-12 col-sm-5 h-100">
                                                <img class="img-fluid h-100"
                                                     src="${pageContext.request.contextPath}/${imageUrl}&v=${version}"
                                                     alt="Doctor Image"
                                                     style="object-fit: cover;">
                                            </div>

                                            <!-- Thông tin bác sĩ -->
                                            <div class="col-12 col-sm-7 h-100 d-flex flex-column">
                                                <div class="mt-auto p-4">
                                                    <h3><c:out value="${doctor.fullName}" /></h3>
                                                    <h5 class="fw-normal fst-italic text-primary mb-2">
                                                        <c:out value="${doctor.levelName}" />
                                                    </h5>
                                                    <p class="mb-1"><strong>ID:</strong> <c:out value="${doctor.id}" /></p>
                                                    <p class="mb-1"><strong>Chuyên khoa:</strong> <c:out value="${doctor.specialization}"/></p>
                                                    <br>
                                                    <p class="mb-0 text-muted">
                                                        <strong>Nhận xét:</strong> <c:out value="${doctor.note}" />
                                                    </p>
                                                </div>

                                                <!-- Mạng xã hội -->
                                                <div class="d-flex mt-auto border-top p-4">
                                                    <a class="btn btn-lg btn-primary btn-lg-square rounded-circle me-3" href="#"><i class="fab fa-twitter"></i></a>
                                                    <a class="btn btn-lg btn-primary btn-lg-square rounded-circle me-3" href="#"><i class="fab fa-facebook-f"></i></a>
                                                    <a class="btn btn-lg btn-primary btn-lg-square rounded-circle" href="#"><i class="fab fa-linkedin-in"></i></a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>


        <jsp:include page="/doctor/testimonial/testimonial.jsp" />
        <jsp:include page="/frontend/footer/footer.jsp" />
        <jsp:include page="/frontend/script/script.jsp" />
    </body>
</html>


