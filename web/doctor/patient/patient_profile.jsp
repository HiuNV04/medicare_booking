<%-- 
    Document   : view_profile
    Created on : May 27, 2025, 10:12:40 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>TEAM3 - Hồ sơ bệnh nhân</title>
       <jsp:include page="/frontend/head/head.jsp" />
    </head>
    <body>

        <!-- topbar Start -->
        <jsp:include page="/frontend/topbar/topbar.jsp" />
        <!-- topbar End -->


        <!-- navbar Start -->
        <jsp:include page="/doctor/navbar/navbar.jsp" />
        <!-- navbar End -->

        <!-- Content Start -->
        <div class="container py-5">
            <h3 class="mb-4">Hồ sơ bệnh nhân</h3>

            <!-- Tabs -->
            <ul class="nav nav-tabs mb-3" id="patientTab" role="tablist">
                <li class="nav-item" role="presentation">
                    <button class="nav-link active" id="waiting-tab" data-bs-toggle="tab" data-bs-target="#waiting" type="button" role="tab">
                        Bệnh nhân chờ khám
                    </button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="examined-tab" data-bs-toggle="tab" data-bs-target="#examined" type="button" role="tab">
                        Bệnh nhân đã khám
                    </button>
                </li>
            </ul>

            <div class="tab-content" id="patientTabContent">

                <!-- Tab 1: Bệnh nhân chờ khám -->
                <div class="tab-pane fade show active" id="waiting" role="tabpanel">
                    <div class="table-responsive">
                        <table class="table table-bordered align-middle">
                            <thead class="table-light">
                                <tr>
                                    <th>Họ tên</th>
                                    <th>Tuổi</th>
                                    <th>Triệu chứng</th>
                                    <th>Nhóm máu</th>
                                    <th>Ngày đăng ký</th>
                                    <th>Thao tác</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- Dữ liệu mẫu -->
                                <tr>
                                    <td>Nguyễn Văn A</td>
                                    <td>30</td>
                                    <td>Sốt cao, đau đầu</td>
                                    <td>A</td>
                                    <td>17-03-2025</td>

                                    <td>
                                        <a href="${pageContext.request.contextPath}/doctor/prescription/create_prescription.jsp" class="btn btn-sm btn-primary">
                                            Chuẩn đoán
                                        </a>
                                    </td>
                                </tr>
                                <!-- Lặp theo dữ liệu thật sau này -->
                            </tbody>
                        </table>
                    </div>
                </div>

                <!-- Tab 2: Bệnh nhân đã khám -->
                <div class="tab-pane fade" id="examined" role="tabpanel">
                    <div class="table-responsive">
                        <table class="table table-bordered align-middle">
                            <thead class="table-light">
                                <tr>
                                    <th>Họ tên</th>
                                    <th>Tuổi</th>
                                    <th>Nhóm máu</th>
                                    <th>Ngày đăng ký</th>
                                    <th>Ngày khám</th>
                                    <th>Chuẩn đoán</th>
                                    <th>Thao tác</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- Dữ liệu mẫu -->
                                <tr>
                                    <td>Trần Thị B</td>
                                    <td>30</td>
                                    <td>A</td>
                                    <td>14-02-2025</td>
                                    <td>19-05-2025</td>
                                    <td>Viêm da liễu</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/doctor/prescription/create_prescription.jsp" class="btn btn-sm btn-info">
                                            Tạo đơn thuốc
                                        </a>
                                    </td>
                                </tr>
                                <!-- Lặp theo dữ liệu thật sau này -->
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
        </div>
        <!-- Content End -->

        <!-- Footer Start -->
        <jsp:include page="/frontend/footer/footer.jsp" />
        <!-- Footer End -->

        <jsp:include page="/frontend/script/script.jsp" />
    </body>
</html>
