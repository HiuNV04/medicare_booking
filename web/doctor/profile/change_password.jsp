<%-- 
    Document   : change_password
    Created on : Jun 4, 2025, 9:56:36 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Đổi mật khẩu</title>
         <jsp:include page="/doctor/head/head.jsp" />
    </head>
    <body>

        <jsp:include page="/frontend/topbar/topbar.jsp" />
        <jsp:include page="/doctor/navbar/navbar.jsp" />

        <% if (request.getAttribute("success") != null) { %>
        <div style="color: green; font-weight: bold; text-align: center; margin-top: 20px;">
            <%= request.getAttribute("success") %>
        </div>
        <% } %>

        <% if (request.getAttribute("error") != null) { %>
        <div style="color: red; font-weight: bold; text-align: center; margin-top: 20px;">
            <%= request.getAttribute("error") %>
        </div>
        <% } %>

        <form action="${pageContext.request.contextPath}/DoctorChangePasswordServlet" method="post">
            <div class="row justify-content-center">
                <div class="col-md-6">

                    <input type="hidden" name="id" value="${doctor.id}" />
                    <input type="hidden" name="action" value="updatePassword" />
                    <br>
                    <div class="mb-3">
                        <label for="currentPassword" class="form-label">Mật khẩu hiện tại</label>
                        <input type="password" class="form-control" id="currentPassword" name="currentPassword" required>
                    </div>

                    <div class="mb-3">
                        <label for="newPassword" class="form-label">Mật khẩu mới</label>
                        <input type="password" class="form-control" id="newPassword" name="newPassword" required>
                    </div>

                    <div class="mb-3">
                        <label for="confirmPassword" class="form-label">Nhập lại mật khẩu</label>
                        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                    </div>

                    <div class="d-flex justify-content-end gap-3 mt-4">
                        <button type="submit" class="btn btn-warning"
                                onclick="return confirm('Bạn chắc chắn muốn cập nhật mật khẩu?')">
                            Cập nhật mật khẩu
                        </button>
                    </div>
                </div>
            </div>
        </form>




        <jsp:include page="/frontend/footer/footer.jsp" />
        <jsp:include page="/frontend/script/script.jsp" />
    </body>
</html>
