/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.admin;

import dal.UserAccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.UserAccount;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="ManageAccountController", urlPatterns={"/manageAccount"})
public class ManageAccountController extends HttpServlet {
   

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        UserAccountDAO dao = new UserAccountDAO();
        List<UserAccount> lst = dao.getAllAccounts();
        request.setAttribute("lstAccount", lst);
        request.getRequestDispatcher("/admin/manageAccount.jsp").forward(request, response);
        
        
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
     }

}