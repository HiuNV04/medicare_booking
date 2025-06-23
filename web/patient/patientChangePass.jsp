
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Patient Change Pass</title>
        <link href="${pageContext.request.contextPath}/css/style2.css" rel="stylesheet">
    </head>
    <body>
        <div class="header">
            <h2>Profile</h2>
        </div>

        <div class="container">
            <%@ include file="sidebar.jsp" %>
            <div>
                <c:set var="i" value="${sessionScope.patient}"/>
                <form action="changePassPatient" method="post" style="margin-right: -108%;">
                    <div class="form-group">
                        <label>Username: </label>
                        <input type="text" class="form-control" name="username" value="${i.getUsername()}" readonly=""/>
                    </div>
                    <div class="form-group">
                        <label>Old Password: </label>
                        <input type="password" class="form-control" name="oPass" value="" required=""/>
                    </div>
                    <div class="form-group">
                        <label>New Password: </label>
                        <input type="password" class="form-control" name="nPass" value="" required=""/>
                    </div>
                    <div class="form-group">
                        <label>Confirm New Password: </label>
                        <input type="password" class="form-control" name="cpw" value="" required=""/>
                    </div>
                    <div style="margin-left: 39%;">
                        <input type="submit" value="SAVE">
                    </div>
                    <h3 style="color: red">${requestScope.msg}</h3>      
                </form>
            </div>
        </div>
    </body>
</html>
