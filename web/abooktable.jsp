<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.HashMap"%>
<%
    String aname= (String)session.getAttribute("aname");
    if(aname!=null){
     java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
    String cd=dateFormat.format(new java.util.Date());

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
            <a href="adminhome.jsp">Orders</a> &nbsp;&nbsp;&nbsp;
            <a href="abooktable.jsp">TableBookings</a> &nbsp;&nbsp;&nbsp;
            <a href="aitem.jsp">Items</a> &nbsp;&nbsp;&nbsp; 
            Welcome <font color="#F7A90F"><b> <%= aname %> </b></font> &nbsp;&nbsp;&nbsp;
            <input class="redbutton" type="button" value="logout" onclick="parent.location='Logout' "/>
        </p>
  </div>
  <div class="block5"></div>
<iframe src="slide.html" style="width:960px;height:365px;max-width:100%;overflow:hidden;border:none;padding:0;margin:0 auto;			display:block;" marginheight="0" marginwidth="0"></iframe>

  <div class="content">
    <div class="cartbg">
    	
    	<br/><center><b><font color="#AD1025">Table Booking Details</font></b></center> <br/>
      <div class="ctable" >
            <table >
                <tr>
                    <th width="5%">Booking ID</th><th width="28%">Email ID</th><th width="17%">Name</th><th width="15%">Slot</th><th width="15%">Date</th><th width="10%">Tables</th><th width="10%">Status</th><th width="10%">Modify</th>   
                </tr>
                <%
                    db.DbConnect db= new db.DbConnect();
                    HashSet<HashMap> allTables= db.getTables(cd);
                    Iterator i=allTables.iterator();
                        while(i.hasNext()){
                            HashMap table =(HashMap)i.next();
                %>
               
                <tr>
                    <td><%=table.get("tbid")%></td>
                     <td><%=table.get("email")%></td>
                     <td><%=table.get("name")%></td>
                     <td><%=table.get("slot")%></td>
                     <td><%=table.get("date")%></td>
                     <td><%=table.get("tbook")%></td>
                     <td><%=table.get("status")%></td> 
                     <td><a href="ACancelTable?name=<%=table.get("name")%>&email=<%=table.get("email")%>&bid=<%=table.get("tbid")%>"><font color="Red" size="2"><b>Cancel</b></font> </a></td>  
                </tr>
                <%
                    }
                %>  
            </table>
      </div>
          <%
        String msg=(String)session.getAttribute("msg");
        if(msg!=null)  
        {
        %>
        <br><center><font size="3"><b><%=msg%></b></font></center><br>
        <%
        }
        session.setAttribute("msg", null);
        %>
        <div class="acanceltable">
            <%
            int tt= db.getTotalTable();
            %>
            
            <h3>Total Tables [<%=tt%>  ]</h3>
        <div class="acform">
        <form action="UpdateTable" method="post">  	
            Update Tables:   
            <input class="lform" name="nt" type="text" value="" />
            <input class="redbutton" value="Save Changes" type="submit" />
        </form>
        <br/><br/>
      
            <br/>
        
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