<%-- 
    Document   : detail_appointment
    Created on : Jul 3, 2025, 9:22:05 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Chi tiết lịch hẹn</title>
        <jsp:include page="/frontend/head/head.jsp" />
        <style>
            .appointment-detail {
                max-width: 960px;
                margin: 40px auto;
            }

            .row.flex-equal {
                display: flex;
                flex-wrap: wrap;
                gap: 30px 0; /* ✅ Khoảng cách giữa các hàng */
            }

            .info-card {
                background-color: #f0f4f8; /* ✅ Màu nền nhẹ cho từng khối */
                border: 1px solid #d0d0d0; /* ✅ Viền sáng nhẹ */
                border-radius: 12px;
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
                padding: 25px 30px;
                height: 100%;
                display: flex;
                flex-direction: column;
                justify-content: space-between;
                transition: transform 0.2s ease;
            }

            .info-card:hover {
                transform: translateY(-3px);
                box-shadow: 0 6px 18px rgba(0, 0, 0, 0.1);
            }

            .info-card h5 {
                font-weight: 700;
                color: #1e88e5;
                border-bottom: 1px solid #ddd;
                padding-bottom: 8px;
                margin-bottom: 18px;
                text-align: left;
            }

            .label {
                font-weight: 700;
                color: #212529;
            }

            .value {
                margin-left: 6px;
                color: #555;
                font-weight: 500;
            }

            .badge {
                font-size: 0.9rem;
                padding: 6px 12px;
                border-radius: 10px;
            }

            .btn-back {
                font-weight: 600;
            }

        </style>
    </head>
    <body>
        <jsp:include page="/frontend/topbar/topbar.jsp" />
        <jsp:include page="/receptionist/navbar/navbar.jsp" />

        <div class="appointment-detail">
            <h3 class="text-center text-primary mb-4">Chi tiết lịch hẹn</h3>

            <c:if test="${not empty message}">
                <div class="alert alert-info text-center fw-semibold rounded-3 mt-3">
                    ${message}
                </div>
            </c:if>
            <div class="row flex-equal">

                <!-- Bệnh nhân -->
                <div class="col-md-6 d-flex">
                    <div class="info-card w-100 position-relative">
                        <div class="d-flex justify-content-between align-items-center">
                            <h5>🧑‍🤝‍🧑 Bệnh nhân</h5>
                            <c:if test="${not empty detail.patientId}">
                                <a href="${pageContext.request.contextPath}/ReceptionistViewPatientServlet?id=${detail.patientId}"
                                   class="btn btn-sm btn-outline-primary">
                                    📄 Hồ sơ
                                </a>
                            </c:if>
                        </div>
                        <p><span class="label">Tên:</span><span class="value">${detail.patientName}</span></p>
                        <p><span class="label">Giới tính:</span><span class="value">${detail.patientGender}</span></p>
                        <p><span class="label">Ngày sinh:</span><span class="value">${detail.patientDob}</span></p>
                    </div>
                </div>



                <!-- Bác sĩ -->
                <div class="col-md-6 d-flex">
                    <div class="info-card w-100">
                        <h5>👨‍⚕️ Bác sĩ</h5>
                        <p><span class="label">Tên:</span><span class="value">${detail.doctorName}</span></p>
                        <p><span class="label">Chuyên khoa:</span><span class="value">${detail.specialization}</span></p>
                        <p><span class="label">Trình độ:</span><span class="value">${detail.doctorLevel}</span></p>
                    </div>
                </div>

                <!-- Thời gian -->
                <div class="col-md-6 d-flex">
                    <div class="info-card w-100">
                        <h5>⏰ Thời gian khám</h5>
                        <p><span class="label">Ngày khám:</span><span class="value">${detail.date}</span></p>
                        <p>
                            <span class="label">Giờ:</span>
                            <span class="value">
                                <fmt:formatDate value="${detail.slotStartTime}" pattern="HH:mm" />
                                -
                                <fmt:formatDate value="${detail.slotEndTime}" pattern="HH:mm" />
                            </span>
                        </p>
                        <p><span class="label">Phòng khám:</span><span class="value">${detail.roomName}</span></p>
                    </div>
                </div>

                <!-- Trạng thái & ghi chú -->
                <div class="col-md-6 d-flex">
                    <div class="info-card w-100">
                        <h5>📌 Trạng thái & Ghi chú</h5>

                        <p>
                            <span class="label">Trạng thái:</span>
                            <span class="value">
                                <c:choose>
                                    <c:when test="${detail.confirmationStatus eq 'Approved'}">
                                        <span class="badge bg-success fw-semibold">Đã xác nhận</span>
                                    </c:when>
                                    <c:when test="${detail.confirmationStatus eq 'Pending'}">
                                        <span class="badge bg-warning text-dark fw-semibold">Chưa xác nhận</span>
                                    </c:when>
                                    <c:when test="${detail.confirmationStatus eq 'Canceled'}">
                                        <span class="badge bg-danger fw-semibold">Đã hủy</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-secondary fw-semibold">Không xác định</span>
                                    </c:otherwise>
                                </c:choose>
                            </span>
                        </p>

                        <p>
                            <span class="label">Ghi chú:</span>
                            <span class="value">${detail.note}</span>
                        </p>

                        <c:if test="${detail.confirmationStatus eq 'Pending'}">
                            <form action="ConfirmSlotAppointmentServlet" method="post" class="d-flex gap-2 mt-3" autocomplete="off">
                                <input type="hidden" name="appointmentId" value="${detail.id}" />

                                <button type="submit" name="action" value="approve" 
                                        class="btn btn-success"
                                        onclick="return confirm('✔ Xác nhận lịch hẹn này?')">
                                    Xác nhận
                                </button>

                                <button type="submit" name="action" value="cancel" 
                                        class="btn btn-danger"
                                        onclick="return confirm('✖ Bạn có chắc muốn từ chối lịch hẹn này?')">
                                    Từ chối
                                </button>
                            </form>
                        </c:if>
                    </div>
                </div>


            </div>
            <div class="text-center mt-3">
                <a href="${pageContext.request.contextPath}/AppointmentListServlet" class="btn btn-outline-primary px-4 btn-back">← Quay lại danh sách</a>
            </div>
        </div>

        <jsp:include page="/frontend/footer/footer.jsp" />
        <jsp:include page="/frontend/script/script.jsp" />
    </body>
</html>


