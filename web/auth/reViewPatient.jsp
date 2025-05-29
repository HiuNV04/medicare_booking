
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Patient</title>
        <style>
            * {
                box-sizing: border-box;
                font-family: Arial, sans-serif;
            }

            body {
                margin: 0;
                padding: 0;
                background-color: #f0f2f5;
            }

            .header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                background-color: #ffffff;
                padding: 20px 40px;
                border-bottom: 1px solid #ccc;
                box-shadow: 0 2px 5px rgba(0,0,0,0.05);
            }

            .header h2 {
                color: #007bff;
                margin: 0;
                font-size: 28px;
            }

            .header a {
                color: #007bff;
                text-decoration: none;
                font-weight: bold;
                font-size: 18px;
            }

            .container {
                display: flex;
                height: auto;
                min-height: calc(100vh - 70px);
            }

            .sidebar {
                width: 280px;
                background-color: #ffffff;
                padding: 20px;
                border-right: 1px solid #ddd;
            }

            .sidebar img {
                width: 100%;
                border-radius: 12px;
                margin-bottom: 20px;
            }

            .sidebar a {
                display: block;
                color: #333;
                text-decoration: none;
                margin: 12px 0;
                padding: 10px 14px;
                border-radius: 6px;
                font-size: 16px;
                transition: 0.3s;
            }

            .sidebar a:hover {
                background-color: #e6f0ff;
                color: #007bff;
            }

            .content {
                flex-grow: 1;
                padding: 40px;
                overflow-y: auto;
            }

            .search-bar {
                display: flex;
                justify-content: center;
                margin-bottom: 30px;
            }

            .search-bar input[type="text"] {
                padding: 10px 15px;
                width: 280px;
                font-size: 16px;
                border: 1px solid #ccc;
                border-radius: 6px;
                outline: none;
                transition: border-color 0.3s;
            }

            .search-bar input[type="text"]:focus {
                border-color: #007bff;
            }

            .search-bar input[type="submit"] {
                margin-left: 10px;
                padding: 10px 20px;
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 6px;
                font-size: 16px;
                cursor: pointer;
                transition: background-color 0.3s;
            }

            .search-bar input[type="submit"]:hover {
                background-color: #0056b3;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                background-color: #fff;
                box-shadow: 0 2px 6px rgba(0, 0, 0, 0.05);
                border-radius: 10px;
                overflow: hidden;
            }

            th, td {
                padding: 14px 18px;
                text-align: left;
                border-bottom: 1px solid #eee;
            }

            th {
                background-color: #007bff;
                color: white;
                font-weight: bold;
            }

            tr:hover {
                background-color: #f9f9f9;
            }

            td img {
                max-width: 70px;
                height: auto;
                border-radius: 6px;
            }
        </style>

        <script>
            const rowsPerPage = 10; // Số dòng mỗi trang
            let currentPage = 1;

            function paginateTable() {
                const table = document.getElementById("patientTable");
                const rows = table.getElementsByTagName("tbody")[0].getElementsByTagName("tr");
                const totalRows = rows.length;
                const totalPages = Math.ceil(totalRows / rowsPerPage);
                const paginationDiv = document.getElementById("pagination");

                // Ẩn tất cả dòng
                for (let i = 0; i < totalRows; i++) {
                    rows[i].style.display = "none";
                }

                // Hiển thị các dòng thuộc trang hiện tại
                const start = (currentPage - 1) * rowsPerPage;
                const end = Math.min(start + rowsPerPage, totalRows);
                for (let i = start; i < end; i++) {
                    rows[i].style.display = "";
                }

                // Xóa các nút trang cũ
                paginationDiv.innerHTML = "";

                // Tạo nút "Previous"
                if (currentPage > 1) {
                    const prevBtn = document.createElement("button");
                    prevBtn.textContent = "Previous";
                    prevBtn.style.marginRight = "5px";
                    prevBtn.onclick = () => {
                        currentPage--;
                        paginateTable();
                    };
                    paginationDiv.appendChild(prevBtn);
                }

                // Tạo các nút số trang
                for (let i = 1; i <= totalPages; i++) {
                    const btn = document.createElement("button");
                    btn.textContent = i;
                    btn.style.margin = "0 4px";
                    btn.style.padding = "6px 12px";
                    btn.style.cursor = "pointer";
                    btn.style.borderRadius = "4px";
                    if (i === currentPage) {
                        btn.style.backgroundColor = "#007bff";
                        btn.style.color = "#fff";
                        btn.style.border = "none";
                    }
                    btn.onclick = () => {
                        currentPage = i;
                        paginateTable();
                    };
                    paginationDiv.appendChild(btn);
                }

                // Tạo nút "Next"
                if (currentPage < totalPages) {
                    const nextBtn = document.createElement("button");
                    nextBtn.textContent = "Next";
                    nextBtn.style.marginLeft = "5px";
                    nextBtn.onclick = () => {
                        currentPage++;
                        paginateTable();
                    };
                    paginationDiv.appendChild(nextBtn);
                }
            }
            window.onload = paginateTable;
        </script>
    </head>
    <body>
        <div class="header">
            <h2>Profile</h2>
            <a href="/MediCare_Booking/auth/home.jsp">Trang chủ</a>
        </div>

        <div class="container">
            <div class="sidebar">
                <a style="margin-bottom: 12%;" href="/MediCare_Booking/img/pic9.jpg">
                    <img src="/MediCare_Booking/img/pic9.jpg" alt="Avatar" style="width:100%;">
                </a>
                <a style="margin-bottom: 12%; font-size: 121%;" href="/MediCare_Booking/auth/receptionist.jsp">Home</a>

                <a style="margin-bottom: 12%; font-size: 121%;" href="${pageContext.request.contextPath}/showReceptionist">Receptionist Profile</a>

                <a style="margin-bottom: 12%; font-size: 121%;" href="${pageContext.request.contextPath}/changePassReceptionist">Change Password</a>

                <a style="margin-bottom: 12%;  font-size: 121%;" href="${pageContext.request.contextPath}/reViewPatient">List Patient</a>

                <a style="margin-bottom: 12%;  font-size: 121%;" href="#">List Appointment</a>

                <a style="margin-bottom: 12%;  font-size: 121%;" href="#">View Feedback</a>
            </div>

            <div  style="margin-left: 6%;">
                <div class="search-bar">
                    <form action="reViewPatient" method="post">
                        <input type="text" name="search" placeholder="Search patient by identity number" />
                        <input type="submit" value="SEARCH"/>
                    </form>
                </div>
                <div style="margin-left: -6%;">
                    <table id="patientTable" border="1">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Full Name</th>
                                <th>Gender</th>
                                <th>Identity Number</th>
                                <th>Insurance Number</th>
                                <th>Phone Number</th>
                                <th>Address</th>
                                <th>Image</th>
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="i" items="${sessionScope.list}">
                                <tr>
                                    <td>${i.getId()}</td>
                                    <td>${i.getFullname()}</td>
                                    <td>${i.getGender()}</td>
                                    <td>${i.getIdentity()}</td>
                                    <td>${i.getInsurance()}</td>
                                    <td>${i.getPhone()}</td>
                                    <td>${i.getAddress()}</td>
                                    <td><img src="${i.getImg()}" alt="Patient Image"/></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div id="pagination" style="margin-top: 20px; text-align: center;"></div>
                </div>
            </div>
        </div>
    </body>
</html>