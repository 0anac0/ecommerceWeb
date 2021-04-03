<%-- 
    Document   : login
    Created on : 17/03/2021, 17:30:40
    Author     : anacl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ecommerce.admin.model.Admin"%>
<%
    if (session.getAttribute("user") != null && session.getAttribute("admin") != null){
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    } else {
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="../components/imports.jsp" %> 
    </head>
    <body>
        <%@ include file="../components/header.jsp" %> 
        <div class="form-container">
            
            <form id="containerLogin" action="/ecommerceWeb/LoginAdminServlet" method="post">
                <h2 id="title">Login do Admin</h2>
                <input id="login" name="login" type="text" placeholder="Digite seu login" required>
                <input id="senha" name="password" type="password" placeholder="Digite sua senha" required>
                <button class="bt-form" type="submit">Entrar</button>                
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
            </form>
        </div>
        
    </body>
</html>

<%
    }
%>
