<%-- 
    Document   : home_receptionist
    Created on : Jun 26, 2025, 8:47:45 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/check/check_receptionist.jsp" %>
<%@ include file="/check/check_login.jsp" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trang lễ tân</title>
        <jsp:include page="/receptionist/head/head.jsp" />
    </head>
    <body>
        <jsp:include page="/receptionist/topbar/topbar.jsp" />
        <jsp:include page="/receptionist/navbar/navbar.jsp" />



        <jsp:include page="/receptionist/footer/footer.jsp" />
        <jsp:include page="/receptionist/script/script.jsp" />
    </body>
</html>
