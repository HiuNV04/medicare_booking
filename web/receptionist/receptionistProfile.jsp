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
        <script>
            function togglePassword() {
                const passInput = document.getElementById("password");
                const icon = event.target;
                if (passInput.type === "password") {
                    passInput.type = "text";
                    icon.textContent = "🙈"; // Đổi icon khi hiện mật khẩu
                } else {
                    passInput.type = "password";
                    icon.textContent = "👁️"; // Đổi icon khi ẩn mật khẩu
                }
            }
        </script>
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
                <a style="margin-bottom: 12%; font-size: 121%;" href="/MediCare_Booking/receptionist/receptionist.jsp">Home</a>

<!--                <a style="margin-bottom: 12%;  font-size: 121%;" href="${pageContext.request.contextPath}/reAddPatient">Add Patient</a>-->

                <a style="margin-bottom: 12%;  font-size: 121%;" href="${pageContext.request.contextPath}/reViewPatient">List Patient</a>

                <a style="margin-bottom: 12%; font-size: 121%;" href="${pageContext.request.contextPath}/showReceptionist">Receptionist Profile</a>

                <a style="margin-bottom: 12%; font-size: 121%;" href="${pageContext.request.contextPath}/changePassReceptionist">Change Password</a>
            </div>
            <div>
                <c:set var="i" value="${sessionScope.receptionist}"/>
                <form class="form1" action="showReceptionist" method="post" enctype="multipart/form-data" style="margin-right: -118%; margin-left: 12%;">
                    <div class="form-group">
                        <input type="hidden" class="form-control" name="id" value="${i.getId()}" readonly=""/>
                    </div>
                    <div class="form-group">
                        <input type="hidden" class="form-control" name="role" value="${i.getRole()}" readonly=""/>
                    </div>
                    <div class="form-group">
                        <label>Email: </label>
                        <input type="text" class="form-control" name="email" value="${i.getEmail()}" readonly=""/>
                    </div>
                    <div class="form-group">
                        <label>Username: </label>
                        <input type="text" class="form-control" name="username" value="${i.getUsername()}" readonly=""/>
                    </div>
                    <div class="form-group">
                        <label>Password:</label>
                        <div style="display: flex; align-items: center;">
                            <input type="password" class="form-control" name="pass" id="password" value="${i.getPassword()}" readonly style="flex: 1;"/>
                            <span onclick="togglePassword()" style="margin-left: 10px; cursor: pointer;">👁️</span>
                        </div>
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
                        <input type="radio" name="gender" required="" value="Nam" ${i.getGender() == 'Nam' ? 'checked' : ''}/> Nam 
                        <input type="radio" name="gender" required="" value="Nữ" ${i.getGender() == 'Nữ' ? 'checked' : ''}/> Nữ
                        <input type="radio" name="gender" required="" value="Other" ${i.getGender() == 'Other' ? 'checked' : ''}/> Other 
                    </div>
                    <div class="form-group">
                        <label>Phone Number: </label>
                        <input type="text" class="form-control" name="phone" value="${i.getPhone()}" required=""/>
                    </div>
                    <div class="form-group">
                        <label>Address: </label>
                        <input type="text" class="form-control" name="address" value="${i.getAddress()}" required=""/>
                    </div>
                    <div class="form-group">
                        <label>Image: </label>
                        <img src="${i.getImg()}" width="100" />
                        <input type="file" name="image" />
                    </div>
                    <div style="margin-left: 39%;">
                        <input type="submit" value="SAVE">
                        <input type="reset" value="RESET"/>
                    </div>
                    <h3>${error}</h3>
                </form>
            </div>
        </div>
    </body>
</html>
