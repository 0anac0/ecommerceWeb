<%-- 
    Document   : principal
    Created on : 28/02/2021, 22:32:56
    Author     : anacl
--%>

<%@ page import="java.util.List" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ecommerce.client.model.Client"%>
<%@ page import="ecommerce.order.model.Order" %>
<%@ page import="ecommerce.order_item.model.OrderItem" %>
<%@ page import="ecommerce.product.model.Product" %>

<%
    if (session.getAttribute("user") == null){
        request.setAttribute("status", false);
        request.setAttribute("message", "Por favor faça login para acessar essa pagina");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("login-cadastro.jsp");
        requestDispatcher.forward(request, response);  
    } else {
        Client client = (Client) session.getAttribute("user");
        List<Order> orders = client.getOrders();

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
                    <a href="edit-client.jsp"><button class="bt-form">Editar</button></a>
                <div id="bts">
                    
                    <a href="DeleteClientServlet"><button class="delete" >Apagar Cadastro</button></a>
                    <a href="LogoutServlet"><button class="delete" >Sair</button></a>

                </div>   
                
                    
                    
                <h2 id="title">Minhas Compras</h2>
                <div class="compras">
                    <% if (orders.size() == 0) {%>
                        Não há compras ainda!
                    <%
                    }%>
                <%
                for (Order order : orders){
                    List<OrderItem> orderItems = order.getOrderItems();
                %>
                
                    <div class="compras-info">
                        <div id="dados">
                            <p > <strong>Pedido</strong> realizado: <%= order.getCreatedAt()%></p>
                            <p id="preco">Total: R$<%= order.getTotal()%></p>

                        </div>
                        <div class="produtos-comprados">
                            <%
                            for (OrderItem item : orderItems) {
                                Product product = item.getProduct();
                            %>    
                                  <li  class="produto">
                                    <img class="produto-img" src="img/orfeu.png" alt="café mineiro">
                                    <p><%= product.getName()%></p>
                                    <p><%= item.getQuantity()%> unids.</p>
                                  <p>R$ <%= item.getPrice()%></p>
                                  </li>
                            <%
                            }
                            %>
                        </div>
                        
                        
                    </div>
                  
                
                <%
                }
                %>
           
                </div>
              
               
        </div>
       
    </body>
</html>
<%
  }
%>