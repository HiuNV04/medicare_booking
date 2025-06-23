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

@WebServlet(name = "ChangePassPatient", urlPatterns = {"/changePassPatient"})
public class ChangePassPatient extends HttpServlet {

    PatientDAO pdao = new PatientDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/patient/patientChangePass.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String oldPass = request.getParameter("oPass");
        String newPass = request.getParameter("nPass");
        String cpw = request.getParameter("cpw");

        String username = (String) session.getAttribute("username");
        Patient p = pdao.getAPatientByUsername(username);

        String msg = util.Exception.getPasswordFormatForPatient(p, oldPass, cpw, newPass);

        if (msg != null) {
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/patient/patientChangePass.jsp").forward(request, response);
        } else {
            pdao.updatePass(username, newPass);
            session.removeAttribute("msg");
            response.sendRedirect("showPatient");
        }
    }
}
