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
        
        <div id="options">
            <div class="adm-opt">
                <h3><a href="/ecommerceWeb/admin/data.jsp">Cadastro</a></h3>
            </div>
            <div class="adm-opt">
                <h3><a href="/ecommerceWeb/admin/new-category.jsp"> Adicionar Categorias</a></h3>
            </div>
            <div class="adm-opt">
                 <h3><a href="/ecommerceWeb/admin/new-product.jsp">Adicionar Produtos</a></h3> 
            </div>
            
            <div class="adm-opt delete">
                 <h3 ><a href="/ecommerceWeb/LogoutServlet">Sair</a></h3> 
            </div>
                
                
        </div>
        <a href="../LogoutServlet">Sair</a>
    </body>/
</html>
<%      } else {
            
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("../client.jsp");
            requestDispatcher.forward(request, response);
        }
    }
  }
%>