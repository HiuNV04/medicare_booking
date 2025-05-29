/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.ReceptionistDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Patient;

/**
 *
 * @author ptson
 */
@WebServlet(name="ReViewPatient", urlPatterns={"/reViewPatient"})
public class ReViewPatient extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        ReceptionistDAO rdao = new ReceptionistDAO();
        List<Patient> list = rdao.getListPatient();
        HttpSession session = request.getSession();
        session.setAttribute("list", list);
        request.getRequestDispatcher("/auth/reViewPatient.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        ReceptionistDAO rdao = new ReceptionistDAO();
        List<Patient> list = rdao.getListPatient();
        HttpSession session = request.getSession();
        session.setAttribute("list", list);
        request.getRequestDispatcher("/auth/reViewPatient.jsp").forward(request, response);
    }
}
