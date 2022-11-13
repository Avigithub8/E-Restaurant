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
    <script src="js/jquery.min.js"></script>
    <link rel="stylesheet" href="js/hbootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"/>
    <script src="js/gijgo.min.js" type="text/javascript"></script>
    <link href="js/gijgo.min.css" rel="stylesheet" type="text/css" />
    <script src="jquery.js" type="text/javascript"></script>
 <script src="jquery-ui.js" type="text/javascript"></script>
 <link src="jquery-ui.css" rel="stylesheet" type="text/css"/>
 <script>
                    $(function(){
                    $('#datepicker').datepicker({
                        uiLibrary: 'bootstrap',
                        minDate:new Date(),
                    });
                    });
                </script>
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
<iframe src="slide.html" style="width:960px;height:365px;max-width:100%;overflow:hidden;border:none;padding:0;margin:0 auto;			display:block;" marginheight="0" marginwidth="0"></iframe>
  
<%
           String msg=(String)session.getAttribute("msg");
            if(msg!=null)  
            {
        %>
        <br><center><font size="5"><%=msg%></font></center><br>
        <%
            session.setAttribute("msg", null);
            }
        %> 
<div class="content">
    <div class="booktable">
     <h3>Book Table</h3>
     <div class="form">
       <div class="form1">
           Booking Date:<br/><br/><br/><br/>
            Slot:<br/><br/><br/>
            No of Tables:<br/><br/>
       </div>
        <form action="BookTable" method="post">
     	<div class="form2">
        	
        	<input id="datepicker"  placeholder="Select date" class="lform" name="bookdate" /><br/><br/>
                
                <select name="slot" class="lform">
                <option value="8AM-10AM">8AM-10AM</option>
                <option value="10AM-12PM">10AM-12PM</option>
                <option value="12PM-2PM">12PM-2PM</option>
                <option value="2PM-4PM">2PM-4PM</option>
                <option value="4PM-6PM">4PM-6PM</option>
                <option value="6PM-8PM">6PM-8PM</option> 
                <option value="8PM-10PM">8PM-10PM</option>
                <option value="10PM-12AM">10PM-12AM</option>
            </select>
              <br/><br/>
       		<select name="no_of_table" class="lform">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select><br/>
       </div>
       <div class="form3">
       	<input class="bluebutton" value="Book" type="submit" />
        </form>
      </div>
       
    </div>
  </div>
  <div class="canceltable">
    	<h3>Cancel Table</h3>
        <div class="form">
       <div class="form1">
       		Booking ID:<br/><br/><br/>
       </div>
        <form action="CancelTable" method="post">
     	<div class="form2">
        	<input class="lform" name="bid" type="text" /><br/><br/>
       </div>
       <div class="form3">
       	<input class="redbutton" value="Cancel Booking" type="submit" />
        </form>
        </div>
       
     </div>
</div>
   </div>
  <div class="footer"> Developed by Avnish Sharma</div>
    
</div>
</body>
</html>
<%       
    }else{
        session.setAttribute("msg", "Plz login First!");
        response.sendRedirect("index.jsp");
    }
%>