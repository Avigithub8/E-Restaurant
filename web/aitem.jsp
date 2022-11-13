<%
    String aname= (String)session.getAttribute("aname");
    if(aname!=null){
%>
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
        <a href="adminhome.jsp">Orders</a> &nbsp;&nbsp;&nbsp;
        <a href="abooktable.jsp">TableBookings</a> &nbsp;&nbsp;&nbsp;
        <a href="aitem.jsp">Items</a> &nbsp;&nbsp;&nbsp; 
        Welcome <font color="#F7A90F"><b><%= aname %></b></font> &nbsp;&nbsp;&nbsp;
        <input class="redbutton" type="button" value="Logout" onclick="parent.location='Logout' "/>
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
        
     <h3>Add New Item</h3>
     <div class="form">
       <div class="form1">
            Item Name:<br/><br/><br/>
            Price:<br/><br/><br/>
            Category:<br/><br/><br/>
            Image:<br/><br/>
       </div>
         <form action="AddItem" method="post" enctype="multipart/form-data">
     	<div class="form2">
        	<input class="lform" name="itemName" type="text" /><br/><br/>
        	<input class="lform" name="price" type="number" /><br/><br/>
            <select name="category" class="lform">
                <option value="vegmain">Veg Main Course</option>
                <option value="nonvegmain">Non-Veg Main Course</option>
                <option value="roti">Roti</option>
                <option value="rice">Rice</option>
                <option value="salad">Salad/Raita</option>
                <option value="bev">Bevarages</option>
                <option value="sweet">Sweet</option>
                <option value="chinese">Chinese</option> 
                <option value="snacks">Snacks</option> 
            </select><br/><br/><br/>
       		<input  type='file' name='image'/><br/>
       </div>
       <div class="form3">
       	<input class="bluebutton" value="Add" type="submit" />
        </form>
      </div>
      
    </div>
  </div>
     
     
  <div class="canceltable">
    	<h3>Modify Item</h3>
        <div class="form">
       <div class="form1">
       		Item Name:<br/><br/><br/>
       </div>
        <form action="SearchItem" method="post">
     	<div class="form2">
        	<input class="mform" name="iname" type="text" />
            <input class="bluebutton" value="GO" type="submit" />
        </form>
           
       </div>     
            
          <div class="removefloat"></div>
          <%
            HashMap itemDetails =(HashMap)session.getAttribute("itemDetails");
            if(itemDetails!=null){
          %>
            <hr/><br/>
            <div class="form1">
                Item Name:<br/><br/><br/>
                Price:<br/><br/><br/>
                Category:<br/><br/><br/>
                Image:<br/><br/><br/>
            </div>
            <form action="ModifyItem" method="post" enctype="multipart/form-data">
            <div class="form2">
                <input name="iname" type="hidden" value=<%=itemDetails.get("iname")%>/>
                <input class="lform" name="itemName" type="text" placeholder=<%=itemDetails.get("iname")%> /><br/><br/>
                <input class="lform" name="price" type="text" placeholder=<%=itemDetails.get("price")%>/><br/><br/>
                <%=itemDetails.get("category")%> &nbsp; <select name="category" class="lform">
                    <option value="vm">Veg Main Course</option>
                    <option value="nvm">Non-Veg Main Course</option>
                    <option value="roti">Roti</option>
                    <option value="rice">Rice</option>
                    <option value="salad">Salad/Raita</option>
                    <option value="bev">Bevarages</option>
                    <option value="sweet">Sweet</option>
                    <option value="vc">Chinese</option> 
                    <option value="nvc">Snacks</option> 
                </select><br/><br/>
                <img src='GetPhoto?iname=<%=(String)itemDetails.get("iname")%>' width="100" height="100" />
               <%-- <input  type='file' name='img'/>--%>
            </div>
                
                
           <div class="form3">          
            <input class="bluebutton" value="Update" type="submit" />&nbsp;&nbsp;
            <input class="redbutton" value="Delete" type="button" onclick="parent.location='DeleteItem.jsp?iname=' "/>
            </form
            </div>
          <%
              }
           
        %> 
     
     </div>
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