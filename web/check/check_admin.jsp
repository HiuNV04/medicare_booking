<%
    if (session == null) {
        response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
        return;
    }

    model.Staff staff = (model.Staff) session.getAttribute("staff");

    if (staff == null || !"admin".equalsIgnoreCase(staff.getRole())) {
        response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
        return;
    }
%>

