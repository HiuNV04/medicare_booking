<%-- 
    Document   : recepChangePass
    Created on : May 29, 2025, 9:27:53 AM
    Author     : ptson
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Receptionist Change Pass</title>
        <link href="${pageContext.request.contextPath}/css/style1.css" rel="stylesheet">
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
                <a style="margin-bottom: 12%; font-size: 121%;" href="/MediCare_Booking/receptionist/receptionist.jsp">Home</a>

<!--                <a style="margin-bottom: 12%;  font-size: 121%;" href="${pageContext.request.contextPath}/reAddPatient">Add Patient</a>-->

                <a style="margin-bottom: 12%;  font-size: 121%;" href="${pageContext.request.contextPath}/reViewPatient">List Patient</a>

                <a style="margin-bottom: 12%; font-size: 121%;" href="${pageContext.request.contextPath}/showReceptionist">Receptionist Profile</a>

                <a style="margin-bottom: 12%; font-size: 121%;" href="${pageContext.request.contextPath}/changePassReceptionist">Change Password</a>
            </div>

            <div>
                <c:set var="i" value="${sessionScope.receptionist}"/>
                <form action="changePassReceptionist" method="post" style="margin-right: -121% ;margin-left: 10%;">
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
                        <input style="border-radius: 10px; padding: 2%" type="submit" value="SAVE">
                    </div>
                    <h3 style="color: red">${requestScope.valid}</h3>
                </form>
            </div>
        </div>
    </body>
</html>
