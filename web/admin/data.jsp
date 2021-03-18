<%-- 
    Document   : data
    Created on : 17/03/2021, 22:45:29
    Author     : anacl
--%>
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
        <div>
            Seus dados, <%= admin.getName()  %>:
            </br>
            <%= admin.getName()  %>
            
            </br>
            <%= admin.getLogin()  %>
        </div>
        
        <div class="form-container">
            
            <div id="containerLogin">

                <h2 id="title">Dados de Cadastro</h2>

                <div class="nome-icon">
                    <h3 class="namelabel">Nome</h3>
                    <img src="/ecommerceWeb/img/user.svg" id="icons">

                </div>
                <p  class="p-dados"><%= admin.getName()  %></p>
                <div id="linha"></div>


                <div class="nome-icon">
                    <h3 class="namelabel">Email</h3>
                    <img src="/ecommerceWeb/img/mail.svg" id="icons">
                </div>
               
                <p  class="p-dados"><%= admin.getEmail()  %></p>

                <div id="linha"></div>

                                <div class="nome-icon">
                    <h3 class="namelabel">Login</h3>
                    <img src="/ecommerceWeb/img/mail.svg" id="icons">
                </div>
               
                <p  class="p-dados"><%= admin.getLogin()  %></p>
                <div id="linha"></div>
              
                <div id="bts">
                    <button class="bt-form"  onclick="edita()">Editar</button>
                    <button class="delete" >Apagar Cadastro</button>
                    <button class="delete" ><a href="LogoutServlet">Sair</a></button>

                </div>    
           
        </div>
    </body>
</html>
<%      } else {
            
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("../index.jsp");
            requestDispatcher.forward(request, response);
        }
    }
  }
%>