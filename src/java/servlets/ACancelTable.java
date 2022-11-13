
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
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

public class ACancelTable extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session =request.getSession();
        String aname= (String)session.getAttribute("aname");
        if(aname!=null){
        try {
            String name= request.getParameter("name");
             String email= request.getParameter("email");
            int tbid=Integer.parseInt(request.getParameter("bid"));
            db.DbConnect db= new db.DbConnect();
            int s=db.checkBid(tbid,email);
            if(s!=-1){
                String s1=db.cancelTableBook(tbid,email);
                if(s1.equalsIgnoreCase("success")){
                int tt= db.getTotalTable();
                db.updateTotalTable(tt+s);
                String msg="Table Cancelled Successfull.Refund Amount initiated to your Paypal account.Get other details from your Mail box.";
                String subject=msg;
                String body="Dear "+name+",Your table Successfullly CANCELLED and Your Booking Id: "+tbid+". Refund Amount initiated to your Paypal account.Thank You!!";
                   final String aemail="abc@gmail.com";
                   final String apass="abc@123";
                   try
                    {
                        Properties properties = new Properties();
                        properties.put("mail.smtp.host", "smtp.gmail.com");  
                        properties.put("mail.smtp.socketFactory.port", "465");  
                        properties.put("mail.smtp.socketFactory.class",  
                              "javax.net.ssl.SSLSocketFactory");  
                        properties.put("mail.smtp.auth", "true");  
                        properties.put("mail.smtp.port", "465");
                        Session ses = Session.getInstance(properties,    
                            new javax.mail.Authenticator() {  
                             protected PasswordAuthentication getPasswordAuthentication() {  
                             return new PasswordAuthentication(aemail, apass); } 
                            });  

                        Message message = new MimeMessage(ses);  
                        message.setFrom(new InternetAddress(aemail));  
                        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));  
                        message.setSubject(subject);  
                        message.setText(body);  
                        Transport.send(message);               
                    }catch(Exception ex)
                    {
                     session.setAttribute("msg", "Mail Sending Failed: Booking has Cancelled");
                     response.sendRedirect("abooktable.jsp");
                    }
                    session.setAttribute("msg", " Booking has Cancelled");
                    response.sendRedirect("abooktable.jsp");
                }else{
                session.setAttribute("msg"," Sorry Cancelation of Table is Failed ");
                response.sendRedirect("abooktable.jsp");
                }
            }else{
                session.setAttribute("msg"," Sorry Your Booking ID is Wrong ");
                response.sendRedirect("abooktable.jsp");
                }
        } catch (Exception ex) {
                session.setAttribute("msg","Exception"+ex);
                response.sendRedirect("abooktable.jsp");
            }
        }else{
            session.setAttribute("msg", "Plz login First!");
            response.sendRedirect("index.jsp");
        }
    }
}
