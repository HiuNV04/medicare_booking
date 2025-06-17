/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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

@WebServlet(name = "ReAddPatient", urlPatterns = {"/reAddPatient"})
public class ReAddPatient extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/receptionist/reAddPatient.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String role = request.getParameter("role");
        String fullname = request.getParameter("fullname");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String identity = request.getParameter("identity");
        String insurance = request.getParameter("insurance");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        request.setAttribute("fullname", fullname);
        request.setAttribute("dob", dob);
        request.setAttribute("gender", gender);
        request.setAttribute("identity", identity);
        request.setAttribute("insurance", insurance);
        request.setAttribute("phone", phone);
        request.setAttribute("address", address);

        String error = util.Exception.duplicateCodeError(identity, insurance, phone);
        if (error != null) {
            request.setAttribute("error", error);
            request.getRequestDispatcher("/receptionist/reAddPatient.jsp").forward(request, response);
        } else {
            PatientDAO pdao = new PatientDAO();
            Patient p = new Patient(role, fullname, dob, gender, identity, insurance, phone, address);
            pdao.insertPatient(p);
            int id = pdao.getIdFromIdentity(identity);
            session.setAttribute("id", id);
            response.sendRedirect("reAddAppointment");
        }
    }
}
