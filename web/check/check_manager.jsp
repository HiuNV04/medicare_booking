<%
    if (session == null) {
        response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
        return;
    }

    model_doctor.Staff staff = (model_doctor.Staff) session.getAttribute("staff");
    if (staff == null || !"manager".equalsIgnoreCase(staff.getRole())) {
        response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
        return;
    }
%>
