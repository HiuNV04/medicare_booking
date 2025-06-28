<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quản lý lịch làm việc</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background: #f8f9fa; }
        .sidebar {
            min-height: 100vh;
            background: #343a40;
            color: #fff;
            padding: 30px 10px 10px 10px;
        }
        .sidebar a { color: #fff; text-decoration: none; display: block; margin-bottom: 15px; }
        .sidebar a.active, .sidebar a:hover { color: #ffc107; font-weight: bold; }
        .main-content { margin-left: 220px; }
        @media (max-width: 768px) {
            .main-content { margin-left: 0; }
            .sidebar { min-height: auto; }
        }
        .schedule-table .slot {
            min-width: 120px;
            height: 60px;
            vertical-align: middle;
            text-align: center;
        }
        .slot.available { background-color: #e8f5e9; }
        .slot.booked { background-color: #ffcdd2; font-weight: bold; }
        .slot.pending { background-color: #fff9c4; }
    </style>
</head>
<body>
    <div class="d-flex">  
    <!-- Sidebar -->
    <div class="sidebar flex-shrink-0">
        <h4 class="mb-4">Quản lý</h4>
        <a href="${pageContext.request.contextPath}/manager/home">Trang chủ</a>
        <a href="${pageContext.request.contextPath}/manager/rooms">Quản lý phòng</a>
        <a href="${pageContext.request.contextPath}/manager/schedule-management" class="active" >Quản lý lịch</a>
        <a href="${pageContext.request.contextPath}/manager/shift-list">Quản lý ca khám</a>
        <a href="#">Báo cáo</a>
        <a href="#">Cài đặt</a>
    </div>
<div class="main-content flex-grow-1 p-4">
    <h2 class="mb-4">Quản lý lịch làm việc của bác sĩ</h2>

    <div class="d-flex align-items-center mb-3">
        <form method="get" class="me-3">
            <input type="hidden" name="doctorId" value="${selectedDoctorId}"/>
            <button type="submit" name="week" value="${currentWeek-1}" class="btn btn-outline-secondary" <c:if test='${currentWeek==0}'>disabled</c:if>>Tuần trước</button>
            <span class="mx-2">Tuần ${currentWeek+1} / ${totalWeeks}</span>
            <button type="submit" name="week" value="${currentWeek+1}" class="btn btn-outline-secondary" <c:if test='${currentWeek==totalWeeks-1}'>disabled</c:if>>Tuần sau</button>
        </form>
        <form method="get" class="ms-3">
            <input type="hidden" name="doctorId" value="${selectedDoctorId}"/>
            <label class="form-label me-2">Tìm theo ngày:</label>
            <input type="date" name="date" value="${selectedDate}" class="form-control d-inline-block" style="width:auto;display:inline-block;" onchange="this.form.submit()"/>
        </form>
    </div>

    <form method="get" class="row g-3 align-items-end mb-4">
        <div class="col-md-4">
            <label for="doctorId" class="form-label">Chọn bác sĩ</label>
            <select name="doctorId" id="doctorId" class="form-select" onchange="this.form.submit()">
                <option value="">-- Vui lòng chọn --</option>
                <c:forEach var="d" items="${doctorList}">
                    <option value="${d.id}" ${d.id == selectedDoctorId ? 'selected' : ''}>${d.fullName}</option>
                </c:forEach>
            </select>
        </div>
    </form>
    
    <c:if test="${not empty selectedDoctorId}">
        <div class="table-responsive">
            <table class="table table-bordered schedule-table">
                <thead class="table-light">
                    <tr>
                        <th>Thời gian</th>
                        <c:forEach var="dateHeader" items="${weekDates}">
                            <th>${dateHeader}</th>
                        </c:forEach>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="hour" begin="6" end="18">
                        <tr>
                            <td>${hour}:00 - ${hour+1}:00</td>
                            <c:forEach var="displayDate" items="${weekDates}" varStatus="dayStatus">
                                <c:set var="sqlDate" value="${sqlWeekDates[dayStatus.index]}" />
                                <fmt:formatDate value="${sqlDate}" pattern="yyyy-MM-dd" var="dateKey" />
                                <c:set var="slotKey" value="${dateKey}_${hour}" />
                                <c:set var="shift" value="${shifts[slotKey]}" />
                                <c:set var="appointmentStatus" value="${appointmentMap[slotKey]}" />

                                <c:choose>
                                    <c:when test="${empty shift}">
                                        <td class="text-center">
                                            <form action="schedule-management" method="post">
                                                <input type="hidden" name="action" value="create"/>
                                                <input type="hidden" name="doctorId" value="${selectedDoctorId}"/>
                                                <input type="hidden" name="date" value="${dateKey}"/>
                                                <input type="hidden" name="hour" value="${hour}"/>
                                                <button type="submit" class="btn btn-sm btn-outline-success">Create Shift</button>
                                            </form>
                                        </td>
                                    </c:when>
                                    <c:when test="${appointmentStatus == 'Pending' || appointmentStatus == 'Approved'}">
                                        <td class="bg-warning text-white text-center">
                                            Pending/Booked
                                        </td>
                                    </c:when>
                                    <c:when test="${not empty shift}">
                                        <td class="bg-success text-white text-center">
                                            Available
                                            <form action="schedule-management" method="post" class="mt-1">
                                                <input type="hidden" name="action" value="delete"/>
                                                <input type="hidden" name="shiftId" value="${shift.id}"/>
                                                <input type="hidden" name="doctorId" value="${selectedDoctorId}"/>
                                                <button type="submit" class="btn btn-sm btn-danger">Cancel</button>
                                            </form>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td class="bg-danger text-white text-center">
                                            Booked
                                        </td>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>

    <%-- Thông báo thành công/thất bại --%>
    <c:if test="${not empty sessionScope.scheduleMessage}">
        <div class="alert alert-${sessionScope.scheduleMessageType} alert-dismissible fade show" role="alert">
            ${sessionScope.scheduleMessage}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
        <c:remove var="scheduleMessage" scope="session"/>
        <c:remove var="scheduleMessageType" scope="session"/>
    </c:if>
</div>
</div>  
</body>
</html> 