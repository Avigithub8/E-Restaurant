/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author INTEL
 */
@MultipartConfig
public class AddItem extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session= request.getSession();
        try{
            String itemName=request.getParameter("itemName");
            String p=request.getParameter("price");
            int price=Integer.parseInt(p);
            String category=request.getParameter("category");
            Part part=request.getPart("image");
            InputStream is=null;
            if(part!=null){
                is=part.getInputStream();
            }
            db.DbConnect db=new db.DbConnect();
            String s=db.addItem(itemName, price, category, is);
            if(s.equals("success")){
                session.setAttribute("msg", "Item Added Successfully");
                response.sendRedirect("aitem.jsp");
            }else{
                session.setAttribute("msg", "Failed! Please Try Again");
                response.sendRedirect("aitem.jsp");
                }
           
             } catch (SQLIntegrityConstraintViolationException e) {
                session.setAttribute("msg", "Item  Already Added ");
                response.sendRedirect("aitem.jsp");
            } catch (Exception ex) {
                session.setAttribute("msg", "Failed "+ex);
                response.sendRedirect("aitem.jsp");
        }
    }
}
