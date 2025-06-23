<div class="sidebar">
    <c:set var="i" value="${sessionScope.patient}"/>
    <a style="margin-bottom: 12%;" href="${i.getImg()}">
        <img src="${i.getImg()}" alt="Avatar" style="width:100%;">
    </a>
    <a style="margin-bottom: 12%; font-size: 121%;" href="/MediCare_Booking/patient/patient.jsp">Home</a>

    <a style="margin-bottom: 12%; font-size: 121%;" href="${pageContext.request.contextPath}/paAddAppointment">Add Appointment</a>
    
                <a style="margin-bottom: 12%; font-size: 121%;" href="${pageContext.request.contextPath}/paListAppointment">List Appointment</a>   

    <a style="margin-bottom: 12%;  font-size: 121%;" href="#">History Feedback</a>

    <a style="margin-bottom: 12%; font-size: 121%;" href="${pageContext.request.contextPath}/showPatient">Patient Profile</a>

    <a style="margin-bottom: 12%; font-size: 121%;" href="${pageContext.request.contextPath}/changePassPatient">Change Password</a>
</div>