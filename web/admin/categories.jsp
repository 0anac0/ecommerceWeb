<%-- 
    Document   : categories
    Created on : 29/03/2021, 22:44:16
    Author     : anacl
--%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ecommerce.admin.model.Admin"%>
<%@page import="ecommerce.category.model.Category"%>
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
                List<Category> categories = (List) request.getAttribute("categories");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="../components/imports.jsp" %>  
    </head>
    <body>
        <%@ include file="../components/header.jsp" %> 
        <div class="container-produtos">
           <h2 id="title">Categorias:</h2>

            <table>
                <tr>
                  <th>Descrição</th>
                  <th>Produtos</th>
                  <th> </th>
                </tr>
                <%
                    for (Category category : categories) {
                        List<Product> products = new ArrayList<>();
                        products = category.getProducts();
                %>
                <tr>
                  <td><%=category.getDescription()%></td>
                  
                  <td>
                    <% for (Product product : products) {%>
                    <span> <%=product.getName()%>;</span>
                    <%}%>
                  </td>
                  <td> <a href="/ecommerceWeb/UpdateCategoryServlet?id=<%=category.getId()%>">Editar</a></td>
                </tr>
                <%
                }
                %>
            </table>
            <div id="options">
                <div class="adm-opt">
                     <h3><a href="/ecommerceWeb/admin/new-category.jsp">Adicionar Categoria</a></h3> 
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
