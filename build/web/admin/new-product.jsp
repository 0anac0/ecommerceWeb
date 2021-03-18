<%-- 
    Document   : product-cadastro
    Created on : 15/03/2021, 01:23:51
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
        <div class="form-container">
            
            <form id="productCadastro" action="../InsertProductServlet" method="post">
                <h2 id="title">Cadastre o novo produto!</h2>
                <input id="nome" type="text" name="name" placeholder="Nome" required>
                <input id="description" type="text"  name="description" placeholder="Digite a descrição" required>
                <input id="image" type="text" name="image" placeholder="Insira url da imagem" required>
                <input id="quantity" type="number" name="quantity" placeholder="Quantidade em estoque" required> 
                <input id="price" type="number"  name="price" step="0.01" placeholder="Preço" required>
                <button class="bt-form" type="submit" >Inserir</button>
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
<%      } else {
            
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("../client.jsp");
            requestDispatcher.forward(request, response);
        }
    }
  }
%>