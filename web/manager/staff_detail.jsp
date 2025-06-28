<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chi tiết nhân sự</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .profile-img {
            max-width: 200px;
            max-height: 200px;
            object-fit: cover;
            border-radius: 50%;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <c:choose>
        <c:when test="${not empty staff || not empty doctor}">
            <c:set var="person" value="${not empty staff ? staff : doctor}"/>
            <h2>Chi tiết nhân sự: ${person.fullName}</h2>
            <hr/>
            <form method="post" action="${pageContext.request.contextPath}/manager/staff-detail" enctype="multipart/form-data">
                <input type="hidden" name="id" value="${person.id}"/>
                <input type="hidden" name="role" value="${person.role}"/>
                <input type="hidden" name="existingImageUrl" value="${person.imageUrl}"/>

                <div class="row">
                    <div class="col-md-4 text-center">
                        <c:if test="${not empty person.imageUrl}">
                            <img id="imagePreview" src="${pageContext.request.contextPath}/${person.imageUrl}" class="profile-img mb-3" alt="Ảnh đại diện">
                        </c:if>
                        <c:if test="${empty person.imageUrl}">
                            <img id="imagePreview" src="https://via.placeholder.com/200" class="profile-img mb-3" alt="Ảnh mặc định">
                        </c:if>
                        <div class="mb-3">
                            <label for="imageFile" class="form-label">Thay đổi ảnh</label>
                            <input type="file" class="form-control" name="imageFile" id="imageFile" accept="image/*">
                        </div>
                    </div>
                    <div class="col-md-8">
                        <div class="mb-3">
                            <label class="form-label">Họ tên</label>
                            <input type="text" class="form-control" name="fullName" value="${person.fullName}" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Ngày sinh</label>
                            <input type="date" class="form-control" name="dateOfBirth" value="${person.dateOfBirth}" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Địa chỉ</label>
                            <input type="text" class="form-control" name="address" value="${person.address}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Số điện thoại</label>
                            <input type="text" class="form-control" name="phoneNumber" value="${person.phoneNumber}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label d-block">Giới tính</label>
                             <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="gender" id="genderMale" value="Nam" ${person.gender == 'Nam' ? 'checked' : ''}>
                                <label class="form-check-label" for="genderMale">Nam</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="gender" id="genderFemale" value="Nữ" ${person.gender == 'Nữ' ? 'checked' : ''}>
                                <label class="form-check-label" for="genderFemale">Nữ</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="gender" id="genderOther" value="Khác" ${person.gender == 'Khác' ? 'checked' : ''}>
                                <label class="form-check-label" for="genderOther">Khác</label>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-success">Lưu thay đổi</button>
                        <a href="${pageContext.request.contextPath}/manager/home" class="btn btn-secondary ms-2">Quay lại</a>
                    </div>
                </div>
            </form>
        </c:when>
        <c:otherwise>
            <div class="alert alert-warning">Không tìm thấy thông tin nhân sự.</div>
             <a href="${pageContext.request.contextPath}/manager/home" class="btn btn-secondary ms-2">Quay lại</a>
        </c:otherwise>
    </c:choose>
</div>

<script>
    const imageFileInput = document.getElementById('imageFile');
    if(imageFileInput) {
        imageFileInput.onchange = function (evt) {
            const [file] = this.files;
            if (file) {
                const preview = document.getElementById('imagePreview');
                preview.src = URL.createObjectURL(file);
                preview.onload = () => {
                    URL.revokeObjectURL(preview.src); // free memory
                }
            }
        };
    }
</script>

</body>
</html> 