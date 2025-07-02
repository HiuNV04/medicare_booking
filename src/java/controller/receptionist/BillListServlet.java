/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.receptionist;
import dal.BillDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Bill;


/**
 *
 * @author ADDMIN
 */
@WebServlet(name = "BillListServlet", urlPatterns = {"/BillListServlet"})
public class BillListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int page = 1;
        int pageSize = 5;
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        int offset = (page - 1) * pageSize;

        BillDAO dao = new BillDAO();
        List<Bill> bills = dao.getBillsPage(offset, pageSize);
        int totalRecords = dao.countAllBills();
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

        request.setAttribute("bills", bills);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("baseUrl", "BillListServlet");
        request.setAttribute("queryString", "");

        request.getRequestDispatcher("/receptionist/list_bill.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
