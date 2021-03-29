<%-- 
    Document   : index
    Created on : 28/02/2021, 20:16:09
    Author     : anacl
--%>

<%@ page import="java.util.List" %>

<%@ page import="ecommerce.product.model.ProductNegocio" %>
<%@ page import="ecommerce.product.model.Product" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
        
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="components/imports.jsp" %> 
    </head>
    <body>
        <%@ include file="components/header.jsp" %> 
        <h2 id="title">Os melhores cafés do Brasil!</h2>
        
        <!--Produtos-->
        
        <section id="content">
            <%  ProductNegocio productNegocio = new ProductNegocio();
                List<Product> products = productNegocio.mockObter();
                for (int i = 0; i < products.size(); i++){
            %>
            <article class="item"> <img src="img/produto1.svg"></img>
                <h3><%=products.get(i).getName()%></h3>
                <p class="price">R$ <%=products.get(i).getPrice()%></p>
                <button class="bt-comprar">Comprar</button>
            </article>
        <%
            }
        %>
        </section>

    </body>
</html>
