<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, java.text.*" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"/>
        <title>Lịch Làm Việc</title>
        <jsp:include page="/frontend/head/head.jsp"/>
        <style>
            body {
                background-color: #f3f6fb;
            }

            .day-card {
                background-color: rgba(255, 255, 255, 0.6);
                border-radius: 16px;
                padding: 20px;
                height: 100%;
                transition: all 0.3s ease;
                border: 2px solid transparent;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.06);
            }

            .day-card:hover {
                transform: translateY(-4px);
                box-shadow: 0 8px 25px rgba(0, 0, 0, 0.1);
            }

            .has-slot {
                border-color: #0d6efd;
                box-shadow: 0 6px 20px rgba(13, 110, 253, 0.25);
            }

            .day-title {
                font-size: 18px;
                font-weight: 600;
                color: #0d6efd;
                margin-bottom: 14px;
            }

            .slot-card {
                display: flex;
                align-items: flex-start;
                padding: 14px 16px;
                background-color: rgba(13, 110, 253, 0.05);
                border-radius: 14px;
                margin-bottom: 12px;
                transition: transform 0.2s ease, box-shadow 0.2s ease;
                box-shadow: 0 2px 6px rgba(0,0,0,0.05);
                cursor: pointer;
            }

            .slot-card:hover {
                transform: translateY(-3px);
                box-shadow: 0 6px 20px rgba(13, 110, 253, 0.2);
            }

            .slot-icon {
                font-size: 22px;
                margin-right: 14px;
                color: #0d6efd;
            }

            .slot-content .slot-time {
                font-weight: 600;
                color: #0d6efd;
                margin-bottom: 4px;
            }

            .slot-content .slot-patient,
            .slot-content .slot-room {
                font-size: 14px;
                color: #333;
                margin-bottom: 2px;
            }

            .no-slot {
                color: #aaa;
                font-style: italic;
            }

            .nav-buttons {
                display: flex;
                justify-content: space-between;
                margin-top: 30px;
            }

            .btn-outline-primary {
                padding: 6px 18px;
                font-weight: 500;
            }
        </style>
    </head>
    <body>
        <jsp:include page="/frontend/topbar/topbar.jsp"/>
        <jsp:include page="/doctor/navbar/navbar.jsp"/>

        <%
            int offset = 0;
            try {
                offset = Integer.parseInt(request.getParameter("offset"));
            } catch (Exception e) {
                offset = 0;
            }

            List<String> displayDays = new ArrayList<>();
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, offset);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 0; i < 6; i++) {
                displayDays.add(sdf.format(cal.getTime()));
                cal.add(Calendar.DATE, 1);
            }
            request.setAttribute("offset", offset);
            request.setAttribute("displayDays", displayDays);
        %>

        <div class="container mt-5">
            <h2 class="text-center text-primary mb-4">📅 Lịch Làm Việc Cá Nhân</h2>

            <div class="row">
                <c:forEach var="day" items="${displayDays}">
                    <div class="col-md-4 mb-4">
                        <c:set var="hasSlot" value="false"/>
                        <c:forEach var="s" items="${slotDetail}">
                            <c:if test="${fn:substring(s.slotDate, 0, 10) == day}">
                                <c:set var="hasSlot" value="true"/>
                            </c:if>
                        </c:forEach>

                        <div class="day-card ${hasSlot ? 'has-slot' : ''}">
                            <div class="day-title">📅 Ngày ${day}</div>

                            <c:choose>
                                <c:when test="${hasSlot}">
                                    <c:forEach var="s" items="${slotDetail}">
                                        <c:if test="${fn:substring(s.slotDate, 0, 10) == day}">
                                            <div class="slot-card">
                                                <div class="slot-icon">⏰</div>
                                                <div class="slot-content">
                                                    <div class="slot-time">${s.start} - ${s.end}</div>
                                                    <div class="slot-patient">
                                                        👤
                                                        <c:choose>
                                                            <c:when test="${not empty s.patientName}">
                                                                ${s.patientName}
                                                            </c:when>
                                                            <c:otherwise>
                                                                (Chưa có người đặt)
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>
                                                    <div class="slot-room">
                                                        🏥
                                                        <c:choose>
                                                            <c:when test="${s.roomId != null}">
                                                                Phòng số: ${s.roomId}
                                                            </c:when>
                                                            <c:otherwise>
                                                                Phòng số: -
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <div class="no-slot">Không có ca khám</div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <!-- Nút chuyển ngày -->
            <div class="nav-buttons d-flex justify-content-between mt-4">
                <form method="get">
                    <input type="hidden" name="offset" value="${offset - 6}"/>
                    <button class="btn btn-outline-primary">← 6 ngày trước</button>
                </form>
                <form method="get">
                    <input type="hidden" name="offset" value="${offset + 6}"/>
                    <button class="btn btn-outline-primary">6 ngày sau →</button>
                </form>
            </div>
        </div>

        <jsp:include page="/frontend/footer/footer.jsp"/>
        <jsp:include page="/frontend/script/script.jsp"/>
    </body>
</html>
