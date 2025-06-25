<%-- 
    Document   : create_prescription
    Created on : May 27, 2025, 1:21:35 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>TEAM3 - Tạo đơn thuốc</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">

        <!-- Favicon -->
        <link href="${pageContext.request.contextPath}/img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Roboto+Condensed:wght@400;700&family=Roboto:wght@400;700&display=swap" rel="stylesheet">  

        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="${pageContext.request.contextPath}/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

        <!-- Customized Bootstrap Stylesheet -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">




        <!-- Owl Carousel CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.min.css">

    </head>
    <body>

        <!-- topbar Start -->
        <jsp:include page="/doctor/topbar/topbar.jsp" />
        <!-- topbar End -->


        <!-- navbar Start -->
        <jsp:include page="/doctor/navbar/navbar.jsp" />
        <!-- navbar End -->


        <!-- Create Prescription Form Start -->
        <%
            int medCount = 1;
            try {
                medCount = Integer.parseInt(request.getParameter("medCount"));
                if (medCount < 1) medCount = 1;
            } catch (Exception e) {
                medCount = 1;
            }

            String[] meds = {"Paracetamol", "Amoxicillin", "Ibuprofen", "Loratadine", "Ciprofloxacin", "Metronidazole"};
        %>

        <div class="container mt-5 mb-5">
            <h2 class="text-center mb-4">Tạo đơn thuốc</h2>

            <!-- Ô chọn số lượng thuốc có nút tăng giảm -->
            <form method="get" action="">
                <div class="row mb-3">
                    <div class="col-md-4">
                        <label class="form-label">Số lượng thuốc cần kê</label>
                        <input type="number" name="medCount" class="form-control" min="1" value="<%=medCount%>" onchange="this.form.submit()">
                    </div>
                </div>
            </form>

            <!-- Form tạo đơn thuốc -->
            <form action="createPrescription" method="post">
                <input type="hidden" name="medCount" value="<%=medCount%>">

                <div class="row mb-3">
                    <div class="col-md-6">
                        <label class="form-label">Bệnh nhân</label>
                        <input type="text" class="form-control" name="patientName" value="${requestScope.patientName}" required>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Ngày tạo</label>
                        <input type="date" class="form-control" name="createdDate" required>
                    </div>
                </div>

                <div class="mb-3">
                    <label class="form-label">Triệu chứng</label>
                    <textarea class="form-control" name="symptoms" rows="3" required></textarea>
                </div>

                <div class="mb-3">
                    <label class="form-label">Chẩn đoán</label>
                    <textarea class="form-control" name="diagnosis" rows="3" required></textarea>
                </div>

                <div class="mb-3">
                    <label class="form-label">Ghi chú thêm</label>
                    <textarea class="form-control" name="notes" rows="2"></textarea>
                </div>

                <label class="form-label">Danh sách thuốc</label>
                <% for (int i = 1; i <= medCount; i++) { %>
                <div class="row mb-2">
                    <div class="col-md-4">
                        <select name="medicineName<%=i%>" class="form-control" required>
                            <option value="">-- Chọn thuốc --</option>
                            <% for (String med : meds) { %>
                            <option value="<%=med%>"><%=med%></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="col-md-4">
                        <input type="text" name="usage<%=i%>" class="form-control" placeholder="Cách dùng" required>
                    </div>
                    <div class="col-md-4">
                        <input type="number" name="quantity<%=i%>" class="form-control" placeholder="Số lượng viên" min="1" required>
                    </div>
                </div>
                <% } %>

                <div class="text-center mt-4">
                    <button type="submit" class="btn btn-success">Tạo đơn thuốc</button>
                </div>
            </form>
        </div>



        <!-- Create Prescription Form End -->










        <!-- Footer Start -->
        <jsp:include page="/doctor/footer/footer.jsp" />
        <!-- Footer End -->


        <!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>


        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/waypoints/waypoints.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>
        <script src="lib/tempusdominus/js/moment.min.js"></script>
        <script src="lib/tempusdominus/js/moment-timezone.min.js"></script>
        <script src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

        <!-- Template Javascript -->
        <script src="js/main.js"></script>
        <!-- jQuery (Owl Carousel cần jQuery) -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

        <!-- Owl Carousel JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>
    </body>
</html>
