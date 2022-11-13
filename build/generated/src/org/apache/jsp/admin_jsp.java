package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class admin_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
      out.write("<head>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n");
      out.write("<title>E-Restaurant</title>\n");
      out.write("<style type=\"text/css\">\n");
      out.write("</style>\n");
      out.write("<link href=\"css/style.css\" rel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("<div class=\"container\">\n");
      out.write("  <div class=\"header\">\n");
      out.write("  \t<h5>E-Restaurant</h5>\n");
      out.write("    <p><a href=\"index.jsp\">Home</a></p>\n");
      out.write("  </div>\n");
      out.write("  <div class=\"block5\"></div>\n");
      out.write("<iframe src=\"slide.html\" style=\"width:960px;height:365px;max-width:100%;overflow:hidden;border:none;padding:0;margin:0 auto;\t\t\tdisplay:block;\" marginheight=\"0\" marginwidth=\"0\"></iframe>\n");
      out.write("  <div class=\"content\">\n");
      out.write("    <div class=\"welcome\">\n");
      out.write("      \t<h3>WELCOME TO OUR SITE</h3>\n");
      out.write("   \t    <img src=\"img/welcome.jpg\" width=\"202\" height=\"141\" />\n");
      out.write("    \t</div>\n");
      out.write("      <p>ERestaurant, offers guests a fun and casual dining experience. The locally owned neighborhood favorite boasts a menu full of mouth-watering appetizers, comfort all-American favorites, and delectable desserts. In the evenings, Geogeske transforms into a hip lounge with a full-service bar and a mix of sultry beats by local Deejays.</p>\n");
      out.write("    <div class=\"login\">\n");
      out.write(" \t    \t<h4>Admin Login Form</h4>\n");
      out.write(" \t      <div class=\"login1\">\n");
      out.write(" \t        Admin ID:<br/><br/><br/>\n");
      out.write("                Password:<br/><br/>\n");
      out.write(" \t      </div>\n");
      out.write("      <form action=\"ALogin\" method=\"post\">\n");
      out.write("          <div class=\"login2\">\n");
      out.write(" \t        <input  class=\"lform\"name=\"aid\" type=\"text\" /><br/><br/>\n");
      out.write("                <input class=\"lform\" name=\"pass\" type=\"password\" /><br/><br/>\n");
      out.write("                \n");
      out.write(" \t      </div>\n");
      out.write("          <div class=\"login3\">\n");
      out.write("          <input class=\"bluebutton\" value=\"Login\" type=\"submit\" />\n");
      out.write("        </form>\n");
      out.write("                ");

           String msg=(String)session.getAttribute("msg");
            if(msg!=null)  
            {
        
      out.write("\n");
      out.write("              <br/><br/>");
      out.print(msg);
      out.write("\n");
      out.write("        ");

            session.setAttribute("msg", null);
            }
        
      out.write("\n");
      out.write("        </div>  \n");
      out.write("      </div>   \n");
      out.write("  </div>\n");
      out.write("  <div class=\"footer\">Designed & Developed by  Britia</div>\n");
      out.write("    \n");
      out.write("</div>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
