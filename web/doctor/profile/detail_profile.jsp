<%-- 
    Document   : detail_profile
    Created on : May 21, 2025, 11:48:31 AM
    Author     : Admin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    model.Doctor doctor = (model.Doctor) request.getAttribute("doctor");

    Boolean isOwner = (Boolean) request.getAttribute("isOwner");
    if (doctor == null) {
        response.sendRedirect(request.getContextPath() + "/DoctorDetailServlet");
        return;
    }
%>

<%
    long version = System.currentTimeMillis();
%>


<!DOCTYPE html>
<html>
    <head>
        <title>Hồ sơ cá nhân</title>
        <jsp:include page="/doctor/head/head.jsp" />
    </head>
    <body>
        <jsp:include page="/doctor/topbar/topbar.jsp" />
        <jsp:include page="/doctor/navbar/navbar.jsp" />

        <!--Update Start-->
        <%-- Đặt trước form để dùng lại --%>
        <c:set var="readonlyAttr" value="${!isOwner ? 'readonly' : ''}" />
        <c:set var="disabledAttr" value="${!isOwner ? 'disabled' : ''}" />


        <form action="${pageContext.request.contextPath}/DoctorUpdateServlet" method="post" enctype="multipart/form-data">
            <div class="container py-5">
                <h2 class="text-center mb-4">Chỉnh sửa <span class="text-warning">hồ sơ</span></h2>
                <c:if test="${not empty message}">
                    <div class="alert alert-success text-center">${message}</div>
                </c:if>

                <div class="row">
                    <!-- Avatar -->
                    <div class="col-md-4 text-center">

                        <img src="${pageContext.request.contextPath}/${doctor.imageUrl}?v=<%= version %>" alt="Ảnh bác sĩ"
                             class="img-fluid shadow-sm"
                             style="width: 100%; max-width: 100%; height: 420px; object-fit: contain; border-radius: 1rem;">


                        <h3 class="mt-3 text-primary">${doctor.fullName}</h3>

                        <!-- Nút chọn file -->
                        <c:if test="${isOwner}">
                            <div class="mb-3">
                                <label for="imageFile" class="btn btn-outline-primary">
                                    <i class="bi bi-upload"></i> Cập nhật
                                </label>
                                <input type="file" id="imageFile" name="imageFile" accept="image/*" class="d-none">
                            </div>
                        </c:if>
                    </div>

                    <!-- Thông tin cá nhân -->
                    <div class="col-md-8">
                        <div class="row">
                            <div class="mb-3 col-md-6">
                                <label for="id" class="form-label">ID</label>
                                <input type="text" class="form-control" id="id" name="id" value="${doctor.id}" readonly>
                            </div>

                            <div class="mb-3 col-md-6">
                                <label for="fullName" class="form-label">Họ tên</label>
                                <input type="text" class="form-control" id="fullName" name="fullName" value="${doctor.fullName}" required ${readonlyAttr}>
                            </div>
                        </div>

                        <div class="row">
                            <div class="mb-3 col-md-6">
                                <label for="address" class="form-label">Địa chỉ</label>
                                <input type="text" class="form-control" id="address" name="address" value="${doctor.address}" required ${readonlyAttr}>
                            </div>

                            <div class="mb-3 col-md-6">
                                <label for="dateOfBirth" class="form-label">Ngày sinh</label>
                                <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth" value="${doctor.dateOfBirth}" required ${readonlyAttr}>
                            </div>
                        </div>

                        <div class="row">
                            <div class="mb-3 col-md-6">
                                <label for="gender" class="form-label">Giới tính</label>
                                <select class="form-select" id="gender" name="gender" ${disabledAttr}>
                                    <option value="Nam" ${doctor.gender == 'Nam' ? 'selected' : ''}>Nam</option>
                                    <option value="Nữ" ${doctor.gender == 'Nữ' ? 'selected' : ''}>Nữ</option>
                                </select>
                            </div>

                            <div class="mb-3 col-md-6">
                                <label for="phoneNumber" class="form-label">Số điện thoại</label>
                                <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" value="${doctor.phoneNumber}" required maxlength="10" ${readonlyAttr}>
                            </div>
                        </div>

                        <div class="row">
                            <div class="mb-3 col-md-6">
                                <label for="email" class="form-label">Email</label>
                                <input type="email" class="form-control" id="email" name="email" value="${doctor.email}" ${readonlyAttr}>
                            </div>

                            <div class="mb-3 col-md-6">
                                <label for="status" class="form-label">Trạng thái</label>
                                <select class="form-select" id="status" name="status" ${disabledAttr}>
                                    <option value="1" ${doctor.status ? 'selected' : ''}>Đang hoạt động</option>
                                    <option value="0" ${!doctor.status ? 'selected' : ''}>Ngừng hoạt động</option>
                                </select>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="specializationId" class="form-label">Chuyên khoa</label>
                                <input type="text" class="form-control" value="${doctor.specialization}" readonly>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="doctorLevelId" class="form-label">Trình độ</label>
                                <input type="text" class="form-control" value="${doctor.levelName}" readonly>
                            </div>

                            <div class="mb-3 col-md-12">
                                <label class="form-label">Nhận xét</label>
                                <textarea class="form-control" name="note" rows="3" ${readonlyAttr}>${doctor.note}</textarea>
                            </div>
                        </div>

                        <!-- Nút cập nhật, xóa -->
                        <c:if test="${isOwner}">
                            <div class="d-flex justify-content-end gap-3 mt-4">
                                <button type="submit" class="btn btn-success">Cập nhật</button>
<!--                                <a href="${pageContext.request.contextPath}/DoctorUpdateServlet" class="btn btn-danger"
                                   onclick="return confirm('Bạn chắc chắn muốn xóa bác sĩ này?')">Xóa</a>-->
                                <a href="${pageContext.request.contextPath}/doctor/profile/change_password.jsp?id=${doctor.id}" 
                                   class="btn btn-warning">Đổi mật khẩu</a>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </form>
        <!--Update End-->

        <jsp:include page="/doctor/footer/footer.jsp" />
        <jsp:include page="/doctor/script/script.jsp" />
    </body>
</html>


