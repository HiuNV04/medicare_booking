/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.patient;

import dal.PatientDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Appointment;
import model.Patient;

/**
 *
 * @author ptson
 */
@WebServlet(name = "PaListAppointment", urlPatterns = {"/paListAppointment"})
public class PaListAppointment extends HttpServlet {

    PatientDAO pdao = new PatientDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Patient p = pdao.getAPatientByEmail("pat1@gmail.com");
        List<Appointment> list = pdao.getListAppointment(p.getId());
        request.setAttribute("list", list);
        request.getRequestDispatcher("/patient/paListAppointment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
