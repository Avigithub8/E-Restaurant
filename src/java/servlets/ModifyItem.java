
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ModifyItem extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     HttpSession session =request.getSession();
        try {
            String iname=request.getParameter("iname");
            String itemName=request.getParameter("itemName");
            int price=Integer.parseInt(request.getParameter("price"));
            String category=request.getParameter("category");
            db.DbConnect db= new db.DbConnect();
            String s=db.updateItem(iname,itemName, price, category);
            if(s.equals("success")){
                session.setAttribute("msg","Item Updation Success");
                response.sendRedirect("aitem.jsp");
                
            }else{
                session.setAttribute("msg"," Sorry Item Updation Failed!!");
                response.sendRedirect("aitem.jsp");
            }
           
        } catch (Exception ex) {
                session.setAttribute("msg","Exception"+ex);
                response.sendRedirect("aitem.jsp");
        }
 
   
      
    }
}
