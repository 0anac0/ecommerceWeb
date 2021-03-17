<%-- 
    Document   : principal
    Created on : 28/02/2021, 22:32:56
    Author     : anacl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ecommerce.client.model.Client"%>
<%
    if (session.getAttribute("user") == null){
        request.setAttribute("status", false);
        request.setAttribute("message", "Por favor faça login para acessar essa pagina");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("login-cadastro.jsp");
        requestDispatcher.forward(request, response);  
    } else {
        Client client = (Client) session.getAttribute("user");
%>

<!DOCTYPE html>
<html>
    <head>
        <%@ include file="components/imports.jsp" %> 
    </head>
    <body>
        <%@ include file="components/header.jsp" %> 
        <h1>Hello World, <%= client.getName()  %>!</h1>
        <a href="LogoutServlet">Sair</a>
    </body>
</html>
<%
  }
%>