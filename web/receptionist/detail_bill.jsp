<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Chi tiết hóa đơn</title>
        <jsp:include page="/frontend/head/head.jsp" />
        <style>
            body {
                background-color: #f5f5f5;
                font-family: 'Segoe UI', 'Roboto', 'Arial', sans-serif;
                color: #333;
            }

            .bill-box {
                background: rgba(0, 47, 85, 0.8); /* xanh đậm trong suốt */
                padding: 40px;
                border-radius: 16px;
                backdrop-filter: blur(8px);
                margin: 60px auto;
                max-width: 960px;
                box-shadow: 0 8px 30px rgba(0, 0, 0, 0.2);
                color: #fff;
            }

            h3 {
                text-align: center;
                color: #00d0ff;
                margin-bottom: 40px;
            }

            .info-label {
                font-weight: bold;
                color: #00d0ff;
            }

            .info-value {
                font-weight: 500;
                color: #fff;
            }

            .info-row {
                display: flex;
                justify-content: space-between;
                flex-wrap: wrap;
                gap: 20px;
                margin-bottom: 30px;
            }

            .info-col {
                flex: 1 1 48%;
            }

            .section-title {
                color: #00ffff;
                margin-bottom: 20px;
                font-weight: 600;
            }

            .table thead {
                background-color: #007bff;
                color: white;
            }

            .table {
                background-color: rgba(255, 255, 255, 0.03);
            }

            .table td, .table th {
                text-align: center;
                color: #eee;
                vertical-align: middle !important;
            }

            .total-box {
                text-align: right;
                font-size: 20px;
                font-weight: bold;
                margin-top: 30px;
                color: #ff9999;
            }

            .btn-pay {
                display: inline-block;
                margin-top: 30px;
                padding: 12px 28px;
                background: #28a745;
                color: white;
                font-weight: bold;
                border-radius: 10px;
                text-decoration: none;
                transition: 0.3s ease;
            }

            .btn-pay:hover {
                background: #218838;
                transform: translateY(-2px);
                box-shadow: 0 0 10px #28a745;
            }

            .badge {
                padding: 0.4em 0.75em;
                font-size: 0.9em;
                border-radius: 8px;
            }
        </style>
    </head>

    <body>
        <jsp:include page="/frontend/topbar/topbar.jsp" />
        <jsp:include page="/receptionist/navbar/navbar.jsp" />

        <div class="container">
            <div class="bill-box">
                <h3>🧾 Chi Tiết Hóa Đơn</h3>

                <div class="info-row">
                    <div class="info-col">
                        <p><span class="info-label">👤 Bệnh nhân:</span> <span class="info-value">${bill.patientName}</span></p>
                        <p><span class="info-label">⏰ Thời gian khám:</span> 
                            <span class="info-value"><fmt:formatDate value="${bill.appointmentTime}" pattern="HH:mm dd/MM/yyyy" /></span>
                        </p>
                    </div>
                    <div class="info-col">
                        <p><span class="info-label">💳 Phương thức thanh toán:</span> 
                            <span class="info-value">${bill.paymentMethod}</span></p>
                        <p><span class="info-label">💰 Trạng thái thanh toán:</span> 
                            <c:choose>
                                <c:when test="${bill.paymentStatus eq 'Paid'}">
                                    <span class="badge bg-success">Đã thanh toán</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge bg-warning text-dark">${bill.paymentStatus}</span>
                                </c:otherwise>
                            </c:choose>
                        </p>
                    </div>
                </div>

                <h5 class="section-title">📄 Danh sách xét nghiệm</h5>
                <div class="table-responsive">
                    <table class="table table-bordered text-center align-middle">
                        <thead>
                            <tr>
                                <th>Tên xét nghiệm</th>
                                <th>Số lượng</th>
                                <th>Đơn giá (VNĐ)</th>
                                <th>Thành tiền (VNĐ)</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="b" items="${billDetails}">
                                <tr>
                                    <td>${b.testName}</td>
                                    <td>${b.quantity}</td>
                                    <td><fmt:formatNumber value="${b.unitPrice}" type="currency" currencySymbol="" /></td>
                                    <td><fmt:formatNumber value="${b.totalTest}" type="currency" currencySymbol="" /></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <div class="total-box">
                    👉 Tổng tiền: 
                    <span>
                        <fmt:formatNumber value="${bill.amount}" type="currency" currencySymbol="₫" />
                    </span>
                </div>

                <c:if test="${bill.paymentStatus ne 'Paid'}">
                    <div class="text-end">
                        <a href="PayBillServlet?id=${bill.id}" class="btn-pay">💸 Thanh Toán Ngay</a>
                    </div>
                </c:if>
            </div>
        </div>

        <jsp:include page="/frontend/footer/footer.jsp" />
        <jsp:include page="/frontend/script/script.jsp" />
    </body>
</html>
