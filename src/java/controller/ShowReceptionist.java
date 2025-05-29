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
import model.Account;
import model.Receptionist;

/**
 *
 * @author ptson
 */
@WebServlet(name="ShowReceptionist", urlPatterns={"/showReceptionist"})
public class ShowReceptionist extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
//        String username = (String) request.getSession().getAttribute("username");
//        Receptionist p = rdao.getAReceptionist(new Account(username));
        ReceptionistDAO rdao = new ReceptionistDAO();
        Receptionist r = rdao.getAReceptionist(new Account("receptionist1"));
        HttpSession session = request.getSession();
        int totalPatients = rdao.getTotalPatients();
        session.setAttribute("receptionist", r);
        session.setAttribute("totalPatients", totalPatients);
        request.getRequestDispatcher("/auth/receptionistProfile.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }
}
