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
    
    Welcome <font color="#F7A90F"><b> <%=userDetails.get("name") %> </b></font> &nbsp;&nbsp;&nbsp;
    <input class="redbutton" type="button" value="logout" onclick="parent.location='logout.jsp' "/>
    
	</p>
  </div>
  <div class="block5"></div>
<iframe src="slide.html" style="width:960px;height:365px;max-width:100%;overflow:hidden;border:none;padding:0;margin:0 auto;			display:block;" marginheight="0" marginwidth="0"></iframe>
	
  <h3>Edit Profile</h3>
   <div class="cartbg">
  <div class="content">
      <%
           String msg=(String)session.getAttribute("msg");
            if(msg!=null)  
            {
        %>
        <br><font size="5"><%=msg%></font><br>
        <%
            session.setAttribute("msg", null);
            }
        %> 
   
    <form action="EditProfile" method="get">
        &nbsp;&nbsp;Name: &nbsp;&nbsp;<input class="lform" name="name" type="text" placeholder='<%=userDetails.get("name")%>' /><br/><br/>
      &nbsp;&nbsp;Email Id: &nbsp;&nbsp;<input class="lform" type="text" value='<%=userDetails.get("email")%>'  disabled /><br/><br/>
      &nbsp;&nbsp;Phone: &nbsp;&nbsp;<input class="lform" name="phone" type="text" placeholder='<%=userDetails.get("phone")%>' /><br/><br/>
      &nbsp;&nbsp;Address: &nbsp;&nbsp;<input class="lform" name="address" placeholder='<%=userDetails.get("address")%>'/><br/><br/>
     <input name ="email" type="hidden" value="<%=userDetails.get("email")%>" />
      <input class="bluebutton" name="" type="submit" value="Update" />
      </form>
     
 
    </div>    
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