<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chỉnh sửa thông tin cá nhân</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>Chỉnh sửa thông tin cá nhân</h2>
    <form method="post" action="${pageContext.request.contextPath}/manager/profile" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${user.id}"/>
        <input type="hidden" name="existingImageUrl" value="${user.imageUrl}"/>

        <div class="row">
            <div class="col-md-4 text-center">
                <c:if test="${not empty user.imageUrl}">
                    <img src="${pageContext.request.contextPath}/${user.imageUrl}" class="img-fluid rounded-circle mb-3" alt="Ảnh đại diện" style="width: 150px; height: 150px; object-fit: cover;">
                </c:if>
                <c:if test="${empty user.imageUrl}">
                    <img src="https://via.placeholder.com/150" class="img-fluid rounded-circle mb-3" alt="Ảnh mặc định">
                </c:if>
                 <div class="mb-3">
                    <label for="imageFile" class="form-label">Thay đổi ảnh đại diện</label>
                    <input type="file" class="form-control" name="imageFile" id="imageFile" accept="image/*">
                </div>
            </div>
            <div class="col-md-8">
                <div class="mb-3">
                    <label class="form-label">Họ tên</label>
                    <input type="text" class="form-control" name="fullName" value="${user.fullName}" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Ngày sinh</label>
                    <input type="date" class="form-control" name="dateOfBirth" value="${user.dateOfBirth}" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Địa chỉ</label>
                    <input type="text" class="form-control" name="address" value="${user.address}">
                </div>
                <div class="mb-3">
                    <label class="form-label">Số điện thoại</label>
                    <input type="text" class="form-control" name="phoneNumber" value="${user.phoneNumber}">
                </div>
                <div class="mb-3">
                    <label class="form-label d-block">Giới tính</label>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="gender" id="genderMale" value="Nam" ${user.gender == 'Nam' ? 'checked' : ''}>
                        <label class="form-check-label" for="genderMale">Nam</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="gender" id="genderFemale" value="Nữ" ${user.gender == 'Nữ' ? 'checked' : ''}>
                        <label class="form-check-label" for="genderFemale">Nữ</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="gender" id="genderOther" value="Khác" ${user.gender == 'Khác' ? 'checked' : ''}>
                        <label class="form-check-label" for="genderOther">Khác</label>
                    </div>
                </div>
                <button type="submit" class="btn btn-success">Lưu thay đổi</button>
                <a href="${pageContext.request.contextPath}/manager/home" class="btn btn-secondary ms-2">Quay lại</a>
            </div>
        </div>
    </form>
</div>

<script>
    document.getElementById('imageFile').onchange = function (evt) {
        const [file] = this.files;
        if (file) {
            const preview = document.querySelector('img.img-fluid.rounded-circle.mb-3');
            preview.src = URL.createObjectURL(file);
            preview.onload = () => {
                URL.revokeObjectURL(preview.src); // free memory
            }
        }
    };
</script>

</body>
</html> 