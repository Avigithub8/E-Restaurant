package servlets;



import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {

    
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
        try{
            String userid=request.getParameter("userid");
            String pass=request.getParameter("pass");
            db.DbConnect db=new db.DbConnect();
            java.util.HashMap userDetails=db.checkLogin(userid, pass);
            if(userDetails!=null){
                session.setAttribute("userDetails", userDetails);
                response.sendRedirect("profile.jsp");
            }else{
                session.setAttribute("msg", "Invalid Id & Password!");
                response.sendRedirect("index.jsp");
            }
        } catch (Exception ex) {
            session.setAttribute("msg", "Login Failed: "+ex);
            response.sendRedirect("index.jsp");
        }
        
    }
}
