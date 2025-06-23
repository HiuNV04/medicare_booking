<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Admin</title>
        <style>
            * {
                box-sizing: border-box;
                font-family: Arial, sans-serif;
            }

            body {
                margin: 0;
                padding: 0;
                height: 100vh;           /* Giới hạn chiều cao toàn trang */
                overflow: auto;       
                font-family: sans-serif;
            }

            .header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                background-color: #f8f8f8;
                padding: 15px 20px;
                border-bottom: 1px solid #ddd;
            }

            .header h2 {
                color: #007bff;
            }

            .header a {
                color: #007bff;
                text-decoration: none;
            }

            .container {
                display: flex;
            }

            .sidebar {
                width: 240px;
                background-color: #f9f9f9;
                padding: 20px;
                border-right: 1px solid #ddd;
                height: 100vh;
                overflow-y: auto;           /* Thêm thanh cuộn dọc nếu tràn */
            }

            .sidebar a {
                display: block;
                color: #007bff;
                text-decoration: none;
                margin: 8px 0;
                padding-left: 10px;
            }

            .submenu {
                display: none;
                margin-left: 10px;
            }

            .submenu a {
                margin-bottom: 10%;
            }

            .content {
                flex-grow: 1;
                padding: 20px;
            }

            .card {
                background-color: #fff;
                border: 1px solid #ddd;
                padding: 15px;
                margin: 10px 0;
                border-radius: 5px;
            }
        </style>

        <script>
            function togglePatientSubMenu() {
                var submenu = document.getElementById("patient-submenu");
                submenu.style.display = submenu.style.display === "block" ? "none" : "block";
            }
            function toggleDoctorSubMenu() {
                var submenu = document.getElementById("doctor-submenu");
                submenu.style.display = submenu.style.display === "block" ? "none" : "block";
            }
            function toggleReceptionistSubMenu() {
                var submenu = document.getElementById("receptionist-submenu");
                submenu.style.display = submenu.style.display === "block" ? "none" : "block";
            }
            function toggleManagerSubMenu() {
                var submenu = document.getElementById("manager-submenu");
                submenu.style.display = submenu.style.display === "block" ? "none" : "block";
            }
        </script>
    </head>
    <body>
        <div class="header">
            <h2>Dashboard</h2>
            <a href="/MediCare_Booking/auth/home.jsp">Log out</a>
        </div>

        <div class="container">
            <div class="sidebar">
                <a style="margin-bottom: 12%;" href="">Home</a>

                <a style="margin-bottom: 12%" href="#" onclick="togglePatientSubMenu()">Patient</a>
                <div id="patient-submenu" class="submenu">
                    <a href="addPatient.jsp">Add Patient</a>
                    <a href="updatePatient.jsp">Update Patient</a>
                    <a href="deletePatient.jsp">Delete Patient</a>
                    <a href="showPatient.jsp">Show Patient</a>
                </div>

                <a style="margin-bottom: 12%;" href="#" onclick="toggleDoctorSubMenu()">Doctor</a>
                <div id="doctor-submenu" class="submenu">
                    <a href="addDoctor.jsp">Add Doctor</a>
                    <a href="updateDoctor.jsp">Update Doctor</a>
                    <a href="deleteDoctor.jsp">Delete Doctor</a>
                    <a href="showDoctor.jsp">Show Doctor</a>
                </div>

                <a style="margin-bottom: 12%;" href="#" onclick="toggleReceptionistSubMenu()">Receptionist</a>
                <div id="receptionist-submenu" class="submenu">
                    <a href="addReceptionis.jsp">Add Receptionist</a>
                    <a href="updateReceptionis.jsp">Update Receptionist</a>
                    <a href="deleteReceptionis.jsp">Delete Receptionist</a>
                    <a href="showReceptionis.jsp">Show Receptionist</a>
                </div>

                <a style="margin-bottom: 12%;" href="#" onclick="toggleManagerSubMenu()">Manager</a>
                <div id="manager-submenu" class="submenu">
                    <a href="addManager.jsp">Add Manager</a>
                    <a href="updateManager.jsp">Update Manager</a>
                    <a href="deleteManager.jsp">Delete Manager</a>
                    <a href="showManager.jsp">Show Manager</a>
                </div>
            </div>

            <div class="content">
                <div class="card">Number of Patient: </div>
                <div class="card">Number of Doctor: </div>
                <div class="card">Number of Receptionist: </div>
                <div class="card">Number of Manager:  </div>
            </div>
        </div>
    </body>
</html>
