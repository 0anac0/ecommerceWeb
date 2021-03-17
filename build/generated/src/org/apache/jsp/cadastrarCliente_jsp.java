package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class cadastrarCliente_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>SMD Ecommerce</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/style.css\"> \n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1>Cadastro do cliente</h1>\n");
      out.write("        <form action=\"InsertClientServlet\" method=\"post\">\n");
      out.write("            <div>Name:</div>\n");
      out.write("            <div><input type=\"text\" name=\"name\" /></div>\n");
      out.write("            <div>Login:</div>\n");
      out.write("            <div><input type=\"text\" name=\"login\" /></div>\n");
      out.write("            <div>Email:</div>\n");
      out.write("            <div><input type=\"text\" name=\"name\" /></div>\n");
      out.write("            <div>Address:</div>\n");
      out.write("            <div><input type=\"text\" name=\"address\" /></div>\n");
      out.write("            <div>Senha:</div>\n");
      out.write("            <div><input type=\"password\" name=\"password\" /></div>\n");
      out.write("            <div><input type=\"submit\" value=\"Signin\" />Entrar</div>\n");
      out.write("        </form>\n");
      out.write("        ");

        if (request.getAttribute("message") != null && request.getAttribute("status") != null ) {
            boolean status = Boolean.parseBoolean(request.getAttribute("status".toString()));
            final String classe = status ? "success" : "failure";
            
      out.write("\n");
      out.write("                <div class=\"");
      out.print(classe );
      out.write('"');
      out.write('>');
      out.print(request.getAttribute("message"));
      out.write("</div>\n");
      out.write("            ");

        }    
        
      out.write("\n");
      out.write("    </body>\n");
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
