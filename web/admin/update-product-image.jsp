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
                <h1>Hello World!</h1>
                <h1><%=product.getName()%></h1>
    </body>
</html>
<%      } else {
            
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("../client.jsp");
            requestDispatcher.forward(request, response);
        }

    }
  }
%>