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
           
            <div id='carro-itens'>
                <div class="compras">
                    <div class="compras-info compra-carrinho">
                            <div id="dados">
                                <p >Café Cerrado Mineiro</p>
                                <a><img src='img/cancel.svg' width="10" height='10'></a>
                            </div>
                            <img class='item-preview' src="img/produto1.svg"></img>
                            <p class="price">R$ 35,00</p>
                            <input type="text" id="quantidade" name="name" value='1' required>
                            <div id='produto-carac'>
                               
                                <a id='mais'><img src="img/plus.svg" width="30" height='30'></a>
                                <a id='menos'><img src="img/menos.svg" width="30" height='30'></a>

                            </div>


                     </div>
                 </div>
                
                <div class="compras">
                    <div class="compras-info compra-carrinho">
                            <div id="dados">
                                <p >Café Cerrado Mineiro</p>
                                <a><img src='img/cancel.svg' width="10" height='10'></a>
                            </div>
                            <img class='item-preview' src="img/produto1.svg"></img>
                            <p class="price">R$ 35,00</p>
                            <input type="text" id="quantidade" name="name" value='1' required>
                            <div id='produto-carac'>
                               
                                <a id='mais'><img src="img/plus.svg" width="30" height='30'></a>
                                <a id='menos'><img src="img/menos.svg" width="30" height='30'></a>

                            </div>


                     </div>
                 </div>
                
                 
                 <button id='bt-carrinho' onclick="checkout()">Fechar compra</button> 
            </div>
            
          

            
        </section>
    </body>
</html>
