<%-- 
    Document   : appointment_list
    Created on : May 27, 2025, 1:21:01 PM
    Author     : Admin
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Danh sách lịch khám</title>
        <jsp:include page="/doctor/head/head.jsp" />
    </head>

    <body>
        <jsp:include page="/doctor/topbar/topbar.jsp" />
        <jsp:include page="/doctor/navbar/navbar.jsp" />
        <div class="container mt-5">
            <h2 class="text-center mb-4">Danh sách lịch khám</h2>


            <table class="table table-bordered table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>STT</th>
                        <th>Tên Bác sĩ</th>
                        <th>Chuyên khoa</th>
                        <th>Trình độ</th>
                        <th>Giờ bắt đầu</th>
                        <th>Giờ kết thúc</th>
                        <th>Ngày khám</th>
                        <th>Trạng thái</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="startIndex" value="${(currentPage - 1) * 5}" />
                    <c:forEach var="s" items="${slotList}" varStatus="loop">
                        <tr>
                            <td>${startIndex + loop.index + 1}</td>
                            <td>${s.doctorName}</td>
                            <td>${s.specializationName}</td>
                            <td>${s.levelName}</td>
                            <td>${s.start}</td>
                            <td>${s.end}</td>
                            <td>${s.slotDate}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${s.booked}">
                                        <span class="badge bg-danger">Đã đặt</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-success">Trống</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <!-- phân trang -->
            <jsp:include page="/pages.jsp"/>

        </div>

        <jsp:include page="/doctor/footer/footer.jsp" />
        <jsp:include page="/doctor/script/script.jsp" />
    </body>
</html>


