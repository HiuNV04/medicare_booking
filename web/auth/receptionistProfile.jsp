<%-- 
    Document   : patientprofile
    Created on : May 26, 2025, 10:15:13 AM
    Author     : ptson
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Receptionist Profile</title>
        <link href="${pageContext.request.contextPath}/css/style1.css" rel="stylesheet">
    </head>
    <body>
        <div class="header">
            <h2>Profile</h2>
            <a href="/MediCare_Booking/auth/home.jsp">Trang chủ</a>
        </div>

        <div class="container">
            <div class="sidebar">
                <c:set var="i" value="${sessionScope.receptionist}"/>
                <a style="margin-bottom: 12%;" href="${i.getImg()}">
                    <img src="${i.getImg()}" alt="Avatar" style="width:100%;">
                </a>
                <a style="margin-bottom: 12%; font-size: 121%;" href="/MediCare_Booking/auth/receptionist.jsp">Home</a>

                <a style="margin-bottom: 12%; font-size: 121%;" href="${pageContext.request.contextPath}/showReceptionist">Receptionist Profile</a>

                <a style="margin-bottom: 12%; font-size: 121%;" href="${pageContext.request.contextPath}/changePassReceptionist">Change Password</a>
                
                <a style="margin-bottom: 12%;  font-size: 121%;" href="${pageContext.request.contextPath}/reViewPatient">List Patient</a>

                <a style="margin-bottom: 12%;  font-size: 121%;" href="#">View Appointment</a>

                <a style="margin-bottom: 12%;  font-size: 121%;" href="#">History Feedback</a>
            </div>
            <div>
                <c:set var="i" value="${sessionScope.receptionist}"/>
                <form class="form1" action="showReceptionist" method="get" style="margin-right: -333%; margin-left: 12%;">
                        <div class="form-group">
                            <label>ID: </label>
                            <input type="text" class="form-control" name="id" value="${i.getId()}" readonly=""/>
                        </div>
                        <div class="form-group">
                            <label>Username: </label>
                            <input type="text" class="form-control" name="username" value="${i.getUsername()}" readonly=""/>
                        </div>
                        <div class="form-group">
                            <label>Email: </label>
                            <input type="text" class="form-control" name="email" value="${i.getEmail()}" readonly=""/>
                        </div>
                        <div class="form-group">
                            <label>Password: </label>
                            <input type="password" class="form-control" name="pass" value="${i.getPassword()}" readonly=""/>
                        </div>
                        <div class="form-group">
                            <label>Full Name: </label>
                            <input type="text" class="form-control" name="fullname" value="${i.getFullname()}" readonly=""/>
                        </div>
                        <div class="form-group">
                            <label>Date of birth: </label>
                            <input type="text" class="form-control" name="dob" value="${i.getDob()}" readonly=""/>
                        </div>
                        <div class="form-group">
                            <label>Gender: </label>
                            <input type="text" class="form-control" name="gender" value="${i.getGender()}" readonly=""/>
                        </div>
                        <div class="form-group">
                            <label>Phone Number: </label>
                            <input type="text" class="form-control" name="phone" value="${i.getPhone()}" readonly=""/>
                        </div>
                        <div class="form-group">
                            <label>Address: </label>
                            <input type="text" class="form-control" name="address" value="${i.getAddress()}" readonly=""/>
                        </div>
                </form>
            </div>
        </div>
    </body>
</html>
