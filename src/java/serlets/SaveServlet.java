/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Emp;
import model.EmpDao;

/**
 *
 * @author Ibrahim Ayad
 */
public class SaveServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //request.getRequestDispatcher("index.html").include(request, response);
            String msg;
            String name = request.getParameter("name");
            String pass = request.getParameter("pass");
            String emial = request.getParameter("email");
            String country = request.getParameter("country");
            if (name == null || pass == null ||emial ==null || name.equals("") || pass.equals("") || emial.equals("")){
                msg = "Enter All Fields";
                 out.println("<h2 style='color: red' >" +  msg +  "</h2>");
                request.getRequestDispatcher("index.html").include(request, response);
            } else{
            Emp e = new Emp();
            e.setName(name);
            e.setPass(pass);
            e.setEmial(emial);
            e.setCountry(country);
            int status = EmpDao.save(e);
            if( status > 0){
                out.println("<h1> Save Sucsess </h1>");
                request.getRequestDispatcher("index.html").include(request, response);
            } else{
                out.println("<h1> unable to save </h1>");
				request.getRequestDispatcher("index.html").include(request, response);
            }
            out.close();
        }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
