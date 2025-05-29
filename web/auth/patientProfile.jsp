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
        <title>Patient Profile</title>
         <link href="${pageContext.request.contextPath}/css/style2.css" rel="stylesheet">
    </head>
    <body>
        <div class="header">
            <h2>Profile</h2>
            <a href="/MediCare_Booking/auth/home.jsp">Trang chủ</a>
        </div>

        <div class="container">
            <div class="sidebar">
                <c:set var="i" value="${sessionScope.user}"/>
                <a style="margin-bottom: 12%;" href="${i.getImg()}">
                    <img src="${i.getImg()}" alt="Avatar" style="width:100%;">
                </a>
                <a style="margin-bottom: 12%; font-size: 121%;" href="/MediCare_Booking/auth/patient.jsp">Home</a>

                <a style="margin-bottom: 12%; font-size: 121%;" href="${pageContext.request.contextPath}/showPatient">Patient Profile</a>

                <a style="margin-bottom: 12%; font-size: 121%;" href="${pageContext.request.contextPath}/updatePatient">Update Profile</a>

                <a style="margin-bottom: 12%; font-size: 121%;" href="${pageContext.request.contextPath}/changePassPatient">Change Password</a>

                <a style="margin-bottom: 12%;  font-size: 121%;" href="#">View Appointment</a>

                <a style="margin-bottom: 12%;  font-size: 121%;" href="#">History Feedback</a>
            </div>
            <div>
                <c:set var="i" value="${sessionScope.user}"/>
                <form action="showPatient" method="get">
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
                            <label>Identity Number: </label>
                            <input type="text" class="form-control" name="identity" value="${i.getIdentity()}" readonly=""/>
                        </div>
                        <div class="form-group">
                            <label>Insurance Number: </label>
                            <input type="text" class="form-control" name="insurance" value="${i.getInsurance()}" readonly=""/>
                        </div>
                        <div class="form-group">
                            <label>Phone Number: </label>
                            <input type="text" class="form-control" name="insurance" value="${i.getPhone()}" readonly=""/>
                        </div>
                        <div class="form-group">
                            <label>Address: </label>
                            <input type="text" class="form-control" name="address" value="${i.getAddress()}" readonly=""/>
                        </div>
                        <div class="form-group">
                            <label>Job: </label>
                            <input type="text" class="form-control" name="job" value="${i.getJob()}" readonly=""/>
                        </div>
                </form>
            </div>
        </div>
    </body>
</html>
