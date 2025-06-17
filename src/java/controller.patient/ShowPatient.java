package controller;

import dal.PatientDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Patient;

@WebServlet(name = "ShowPatient", urlPatterns = {"/showPatient"})
public class ShowPatient extends HttpServlet {

    PatientDAO pdao = new PatientDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        String username = (String) request.getSession().getAttribute("username");
//        Patient p = pdao.getAPatient(new Account(username));
        Patient p = pdao.getAPatientByEmail("pat1@gmail.com");
        HttpSession session = request.getSession();
        session.setAttribute("patient", p);
        session.setAttribute("id", String.valueOf(p.getId()));
        session.setAttribute("username", p.getUsername());
        request.getRequestDispatcher("/patient/patientProfile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        Patient p = (Patient) session.getAttribute("patient");
// Dùng thông tin:
//        int id = p.getId();

        String id_raw = (String) request.getSession().getAttribute("id");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String identity = request.getParameter("identity");
        String insurance = request.getParameter("insurance");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String img = request.getParameter("image");
        Patient pUpdate = new Patient(gender, dob, identity, insurance, phone, address, img);
        int id;
        try {
            id = Integer.parseInt(id_raw);
            pdao.updatePatient(pUpdate, id);
        } catch (Exception e) {
        }
        response.sendRedirect("showPatient");
    }
}
