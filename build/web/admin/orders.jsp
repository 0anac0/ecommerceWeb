<%-- 
    Document   : orders
    Created on : 02/04/2021, 21:12:40
    Author     : anacl
--%>

<%@ page import="java.util.List" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ecommerce.admin.model.Admin"%>
<%@page import="ecommerce.order.model.Order"%>

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
                List<Order> orders = (List) request.getAttribute("orders");
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
                  <th>Data de criação</th>
                  <th>Cliente</th>
                  <th>Valor total</th>
                  <th> </th>
                </tr>
                <%
                    for (Order order : orders) {
                %>
                <tr>
                  <td><%=order.getCreatedAt()%></td>
                  <td><%=order.getClient().getName()%></td>
                  <td>R$<%=order.getTotal()%></td>
                  <td><a href="DeleteOrderServlet?id=<%=order.getId()%>">Excluir pedido</a></td>
                </tr>
                <%
                }
                %>
            </table>
            <%
            if (request.getAttribute("message") != null) {
                boolean status = true;
                if (request.getAttribute("status") != null ) {
                  status = Boolean.parseBoolean(request.getAttribute("status").toString());
                }
                final String classe = status ? "alert-amarelo" : "alert";
                %>
                    <div id="<%=classe %>"><%=request.getAttribute("message")%></div>
                <%
            }    
            %>
        </div>
        
    </body>
    
</html>
<%      } else {
            
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("../client.jsp");
            requestDispatcher.forward(request, response);
        }
    }
  }
%>
