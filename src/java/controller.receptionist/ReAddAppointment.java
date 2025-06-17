package controller;

import dal.PatientDAO;
import dal.ReceptionistDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Doctor;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "ReAddAppointment", urlPatterns = {"/reAddAppointment"})
public class ReAddAppointment extends HttpServlet {

    ReceptionistDAO rdao = new ReceptionistDAO();
    PatientDAO pdao = new PatientDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Doctor> doctor = rdao.getDoctorAndSpecialization();

        String id = (String) session.getAttribute("id");

        String name = "";
        request.setAttribute("doctor", doctor);

        if (id != null) { //ktra trường hợp khi chưa thêm 1 bệnh nhân
            name = pdao.getNameFromId(Integer.parseInt(id));
            request.setAttribute("name", name);
        } else {
            request.setAttribute("name", name);
        }

        request.getRequestDispatcher("/receptionist/reAddAppointment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
