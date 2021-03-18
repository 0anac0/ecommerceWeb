<%-- 
    Document   : new-category
    Created on : 17/03/2021, 19:12:54
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
            request.setAttribute("status", false);
            request.setAttribute("message", "Por favor faça login de admin para acessar essa pagina");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
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
        <div class="form-container">
            
            <form id="containerLogin" action="/ecommerceWeb/InsertCategoryServlet" method="post">
                <h2 id="title">Cadastre a nova categoria!</h2>
                <input id="description" type="text"  name="description" placeholder="Digite a descrição" required>
                <button class="bt-form" type="submit" >Inserir</button>
            </form>
        </div>
        <%
        if (request.getAttribute("message") != null && request.getAttribute("status") != null ) {
            boolean status = Boolean.parseBoolean(request.getAttribute("status").toString());
            final String classe = status ? "success" : "failure";
            %>
                <div class="<%=classe %>"><%=request.getAttribute("message")%></div>
            <%
        }    
        %>
    </body>
    
</html>
<%      } else {
            
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("../client.jsp");
            requestDispatcher.forward(request, response);
        }
    }
  }
%>