<%
    // Ch?n cache
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
    response.setDateHeader("Expires", 0); // Proxies

    // Ki?m tra t?t c? vai trò
    if (
        session.getAttribute("admin") == null &&
        session.getAttribute("doctor") == null &&
        session.getAttribute("patient") == null &&
        session.getAttribute("receptionist") == null &&
        session.getAttribute("manager") == null
    ) {
        response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
        return;
    }
%>
