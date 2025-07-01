<%
    if (session == null) {
        response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
        return;
    }

    model_doctor.Doctor doctor = (model_doctor.Doctor) session.getAttribute("doctor");
    if (doctor == null) {
        response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
        return;
    }
%>
