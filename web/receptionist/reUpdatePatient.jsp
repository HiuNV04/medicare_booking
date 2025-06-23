<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Patient</title>
        <link href="${pageContext.request.contextPath}/css/style2.css" rel="stylesheet">
        <style>
            .patient-img {
                float: left;
                margin-right: 24px;   /* Khoảng cách với phần còn lại của form */
                width: 200px;         /* Tăng kích thước ảnh (có thể chỉnh lại theo ý bạn) */
                height: 200px;        /* Có thể bỏ nếu muốn giữ tỉ lệ gốc */
                object-fit: cover;    /* Cắt hình vừa vặn */
                border-radius: 8px;   /* Bo góc nhẹ cho đẹp */
                border: 2px solid #ccc;
            }
        </style>
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
                <form action="reUpdatePatient" method="post" enctype="multipart/form-data" style="margin-right: -78%;">
                    <div style="overflow: hidden;">
                        <img src="${p.getImg()}" class="patient-img" />
                        <div style="overflow: hidden;">
                            <div class="form-group">
                                <input type="hidden" class="form-control" name="id" value="${p.getId()}" readonly=""/>
                            </div>
                            <div class="form-group">
                                <label>Full Name: </label>
                                <input type="text" class="form-control" name="fullname" value="${p.getFullname()}" required=""/>
                            </div>
                            <div class="form-group">
                                <label>Date of birth: </label>
                                <input type="date" class="form-control" name="dob" value="${p.getDob()}" required=""/>
                            </div>
                            <div class="form-group">
                                <label for="gender">Gender:</label>
                                <select name="gender" required="" style="padding: 10px 15px;font-size: 16px;border: 1px solid #ccc; border-radius: 6px;outline: none;width: 100%;">
                                    <option value="Male" ${p.getGender() == 'Male' ? 'selected' : ''}>Male</option>
                                    <option value="Female" ${p.getGender() == 'Female' ? 'selected' : ''}>Female</option>
                                    <option value="Other" ${p.getGender() == 'Other' ? 'selected' : ''}>Other</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Identity Number: </label>
                                <input type="text" class="form-control" name="identity" value="${p.getIdentity()}" required=""/>
                            </div>
                            <div class="form-group">
                                <label>Insurance Number: </label>
                                <input type="text" class="form-control" name="insurance" value="${p.getInsurance()}" required=""/>
                            </div>
                            <div class="form-group">
                                <label>Phone Number: </label>
                                <input type="text" class="form-control" name="phone" value="${p.getPhone()}" required=""/>
                            </div>
                            <div class="form-group">
                                <label>Address: </label>
                                <input type="text" class="form-control" name="address" value="${p.getAddress()}" required=""/>
                            </div>
                            <div class="form-group">
<!--                                <label>Upload Image:</label>-->
                                <input type="hidden" name="image" accept="image/*"/>
                            </div>
                            <div style="margin-left: 39%;">
                                <input type="submit" value="SAVE">
                                <input type="reset" value="RESET"/>
                            </div>   
                        </div>
                    </div>
                    <h3 style="color:red">${error}</h3>
                </form>
            </div>
        </div>
    </body>
</html>
