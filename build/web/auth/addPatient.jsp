<%-- 
    Document   : addPatient
    Created on : May 22, 2025, 4:34:36 PM
    Author     : ptson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Patient</title>
        <style>
            .form-box {
                background: #fff;
                width: 600px;
                margin: 30px auto;
                border: 1px solid #e0e0e0;
                border-radius: 6px;
                box-shadow: 0 2px 8px rgba(0,0,0,0.05);
                padding: 30px 40px;
            }

            .form-header {
                font-size: 20px;
                font-weight: 600;
                color: #333;
                padding-bottom: 15px;
                border-bottom: 1px solid #e0e0e0;
                margin-bottom: 25px;
                text-align: center;
            }

            .form-group {
                margin-bottom: 20px;
            }

            .form-group label {
                display: block;
                font-weight: 500;
                margin-bottom: 6px;
                color: #333;
            }

            .form-group input {
                width: 100%;
                padding: 10px 12px;
                border: 1px solid #ccc;
                border-radius: 4px;
                font-size: 15px;
                box-sizing: border-box;
            }

            .form-actions {
                text-align: center;
                margin-top: 20px;
            }

            .btn-primary, .btn-secondary {
                padding: 10px 20px;
                font-weight: bold;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                margin: 0 5px;
                font-size: 14px;
            }

            .btn-primary {
                background-color: #007bff;
                color: white;
            }

            .btn-primary:hover {
                background-color: #0056b3;
            }

            .btn-secondary {
                background-color: #6c757d;
                color: white;
            }

            .btn-secondary:hover {
                background-color: #5a6268;
            }

        </style>
    </head>
    <body>
        <div class="form-box">
            <div class="form-header">Add User</div>
            <form action="addUserServlet" method="post">
                <div class="form-group">
                    <label>Username:</label>
                    <input type="text" name="username" placeholder="Enter name" required />
                </div>
                <div class="form-group">
                    <label>Email Address:</label>
                    <input type="email" name="email" placeholder="Enter email" required />
                </div>
                <div class="form-group">
                    <label>Password:</label>
                    <input type="password" name="password" placeholder="********" required />
                </div>
                <div class="form-group">
                    <label>Role ID:</label>
                    <input type="text" name="role" placeholder="Enter role" />
                </div>
                <div class="form-group">
                    <label>Phone:</label>
                    <input type="text" name="phone" placeholder="Enter phone" />
                </div>
                <div class="form-actions">
                    <button type="submit" class="btn-primary">Add</button>
                    <button type="reset" class="btn-secondary">Reset</button>
                </div>
            </form>
        </div>
    </body>
</html>
