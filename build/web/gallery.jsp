
<%@page import="java.util.HashMap"%>
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
    
    <%
    HashMap userDetails=(HashMap)session.getAttribute("userDetails");
    if(userDetails!=null){
       
        %>
     <a href="profile.jsp">Profile</a> &nbsp;&nbsp;&nbsp; 
    <a href="booktable.jsp">BookTable</a> &nbsp;&nbsp;&nbsp;
    <a href="menu.jsp">Menu</a> &nbsp;&nbsp;&nbsp;    
    <a href="cart.jsp">Cart</a> &nbsp;&nbsp;&nbsp;
     <a href="gallery.jsp">Gallery</a> &nbsp;&nbsp;&nbsp;
    <a href="chefs.jsp">Chefs</a> &nbsp;&nbsp;&nbsp;
    <a href="contact.jsp">Contact</a> &nbsp;&nbsp;&nbsp;

   
    Welcome<font color="#F7A90F"><b> <%=userDetails.get("name") %> </b></font> &nbsp;&nbsp;&nbsp; 
    <input class="redbutton" type="button" value="logout" onclick="parent.location='Logout' "/>
    <%}else{%>
    <a href="index.jsp">Home</a> &nbsp;&nbsp;&nbsp;
    <a href="gallery.jsp">Gallery</a> &nbsp;&nbsp;&nbsp;
    <a href="chefs.jsp">Chefs</a> &nbsp;&nbsp;&nbsp;
    <a href="contact.jsp">Contact</a> &nbsp;&nbsp;&nbsp;
  <%
    }%>
       </p>
  </div>
  <div class="block5"></div>
<iframe src="slide.html" style="width:960px;height:365px;max-width:100%;overflow:hidden;border:none;padding:0;margin:0 auto;			display:block;" marginheight="0" marginwidth="0"></iframe>
	<br/>
	<h3>E-Restaurant's Gallery</h3>
  <div class="gallerycontent">
    <div class="image">
    <img src="img/img1.jpg" width="250" height="180" /> 
    </div>
    <div class="image">
    <img src="img/img2.jpg" width="250" height="180" />
    </div>
    <div class="image">
    <img src="img/img3.jpg" width="250" height="180" />
    </div>
    <div class="image">
    <img src="img/img4.jpg" width="250" height="180" /> 
    </div>
    <div class="image">
    <img src="img/img5.jpg" width="250" height="180" />
    </div>
    <div class="image">
    <img src="img/img6.jpg" width="250" height="180" />
    </div>
 	 <div class="image">
    <img src="img/img7.jpg" width="250" height="180" />
    </div>
    <div class="image">
    <img src="img/img8.jpg" width="250" height="180" /> 
    </div>
    <div class="image">
    <img src="img/img9.jpg" width="250" height="180" />
    </div>
   </div>
  <div class="footer">Designed & Developed by Avnish Sharma</div>
    
</div>
</body>
</html>
