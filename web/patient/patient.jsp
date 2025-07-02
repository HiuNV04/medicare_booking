<%-- 
    Document   : patient
    Created on : May 26, 2025, 9:33:20 AM
    Author     : ptson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Patient Page</title>
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
                    <img src="/MediCare_Booking/img/pic9.jpg" alt="" style="width:100%;">
                </a>
                <a style="margin-bottom: 12%; font-size: 121%;" href="/MediCare_Booking/patient/patient.jsp">Home</a>
                
                <a style="margin-bottom: 12%; font-size: 121%;" href="${pageContext.request.contextPath}/paAddAppointment">Add Appointment</a>  
                
                <a style="margin-bottom: 12%; font-size: 121%;" href="${pageContext.request.contextPath}/paListAppointment">List Appointment</a>   
                
                <a style="margin-bottom: 12%;  font-size: 121%;" href="#">History Feedback</a>

                <a style="margin-bottom: 12%; font-size: 121%;" href="${pageContext.request.contextPath}/showPatient">Patient Profile</a>

                <a style="margin-bottom: 12%; font-size: 121%;" href="${pageContext.request.contextPath}/changePassPatient">Change Password</a>
            </div>

            <div class="content">
                <div class="card">Number of Appointment: </div>
                <div class="card">Number of Medical Record Feedback: </div>
            </div>
        </div>
    </body>
</html>
