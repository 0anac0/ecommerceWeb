<%-- 
    Document   : edit-client
    Created on : 17/03/2021, 23:37:44
    Author     : anacl
--%>
<%-- 
    Document   : cadastrarCliente
    Created on : 28/02/2021, 20:55:09
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
        <div class="form-container">
            
            <form id="containerLogin" action="AlterClientServlet" method="post">
                <h2 id="title">Modifique seus dados!</h2>
                <input id="nome" type="text" name="name" placeholder="Nome" value="<%= client.getName() %>" required>
                <input id="email" type="email"  name="email" placeholder="Digite seu e-mail" value="<%= client.getEmail() %>" required>
                <input id="login" type="text" name="login" placeholder="Digite seu login" value="<%= client.getLogin() %>" required>
                <input id="address" type="text" name="address" placeholder="Ponha seu endereço" value="<%= client.getAddress() %>" required> 
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
}
%>