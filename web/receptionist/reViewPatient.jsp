
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

            .sort-bar {
                display: flex;
                justify-content: center;
                margin-bottom: 30px;
            }

            .sort-bar select {
                padding: 10px 15px;
                width: 200px; /* Adjust width to match search bar or as needed */
                font-size: 16px;
                border: 1px solid #ccc;
                border-radius: 6px;
                outline: none;
                background-color: #fff;
                color: #333;
                cursor: pointer;
                transition: border-color 0.3s, box-shadow 0.3s;
            }

            .sort-bar select:focus {
                border-color: #007bff;
                box-shadow: 0 0 5px rgba(0, 123, 255, 0.3);
            }

            .sort-bar select option {
                background-color: #fff;
                color: #333;
            }

            .btn-submit {
                margin-left: 10px;
                padding: 10px 20px;
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 6px;
                font-size: 16px;
                cursor: pointer;
                transition: background-color 0.3s, transform 0.2s;
            }

            .btn-submit:hover {
                background-color: #0056b3;
                transform: scale(1.05);
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

            /* Đảm bảo bảng không vượt khung */
            .table-container {
                overflow-x: auto; /* Cuộn ngang nếu vượt */
            }

            /* Bọc các nút hành động trong 1 div để chúng nằm gọn */
            .action-buttons {
                display: flex;
                gap: 2px;
                flex-wrap: wrap;
            }

            /* Giảm padding để nút gọn gàng hơn */
            .btn-submit {
                padding: 8px 14px;
                font-size: 14px;
                white-space: nowrap;
            }

            .pagination {
                display: flex;
                justify-content: center;
                align-items: center;
                margin-top: 30px;
                gap: 8px;
                flex-wrap: wrap;
            }

            .pagination a {
                padding: 10px 16px;
                text-decoration: none;
                border-radius: 6px;
                background-color: #007bff;
                color: white;
                font-weight: 600;
                transition: background-color 0.3s, transform 0.2s;
                box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            }

            .pagination a:hover {
                background-color: #0056b3;
                transform: scale(1.05);
            }

            .pagination a.active {
                background-color: #0056b3;
                pointer-events: none;
            }

            .pagination a.disabled {
                background-color: #ccc;
                color: #666;
                pointer-events: none;
                cursor: default;
                box-shadow: none;
            }

        </style>
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
                <a style="margin-bottom: 12%; font-size: 121%;" href="/MediCare_Booking/receptionist/receptionist.jsp">Home</a>

<!--                <a style="margin-bottom: 12%;  font-size: 121%;" href="${pageContext.request.contextPath}/reAddPatient">Add Patient</a>-->

                <a style="margin-bottom: 12%;  font-size: 121%;" href="${pageContext.request.contextPath}/reViewPatient">List Patient</a>

                <a style="margin-bottom: 12%; font-size: 121%;" href="${pageContext.request.contextPath}/showReceptionist">Receptionist Profile</a>

                <a style="margin-bottom: 12%; font-size: 121%;" href="${pageContext.request.contextPath}/changePassReceptionist">Change Password</a>
            </div>

            <div style="margin-left: 5%;">
                <div class="search-bar">
                    <c:set var="i" value="${requestScope.identity}"/>
                    <form action="reViewPatient" method="post">
                        <input type="text" name="identity" placeholder="Search patient by identity number" value="${i.getIdentity()}"/>
                        <input type="submit" value="SEARCH" class="btn-submit"/> 
                    </form>
                </div>
                <div class="sort-bar" style="margin-bottom: 2%; margin-top: -1%;">
                    <form action="reViewPatient" method="get">
                        <select name="field">
                            <option value="id" ${requestScope.field == 'id' ? 'selected' : ''}>ID</option>
                            <option value="full_name" ${requestScope.field == 'name' ? 'selected' : ''}>Name</option>
                        </select>
                        <select name="order">
                            <option value="asc" ${requestScope.order == 'asc' ? 'selected' : ''}>ASC</option>
                            <option value="desc" ${requestScope.order == 'desc' ? 'selected' : ''}>DESC</option>
                        </select>
                        <input type="submit" value="SORT" class="btn-submit"/>
                        <a href="${pageContext.request.contextPath}/reAddPatient" class="btn-submit" style="margin-left: 10px;text-decoration: none;font-size: 16px;">Add Patient</a>
                    </form>
                </div>
                <div style="margin-left: -6%; margin-right: -1%">
                    <table id="patientTable" border="1">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Full Name</th>
                                <th>Gender</th>
                                <th>Date of Birth</th>
                                <th>Identity Number</th>
                                <th>Insurance Number</th>
                                <th>Phone Number</th>
                                <th>Address</th>
                                <th>Image</th>
                                <th>Action</th> 
                            </tr>
                        </thead>

                        <c:forEach var="i" items="${listA}">
                            <tr>
                                <td>${i.getId()}</td>
                                <td>${i.getFullname()}</td>
                                <td>${i.getGender()}</td>
                                <td>${i.getDob()}</td>
                                <td>${i.getIdentity()}</td>
                                <td>${i.getInsurance()}</td>
                                <td>${i.getPhone()}</td>
                                <td>${i.getAddress()}</td>
                                <td><img src="${i.getImg()}" alt="Patient Image"/></td>
                                <td>
                                    <div class="action-buttons">
                                        <a href="reUpdatePatient?id=${i.getId()}" class="btn-submit">Update</a>
                                        <a href="reDeletePatient?id=${i.getId()}" class="btn-submit">Delete</a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div class="pagination">
                        <c:forEach begin="1" end="${endPage}" var="i">
                            <c:url var="pageUrl" value="reViewPatient">
                                <c:param name="index" value="${i}" />
                                <c:if test="${not empty field}">
                                    <c:param name="field" value="${field}" />
                                </c:if>
                                <c:if test="${not empty order}">
                                    <c:param name="order" value="${order}" />
                                </c:if>
                            </c:url>
                            <a href="${pageUrl}" class="${param.index == i ? 'active' : ''}">${i}</a>
                        </c:forEach>
                    </div>
                </div>
            </div>          
        </div>
    </body>
</html>