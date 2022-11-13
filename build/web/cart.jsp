<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.HashMap"%>
<%
    HashMap userDetails=(HashMap)session.getAttribute("userDetails");
    if(userDetails!=null){
        db.DbConnect db =new db.DbConnect();
       HashMap cartItem= db.getCart((String)userDetails.get("email"));
       
       int q=0;
        String i="";
        float t=0;
       if(cartItem!=null){
            i=(String)cartItem.get("item");
            t=(float)cartItem.get("total");
            StringTokenizer stk=new StringTokenizer(i,"#");
            while(stk.hasMoreTokens()){
                 stk.nextToken();
                q++;
            }
        }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>E-Restaurant</title>
<style type="text/css">
</style>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="container">
  <div class="header">
  	<h5>E-Restaurant</h5>
    <p>
    
    <a href="profile.jsp">Profile</a> &nbsp;&nbsp;&nbsp; 
    <a href="booktable.jsp">BookTable</a> &nbsp;&nbsp;&nbsp;
    
    <a href="menu.jsp">Menu</a> &nbsp;&nbsp;&nbsp;
    <a href="chefs.jsp">Chefs</a> &nbsp;&nbsp;&nbsp;
    <a href="gallery.jsp">Gallery</a> &nbsp;&nbsp;&nbsp;
    <a href="contact.jsp">Contact</a> &nbsp;&nbsp;&nbsp;
    <a href="cart.jsp">Cart[<%=q%>]</a> &nbsp;&nbsp;&nbsp;
    
    Welcome <font color="#F7A90F"><b>  <%=userDetails.get("name") %></b></font> &nbsp;&nbsp;&nbsp;
    <input class="redbutton" type="button" value="Logout" onclick="parent.location='Logout' "/>

	</p>
  </div>
  <div class="block5"></div>
<iframe src="slide.html" style="width:960px;height:365px;max-width:100%;overflow:hidden;border:none;padding:0;margin:0 auto;			display:block;" marginheight="0" marginwidth="0"></iframe>	
  <h3>My Cart</h3>
  <div class="content">
    <div class="cartbg">
        
       <%
        
            String msg=(String)session.getAttribute("msg");
            if(msg!=null)  
            {
            %>
              <center><b><font color="#AD1025"><%=msg%></font></b></center> 
            <%
            }session.setAttribute("msg",null);

        
            if(q==0)
        {
           %>
            <center><b><font color="#AD1025">Cart is Empty</font></b></center> 
        <%
            }       
        else
        {
        %>
            <div class="ctable" >
            <table >
                <tr>
                    <th width="62%">Item List</th><th width="14%">Qty</th><th width="9%">Price</th><th width="15%">Total</th>   
                </tr>               
                     <%  
                    StringTokenizer stk=new StringTokenizer(i,"#");
                while(stk.hasMoreTokens()){
                     String p=stk.nextToken();
                    StringTokenizer sk=new StringTokenizer(p,":");
                    String pn=sk.nextToken();
                    int qty=Integer.parseInt(sk.nextToken());
                    double pr=Double.parseDouble(sk.nextToken());
                %>
                       <tr>                           
                           <td><img src="img/close.png" width="15" height="15" /></a>					                          
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <%=pn%> </td>
                            <td><%=qty%></td>
                            <td><%=pr%></td>
                            <td><%=qty*pr%></td>
                        </tr> 
                <%            
                    }
                    session.setAttribute("total", t);
                %>
               
            </table>
            </div>
            <div class="total">             
              <font size="+1">&nbsp;&nbsp;&nbsp;Net Amount: &nbsp;&nbsp;</font>
              <font size="+1.5"><b><%=t%>/- </b></font>
               
                
                <br/><br/><br/>
            <font size="+.5"><b>Delivery Addresss </b></font><br/><br/>
            
            <form action="Order" method="post">
                <input type="text"class="form-control" name="address" />
                <br/><br/>
                <input class="bluebutton" name="" type="submit" value="Order Now"/>
            </form> 
            
                
            </div>
        
    </div>    
  </div>
  <div class="footer">Designed & Developed by Avnish Sharma</div>
    
</div>
</body>
</html>
<%    
    }
    }else{
        session.setAttribute("msg", "Plz login First!");
        response.sendRedirect("index.jsp");
    }
%>