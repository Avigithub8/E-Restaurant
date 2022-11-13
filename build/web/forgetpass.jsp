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
    <p><a href="menu.jsp">Menu</a>  &nbsp;&nbsp;&nbsp;
    <a href="chefs.jsp">Chefs</a> &nbsp;&nbsp;&nbsp;
    <a href="gallery.jsp">Gallery</a> &nbsp;&nbsp;&nbsp;
    <a href="contact.jsp">Contact</a> 
	</p>
  </div>
  <div class="block5"></div>
<iframe src="slide.html" style="width:960px;height:365px;max-width:100%;overflow:hidden;border:none;padding:0;margin:0 auto;			display:block;" marginheight="0" marginwidth="0"></iframe>
  <h3>Forget Password</h3
  ><div class="content">
  <div class="cartbg">
      <%
           String msg=(String)session.getAttribute("msg");
            if(msg!=null)  
            {
        %>
        <br><%=msg%><br>
        <%
            session.setAttribute("msg", null);
            }
        %> 
      
      
      &nbsp;&nbsp;<b>We will send your password to your register Email ID.<br/><br/></b>
    <form action="ForgetPass" method="post">
      &nbsp;&nbsp;Enter Email Id: &nbsp;&nbsp;<input class="lform" name="email" type="text" /><br/><br/>
      <input class="bluebutton" name="" type="submit" value="Submit" />
      </form>
      
    </div> 
  </div>
    </div>
  <div class="footer">Designed & Developed by Avnish Sharma</div>
    
</div>
</body>
</html>
