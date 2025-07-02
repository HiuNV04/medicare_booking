<%-- 
    Document   : appointment_history
    Created on : May 27, 2025, 8:53:46 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>TEAM3 - Lịch sử lịch khám</title>
        <jsp:include page="/frontend/head/head.jsp" />
    </head>
    <body>

        <!-- topbar Start -->
        <jsp:include page="/frontend/topbar/topbar.jsp" />
        <!-- topbar End -->



        <!-- navbar Start -->
        <jsp:include page="/doctor/navbar/navbar.jsp" />
        <!-- navbar End -->


        <!-- Appointment Today List Start -->
        <div class="container mt-5">
            <h3 class="mb-4 text-primary text-center">Danh sách lịch hẹn hôm nay</h3>

            <div class="row mb-3">
                <div class="col-md-6">
                    <input type="text" class="form-control" placeholder="Tìm theo tên bệnh nhân...">
                </div>
                <div class="col-md-3">
                    <select class="form-select">
                        <option value="">Tất cả trạng thái</option>
                        <option value="1">Chờ khám</option>
                        <option value="2">Đã khám</option>
                        <option value="3">Đã hủy</option>
                    </select>
                </div>
                <div class="col-md-3">
                    <button class="btn btn-primary w-100">Lọc</button>
                </div>
            </div>

            <table class="table table-bordered table-hover">
                <thead class="table-primary">
                    <tr>
                        <th>STT</th>
                        <th>Tên bệnh nhân</th>
                        <th>Chuyên khoa</th>
                        <th>Thời gian</th>
                        <th>Trạng thái</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>Nguyễn Văn A</td>
                        <td>Tim mạch</td>
                        <td>09:00 - 09:30</td>
                        <!--                        <td><span class="badge bg-warning text-dark">Chờ khám</span></td>-->
                        <td><span class="badge bg-success">Đã khám</span></td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Trần Thị B</td>
                        <td>Da liễu</td>
                        <td>10:00 - 10:30</td>
                        <td><span class="badge bg-success">Đã khám</span></td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>Lê Văn C</td>
                        <td>Nội tiết</td>
                        <td>11:00 - 11:30</td>
                        <!--                        <td><span class="badge bg-success">Đã hủy</span></td>-->
                        <td><span class="badge bg-success">Đã khám</span></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <!-- Appointment Today List End -->

        <jsp:include page="/frontend/footer/footer.jsp" />
        <jsp:include page="/frontend/script/script.jsp" />
    </body>
</html>
