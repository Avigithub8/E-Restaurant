
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateTable extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String n=(String)session.getAttribute("aname");
    if(n!=null)  
    {
        try{

    int nt=Integer.parseInt(request.getParameter("nt"));
    
    db.DbConnect db=new db.DbConnect();
   String s= db.updateTotalTable(nt);
   if(s.equalsIgnoreCase("Success")){
            session.setAttribute("msg", "Table Successfully Updated");
            response.sendRedirect("abooktable.jsp");
   }else{
      session.setAttribute("msg", "Table Updation Failed!!!");
            response.sendRedirect("abooktable.jsp");
   }
        }catch(Exception ex){
            
        }
    }else{ 
        session.setAttribute("msg","plz Login First!!!!!");
        response.sendRedirect("index.jsp");
    }  
    }
}
