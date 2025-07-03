<%-- 
    Document   : profile_patient
    Created on : Jul 1, 2025, 11:13:03 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hồ sơ bệnh nhân</title>
        <jsp:include page="/frontend/head/head.jsp" />
    </head>
    <body>
        <jsp:include page="/frontend/topbar/topbar.jsp" />

        <div class="container mt-5">
            <h2 class="text-center mb-4">📋 Hồ sơ bệnh nhân</h2>

            <c:if test="${not empty patient}">
                <div class="row justify-content-center">
                    <div class="col-md-8 bg-light rounded p-4 shadow border">

                        <div class="text-center mb-4">
                            <img src="${pageContext.request.contextPath}/ImageServlet?path=external_images/patient/${patient.id}.jpg"
                                 class="rounded-circle border" width="120" height="120" alt="Avatar bệnh nhân"
                                 onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/assets/img/default-avatar.png';">
                        </div>

                        <table class="table table-borderless">
                            <tr>
                                <th>Họ tên:</th>
                                <td>${patient.fullName}</td>
                            </tr>
                            <tr>
                                <th>Ngày sinh:</th>
                                <td><fmt:formatDate value="${patient.dateOfBirth}" pattern="dd/MM/yyyy"/></td>
                            </tr>
                            <tr>
                                <th>Giới tính:</th>
                                <td>${patient.gender}</td>
                            </tr>
                            <tr>
                                <th>Số điện thoại:</th>
                                <td>${patient.phoneNumber}</td>
                            </tr>
                            <tr>
                                <th>Địa chỉ:</th>
                                <td>${patient.address}</td>
                            </tr>

                            <c:if test="${role eq 'receptionist'}">
                                <tr>
                                    <th>Số CCCD:</th>
                                    <td>${patient.identityNumber}</td>
                                </tr>
                                <tr>
                                    <th>Mã BHYT:</th>
                                    <td>${patient.insuranceNumber}</td>
                                </tr>
                            </c:if>
                        </table>

                        <div class="text-center mt-4">
                            <a href="javascript:history.back()" class="btn btn-secondary">⬅ Quay lại</a>
                        </div>
                    </div>
                </div>
            </c:if>

            <c:if test="${empty patient}">
                <div class="alert alert-danger text-center">
                    Không tìm thấy hồ sơ bệnh nhân!
                </div>
            </c:if>
        </div>

        <jsp:include page="/frontend/footer/footer.jsp" />
        <jsp:include page="/frontend/script/script.jsp" />
    </body>
</html>
