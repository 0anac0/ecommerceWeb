<%-- 
    Document   : edit-product
    Created on : 02/04/2021, 23:50:30
    Author     : anacl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ecommerce.admin.model.Admin"%>
<%@page import="ecommerce.product.model.Product"%>

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
                Product product = (Product) request.getAttribute("product");
                if (product == null) {
                    request.setAttribute("message", "Produto não encontrado");
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("../ListProductsServlet");
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
        <div class="form-container">
            
            <form id="containerLogin" action="ApplyUpdateProductServlet" method="post">
                <h2 id="title">Altere o produto <%= product.getId()%>!</h2>
                
                <input type="number" name="id" hidden value="<%= product.getId()%>"required>
                <input id="nome" type="text" name="name" value="<%= product.getName()%>" placeholder="Nome" required>
                <input id="description" type="text"  name="description" value="<%= product.getDescription()%>" placeholder="Digite a descrição" required>
                <input id="image" type="text" name="image" placeholder="Insira url da imagem" value="<%= product.getImage()%>" required>
                <input id="quantity" type="number" name="quantity" placeholder="Quantidade em estoque" value="<%= product.getQuantity()%>" required> 
                <input id="price" type="number"  name="price" step="0.01" value="<%= product.getPrice()%>" placeholder="Preço" required>
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
<%         }
        } else {

            
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("../client.jsp");
            requestDispatcher.forward(request, response);
        }
    }
  }
%>