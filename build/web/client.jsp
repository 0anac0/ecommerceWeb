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
        

        <!--Container de formulários-->
        
        <div class="form-container">
            
            <div id="containerLogin">

                <h2 id="title">Dados de Cadastro</h2>

                <div class="nome-icon">
                    <h3 class="namelabel">Nome</h3>
                    <img src="/ecommerceWeb/img/user.svg" id="icons">       

                </div>
                
                <p  class="p-dados"><%= client.getName()  %></p>
                <div id="linha"></div>
                <div class="nome-icon">
                    <h3 class="namelabel">Email</h3>
                    <img src="/ecommerceWeb/img/mail.svg" id="icons">

                </div>
               
                <p  class="p-dados"><%= client.getEmail()  %></p>
                <div id="linha"></div>

                <h3 class="namelabel">Endereço</h3>
                <p  class="p-dados"><%= client.getAddress() %></p>
                <div id="linha"></div>
                 <button class="bt-form"  onclick="edita()">Editar</button>
                <h2 id="title">Minhas Compras</h2>
                <div class="compras">
                    <div class="compras-info">
                        <div id="dados">
                            <p > <strong>Pedido</strong> realizado: xx de xxx de xxx</p>
                            <p > <strong>Enviar para:</strong> Fulano de tal tal</p>
                            <p id="preco">Total</p>
                            
                        </div>
                        <img href="img/orfeu.png">
                        
                    </div>
                  
                </div>
                 <div class="compras">
                    <div class="compras-info">
                        <div id="dados">
                            <p > <strong>Pedido</strong> realizado: xx de xxx de xxx</p>
                            <p > <strong>Enviar para:</strong> Fulano de tal tal</p>
                            <p id="preco">Total</p>
                            
                        </div>
                        <div class="produtos-comprados">
                            
                                  <li  class="produto">
                                    <img href="img/orfeu.png" alt="café mineiro">
                                    <p>Nome do produto</p>
                                  <p>Preço</p>
                                  </li>
                                   <li  class="produto">
                                    <img href="img/orfeu.png" alt="café mineiro">
                                    <p>Nome do produto</p>
                                  <p>Preço</p>
                                  </li>
                      
                        </div>
                        
                        
                    </div>
                  
                </div>
           
              
                <div id="bts">
                    
                    <a href="AlterClientServlet"><button class="delete" >Editar</button></a>
                    <a href="DeleteClientServlet"><button class="delete" >Apagar Cadastro</button></a>
                    <a href="LogoutServlet"><button class="delete" >Sair</button></a>

                </div>   
               
        </div>
       
    </body>
</html>
<%
  }
%>