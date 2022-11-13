/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author INTEL
 */
public class Logout extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
        HashMap userDetails=(HashMap)session.getAttribute("userDetails");
        String aname= (String)session.getAttribute("aname");
        if(userDetails!=null){
            session.invalidate();
            response.sendRedirect("index.jsp");
            }else if(aname!=null){
            session.invalidate();
            response.sendRedirect("index.jsp");
           }else{
            session.setAttribute("msg", "Plz login First!");
            response.sendRedirect("index.jsp");
        }
    }
}