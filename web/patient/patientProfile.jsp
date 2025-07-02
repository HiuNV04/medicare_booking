
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Patient Profile</title>
        <link href="${pageContext.request.contextPath}/css/style2.css" rel="stylesheet">
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
            <%@ include file="sidebar.jsp" %>
            <div>
                <c:set var="i" value="${sessionScope.patient}"/>
                <form action="showPatient" method="post" enctype="multipart/form-data" style="margin-right: -105%;">
                    <div class="form-group">
                        <!--                        <label>ID: </label>-->
                        <input type="hidden" class="form-control" name="id" value="${i.getId()}" readonly=""/>
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
                        <input type="date" class="form-control" name="dob" value="${i.getDob()}" required=""/>
                    </div>
                    <div class="form-group">
                        <label for="gender">Gender:</label>
                        <select name="gender" required="" style="padding: 10px 15px;font-size: 16px;border: 1px solid #ccc; border-radius: 6px;outline: none;width: 100%;">
                            <option value="Male" ${i.getGender() == 'Male' ? 'selected' : ''}>Male</option>
                            <option value="Female" ${i.getGender() == 'Female' ? 'selected' : ''}>Female</option>
                            <option value="Other" ${i.getGender() == 'Other' ? 'selected' : ''}>Other</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Identity Number: </label>
                        <input type="text" class="form-control" name="identity" value="${i.getIdentity()}" required=""/>
                    </div>
                    <div class="form-group">
                        <label>Insurance Number: </label>
                        <input type="text" class="form-control" name="insurance" value="${i.getInsurance()}" required=""/>
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
                    <h3 style="color:red">${error}</h3>
                </form>
            </div>
        </div>
    </body>
</html>
