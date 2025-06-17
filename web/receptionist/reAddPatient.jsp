
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Receptionist Add Patient</title>
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
                <form class="form1" action="reAddPatient" method="post" style="margin-right: -150%; margin-left: 12%;">
                    <div class="form-group">
                        <input type="hidden" class="form-control" name="role" value="Patient" required=""/>
                    </div>
                    <div class="form-group">
                        <input type="hidden" class="form-control" name="id" value="${sessionScope.id}" required=""/>
                    </div>
                    <div class="form-group">
                        <label>Full Name: </label>
                        <input type="text" class="form-control" name="fullname" value="${fullname}" required=""/>
                    </div>
                    <div class="form-group">
                        <label>Date of birth: </label>
                        <input type="date" class="form-control" name="dob" value="${dob}" required=""/>
                    </div>
                    <div class="form-group">
                        <label for="gender">Gender:</label>
                        <select name="gender" id="gender" required="" style="padding: 10px 15px;font-size: 16px;border: 1px solid #ccc; border-radius: 6px;outline: none;width: 100%;">
                            <option value="Male" ${gender == 'Male' ? 'selected' : ''}>Male</option>
                            <option value="Female" ${gender == 'Female' ? 'selected' : ''}>Female</option>
                            <option value="Other" ${gender == 'Other' ? 'selected' : ''}>Other</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Identity Number: </label>
                        <input type="text" class="form-control" name="identity" value="${identity}" required=""/>
                    </div>
                    <div class="form-group">
                        <label>Insurance Number: </label>
                        <input type="text" class="form-control" name="insurance" value="${insurance}" required=""/>
                    </div>
                    <div class="form-group">
                        <label>Phone Number: </label>
                        <input type="text" class="form-control" name="phone" value="${phone}" required=""/>
                    </div>
                    <div class="form-group">
                        <label>Address: </label>
                        <input type="text" class="form-control" name="address" value="${address}" required=""/>
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
