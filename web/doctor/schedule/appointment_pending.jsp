<%-- 
    Document   : appointment_pending
    Created on : May 27, 2025, 8:54:58 PM
    Author     : Admin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>TEAM3 - Trạng thái xác nhận</title>
        <jsp:include page="/frontend/head/head.jsp" />
    </head>
    <body>

        <!-- topbar Start -->
        <jsp:include page="/frontend/topbar/topbar.jsp" />
        <!-- topbar End -->

        <!-- navbar Start -->
        <jsp:include page="/doctor/navbar/navbar.jsp" />
        <!-- navbar End -->




        <div class="container mt-5">

            <h2 class="text-center mb-4">Danh sách lịch hẹn <span class="text-warning">chờ xác nhận</span>
            </h2>


            <!-- Bảng danh sách -->
            <table class="table table-bordered table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>STT</th>
                        <th>Tên bệnh nhân</th>
                        <th>Ngày sinh</th>
                        <th>Giới tính</th>                     
                        <th>Ngày khám</th>
                        <th>Giờ khám</th>
                        <th>Chuyên khoa</th>
                        <th>Ghi chú</th>
                        <th>Thao tác</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Dữ liệu mẫu -->
                    <tr>
                        <td>1</td>
                        <td>Nguyễn Bảo An</td>
                        <td>20-09-2004</td>
                        <td>Nam</td>                 
                        <td>21-05-2025</td>
                        <td>08:00 - 08:30</td>
                        <td>Da liễu</td>
                        <td>Da suốt hiện nhièu vết mần đỏ</td>
                        <td>
                            <form method="post" action="HandleAppointmentServlet" class="d-inline">
                                <input type="hidden" name="appointmentId" value="1">
                                <input type="hidden" name="action" value="accept">
                                <button type="submit" class="btn btn-success btn-sm">Xác nhận</button>
                            </form>
                            <form method="post" action="HandleAppointmentServlet" class="d-inline ms-1">
                                <input type="hidden" name="appointmentId" value="1">
                                <input type="hidden" name="action" value="cancel">
                                <button type="submit" class="btn btn-danger btn-sm">Hủy</button>
                            </form>
                        </td>
                    </tr>

                    <tr>
                        <td>2</td>
                        <td>Nguyễn Văn Giang</td>
                        <td>15-07-1999</td>
                        <td>Nam</td>
                        <td>24-05-2025</td>
                        <td>08:00 - 08:30</td>
                        <td>Tim mạch</td>
                        <td>Gia đình có tiền sử di truyền bệnh tim mạch</td>
                        <td>
                            <form method="post" action="HandleAppointmentServlet" class="d-inline">
                                <input type="hidden" name="appointmentId" value="5">
                                <input type="hidden" name="action" value="accept">
                                <button type="submit" class="btn btn-success btn-sm">Xác nhận</button>
                            </form>
                            <form method="post" action="HandleAppointmentServlet" class="d-inline ms-1">
                                <input type="hidden" name="appointmentId" value="5">
                                <input type="hidden" name="action" value="cancel">
                                <button type="submit" class="btn btn-danger btn-sm">Hủy</button>
                            </form>
                        </td>
                    </tr>

                    <tr>
                        <td>3</td>
                        <td>Trần Đức Minh</td>                       
                        <td>08-12-2001</td>
                        <td>Nam</td>
                        <td>26-05-2025</td>
                        <td>07:30 - 09:30</td>
                        <td>Tai Mũi Họng</td>
                        <td>Đau tai và viêm họng</td>
                        <td>
                            <form method="post" action="HandleAppointmentServlet" class="d-inline">
                                <input type="hidden" name="appointmentId" value="3">
                                <input type="hidden" name="action" value="accept">
                                <button type="submit" class="btn btn-success btn-sm">Xác nhận</button>
                            </form>
                            <form method="post" action="HandleAppointmentServlet" class="d-inline ms-1">
                                <input type="hidden" name="appointmentId" value="3">
                                <input type="hidden" name="action" value="cancel">
                                <button type="submit" class="btn btn-danger btn-sm">Hủy</button>
                            </form>
                        </td>
                    </tr>

                    <!-- Thêm các dòng khác nếu cần -->
                </tbody>
            </table>
        </div>



        <!-- Footer Start -->
        <jsp:include page="/frontend/footer/footer.jsp" />
        <!-- Footer End -->

        <!-- Footer Start -->
        <jsp:include page="/frontend/script/script.jsp" />
        <!-- Footer End -->


    </body>
</html>


