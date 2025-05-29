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
        <title>Patient Change Pass</title>
        <link href="${pageContext.request.contextPath}/css/style2.css" rel="stylesheet">
    </head>
    <body>
        <div class="header">
            <h2>Profile</h2>
            <a href="/MediCare_Booking/auth/home.jsp">Trang chủ</a>
        </div>

        <div class="container">
            <div class="sidebar">
                <a style="margin-bottom: 12%;" href="/MediCare_Booking/img/pic9.jpg">
                    <img src="/MediCare_Booking/img/pic9.jpg" alt="Avatar" style="width:100%;">
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
                <form action="changePassPatient" method="post">
                    <div class="form-group">
                        <label>Username: </label>
                        <input type="text" class="form-control" name="user" value="${i.getUsername()}" readonly=""/>
                    </div>
                    <div class="form-group">
                        <label>Old Password: </label>
                        <input type="password" class="form-control" name="oPass" value="" required=""/>
                    </div>
                    <div class="form-group">
                        <label>New Password: </label>
                        <input type="password" class="form-control" name="nPass" value="" required=""/>
                    </div>
                    <div class="form-group">
                        <label>Confirm New Password: </label>
                        <input type="password" class="form-control" name="cpw" value="" required=""/>
                    </div>
                    <div style="margin-left: 39%;">
                        <input type="submit" value="SAVE">
                    </div>
                    <h3 style="color: red">${sessionScope.msg}</h3>
                    
                </form>
            </div>
        </div>
    </body>
</html>
