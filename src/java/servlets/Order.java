
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Order extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
            HashMap userDetails =(HashMap) session.getAttribute("userDetails");
            if(userDetails!=null){
       try{
            String address=request.getParameter("address");
           
            String email=(String)userDetails.get("email");
              String name=(String)userDetails.get("name");
              java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
            String cd=dateFormat.format(new java.util.Date());
            
            db.DbConnect db = new db.DbConnect();
           int maxoid=db.getMaxOrderID();
          
           HashMap cartItem= db.getCart((String)userDetails.get("email"));
           if(cartItem!=null){
               float total= (float) cartItem.get("total");
               String item=(String) cartItem.get("item");
               String s=db.insertOrder(maxoid, email, item, total, cd, address);
              
               if(s.equalsIgnoreCase("success")){
               db.deleteCart(email);
              
                String msg="Order Placed Successfull. Your Order ID: "+maxoid;
               final String subject=msg;
               final String body="Dear "+name+", Your Order Successfullly placed. \n Your Order Id: "+maxoid+
                "\n Thank You!!";
               final String SEmail="testmailid799@gmail.com";
               final String SPass="test@1234";
               try
             {
            Properties props=new Properties();
            props.put("mail.smtp.host","smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port","465");
            props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth","true");
            props.put("mail.smtp.port","465");
            Session ses=Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(SEmail,SPass);
                }
            }
            );
            Message message=new MimeMessage(ses);
            message.setFrom(new InternetAddress(SEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject(subject);
            message.setContent(body,"text/html" );
            Transport.send(message);
            session.setAttribute("msg","Your Order  successfully Placed. Order delivered within 30 minutes. Get other details from your Mail box. OrderId :-"+maxoid);
            response.sendRedirect("cart.jsp");
             }catch(Exception ex)   {
               msg=msg+" Mail Sending Fail<br/>"+ex;
               session.setAttribute("msg", msg);
               response.sendRedirect("cart.jsp");
            }
               }else{
                   session.setAttribute("msg", "Sorry Order Failed");
                     response.sendRedirect("cart.jsp");
               }
           }else{
             session.setAttribute("msg","No Item in cart!");  
           }           
        }catch(Exception ex){
            session.setAttribute("msg", ""+ex);
             response.sendRedirect("cart.jsp");
        }
       }else{
             session.setAttribute("msg", "Plz Login First");
             response.sendRedirect("index.jsp");
         } 
      
    }

}
