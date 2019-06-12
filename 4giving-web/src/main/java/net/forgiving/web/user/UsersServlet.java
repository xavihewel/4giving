/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.web.user;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.forgiving.user.UserLocal;

/**
 *
 * @author gabalca
 */
@WebServlet(name = "UsersServlet", urlPatterns = {"/users"})
public class UsersServlet extends HttpServlet {

    //@EJB
    private UserLocal usersManager;

    @Override
    public void init() throws ServletException {
        super.init(); 
        
        try{
            InitialContext ic=new InitialContext();
            usersManager = (UserLocal)ic.lookup("java:app/"
                    + "net.forgiving-4giving-ejb-1.0-SNAPSHOT/"
                    + "UsersManager!net.forgiving.user.UserLocal");
            /*
            usersManager = (UserLocal)ic.lookup("java:global/4giving-ear/"
                    + "net.forgiving-4giving-ejb-1.0-SNAPSHOT/"
                    + "UsersManager!net.forgiving.user.UserLocal");
            */
            System.out.println("Obtingut UserLocal");
        }catch(NamingException ex){
            System.out.println("ERROR obtenint UserLocal");
            ex.printStackTrace();
            throw new UnavailableException("No es pot incialitzar, em falta el bean ");
        }
        
    }
    
    
    
    
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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UsersServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UsersServlet at " + request.getContextPath() + "</h1>");
            if(request.getParameter("id")!=null){
                
                out.println("L'usuari és: "
                        +usersManager.getUser(
                                Long.parseLong(request.getParameter("id"))));
            }else{
                out.println("Tots els usuaris són: ");
                usersManager.getAllUsers()
                        .forEach(us -> out.println(us+"<br/>"));
            }
            out.println("</body>");
            out.println("</html>");
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
