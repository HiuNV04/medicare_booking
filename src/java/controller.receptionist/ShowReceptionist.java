/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ReceptionistDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Receptionist;

/**
 *
 * @author ptson
 */
@WebServlet(name = "ShowReceptionist", urlPatterns = {"/showReceptionist"})
public class ShowReceptionist extends HttpServlet {

    ReceptionistDAO rdao = new ReceptionistDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        String username = (String) request.getSession().getAttribute("username");
//        Receptionist p = rdao.getAReceptionist(new Account(username));
        Receptionist r = rdao.getAReceptionistByEmail("receptionist1@medicare.com");
        HttpSession session = request.getSession();
        int totalPatients = rdao.getTotalPatients();
        session.setAttribute("receptionist", r);
        session.setAttribute("id", String.valueOf(r.getId()));
        session.setAttribute("totalPatients", totalPatients);
        request.getRequestDispatcher("/receptionist/receptionistProfile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id_raw = (String) request.getSession().getAttribute("id");
        String fullname = request.getParameter("fullname");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String img = request.getParameter("image");
        Receptionist rUpdate = new Receptionist(fullname, gender, phone, address, img);
        int id;
        try {
            id = Integer.parseInt(id_raw);
            rdao.update(rUpdate, id);
        } catch (Exception e) {
        }
        response.sendRedirect("showReceptionist");
    }
}
