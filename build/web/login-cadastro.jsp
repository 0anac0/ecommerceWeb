<%-- 
    Document   : cadastrarCliente
    Created on : 28/02/2021, 20:55:09
    Author     : anacl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ecommerce.client.model.Client"%>

<!DOCTYPE html>
<html>
    <head>
        <%@ include file="components/imports.jsp" %> 
    </head>
    <body>
        <%@ include file="components/header.jsp" %> 
        <div class="form-container">
            
            <form id="containerLogin" action="LoginServlet" method="post">
                <h2 id="title">Faça o login</h2>
                <input id="login" name="login" type="text" placeholder="Digite seu login" required>
                <input id="senha" name="password" type="password" placeholder="Digite sua senha" required>
                <button class="bt-form" type="submit">Entrar</button>
                <a id="cadastro" onclick="cadastro()"><p>Não possui conta? Cadastre-se!</p></a>
                    
            </form>
            
            <form id="containerCadastro" action="InsertClientServlet" method="post">
                <h2 id="title">Faça seu cadastro</h2>
                <input id="nome" type="text" name="name" placeholder="Nome" required>
                <input id="email" type="email"  name="email" placeholder="Digite seu e-mail" required>
                <input id="login" type="text" name="login" placeholder="Digite seu login" required>
                <input id="address" type="text" name="address" placeholder="Ponha seu endereço" required> 
                <input id="senha" type="password"  name="password" placeholder="Digite sua senha" required>
                <button class="bt-form" type="submit" >Entrar</button>
                <a id="login-volta" href="login-cadastro.jsp"><p>Já possui conta? Faça o login</p></a>
                
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
