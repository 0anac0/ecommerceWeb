<%-- 
    Document   : relatorios
    Created on : 31/03/2021, 20:51:40
    Author     : carlo
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Relatórios</title>
          <%@ include file="../components/imports.jsp" %> 
    </head>
    <body>
    <%@ include file="../components/header.jsp" %> 
   
    
        <h1 id="title">Relatórios</h1>
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
        <div id="relatorios">
           
                    <form action="/ecommerceWeb/ClientSalesReportServlet" class="form-cont">
                        
                        <h3 class="title-relatorio">Total de compras por cliente</h3>
                        
                        <img id="img-relatorio" src="">
                        
                        <label for="start" id="label-start">Data de início </label>
                        <input type="date" id="start" name="begin" placeholder="dd/mm/aaaa" required>

                        <label for="end" id="label-end">Data de Término </label>
                        <input type="date" id="start" name="end" placeholder="dd/mm/aaaa" required>

                        <input id="bt-rela" type="submit" value="Consultar" >
                    
                    </form>   
                   
       
     
                    <form action="/ecommerceWeb/ProductsOutOfStockReportServlet" class="form-cont">
                        
                        <h3 class="title-relatorio">Produtos indisponíveis no estoque</h3>

                        <img id="img-relatorio" src="">

                        <input  id="bt-rela" type="submit" value="Consultar" >
                    
                    </form>

                    <form action="/ecommerceWeb/SalesReportServlet" class="form-cont">

                        <div class="title-relatorio"> <h3 >Total de compras por data</h3></div>    

                        <img id="img-relatorio" src="">

                        <label for="start" id="label-start">Data de início </label>
                        <input type="date" id="start" name="begin" placeholder="dd/mm/aaaa" required>

                        <label for="end" id="label-end">Data de Término </label>
                        <input type="date" id="start" name="end" placeholder="dd/mm/aaaa" required>

                        <input  id="bt-rela" type="submit" value="Consultar" >

                    </form>     


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
