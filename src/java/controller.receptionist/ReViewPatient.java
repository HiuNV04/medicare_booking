/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.PatientDAO;
import dal.ReceptionistDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Patient;

/**
 *
 * @author ptson
 */
@WebServlet(name = "ReViewPatient", urlPatterns = {"/reViewPatient"})
public class ReViewPatient extends HttpServlet {

    ReceptionistDAO rdao = new ReceptionistDAO();
    PatientDAO pdao = new PatientDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String field = request.getParameter("field");
        String order = request.getParameter("order");

        // Gửi lại các tham số sort để hiển thị selected option trong JSP
        request.setAttribute("field", field);
        request.setAttribute("order", order);

        try {
            String indexPage = request.getParameter("index");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            int count = pdao.getTotalPatient();
            int endPage = count / 8;
            if (count % 8 != 0) {
                endPage++;
            }
            List<Patient> list;
            if ((field != null && order != null)
                    && (field.equals("id") || field.equals("full_name"))
                    && (order.equals("asc") || order.equals("desc"))) {
                list = pdao.getSortedPatientsWithPaging(field, order, index);
            } else {
                list = pdao.pagingPatient(index);
            }
            request.setAttribute("endPage", endPage);
            request.setAttribute("listA", list);

            request.getRequestDispatcher("/receptionist/reViewPatient.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String identity = request.getParameter("identity");

        if (identity != null && !identity.trim().isEmpty()) {
            // Nếu search theo identity, chỉ trả về đúng 1 bệnh nhân (nếu có)
            Patient p = rdao.getAPatientByIdentity(identity.trim());
            List<Patient> list = new ArrayList<>();
            if (p != null) {
                list.add(p);
            } else {
                request.setAttribute("error", "Không tìm thấy bệnh nhân với số CMND/CCCD: " + identity);
            }
            request.setAttribute("endPage", 1);
            request.setAttribute("listA", list);
            request.getRequestDispatcher("/receptionist/reViewPatient.jsp").forward(request, response);
        }
    }
}
