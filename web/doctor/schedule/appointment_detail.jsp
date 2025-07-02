<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, java.text.*" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Lịch Ca Khám</title>
        <jsp:include page="/doctor/head/head.jsp" />
        <style>
            body {
                background-color: #f3f6fb;
            }

            .day-card {
                background-color: #ffffff;
                border-radius: 12px;
                padding: 20px;
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
                transition: 0.3s;
                height: 100%;
            }

            .day-card:hover {
                transform: translateY(-5px);
                box-shadow: 0 6px 18px rgba(0, 0, 0, 0.12);
            }

            .day-title {
                font-size: 18px;
                font-weight: bold;
                color: #007bff;
                margin-bottom: 12px;
            }

            .slot-info {
                font-size: 14px;
                margin-bottom: 8px;
                padding: 8px;
                background-color: #f0f4f8;
                border-radius: 6px;
                animation: fadeIn 0.4s ease-in-out;
            }

            .no-slot {
                color: #888;
                font-style: italic;
                animation: fadeIn 0.4s ease-in-out;
            }

            @keyframes fadeIn {
                from {
                    opacity: 0;
                    transform: translateY(5px);
                }
                to {
                    opacity: 1;
                    transform: translateY(0);
                }
            }
        </style>
    </head>
    <body>
        <jsp:include page="/doctor/topbar/topbar.jsp" />
        <jsp:include page="/doctor/navbar/navbar.jsp" />

        <%
    List<String> next8Days = new ArrayList<>();
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    for (int i = 0; i < 8; i++) {
        next8Days.add(sdf.format(cal.getTime()));
        cal.add(Calendar.DATE, 1);
    }
    request.setAttribute("next8Days", next8Days);
        %>

        <div class="container mt-5">
            <h2 class="text-center text-primary mb-4">Lịch Ca Khám 8 Ngày Tới</h2>

            <div class="row">
                <c:forEach var="targetDateStr" items="${next8Days}">
                    <div class="col-md-3 mb-4">
                        <div class="day-card">
                            <div class="day-title">
                                Ngày 
                                <c:out value="${targetDateStr}" />
                            </div>

                            <c:set var="found" value="false" />
                            <c:forEach var="s" items="${slotDetail}">
                                <c:if test="${fn:substring(s.slotDate, 0, 10) == targetDateStr}">
                                    <div class="slot-info">
                                        🕒 ${s.start} - ${s.end}<br/>
                                        🏥 ${s.specializationName}<br/>
                                        🎓 ${s.levelName}
                                    </div>
                                    <c:set var="found" value="true" />
                                </c:if>
                            </c:forEach>

                            <c:if test="${!found}">
                                <div class="no-slot">Không có ca khám</div>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>

            </div>
        </div>

        <jsp:include page="/doctor/footer/footer.jsp" />
        <jsp:include page="/doctor/script/script.jsp" />
    </body>
</html>
