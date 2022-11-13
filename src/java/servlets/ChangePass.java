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
public class ChangePass extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       HttpSession session =request.getSession();
        try{
        
        HashMap userDetails =(HashMap)session.getAttribute("userDetails");
        String o=request.getParameter("opass");
        String n=request.getParameter("npass");
        String c=request.getParameter("cpass");
        if(n.equals(c))
        {
            db.DbConnect db=new db.DbConnect();
                 String s=db.changePass(n, (String)userDetails.get("email"), o);
                 if(s.equalsIgnoreCase("success")){
                            session.setAttribute("msg","Password Updated!");
                            response.sendRedirect("profile.jsp");
                    }else{
                            session.setAttribute("msg","Password Updation Failed!");
                            response.sendRedirect("changepass.jsp");
                    }
            
        }else{
            session.setAttribute("msg","Confrim Password is not match!!! ");
            response.sendRedirect("changepass.jsp");
        }
    
       }catch(Exception ex){
         session.setAttribute("msg","Exception :"+ex);
            response.sendRedirect("changepass.jsp");

        }
    }
}
