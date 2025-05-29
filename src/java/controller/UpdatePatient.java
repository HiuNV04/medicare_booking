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
@WebServlet(name = "UpdatePatient", urlPatterns = {"/updatePatient"})
public class UpdatePatient extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/auth/patientUpdate.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id_raw = request.getParameter("id");
        String fullname = request.getParameter("fullname");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String identity = request.getParameter("identity");
        String insurance = request.getParameter("insurance");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String job = request.getParameter("job");
        String img = request.getParameter("img");
        PatientDAO pdao = new PatientDAO();
        Patient pUpdate = new Patient(fullname, dob, gender, identity, insurance, phone, address, job, img);
        int id;
        try {
            id = Integer.parseInt(id_raw);
            pdao.updatePatient(pUpdate, id);
        } catch (Exception e) {
        }
        response.sendRedirect("showPatient");
    }
}
