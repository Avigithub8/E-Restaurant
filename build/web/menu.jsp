<%@page import="java.util.HashSet"%>
<%@page import="java.util.HashMap"%>
<%
    HashMap userDetails=(HashMap)session.getAttribute("userDetails");
    if(userDetails!=null){
       
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
    <a href="cart.jsp">Cart</a> &nbsp;&nbsp;&nbsp;
    
    Welcome <font color="#F7A90F"><b>  <%=userDetails.get("name") %> </b></font> &nbsp;&nbsp;&nbsp; 
    <input class="redbutton" type="button" value="logout" onclick="parent.location='Logout' "/>
    
    </p>
  </div>
  <div class="block5"></div>
 
              
        <%
           String msg=(String)session.getAttribute("msg");
            if(msg!=null)  
            {
        %>
         <center><b><font size="5" color="#AD1025"><%=msg%></font></b></center> 
        <%
            session.setAttribute("msg", null);
            }
        %>  
<div class="content">
    <div class="menu1">
        <a href="GetMenu?category=vegmain"> Veg Main Course</a><br/><hr/><br/>
        <a href="GetMenu?category=nonvegmain"> Non-Veg Main Course</a><br/><hr/><br/>
        <a href="GetMenu?category=rice"> Rice</a><br/><hr/><br/>
        <a href="GetMenu?category=roti"> Roti</a><br/><hr/><br/>
        <a href="GetMenu?category=salad"> Salad/Raita</a><br/><hr/><br/>
        <a href="GetMenu?category=bev"> Beverages</a><br/><hr/><br/>
        <a href="GetMenu?category=sweet"> Sweets</a><br/><hr/><br/>
        <a href="GetMenu?category=chinese"> Chinese</a><br/><hr/><br/>
        <a href="GetMenu?category=snacks"> Snacks</a><br/><br/>
        
    </div><%
    HashSet allItemDetails=(HashSet)session.getAttribute("allItemDetails");
    if(allItemDetails!=null){
            java.util.Iterator i=allItemDetails.iterator();
            while(i.hasNext()){
                HashMap itemDetails=(HashMap)i.next();
        %>
    
  	<div class="menu2">
        
            <div class="item">
                <img src='GetPhoto?iname=<%=itemDetails.get("iname")%>' width="100" height="100" />
                <div class="itemd">
                    <font size="+1.5"><%=itemDetails.get("iname")%></font><br/><br/>
                    Price: <font color="#AD1025"><%=itemDetails.get("price")%>/-</font>
                </div>
                <div class="itemb">
                    <form action="CartPro" method="post">
                        &nbsp;Quantity: &nbsp;&nbsp;<select name="noi" class="iform">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select><br/><br/>
                    <input name="iname" type="hidden" value='<%=itemDetails.get("iname")%>' />
                    <input name="price" type="hidden" value='<%=itemDetails.get("price")%>' />
                    <input name="category" type="hidden" value='<%=itemDetails.get("category")%>' />
                    <input class="bluebutton" value="Add to Cart" type="submit" />
                </form>
                </div> 
            </div>
        
	</div>
 <%}
}else{
%>
<div class="menu2">
        
            <div class="item">
                <%-- <img src='' width="100" height="100" />--%>
                <div class="itemd">
              <font size="5">No Item Found!!</font>      
            </div>
        
	</div>
<%
}
%>
             
  </div>

        
  <div class="footer">Developed by Avnish Sharma</div>
    
</div>
</body>
</html>
<%       
    }else{
        session.setAttribute("msg", "Plz login First!");
        response.sendRedirect("index.jsp");
    }
%>