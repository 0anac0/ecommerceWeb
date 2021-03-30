<%-- 
    Document   : products
    Created on : 29/03/2021, 22:22:40
    Author     : anacl
--%>
<%@ page import="java.util.List" %>

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
                List<Product> products = (List) request.getAttribute("products");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="../components/imports.jsp" %>  
    </head>
    <body>
        <%@ include file="../components/header.jsp" %> 
        <div class="container-produtos">
           <h2 id="title">Produtos:</h2>

            <table>
                <tr>
                  <th>Nome</th>
                  <th>Descrição</th>
                  <th>Preço</th>
                  <th>Imagem</th>
                  <th>Quantidade</th>
                  <th> </th>
                </tr>
                <%
                    for (Product product : products) {
                %>
                <tr>
                  <td><%=product.getName()%></td>
                  <td><%=product.getDescription()%></td>
                  <td><%=product.getPrice()%></td>
                  <td><%=product.getImage()%></td>
                  <td><%=product.getQuantity()%></td>
                  <td>Editar</td>
                </tr>
                <%
                }
                %>
            </table>
            <div id="options">
                <div class="adm-opt">
                     <h3><a href="/ecommerceWeb/admin/new-product.jsp">Adicionar Produto</a></h3> 
                </div>
            </div>
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
