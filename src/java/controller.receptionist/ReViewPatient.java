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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Patient> list = rdao.getListPatient();
        HttpSession session = request.getSession();
        session.setAttribute("list", list);
        request.getRequestDispatcher("/receptionist/reViewPatient.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String identity = request.getParameter("identity");
        String field = request.getParameter("field");
        String order = request.getParameter("order");

        // Gửi lại các tham số sort để hiển thị selected option trong JSP
        request.setAttribute("field", field);
        request.setAttribute("order", order);

        List<Patient> list = new ArrayList<>();

        if (identity != null && !identity.trim().isEmpty()) {
            Patient p = rdao.getAPatientByIdentity(identity.trim());
            if (p != null) {
                list.add(p);
            } else {
                request.setAttribute("error", "Không tìm thấy bệnh nhân với số CMND/CCCD: " + identity);
            }
        } else {
            if ((field != null && order != null)
                    && (field.equals("id") || field.equals("name"))
                    && (order.equals("asc") || order.equals("desc"))) {
                list = rdao.getListPatientSorted(field, order);
            } else {
                list = rdao.getListPatient(); // fallback nếu không có gì
            }
        }
        session.setAttribute("list", list);
        request.getRequestDispatcher("/receptionist/reViewPatient.jsp").forward(request, response);
    }
}
