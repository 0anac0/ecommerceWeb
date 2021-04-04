<%-- 
    Document   : edit-admin
    Created on : 04/04/2021, 18:05:42
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
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="../components/imports.jsp" %>  
    </head>
    <body>
        <%@ include file="../components/header.jsp" %> 
        
        <div class="form-container">
            
            <form id="containerLogin" action="/ecommerceWeb/UpdateAdminServlet" method="post">
                <h2 id="title">Modifique seus dados de Admin!</h2>
                <input id="nome" type="text" name="name" placeholder="Nome" value="<%= admin.getName() %>" required>
                <input id="email" type="email"  name="email" placeholder="Digite seu e-mail" value="<%= admin.getEmail() %>" required>
                <input id="login" type="text" name="login" placeholder="Digite seu login" value="<%= admin.getLogin() %>" required>
                <input id="senha" type="password"  name="password" placeholder="Digite sua senha" required>
                <button class="bt-form" type="submit" >Alterar</button>
                
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
<%
        } else {

            
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("../client.jsp");
            requestDispatcher.forward(request, response);
        }
    }
  }
%>
