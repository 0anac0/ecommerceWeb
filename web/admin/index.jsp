<%-- 
    Document   : index
    Created on : 17/03/2021, 17:27:16
    Author     : anacl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ecommerce.admin.model.Admin"%>
<%
    if (session.getAttribute("user") == null){
        request.setAttribute("status", false);
        request.setAttribute("message", "Por favor faça login para acessar essa pagina");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
        requestDispatcher.forward(request, response);
    } else {
        if (session.getAttribute("admin") == null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("../client.jsp");
            requestDispatcher.forward(request, response);
        } else {
            
            if((Boolean) session.getAttribute("admin") == true){
                Admin admin = (Admin) session.getAttribute("user");
%>

<!DOCTYPE html>
<html>
    <head>
        <%@ include file="../components/imports.jsp" %> 
    </head>
    <body>
        <%@ include file="../components/header.jsp" %> 
        <h1>Hello World, <%= admin.getName()  %>!</h1>
        <div>
            Seus dados, <%= admin.getName()  %>:
            </br>
            <%= admin.getName()  %>
            
            </br>
            <%= admin.getLogin()  %>
        </div>
        <a href="new-product.jsp">Cadastre um novo produto!</a>
        <a href="new-category.jsp">Cadastre uma nova categoria!</a>
        <a href="../LogoutServlet">Sair</a>
    </body>
</html>
<%      } else {
            
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("../client.jsp");
            requestDispatcher.forward(request, response);
        }
    }
  }
%>