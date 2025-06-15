///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//
//package controller;
//
//import dal.UserDAO;
//import dal.DoctorDAO;
//import model.User;
//import model.Doctor;
//import java.io.IOException;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import java.io.PrintWriter;
//
///**
// *
// * @author ADMIN
// */
//@WebServlet(name="LoginController", urlPatterns={"/login"})
//public class LoginController extends HttpServlet {
//   
//    /** 
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet LoginController</title>");  
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet LoginController at " + request.getContextPath () + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    } 
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /** 
//     * Handles the HTTP <code>GET</code> method.
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
//    } 
//
//    /** 
//     * Handles the HTTP <code>POST</code> method.
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        String loginId = request.getParameter("loginId");
//        String password = request.getParameter("password");
//        HttpSession session = request.getSession();
//        UserDAO userDAO = new UserDAO();
//        DoctorDAO doctorDAO = new DoctorDAO();
//        // TODO: Thêm PatientDAO nếu cần
//        User user = userDAO.getUserByUsernameOrEmailAndPassword(loginId, password);
//        if (user != null) {
//            session.setAttribute("user", user);
//            response.sendRedirect("manager/home");
//            return;
//        }
//        Doctor doctor = doctorDAO.getDoctorByUsernameOrEmailAndPassword(loginId, password);
//        if (doctor != null) {
//            session.setAttribute("doctor", doctor);
//            // response.sendRedirect("doctor/home");
//            response.sendRedirect("manager/home"); // demo
//            return;
//        }
//        // TODO: Kiểm tra patient nếu có
//        request.setAttribute("error", "Sai thông tin đăng nhập");
//        request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
//    }
//
//    /** 
//     * Returns a short description of the servlet.
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
