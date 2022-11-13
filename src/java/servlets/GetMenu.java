
package servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetMenu extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session= request.getSession();
        HashMap userDetails=(HashMap)session.getAttribute("userDetails");
        try{ 
        if(userDetails!=null){
        String category=request.getParameter("category");
        //  session.setAttribute("msg",category);
        if(category!=null){
           db.DbConnect db=new db.DbConnect();
           HashSet allItemDetails=db.getItembyCateg(category);
           if(allItemDetails!=null){
                session.setAttribute("allItemDetails",allItemDetails);
                response.sendRedirect("menu.jsp");
                }
            else{
            session.setAttribute("msg","No Item Available");
            response.sendRedirect("menu.jsp");
            }
         }else{
            session.setAttribute("msg","Please Select Category");
            response.sendRedirect("menu.jsp");
       }
        }else{
            session.setAttribute("msg","Plz Login first");
        response.sendRedirect("index.jsp");
        }
    }catch (Exception ex) {
        session.setAttribute("msg","Exception"+ex);
        response.sendRedirect("menu.jsp");   
             
    }
    }
}


