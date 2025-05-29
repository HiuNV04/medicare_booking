/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.PatientDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Patient;

/**
 *
 * @author ptson
 */
@WebServlet(name = "ShowPatient", urlPatterns = {"/showPatient"})
public class ShowPatient extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PatientDAO pdao = new PatientDAO();
//        String username = (String) request.getSession().getAttribute("username");
//        Patient p = pdao.getAPatient(new Account(username));
        Patient p = pdao.getAPatient(new Account("patient2"));
        HttpSession session = request.getSession();
        session.setAttribute("user", p);
        request.getRequestDispatcher("/auth/patientProfile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
