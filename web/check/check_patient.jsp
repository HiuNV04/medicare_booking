<%
    if (session == null) {
        response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
        return;
    }

    model.Patient patient = (model.Patient) session.getAttribute("patient");
    if (patient == null) {
        response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
        return;
    }
%>
