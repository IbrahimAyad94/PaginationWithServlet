/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
public class ViewServlet extends HttpServlet {

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
         out.println("<a href='index.html'>Add New Employee</a>");  
         out.println("<h1>Employees List</h1>"); 
         String spage = request.getParameter("page");
         int page = Integer.parseInt(spage);
          int total=3;  
        if(page == 1){}  
        else{  
            page =page - 1;  
            page =page * total+1;  
        }  
        List<Emp> list=EmpDao.getRecords(page, total); 
           // List<Emp> list=EmpDao.getAllEmployees(); 
            out.print("<table border='1' width='100%'>"); 
            out.print("<tr><th>Id</th><th>Name</th><th>Password</th><th>Email</th><th>Country</th> " 
                + "<th>Edit</th><th>Delete</th></tr>");  
            
            for(Emp e:list){  
         out.print("<tr><td>"+e.getId()+"</td><td>"+e.getName()+"</td><td>"+e.getPass()+"</td>" +  
                 "<td>"+e.getEmial()+"</td><td>"+e.getCountry()+"</td><td><a href='EditServlet?id="+e.getId()+"'>edit</a></td> " 
                + "<td><a href='DeleteServlet?id="+e.getId()+"'>delete</a></td></tr>");  
        }  
            
        out.print("</table>"); 
        out.print("<a href='ViewServlet?page=1'>1</a> ");  
        out.print("<a href='ViewServlet?page=2'>2</a> ");  
        out.print("<a href='ViewServlet?page=3'>3</a> ");  
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
