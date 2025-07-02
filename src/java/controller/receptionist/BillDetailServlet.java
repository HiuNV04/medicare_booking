package controller.receptionist;

import dal.BillDAO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Bill;

@WebServlet(name = "BillDetailServlet", urlPatterns = {"/BillDetailServlet"})
public class BillDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int billId = Integer.parseInt(request.getParameter("id"));

        BillDAO dao = new BillDAO();
        List<Bill> billDetails = dao.getBillDetailById(billId);

        if (billDetails == null || billDetails.isEmpty()) {
            response.sendRedirect("error.jsp"); // hoặc forward đến trang thông báo lỗi
            return;
        }

        Bill billInfo = billDetails.get(0); // lấy thông tin chung như tên bệnh nhân, thời gian

        request.setAttribute("bill", billInfo);
        request.setAttribute("billDetails", billDetails);

        request.getRequestDispatcher("/receptionist/detail_bill.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


}
