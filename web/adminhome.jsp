<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.Iterator"%>
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
        Welcome <font color="#F7A90F"><b>  <%= aname %> </b></font> &nbsp;&nbsp;&nbsp;
        <input class="redbutton" type="button" value="logout" onclick="parent.location='Logout' "/>
    </p>
  </div>
  <div class="block5"></div>
<iframe src="slide.html" style="width:960px;height:365px;max-width:100%;overflow:hidden;border:none;padding:0;margin:0 auto;			display:block;" marginheight="0" marginwidth="0"></iframe>

  <div class="content">
    <div class="cartbg">
    	<br/><center><b><font color="#AD1025">List of Orders Placed ()</font></b></center> <br/>
      <div class="ctable" >
            <table >
                <tr>
                    <th width="12%">Order ID</th><th width="60%">Items</th><th width="11%">Total</th><th width="17%">Address</th>   
                </tr>
                <%
                    
                    db.DbConnect db =new db.DbConnect();
                    HashSet<HashMap> allOrders=db.getOrders(cd);
                    Iterator i=allOrders.iterator();
                        while(i.hasNext()){
                            String item="";
                            HashMap order =(HashMap)i.next();
                            String it=(String)order.get("items");
                            StringTokenizer stk=new StringTokenizer(it,"#");
                             while(stk.hasMoreTokens()){
                            String p=stk.nextToken();
                           StringTokenizer sk=new StringTokenizer(p,":");
                            item+=sk.nextToken()+"("+sk.nextToken()+") ";
                            }
                        %>
                
                <tr>
                    <td><%=order.get("oid")%></td>
                     <td><%=item%></td>
                     <td><%=order.get("amt")%></td>
                     <td><%=order.get("address")%></td>
                </tr>
                   <%  
                        }
                   %>
            </table>
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