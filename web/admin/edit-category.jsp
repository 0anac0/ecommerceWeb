<%-- 
    Document   : edit-category
    Created on : 04/04/2021, 22:12:54
    Author     : anacl
--%>
<%@ page import="java.util.List" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ecommerce.admin.model.Admin"%>
<%@page import="ecommerce.category.model.Category"%>
<%@page import="ecommerce.product.model.Product"%>
<%@page import="ecommerce.product.model.ProductNegocio"%>
<%@page import="ecommerce.product_category.model.ProductCategory"%>

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
                Category category = (Category) request.getAttribute("category");
                if (category == null) {
                    request.setAttribute("message", "Categoria não encontrada");
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("../ListCategoriesServlet");
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

        <div class="form-container">
            
            <form id="containerLogin" action="ApplyUpdateCategoryServlet" method="post">
                <h2 id="title">Altere a categoria <%= category.getId()%>!</h2>
                
                <input type="number" name="id" hidden value="<%= category.getId()%>"required>
                <input id="description" type="text"  name="description" value="<%= category.getDescription()%>" placeholder="Digite a descrição" required>
                <button class="bt-form" type="submit" >Alterar</button>
            </form>
                
        <h2 id="title">Produtos associados a categoria <%= category.getId()%>!</h2>
        
        <section id="cart-content">

            <div id='carro-itens'>
           <%
                List<ProductCategory> productCategories = category.getProductCategories();
                for (ProductCategory pc : productCategories) {
                    Product product = pc.getProduct();
            %>
                <div class="compras" style="margin-bottom: 6px;">
                    <div class="compras-info compra-carrinho">
                            <div id="dados">
                                <p ><%= product.getName()%></p>
                                <a href="/ecommerceWeb/DeleteProductCategoryServlet?productId=<%= product.getId()%>&categoryId=<%=category.getId()%>">
                                    <img src='img/cancel.svg' width="10" height='10'>
                                </a>
                            </div>
                    </div>
                </div>
            <%
                }
            %>
                <div class="compras" style="margin-bottom: 6px;">
                    <div class="compras-info compra-carrinho">
                            <div id="dados">
                                <p >Nova associação</p>
                            </div>
                            <form action="InsertProductCategoryServlet">
                               
                                <input type="number" name="categoryId" hidden value="<%= category.getId()%>" required>
                                <select name="productId" required>
                                    <%
                                    ProductNegocio productNegocio = new ProductNegocio();
                                    List<Product> products = productNegocio.findAll();
                                    for (Product product : products) {
                                    %>
                                    <option value="<%=product.getId()%>"><%=product.getName()%></option>
                                    <%
                                    }
                                    %>
                                </select>
                                <button class="bt-form" type="submit" >Alterar</button>
                                        
                            </form>
                    
                    </div>
                </div>
        
            </div>
        </section>
        </div>

    </body>
    
</html>
<%         }
        } else {

            
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("../client.jsp");
            requestDispatcher.forward(request, response);
        }
    }
  }
%>