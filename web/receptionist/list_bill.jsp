<%-- 
    Document   : list_bill
    Created on : Jun 30, 2025, 6:09:20 PM
    Author     : ADDMIN
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/check/check_receptionist.jsp" %>
<%@ include file="/check/check_login.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Danh sách hóa đơn</title>
        <jsp:include page="/frontend/head/head.jsp" />
    </head>

    <body>
        <jsp:include page="/frontend/topbar/topbar.jsp" />
        <jsp:include page="/receptionist/navbar/navbar.jsp" />

        <div class="container mt-5">
            <h2 class="text-center mb-4">💳 Danh sách hóa đơn</h2>

            <table class="table table-bordered table-hover">
                <thead class="table-dark text-center">
                    <tr>
                        <th>STT</th>
                        <th>Tên bệnh nhân</th>
                        <th>Ngày tạo</th>
                        <th>Phương thức thanh toán</th>
                        <th>Trạng thái</th>
                        <th>Tổng tiền</th>
                        <th>Chi tiết</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="startIndex" value="${(currentPage - 1) * 5}" />
                    <c:forEach var="b" items="${bills}" varStatus="loop">
                        <tr>
                            <td>${startIndex + loop.index + 1}</td>
                            <td>${b.patientName}</td>
                            <td><fmt:formatDate value="${b.appointmentTime}" pattern="dd/MM/yyyy  - HH:mm" /></td>
                            <td>${b.paymentMethod}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${b.paymentStatus eq 'Paid'}">
                                        <span class="badge bg-success">Đã thanh toán</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-warning text-dark">Chưa thanh toán</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <fmt:formatNumber value="${b.amount}" type="currency" currencySymbol="₫" groupingUsed="true"/>
                            </td>
                            <td class="text-center">
                                <a href="${pageContext.request.contextPath}/BillDetailServlet?id=${b.id}" class="btn btn-sm btn-info">Xem</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>

            </table>

            <!-- phân trang -->
            <jsp:include page="/pages.jsp"/>
        </div>

        <jsp:include page="/frontend/footer/footer.jsp" />
        <jsp:include page="/frontend/script/script.jsp" />
    </body>

</html>
