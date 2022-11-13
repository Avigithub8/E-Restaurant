
package servlets;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class GetPhoto extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String iname=request.getParameter("iname");
            db.DbConnect db=new db.DbConnect();
            byte[] b=db.getPhoto(iname);
            if(b==null){
//               FileInputStream fin=new FileInputStream("D:\\netbeans project\\ERestaurantN\\src\\java\\jar\\default.png");
//               b=new byte[20000];
//               fin.read(b);
            }
            response.getOutputStream().write(b);
        } catch (Exception ex) {
            ex.printStackTrace();
//            FileInputStream fin=new FileInputStream("D:\\netbeans project\\ERestaurantN\\src\\java\\jar\\default.png");
//            byte []b=new byte[20000];
//            fin.read(b);
//            response.getOutputStream().write(b);
        }
    }

}
