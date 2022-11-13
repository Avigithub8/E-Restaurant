
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
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

public class BookTable extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session = request.getSession();
        try {
            HashMap userDetail=(HashMap)session.getAttribute("userDetails");
            if(userDetail!=null){
                String uname=(String) userDetail.get("name");
                String email=(String) userDetail.get("email");
                String bookdt=request.getParameter("bookdate");
                String slot=request.getParameter("slot");
                int nt=Integer.parseInt(request.getParameter("no_of_table"));
                db.DbConnect db= new db.DbConnect();
                int totalTable=db.getTotalTable();  //
                int ta= db.getTableAllocated(bookdt, slot);
                if(nt<=(totalTable-ta)){  
                   int tbid=db.getMaxTableBookID(); 
                   String s=db.bookTable(tbid, uname, bookdt, slot, email, nt);
                   if(s.equalsIgnoreCase("success")){
                   String s1= db.updateTotalTable(totalTable-nt);
                   if(s1.equalsIgnoreCase("success")){
                   String msg="Table Booked Successfull. Your Booking ID: "+tbid+". Get other details from your Mail box.";
                   String subject=msg;
                   String body="Dear "+uname+",\n Your Table Successfullly Booked and Your Booking Id: "+tbid+
                            ".\nTotal No of tables: "+nt+"  .\n Booking Date & Slot: "+bookdt+" :  "+slot+".\n\nThank You!!";

                   final String aemail="testmailid799@gmail.com";
                   final String apass="test@1234";
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
                    }
                    catch(Exception ex)
                    {
                     session.setAttribute("msg", "Mail Sending Failed: Your Table has booked Your Booking id : "+tbid);
                     response.sendRedirect("booktable.jsp");
                    }
                   //session.setAttribute("msg", "Booking Successful. Your Booking ID: "+tbid);
                   //response.sendRedirect("booktable.jsp");
                   }else{
                      //response.sendRedirect("booktable.jsp");
                   }
               }else{
                   session.setAttribute("msg", "Table Booking Failed");
                   response.sendRedirect("booktable.jsp");
               } 
            }else{
                   session.setAttribute("msg", "Sorry All Table are Already Booked");
                   response.sendRedirect("booktable.jsp");
            }
            }else{
                session.setAttribute("msg", "Plese Login First");
                response.sendRedirect("index.jsp");
            }
        } catch (Exception ex) {;
                    session.setAttribute("msg", ""+ex);
                   response.sendRedirect("booktable.jsp");
        }
    }

}
