
package servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class NewUser extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session=request.getSession();
        try{
            String name=request.getParameter("name");
            String email=request.getParameter("email");
            String pass=request.getParameter("pass");
            String phone=request.getParameter("phone");
            String address=request.getParameter("address");
            db.DbConnect db= new db.DbConnect();
            String m=db.newUser(name,email,pass,phone,address);
            if(m.equalsIgnoreCase("Success")){
                 HashMap userDetails=new HashMap();
                 userDetails.put("name", name);
                 userDetails.put("email", email);
                 userDetails.put("phone", phone);
                 userDetails.put("address", address);
                session.setAttribute("userDetails",userDetails);
                response.sendRedirect("profile.jsp");
            }else if(m.equalsIgnoreCase("Already")){
                session.setAttribute("msg", "Email ID Already Exist!");
                response.sendRedirect("index.jsp");
            }else {
                session.setAttribute("msg", "Registration Failed!");
                response.sendRedirect("index.jsp");
            
            }
     
        } catch (Exception ex) {
            session.setAttribute("msg", "Registration Failed: "+ex);
            response.sendRedirect("index.jsp");
        }
        
    }
      
}
