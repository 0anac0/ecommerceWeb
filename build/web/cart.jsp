<%-- 
    Document   : cart
    Created on : 02/03/2021, 23:24:22
    Author     : anacl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ecommerce.client.model.Client"%>

<%@ page import="java.util.List" %>
<%@ page import="ecommerce.cart.model.CartNegocio" %>
<%@ page import="ecommerce.cart.model.CartItem" %>
<%@ page import="ecommerce.product.model.Product" %>

<!DOCTYPE html>
<html>
    <head>
        <%@ include file="components/imports.jsp" %> 
    </head>
    <body>
        <%@ include file="components/header.jsp" %> 
        
        <h2 id="title">Seu carrinho!</h2>
        
        <!--Produtos-->
        
        <section id="cart-content">

            <div id='carro-itens'>
           <%
                List<CartItem> cartItems = CartNegocio.getCartItemsFromRequest(request);
                for (int i = 0; !cartItems.isEmpty() && i < cartItems.size(); i ++) {
                    CartItem cartItem = cartItems.get(i);
                    Product product = cartItem.getProduct();
            %>
                <div class="compras">
                    <div class="compras-info compra-carrinho">
                            <div id="dados">
                                <p ><%= product.getName()%></p>
                                <a href="RemoveCartItemServlet?productId=<%= product.getId()%>"><img src='img/cancel.svg' width="10" height='10'></a>
                            </div>
                            <img class="item-preview" src="img/produto1.svg"></img>
                            <p class="price">R$ <%= product.getPrice()*cartItem.getQuantity()%></p>
                            <p id="quantidade"><%= cartItem.getQuantity() %> </p>
                            <div id='produto-carac'>
                               
                                <a id="menos" href="AddCartItemServlet?productId=<%= product.getId()%>&quantity=-1">
                                    <img src="img/menos.svg" width="30" height='30'>
                                </a>
                                <a id="mais" href="AddCartItemServlet?productId=<%= product.getId()%>&quantity=1">
                                    <img src="img/plus.svg" width="30" height='30'>
                                </a>
                                

                            </div>


                    </div>
                </div>
                
            <%
                }
            %>
            <%
            if (!cartItems.isEmpty()) {
            %>     
                <a style="margin: auto;" href="InsertOrderServlet"><button id='bt-carrinho'>Fechar compra</button></a>
            <%
            } else {
            %>
            <div style="margin: auto;">
                <div class="compras"  > Carrinho vazio, escolha seus itens. </div></div>
            <%
            }
            %>
            </div>
            
        </section>
    </body>
</html>
