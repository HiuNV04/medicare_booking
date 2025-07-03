<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Hồ sơ bệnh nhân</title>
    </head>
    <body>



        <div class="container mt-5">
            <h3 class="mb-4">📋 Thông tin bệnh nhân</h3>

            <div class="row g-4">
                <!-- Ảnh đại diện -->
                <div class="col-md-4 text-center">
                    <img src="${pageContext.request.contextPath}/ImageServlet?path=${patient.imageUrl}" 
                         alt="Ảnh bệnh nhân" class="img-fluid rounded-circle" style="max-width: 200px;">
                </div>

                <!-- Thông tin chi tiết -->
                <div class="col-md-8">
                    <table class="table table-bordered">
                        <tr>
                            <th>Họ tên</th>
                            <td>${patient.fullName}</td>
                        </tr>
                        <tr>
                            <th>Ngày sinh</th>
                            <td><fmt:formatDate value="${patient.dateOfBirth}" pattern="dd-MM-yyyy"/></td>
                        </tr>
                        <tr>
                            <th>Giới tính</th>
                            <td>${patient.gender}</td>
                        </tr>
                        <tr>
                            <th>Email</th>
                            <td>${patient.email}</td>
                        </tr>
                        <tr>
                            <th>Số điện thoại</th>
                            <td>${patient.phoneNumber}</td>
                        </tr>
                        <tr>
                            <th>Địa chỉ</th>
                            <td>${patient.address}</td>
                        </tr>
                    </table>
                </div>
            </div>

            <!-- Nút quay lại -->
            <div class="mt-4">
                <a href="javascript:history.back()" class="btn btn-secondary">← Quay lại</a>
            </div>
        </div>



    </body>
</html>
