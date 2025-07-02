<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



<c:set var="version" value="<%= System.currentTimeMillis() %>" />
<c:set var="cleanImageUrl" value="${fn:split(doctor.imageUrl, '?')[0]}" />
<c:set var="readonlyAttr" value="${!isOwner ? 'readonly' : ''}" />
<c:set var="disabledAttr" value="${!isOwner ? 'disabled' : ''}" />

<!DOCTYPE html>
<html>
    <head>
        <title>Hồ sơ bác sĩ</title>
        <jsp:include page="/frontend/head/head.jsp" />
        <style>
            body {
                background: #f8fafd;
            }
            .profile-container {
                background: #ffffff;
                border-radius: 1.2rem;
                padding: 2rem;
                box-shadow: 0 0 20px rgba(0,0,0,0.05);
            }
            .profile-avatar img {
                width: 200px;
                height: 200px;
                object-fit: cover;
                border-radius: 50%;
                border: 4px solid #e0ecff;
            }
            .btn-update-img {
                margin-top: 1rem;
                padding: 8px 16px;
                border-radius: 6px;
                border: 1px solid #007bff;
                background: #fff;
                color: #007bff;
                font-weight: 500;
                transition: all 0.3s ease;
            }
            .btn-update-img:hover {
                background: #007bff;
                color: #fff;
            }
            .btn-save, .btn-change-password {
                padding: 10px 24px;
                font-weight: 600;
                font-size: 16px;
                border-radius: 8px;
                border: none;
            }
            .btn-save {
                background-color: #0abf53;
                color: white;
            }
            .btn-change-password {
                background-color: #fd7e14;
                color: white;
            }
        </style>
    </head>
    <body>
        <jsp:include page="/frontend/topbar/topbar.jsp" />
        <jsp:include page="/doctor/navbar/navbar.jsp" />

        <form action="${pageContext.request.contextPath}/DoctorUpdateServlet" method="post" enctype="multipart/form-data">
            <div class="container py-5">
                <div class="profile-container row g-4">
                    <!-- Avatar & Info -->
                    <div class="col-md-4 text-center profile-avatar">
                        <c:choose>
                            <c:when test="${not empty cleanImageUrl}">
                                <img src="${pageContext.request.contextPath}/${doctor.imageUrl}&v=${version}" alt="Ảnh bác sĩ">
                            </c:when>
                            <c:otherwise>
                                <img src="${pageContext.request.contextPath}/img/default-doctor.jpg" alt="Ảnh mặc định">
                            </c:otherwise>
                        </c:choose>
                        <h4 class="mt-3 text-dark fw-bold"></h4>
                        <c:if test="${isOwner}">
                            <label for="imageFile" class="btn-update-img">
                                <i class="bi bi-camera"></i> Cập nhật ảnh
                            </label>
                            <input type="file" id="imageFile" name="imageFile" accept="image/*" class="d-none">
                        </c:if>
                    </div>

                    <!-- Thông tin -->
                    <div class="col-md-8">
                        <h5 class="mb-4 fw-bold text-dark"><i class="bi bi-person-vcard"></i> Thông tin cá nhân</h5>

                        <div class="row g-3">
                            <div class="col-md-6">
                                <label class="form-label">ID</label>
                                <input type="text" class="form-control" value="${doctor.id}" readonly name="id">
                            </div>
                            <div class="col-md-6">
                                <label class="form-label">Họ tên</label>
                                <input type="text" class="form-control" name="fullName" value="${doctor.fullName}" required ${readonlyAttr}>
                            </div>

                            <div class="col-md-6">
                                <label class="form-label">Ngày sinh</label>
                                <input type="date" class="form-control" name="dateOfBirth" value="${doctor.dateOfBirth}" required ${readonlyAttr}>
                            </div>
                            <div class="col-md-6">
                                <label class="form-label">Giới tính</label>
                                <select class="form-select" name="gender" ${disabledAttr}>
                                    <option value="Nam" ${doctor.gender == 'Nam' ? 'selected' : ''}>Nam</option>
                                    <option value="Nữ" ${doctor.gender == 'Nữ' ? 'selected' : ''}>Nữ</option>
                                </select>
                            </div>

                            <div class="col-md-6">
                                <label class="form-label">Số điện thoại</label>
                                <input type="text" class="form-control" name="phoneNumber" value="${doctor.phoneNumber}" maxlength="10" required ${readonlyAttr}>
                            </div>
                            <div class="col-md-6">
                                <label class="form-label">Email</label>
                                <input type="email" class="form-control" name="email" value="${doctor.email}" ${readonlyAttr}>
                            </div>

                            <div class="col-md-6">
                                <label class="form-label">Trạng thái</label>
                                <select class="form-select" name="status" ${disabledAttr}>
                                    <option value="1" ${doctor.status ? 'selected' : ''}>Đang hoạt động</option>
                                    <option value="0" ${!doctor.status ? 'selected' : ''}>Ngừng hoạt động</option>
                                </select>
                            </div>
                            <div class="col-md-6">
                                <label class="form-label">Địa chỉ</label>
                                <input type="text" class="form-control" name="address" value="${doctor.address}" required ${readonlyAttr}>
                            </div>

                            <div class="col-md-6">
                                <label class="form-label">Chuyên khoa</label>
                                <input type="text" class="form-control" name="specialization" value="${doctor.specialization}" readonly>
                            </div>
                            <div class="col-md-6">
                                <label class="form-label">Trình độ</label>
                                <input type="text" class="form-control" name="levelName" value="${doctor.levelName}" readonly>
                            </div>


                            <div class="col-12">
                                <label class="form-label">Giới thiệu</label>
                                <textarea class="form-control" name="note" rows="3" ${readonlyAttr}>${doctor.note}</textarea>
                            </div>

                            <c:if test="${isOwner}">
                                <div class="d-flex justify-content-end gap-3 mt-4">
                                    <button type="submit" class="btn btn-save">Lưu thay đổi</button>
                                    <a href="${pageContext.request.contextPath}/doctor/profile/change_password.jsp?id=${doctor.id}" class="btn btn-change-password">Đổi mật khẩu</a>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </form>

        <jsp:include page="/frontend/footer/footer.jsp" />
        <jsp:include page="/frontend/script/script.jsp" />
    </body>
</html>
