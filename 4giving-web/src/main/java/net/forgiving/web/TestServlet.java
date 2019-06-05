/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.forgiving.TestBean;

/**
 *
 * @author gabalca
 */
@WebServlet(name = "test", urlPatterns ="/test")
public class TestServlet extends HttpServlet {
    
    @EJB
    private TestBean testBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw
                = resp.getWriter();
        pw.write("test: "+testBean.getTest());
    }
    
}
