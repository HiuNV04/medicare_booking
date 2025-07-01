<%
    if (session == null) {
        response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
        return;
    }

    model_doctor.Patient patient = (model_doctor.Patient) session.getAttribute("patient");
    if (patient == null) {
        response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
        return;
    }
%>
