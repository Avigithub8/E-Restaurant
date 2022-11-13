
package db;

import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;

public class DbConnect {
   private Connection c;
    private Statement st;
    private PreparedStatement insertCart,getItembyCateg,updateUser,forgetPass,newUser, changePass,
            updateTotalTable, bookTable,getTotalTable,checkLogin,checkALogin, addItem,searchItem,
            getPhoto,updateItem,getCart,updateCart,getMaxOrderID,insertOrder,deleteCart,getOrders,
            getTableAllocated,getMaxTableBookID,checkBid,cancelTableBook,getTables;
    public DbConnect() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        c=DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/e_restaurant1","username","password");
        st=c.createStatement();
        checkLogin=c.prepareStatement(
            "select * from userinfo where email=? and pass=?");
        checkALogin=c.prepareStatement(
            "select * from admininfo where aid=? and pass=?");
        addItem =c.prepareStatement(
            "insert into itemdetails values(?,?,?,?)");
        searchItem=c.prepareStatement(
            "select * from itemdetails where iname=?");
        getPhoto=c.prepareStatement(
            "select image from itemdetails where iname=?");
        updateItem=c.prepareStatement(
            "update itemdetails set iname=?,price=?,category=? where iname=?");
        bookTable=c.prepareStatement(
            "insert into tablebook values(?,?,?,?,?,?,?)");
        getTotalTable=c.prepareStatement("select * from totaltable");
        updateTotalTable=c.prepareStatement("update totaltable set nt=?");
        changePass=c.prepareStatement(
            "update userinfo set pass = ? where email= ? and pass=?");
        newUser=c.prepareStatement(
            "insert into userinfo values(?,?,?,?,?)");
        forgetPass=c.prepareStatement(
             "select pass from userinfo where email=? ");
        updateUser=c.prepareStatement(
            "update userinfo set name=?, phone= ?, address=? where email=?");
        getItembyCateg=c.prepareStatement(
            "select * from itemdetails where category=?");
        insertCart=c.prepareStatement(
            "insert into cart(email,items,total)values(?,?,?)");
        getCart=c.prepareStatement(
            "select * from cart  where email=? ");
        updateCart=c.prepareStatement(
	    "update cart set items=? ,total=? where email=?");
         getMaxOrderID=c.prepareStatement(
             "select max(oid) from orderbook");
         insertOrder=c.prepareStatement(
             "insert into orderbook values(?,?,?,?,?,?)");
         deleteCart=c.prepareStatement(
            "delete from cart where email=?");
         getOrders=c.prepareStatement(
            "select * from orderbook where odate=?");
         getTableAllocated=c.prepareStatement(
           "select tbook from tablebook where date=? and slot=? and status='Booked'");
       getMaxTableBookID = c.prepareStatement(
            "select max(tbid) from tablebook");
       checkBid=c.prepareStatement(
               "select * from tablebook where tbid=? and email=? and status='Booked'");
       cancelTableBook=c.prepareStatement(
               "update tablebook  set status='Canceled' where tbid=? and email= ? and status='Booked'");
       getTables=c.prepareStatement(
               "select * from tablebook where date>=?");
    }
   
    public HashMap checkLogin(String userid,String pass) throws SQLException{
        checkLogin.setString(1, userid);
        checkLogin.setString(2, pass);
        ResultSet rs=checkLogin.executeQuery();
        if(rs.next()){
            HashMap userDetails=new HashMap();
            userDetails.put("name", rs.getString("name"));
            userDetails.put("email", rs.getString("email"));
            userDetails.put("phone", rs.getString("phone"));
            userDetails.put("address", rs.getString("address"));
             return userDetails;
        }else{
            return null;
        }
    }
    public int getTotalTable() throws SQLException{
     ResultSet rs=  getTotalTable.executeQuery();       //
     if(rs.next()){
         return rs.getInt("nt");
     }
     else{
         return 0;
     }
    }
 
    public int getTableAllocated(String bookdt, String slot) throws SQLException{
        getTableAllocated.setString(1, bookdt);           //
        getTableAllocated.setString(2, slot);
        ResultSet rs=getTableAllocated.executeQuery();
        int ta=0;
        while(rs.next()){
         ta+=rs.getInt("tbook");
        }
        if(ta!=0){
            return ta;
        }else{
         return 0;   
        }
    }
     public String updateTotalTable(int nt) throws SQLException {
        updateTotalTable.setInt(1, nt);             //
       int x= updateTotalTable.executeUpdate();
     if(x!=0){
         return "success";
     }
     else{
         return "Failed";
     }
    }
    public HashMap searchItem(String iname) throws SQLException {
       searchItem.setString(1, iname);          //
        ResultSet rs=searchItem.executeQuery();
        if(rs.next()){
           HashMap itemDetails=new HashMap();
            itemDetails.put("iname", rs.getString("iname"));
            itemDetails.put("price", rs.getInt("price"));
            itemDetails.put("category", rs.getString("category"));
            
             return itemDetails;
        }else{
        return null;
        }
        }
    
    public int getMaxOrderID() throws SQLException {
      
        ResultSet rs=getMaxOrderID.executeQuery();
        if(rs.next()){
             return rs.getInt(1)+1;
        }else{
        return 0;
        }
        }
    public int checkBid(int tbid,String email) throws SQLException {
        checkBid.setInt(1, tbid);          //
        checkBid.setString(2,email);
        ResultSet rs=checkBid.executeQuery();
        if(rs.next()){
             return rs.getInt("tbook");
        }else{
        return -1;
        }
        }
    public String  deleteCart(String email) throws SQLException {
      deleteCart.setString(1, email);
      int x= deleteCart.executeUpdate();
        
        if(x!=0){
             return "success";
        }else{
            return "failed";
        }
        }
     
    public String checkALogin(String aid, String pass) throws SQLException {
       checkALogin.setString(1, aid);          //
        checkALogin.setString(2, pass);
        ResultSet rs=checkALogin.executeQuery();
        if(rs.next()){
                return rs.getString("name");
        }else{
                return null;
            }
        }
    public String addItem(String itemName, int price,String category, InputStream image)throws SQLException {
       addItem.setString(1, itemName);          //
        addItem.setInt(2, price);
        addItem.setString(3, category);
        addItem.setBinaryStream(4, image);
        int x=addItem.executeUpdate();
        if(x!=0){
           return "success";
        }else{
        return "failed";
        }
        }
    public String bookTable(int tbid,String name,String bookdt,String slot,String email,int nt)throws SQLException {
        bookTable.setInt(1, tbid);      //
        bookTable.setString(2, name);
        bookTable.setString(3, bookdt);
        bookTable.setString(4, slot);
        bookTable.setString(5, email);
        bookTable.setInt(6, nt);
        bookTable.setString(7, "Booked");
        int x=bookTable.executeUpdate();
        if(x!=0){
           return "success";
        }else{
        return "failed";
        }
        }

    public String updateItem(String iname,String itemName, int price,String category)throws SQLException {
       
        updateItem.setString(1, itemName);                     //
        updateItem.setInt(2, price);
        updateItem.setString(3, category);
         updateItem.setString(4, iname);
        int x=updateItem.executeUpdate();
        if(x!=0){
           return "success";
        }else{
        return "failed";
        }
        }

    public byte[] getPhoto(String iname) throws SQLException {
    getPhoto.setString(1,iname);            //
    ResultSet rs=getPhoto.executeQuery();
        if(rs.next()){
            byte[] b=rs.getBytes("image");
                if(b.length!=0)
                    return b;
                else
                    return null;
        }else{
        return null;
        }
    }

    public String changePass(String n, String e, String o) throws SQLException {
    changePass.setString(1, n);             //
        changePass.setString(2, e);
        changePass.setString(3, o);
        int x= changePass.executeUpdate();
        if(x!=0){
            return "success";
        }else{
            return "Failed";
        }
    
    }

    public String newUser(String name, String email, String pass, String phone, String address) throws SQLException {
        try{
        newUser.setString(1, email);        //
        newUser.setString(2, name);
        newUser.setString(3, pass);
        newUser.setString(4, phone);
        newUser.setString(5, address);
        int x=newUser.executeUpdate();
        if(x!=0)
           return "Success";
        else
            return "Failed";
        }catch(java.sql.SQLIntegrityConstraintViolationException ex){
            return "Already";
        }
    }
     public String insertOrder(int oid, String email, String item, float total, String cd , String address) throws SQLException {
            insertOrder.setInt(1, oid);
            insertOrder.setString(2, email);
            insertOrder.setString(3, item);
            insertOrder.setFloat(4, total);
            insertOrder.setString(5, cd);
            insertOrder.setString(6, address);
            int x=insertOrder.executeUpdate();
            if(x!=0){
                return "success";
            }else{
                return null;
            }
    }
        public String forgetPass(String e){
        try{
            forgetPass.setString(1, e);
            ResultSet rs=forgetPass.executeQuery();
            if(rs.next()){
                    return rs.getString("pass");
            }else{
                return null;
            }
        }catch(Exception ex){
            return null;
        }
    }

    public String updateUser(String name, String email, String phone, String address) throws SQLException {
    
        updateUser.setString(1, name);           //
        updateUser.setString(2, phone);
        updateUser.setString(3, address);
        updateUser.setString(4, email);
        int x=updateUser.executeUpdate();
        if(x!=0)
           return "Success";
        else
            return "Failed";
    
    }
    public HashSet<HashMap>  getItembyCateg(String category) throws SQLException {
    getItembyCateg.setString(1,category);           //
    ResultSet rs=getItembyCateg.executeQuery();
    HashSet<HashMap>allItemDetails=new HashSet();
    while(rs.next()){
           HashMap itemDetails =new HashMap();
            itemDetails.put("iname", rs.getString("iname"));
            itemDetails.put("price", rs.getInt("price"));
            itemDetails.put("category", rs.getString("category"));
            allItemDetails.add(itemDetails);
            
         }
        return allItemDetails;
     }
     public HashSet<HashMap>  getOrders(String cd) throws SQLException {
        getOrders.setString(1,cd);
        ResultSet rs=getOrders.executeQuery();
        HashSet<HashMap>allOrders=new HashSet();
        while(rs.next()){
               HashMap order =new HashMap();
                order.put("oid", rs.getInt("oid"));
                order.put("items", rs.getString("items"));
                order.put("amt", rs.getFloat("amt"));
                order.put("address", rs.getString("addr"));
                allOrders.add(order);
             }
        return allOrders;
     }
     public HashSet<HashMap>  getTables(String cd) throws SQLException {
        getTables.setString(1,cd);          //
        ResultSet rs=getTables.executeQuery();
        HashSet<HashMap>allTables=new HashSet();
        while(rs.next()){
               HashMap table =new HashMap();
                table.put("tbid", rs.getInt("tbid"));
                table.put("name", rs.getString("name"));
                table.put("email", rs.getString("email"));
                table.put("slot", rs.getString("slot"));
                table.put("date", rs.getString("date"));
                table.put("tbook", rs.getString("tbook"));
                table.put("status", rs.getString("status"));
                allTables.add(table);
             }
        return allTables;
     }
    public String insertCart(String email, String item, float price)throws SQLException {
            insertCart.setString(1,email);         //
            insertCart.setString(2,item);
            insertCart.setFloat(3, price);
            
        int x=insertCart.executeUpdate();
        if(x!=0){
           return "success";
        }else{
        return "failed";
        }
        }
      public HashMap getCart(String email) throws SQLException {
        getCart.setString(1,email);           //
        ResultSet rs=getCart.executeQuery();
        if(rs.next()){
            HashMap cartItem= new HashMap();
            cartItem.put("item",rs.getString("items"));
            cartItem.put("total",rs.getFloat("total"));
            return cartItem;
        }else{
         return null;
        }
    }
      public String updateCart(String item, float total, String email) throws SQLException {
        updateCart.setString(1,item);
        updateCart.setFloat(2,total);
        updateCart.setString(3,email);
        int x=updateCart.executeUpdate();
        if(x!=0){
            return"Success";
        }else{
         return "Failed";
        }
    }

    public int getMaxTableBookID() throws SQLException {
        ResultSet rs=getMaxTableBookID.executeQuery();
        if(rs.next()){
             return rs.getInt(1)+1;
        }else{
        return 0;
        }
    }

    public String cancelTableBook(int tbid ,String email) throws SQLException {
         cancelTableBook.setInt(1,tbid);
          cancelTableBook.setString(2,email);
        int x=cancelTableBook.executeUpdate();
        if(x!=0){
            return"Success";
        }else{
         return "Failed";
        }
    }
}
