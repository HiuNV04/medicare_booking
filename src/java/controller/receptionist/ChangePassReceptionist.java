/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.receptionist;

import dal.SonDAO;
 import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Receptionist;
import model.Receptionist1;

/**
 *
 * @author ptson
 */
@WebServlet(name = "ChangePassReceptionist", urlPatterns = {"/changePassReceptionist"})
public class ChangePassReceptionist extends HttpServlet {

    SonDAO rdao = new SonDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/receptionist/recepChangePass.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Receptionist r = (Receptionist) session.getAttribute("receptionist");
        String username = r.getUsername();
        String oldPass = request.getParameter("oPass");
        String newPass = request.getParameter("nPass");
        String cpw = request.getParameter("cpw");

        String valid = util.Exception.getPasswordFormatForReceptionist(r, oldPass, cpw, newPass);

        if (valid != null) {
            request.setAttribute("valid", valid);
            request.getRequestDispatcher("/receptionist/recepChangePass.jsp").forward(request, response);
        } else {
            Receptionist1 a = new Receptionist1(username);
            rdao.updatePass(username, newPass);
            response.sendRedirect("showReceptionist");
        }
    }
}
