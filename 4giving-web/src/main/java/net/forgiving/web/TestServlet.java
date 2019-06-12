/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.web;

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
import javax.servlet.http.HttpSession;
import net.forgiving.test.StatefulTestBean;
import net.forgiving.test.TestBean;
import net.forgiving.user.UserLocal;

/**
 *
 * @author gabalca
 */
@WebServlet(name = "test", urlPatterns ="/test")
public class TestServlet extends HttpServlet {
    
    @EJB(lookup = "java:app",name = "testBean") //"java:comp/testBean"
    private StatefulTestBean testBean;
    
    private ShoppingCartBean cart;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw
                = resp.getWriter();
        pw.write("test: "+testBean.getTest());
        
        testBean.setTest(req.getParameter("test"));
        
        if(req.getParameter("fi")!=null){
            testBean.finish();
            //aqui el EJB ja no es pot fer servir.
        }
        
        ShoppingCartBean cart=getCurrentCart(req.getSession());
        
    }

    private ShoppingCartBean getCurrentCart(HttpSession session) throws UnavailableException {
        ShoppingCartBean current = 
                (ShoppingCartBean)session.getAttribute("SHOPPING_CART");
        if(current==null){
            try{
                InitialContext ic=new InitialContext();
                current = (ShoppingCartBean)ic.lookup("java:module/"
                        + "ShoppingCartBean");

                System.out.println("Obtingut Carro");
            }catch(NamingException ex){
                System.out.println("ERROR obtenint Carro");
                ex.printStackTrace();
                throw new UnavailableException("No es pot incialitzar, em falta el bean ");
            }
        }
        return current;
    }
    
}
