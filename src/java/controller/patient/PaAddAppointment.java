/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.patient;

import dal.PatientDAO;
import dal.SonDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.List;
import model.Doctor;
import model.Doctor1;
import model.Patient;
import model.Patient1;

/**
 *
 * @author ptson
 */
@WebServlet(name = "PaAddAppointment", urlPatterns = {"/paAddAppointment"})
public class PaAddAppointment extends HttpServlet {

    SonDAO pdao = new SonDAO();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Patient1 p = pdao.getAPatientByEmail("pat1@gmail.com");
        List<Doctor1> doctorList = pdao.getListDoctor();
        String id_raw = request.getParameter("doctorId");

        Doctor1 selectedDoctor = null;
        if (id_raw != null) {
            int id = Integer.parseInt(id_raw);
            for (Doctor1 d : doctorList) {
                if (d.getId() == id) {
                    selectedDoctor = d;
                    break;
                }
            }
        } else if (!doctorList.isEmpty()) {
            selectedDoctor = doctorList.get(0); // chọn bác sĩ đầu tiên đúng chuẩn
        }
        request.setAttribute("doctor", doctorList);
        request.setAttribute("selectedDoctor", selectedDoctor);
        request.setAttribute("p", p);
        request.getRequestDispatcher("/patient/paAddAppointment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
