package servlets;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ALogin extends HttpServlet {

    
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
        try{
            String aid=request.getParameter("aid");
            String pass=request.getParameter("pass");
            db.DbConnect db=new db.DbConnect();
          String aname =db.checkALogin(aid, pass);
          
            if(aname!=null){
                session.setAttribute("aname", aname);
                response.sendRedirect("adminhome.jsp");
            }else{
                session.setAttribute("msg", "Invalid Id & Password!");
                response.sendRedirect("admin.jsp");
            }
        } catch (Exception ex) {
            session.setAttribute("msg", "Login Failed: "+ex);
            response.sendRedirect("admin.jsp");
        }
        
    }
}
