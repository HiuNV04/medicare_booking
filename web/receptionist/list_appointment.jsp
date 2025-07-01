<%-- 
    Document   : list_appointment
    Created on : Jun 30, 2025, 5:10:03 PM
    Author     : ADDMIN
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Danh sách lịch hẹn</title>
        <jsp:include page="/receptionist/head/head.jsp" />
    </head>

    <body>
        <jsp:include page="/receptionist/topbar/topbar.jsp" />
        <jsp:include page="/receptionist/navbar/navbar.jsp" />

        <div class="container mt-5">
            <h2 class="text-center mb-4">📅 Danh sách lịch hẹn</h2>

            <table class="table table-bordered table-hover">
                <thead class="table-dark text-center">
                    <tr>
                        <th>STT</th>
                        <th>Bệnh nhân</th>
                        <th>Bác sĩ</th>
                        <th>Ngày khám</th>
                        <th>Giờ bắt đầu</th>
                        <th>Giờ kết thúc</th>
                        <th>Phòng</th>
                        <th>Trạng thái</th>
                        <th>Chi tiết</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="startIndex" value="${(currentPage - 1) * 5}" />
                    <c:forEach var="a" items="${appointments}" varStatus="loop">
                        <tr>
                            <td class="text-center">${startIndex + loop.index + 1}</td>
                            <td>${a.patientName}</td>
                            <td>${a.doctorName}</td>
                            <td><fmt:formatDate value="${a.date}" pattern="dd/MM/yyyy" /></td>
                            <td><fmt:formatDate value="${a.slotStartTime}" pattern="HH:mm" /></td>
                            <td><fmt:formatDate value="${a.slotEndTime}" pattern="HH:mm" /></td>
                            <td class="text-center">${a.roomId}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${a.confirmationStatus eq 'Confirmed'}">
                                        <span class="badge bg-success">Đã xác nhận</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-warning text-dark">${a.confirmationStatus}</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td class="text-center">
                                <a href="${pageContext.request.contextPath}/appointment-detail?id=${a.id}" class="btn btn-sm btn-info">Xem</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- phân trang -->
            <jsp:include page="/pages.jsp"/>
        </div>

        <jsp:include page="/receptionist/footer/footer.jsp" />
        <jsp:include page="/receptionist/script/script.jsp" />
    </body>
</html>


