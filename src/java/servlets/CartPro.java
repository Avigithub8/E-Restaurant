
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CartPro extends HttpServlet {

   
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       HttpSession session= request.getSession();
      HashMap userDetails=(HashMap)session.getAttribute("userDetails");
         if(userDetails!=null){
       try{
           String iname=request.getParameter("iname");
           float  price=Float.parseFloat(request.getParameter("price"));
           String category=request.getParameter("category");
           String email=(String)userDetails.get("email");
           int noi=Integer.parseInt(request.getParameter("noi"));
           db.DbConnect db= new db.DbConnect();
           HashMap cartItem= db.getCart(email);
         float total;
         String item;
           if(cartItem!=null){
                total=(Float) cartItem.get("total");
                item=(String) cartItem.get("item");
                StringTokenizer stk=new StringTokenizer(item,"#");
                int c=0;
                while(stk.hasMoreTokens()){
                String p=stk.nextToken();
                StringTokenizer sk=new StringTokenizer(p,":");
                if(sk.nextToken().equalsIgnoreCase(iname)){
                    session.setAttribute("msg","Item Already Exist!");
                    c=1;
                    break;
                }
                }
                if(c==0){
                   item+="#"+iname+":"+noi+":"+price;
                    total+=price*noi;
                   // System.out.println(total);
                    String s=db.updateCart(item,total,email);
                    if(s.equalsIgnoreCase("success")){
                    session.setAttribute("msg","Item added to Cart!");
                    response.sendRedirect("menu.jsp");
                    }else{
                    session.setAttribute("msg","Item Not added!");
                    response.sendRedirect("menu.jsp");
                    }
                  }
                if(c==1){
                    response.sendRedirect("menu.jsp");
                }
                
           }else{
                    item=iname+":"+noi+":"+price;
                    String s=db.insertCart(email, item, price*noi);
                    if(s.equalsIgnoreCase("success")){
                         session.setAttribute("msg","Item added to cart!");
                         response.sendRedirect("menu.jsp");
                    }
            }
       }catch(Exception ex){
           session.setAttribute("msg",""+ex);
           ex.printStackTrace();
             response.sendRedirect("menu.jsp");
       }
       
         }else{
             session.setAttribute("msg", "Plz Login First");
             response.sendRedirect("menu.jsp");
         } 
        
        
        
        
        
        
        
    }

}
