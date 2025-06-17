<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Đăng ký tài khoản</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background-color: #f8f9fa;
            }
            .register-container {
                max-width: 650px;
                margin: 50px auto;
                padding: 30px;
                background-color: #ffffff;
                border-radius: 12px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .form-label {
                font-weight: 500;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="register-container">
                <h2 class="mb-4 text-center">Đăng ký tài khoản</h2>

                <c:if test="${not empty error}">
                    <div class="alert alert-danger">${error}</div>
                </c:if>

                <c:if test="${not empty success}">
                    <div class="alert alert-success">${success}</div>
                </c:if>

                <form method="post" action="register" enctype="multipart/form-data">
                    <!-- Ảnh đại diện -->
                    <div class="mb-3">
                        <label class="form-label">Ảnh đại diện (link hoặc upload):</label>
                        <input type="text" class="form-control mb-2" name="image_url" placeholder="Nhập đường dẫn ảnh (nếu có)">
                        <input type="file" class="form-control" name="image_file" accept="image/*">
                    </div>

                    <div class="row">
                        <div class="mb-3 col-md-6">
                            <label class="form-label">Email:</label>
                            <input type="email" class="form-control" name="email" required>
                        </div>
                        <div class="mb-3 col-md-6">
                            <label class="form-label">Tên đăng nhập:</label>
                            <input type="text" class="form-control" name="username" required>
                        </div>
                    </div>

                    <div class="row">
                        <div class="mb-3 col-md-6">
                            <label class="form-label">Mật khẩu:</label>
                            <input type="password" class="form-control" name="password" required>
                        </div>
                        <div class="mb-3 col-md-6">
                            <label class="form-label">Nhập lại mật khẩu:</label>
                            <input type="password" class="form-control" name="confirmPassword" required>
                        </div>
                    </div>

                    <div class="row">
                        <div class="mb-3 col-md-6">
                            <label class="form-label">Họ và tên:</label>
                            <input type="text" class="form-control" name="full_name" required>
                        </div>
                        <div class="mb-3 col-md-6">
                            <label class="form-label">Ngày sinh:</label>
                            <input type="date" class="form-control" name="date_of_birth" required>
                        </div>
                    </div>

                    <div class="row">
                        <div class="mb-3 col-md-6">
                            <label class="form-label">Giới tính:</label>
                            <select class="form-select" name="gender" required>
                                <option value="">-- Chọn giới tính --</option>
                                <option value="Nam">Nam</option>
                                <option value="Nữ">Nữ</option>
                                <option value="Khác">Khác</option>
                            </select>
                        </div>
                        <div class="mb-3 col-md-6">
                            <label class="form-label">Số điện thoại:</label>
                            <input type="text" class="form-control" name="phone_number" required>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Địa chỉ:</label>
                        <input type="text" class="form-control" name="address" required>
                    </div>

                    <div class="row">
                        <div class="mb-3 col-md-6">
                            <label class="form-label">Số CCCD/CMND:</label>
                            <input type="text" class="form-control" name="identity_number">
                        </div>
                        <div class="mb-3 col-md-6">
                            <label class="form-label">Số BHYT:</label>
                            <input type="text" class="form-control" name="insurance_number">
                        </div>
                    </div>

                    <!-- Ẩn role: luôn là Patient -->
                    <input type="hidden" name="role" value="Patient">
                    <!-- Trạng thái tài khoản: mặc định là 1 (active), nếu muốn có checkbox thì mở comment dưới -->
                    <input type="hidden" name="status" value="1">

                    <!--
                    <div class="mb-3">
                        <label class="form-label">Kích hoạt tài khoản?</label>
                        <select class="form-select" name="status">
                            <option value="1" selected>Hoạt động</option>
                            <option value="0">Chưa kích hoạt</option>
                        </select>
                    </div>
                    -->

                    <div class="d-grid mb-2">
                        <button type="submit" class="btn btn-primary">Đăng ký</button>
                    </div>
                    <div class="d-grid">
                        <a href="login" class="btn btn-outline-secondary">Trở về đăng nhập</a>
                    </div>
                </form>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
