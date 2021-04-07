<%-- 
    Document   : update-product-image
    Created on : 29/03/2021, 21:33:22
    Author     : anacl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ecommerce.admin.model.Admin"%>
<%@page import="ecommerce.product.model.Product"%>

<%
    if (session.getAttribute("user") == null){
        request.setAttribute("status", false);
        request.setAttribute("message", "Por favor faça login para acessar essa pagina");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
        requestDispatcher.forward(request, response);
    } else {
        if (session.getAttribute("admin") == null) {
            request.setAttribute("status", false);
            request.setAttribute("message", "Por favor faça login de admin para acessar essa pagina");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(request, response);
        } else {
            
            if((Boolean) session.getAttribute("admin") == true){
                Admin admin = (Admin) session.getAttribute("user");
                Product product = (Product) request.getAttribute("product");
                if (product == null) {
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
                    requestDispatcher.forward(request, response);
                }
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="../components/imports.jsp" %>  
    </head>
    <body>
        <%@ include file="../components/header.jsp" %> 
          <%
            if (request.getAttribute("message") != null && request.getAttribute("status") != null ) {
                boolean status = Boolean.parseBoolean(request.getAttribute("status").toString());
                final String id = status ? "alert-amarelo" : "alert";
        %>
                <div id="<%=id%>" onclick='alerta()'>
                    <% if (id == "alert") { %>
                        <a><img src="/ecommerceWeb/img/cancel.svg" width="20" height="20"></a>
                    <% } %>
                    <p><%=request.getAttribute("message")%></p>
                </div>
        <%
            }
        %>
        <h2 id="title">Editar imagem do produto </h2>
        <h3 class="sub-title">"<%=product.getName()%>"!</h3>
            <div class="form-container">
            
                <form action="UploadProductImageServlet" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="id" value="<%=product.getId()%>">

                    <div>Foto atual:</div>

                    <%
                    if (product.getImage() ==null || product.getImage().trim().length() == 0) {
                    %>
                    <div>Este produto não possui imagem ainda.</div>
                    <%  } else { %>
                    <div><a href="DownloadProductImageServlet?id=<%=product.getId()%>">Baixar imagem do produto</a></div>
                    <%
                    }
                    %>
                    <div>Nova foto:</div>
                    <div><input type="file" name="image"></div>
                    <input type="submit" value="Atualizar foto">
                </form>    
            </div>
    </body>
</html>
<%      } else {
            
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("../client.jsp");
            requestDispatcher.forward(request, response);
        }

    }
  }
%>