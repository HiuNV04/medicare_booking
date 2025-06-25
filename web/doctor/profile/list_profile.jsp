<%-- 
    Document   : edit_profile
    Created on : May 20, 2025, 11:48:31 AM
    Author     : Admin
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Danh sách bác sĩ</title>
        <jsp:include page="/doctor/head/head.jsp" />

    </head>
    <body>
        <jsp:include page="/doctor/topbar/topbar.jsp" />
        <jsp:include page="/doctor/navbar/navbar.jsp" />

        <!-- ====== BẮT ĐẦU CHỨC NĂNG TÌM KIẾM / LỌC BÁC SĨ ====== -->
        <div class="container mt-5">
            <h2 class="text-center mb-4">Quản lý <span class="text-warning">bác sĩ</span></h2>

            <!-- Form tìm kiếm / lọc -->
            <form action="DoctorListServlet" method="get" class="row g-3 mb-4">
                <div class="col-md-3">
                    <input type="text" name="name" class="form-control" placeholder="Nhập tên bác sĩ..." value="${param.name}" />
                </div>

                <div class="col-md-2">
                    <select name="gender" class="form-select">
                        <option value="">-- Giới tính --</option>
                        <option value="Nam" <c:if test="${param.gender == 'Nam'}">selected</c:if>>Nam</option>
                        <option value="Nữ" <c:if test="${param.gender == 'Nữ'}">selected</c:if>>Nữ</option>
                        </select>
                    </div>

                    <div class="col-md-3">
                        <select name="specializationId" class="form-select">
                            <option value="">-- Chuyên khoa --</option>
                        <c:forEach var="special" items="${specializations}">
                            <option value="${special[0]}" <c:if test="${param.specializationId == special[0]}">selected</c:if>>${special[1]}</option>
                        </c:forEach>
                    </select>
                </div>



                <div class="col-md-3">
                    <select name="levelId" class="form-select">
                        <option value="">-- Trình độ --</option>
                        <c:forEach var="level" items="${levels}">
                            <option value="${level[0]}" <c:if test="${param.levelId == level[0]}">selected</c:if>>${level[1]}</option>
                        </c:forEach>
                    </select>
                </div>



                <div class="col-md-auto">
                    <button type="submit" class="btn btn-primary w-100">Lọc</button>
                </div>

            </form>




            <!-- Bảng hiển thị danh sách bác sĩ -->
            <c:if test="${not empty doctors}">
                <div class="table-responsive">
                    <table class="table table-bordered table-hover align-middle">
                        <thead class="table-dark text-center">
                            <tr>
                                <th>ID</th>
                                <th>Họ tên</th>
                                <th>Email</th>
                                <th>Giới tính</th>
                                <th>Ngày sinh</th>
                                <th>SĐT</th>
                                <th>Địa chỉ</th>
                                <th>Chuyên khoa</th>
                                <th>Trình độ</th>
                                <th>Thao tác</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="doctor" items="${doctors}">
                                <tr>
                                    <td class="text-center"><c:out value="${doctor.id}" /></td>
                                    <td><c:out value="${doctor.fullName}" /></td>
                                    <td><c:out value="${doctor.email}" /></td>
                                    <td><c:out value="${doctor.gender}" /></td>
                                    <td><fmt:formatDate value="${doctor.dateOfBirth}" pattern="dd/MM/yyyy" /></td>
                                    <td><c:out value="${doctor.phoneNumber}" /></td>
                                    <td><c:out value="${doctor.address}" /></td>
                                    <td><c:out value="${doctor.specialization}" /></td>
                                    <td><c:out value="${doctor.levelName}" /></td>
                                    <td class="text-center">
                                        <c:choose>
                                            <c:when test="${sessionScope.doctor != null && sessionScope.doctor.id == doctor.id}">
                                                <a href="${pageContext.request.contextPath}/DoctorUpdateServlet?id=${doctor.id}" class="btn btn-sm btn-info">Chi tiết</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="${pageContext.request.contextPath}/DoctorUpdateServlet?id=${doctor.id}" class="btn btn-sm btn-secondary">Xem</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>



            <!-- phân trang -->
            <c:if test="${totalPages > 1}">
                <div class="d-flex justify-content-center mt-4">
                    <nav aria-label="Pagination bác sĩ">
                        <ul class="pagination pagination-sm">

                            <!-- Nút "Trước" -->
                            <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                <a class="page-link rounded-pill px-3" href="DoctorListServlet?page=${currentPage - 1}&name=${param.name}&gender=${param.gender}&levelId=${param.levelId}&specializationId=${param.specializationId}">
                                    &laquo; Trước
                                </a>
                            </li>

                            <!-- Các trang -->
                            <c:forEach begin="1" end="${totalPages}" var="i">
                                <li class="page-item ${i == currentPage ? 'active' : ''}">
                                    <a class="page-link rounded-circle mx-1 text-center" style="width: 38px;" href="DoctorListServlet?page=${i}&name=${param.name}&gender=${param.gender}&levelId=${param.levelId}&specializationId=${param.specializationId}">
                                        ${i}
                                    </a>
                                </li>
                            </c:forEach>

                            <!-- Nút "Sau" -->
                            <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                                <a class="page-link rounded-pill px-3" href="DoctorListServlet?page=${currentPage + 1}&name=${param.name}&gender=${param.gender}&levelId=${param.levelId}&specializationId=${param.specializationId}">
                                    Sau &raquo;
                                </a>
                            </li>

                        </ul>
                    </nav>
                </div>
            </c:if>



            <c:if test="${empty doctors}">
                <div class="text-center text-muted">Không tìm thấy bác sĩ nào.</div>
            </c:if>
        </div>
        <!-- ====== KẾT THÚC CHỨC NĂNG TÌM KIẾM / LỌC BÁC SĨ ====== -->


        <jsp:include page="/doctor/footer/footer.jsp" />
        <jsp:include page="/doctor/script/script.jsp" />
    </body>
    <!--</html>-->


