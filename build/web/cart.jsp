<%-- 
    Document   : cart
    Created on : 02/03/2021, 23:24:22
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
        
        <h2 id="title">Seu carrinho!</h2>
        
        <!--Produtos-->
        
        <section id="cart-content">
           
            <article class="cart-item"> 
                <img class='item-preview' src="img/produto1.svg"></img>
                <div class='item-info'>
                    <h3>Café Cerrado Mineiro</h3>
                    <p class="price">R$ 35,00</p>
                    <div class="quantity"> 
                        <p>Quantity</p> 
                        <input type="number" class="quantity-input" value='10' required>
                    </div>
                </div>
            </article>

            <article class="cart-item"> 
                <img class='item-preview' src="img/produto1.svg"></img>
                <div class='item-info'>
                    <h3>Café Cerrado Mineiro</h3>
                    <p class="price">R$ 35,00</p>
                    <div class="quantity"> 
                        <p>Quantity</p> 
                        <input type="number" class="quantity-input" value='10' required>
                    </div>
                </div>
            </article>

            <button class="bt-form" onclick="checkout()">Fechar compra</button> 
        </section>
    </body>
</html>
