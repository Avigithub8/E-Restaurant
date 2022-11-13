
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SearchItem extends HttpServlet {

  
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     HttpSession session=request.getSession();
        try {
            String itemName= request.getParameter("iname");
            db.DbConnect db= new db.DbConnect();
             HashMap itemDetails =db.searchItem(itemName);
             
            if(itemDetails!=null){
                session.setAttribute("itemDetails",itemDetails);
                response.sendRedirect("aitem.jsp");
                }
                else{
                session.setAttribute("msg","Item Not Found");
                response.sendRedirect("aitem.jsp");
            }
            
        } catch (Exception ex) {
                session.setAttribute("msg","Exception:"+ex);
                response.sendRedirect("aitem.jsp");
        }
        
        
        
        
    }

}
