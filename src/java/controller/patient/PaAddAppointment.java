/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.patient;

import dal.PatientDAO;
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
import model.Patient;

/**
 *
 * @author ptson
 */
@WebServlet(name = "PaAddAppointment", urlPatterns = {"/paAddAppointment"})
public class PaAddAppointment extends HttpServlet {

    PatientDAO pdao = new PatientDAO();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Patient p = pdao.getAPatientByEmail("pat1@gmail.com");
        List<Doctor> doctorList = pdao.getListDoctor();
        String id_raw = request.getParameter("doctorId");

        Doctor selectedDoctor = null;
        if (id_raw != null) {
            int id = Integer.parseInt(id_raw);
            for (Doctor d : doctorList) {
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
