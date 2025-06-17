
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Receptionist Add Appointment</title>
        <link href="${pageContext.request.contextPath}/css/style1.css" rel="stylesheet">
    </head>
    <body>
        <div class="header">
            <h2>Add Patient</h2>
            <a href="/MediCare_Booking/auth/home.jsp">Trang chủ</a>
        </div>

        <div class="container">
            <div class="sidebar">
                <a style="margin-bottom: 12%;" href="/MediCare_Booking/img/pic9.jpg">
                    <img src="/MediCare_Booking/img/pic9.jpg" alt="Avatar" style="width:100%;">
                </a>
                <a style="margin-bottom: 12%; font-size: 121%;" href="/MediCare_Booking/receptionist/receptionist.jsp">Home</a>

                <a style="margin-bottom: 12%;  font-size: 121%;" href="${pageContext.request.contextPath}/reAddPatient">Add Patient</a>

                <a style="margin-bottom: 12%;  font-size: 121%;" href="${pageContext.request.contextPath}/reViewPatient">List Patient</a>

                <a style="margin-bottom: 12%;  font-size: 121%;" href="${pageContext.request.contextPath}/reAddAppointment">Add Appointment</a>

                <a style="margin-bottom: 12%;  font-size: 121%;" href="${pageContext.request.contextPath}/reViewAppointment">List Appointment</a>

                <a style="margin-bottom: 12%;  font-size: 121%;" href="#">History Feedback</a>

                <a style="margin-bottom: 12%; font-size: 121%;" href="${pageContext.request.contextPath}/showReceptionist">Receptionist Profile</a>

                <a style="margin-bottom: 12%; font-size: 121%;" href="${pageContext.request.contextPath}/changePassReceptionist">Change Password</a>
            </div>
            <div>
                <form class="form1" action="reAddAppointment" method="post" style="margin-right: -150%; margin-left: 12%;">
                    <div class="form-group">
                        <label>Full Name: </label>
                        <input type="text" class="form-control" name="fullname" value="${name}" readonly=""/>
                    </div>
                    <div class="form-group">
                        <label for="doctor">Doctor: </label>
                        <select name="doctor" required="" style="padding: 10px 15px;font-size: 16px;border: 1px solid #ccc; border-radius: 6px;outline: none;width: 100%;">
                            <c:forEach var="doc" items="${doctor}">
                                <option value="${doc.id}">${doc.getName()} - ${doc.getSpecializationName()} - ${doc.getRoomName()}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Date: </label>
                        <input type="date" class="form-control" name="date" value="" required=""/>
                    </div>
                    <div class="form-group">
                        <label for="startTime">Start Time:</label>
                        <select name="startTime" id="" required="" style="padding: 10px 15px;font-size: 16px;border: 1px solid #ccc; border-radius: 6px;outline: none;width: 100%;">
                            <option value="">08:00:00</option>
                            <option value="">09:00:00</option>
                            <option value="">10:00:00</option>
                            <option value="">11:00:00</option>
                            <option value="">13:00:00</option>
                            <option value="">14:00:00</option>
                            <option value="">15:00:00</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="endTime">End Time:</label>
                        <select name="endTime" id="" required="" style="padding: 10px 15px;font-size: 16px;border: 1px solid #ccc; border-radius: 6px;outline: none;width: 100%;">
                            <option value="">09:00:00</option>
                            <option value="">10:00:00</option>
                            <option value="">11:00:00</option>
                            <option value="">12:00:00</option>
                            <option value="">14:00:00</option>
                            <option value="">15:00:00</option>
                            <option value="">16:00:00</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="status">Status:</label>
                        <select name="status" id="" required="" style="padding: 10px 15px;font-size: 16px;border: 1px solid #ccc; border-radius: 6px;outline: none;width: 100%;">
                            <option value="">Pending</option>
                            <option value="">Done</option>
                            <option value="">Cancel</option>
                        </select>
                    </div>
                    <div style="margin-left: 39%;">
                        <input type="submit" value="ADD">
                        <input type="reset" value="RESET"/>
                    </div>
                    <h3 style="color:red">${requestScope.error}</h3>
                </form>
            </div>
        </div>
    </body>
</html>
