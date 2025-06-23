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
            <%@ include file="sidebar.jsp" %>
            <div>
                <form action="paAddAppointment" method="post" style="margin-right: -253%;;">
                    <div class="form-group">
                        <!--                        <label>ID: </label>-->
                        <input type="hidden" class="form-control" name="id" value="${p.getId()}" readonly=""/>
                    </div>
                    <div class="form-group">
                        <label>email: </label>
                        <input type="text" class="form-control" name="email" value="${p.getEmail()}" readonly=""/>
                    </div>
                    <div class="form-group">
                        <label>Full Name: </label>
                        <input type="text" class="form-control" name="name" value="${p.getFullname()}" readonly=""/>
                    </div>
                    <div class="form-group">
                        <label for="doctorId">Select Doctor:</label>
                        <select name="doctorId" id="doctorId" required>
                            <option value="">-- Select Doctor --</option>
                            <c:forEach var="doctor" items="${doctors}">
                                <option value="${doctor.id}">${doctor.fullName}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Doctor Information Display -->
                    <div id="doctorInfo" class="form-group">
                        <c:if test="${not empty selectedDoctor}">
                            <p><strong>Specialization:</strong> ${selectedDoctor.specializationName}</p>
                            <p><strong>Room:</strong> ${selectedDoctor.roomName}</p>
                            <p><strong>Description:</strong> ${selectedDoctor.specializationDescription}</p>
                        </c:if>
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
                    <div style="margin-left: 39%;">
                        <input type="submit" value="ADD">
                        <input type="reset" value="RESET"/>
                    </div>
                    <h3 style="color:red">${error}</h3>
                </form>
            </div>
        </div>
    </body>
</html>