<%-- 
    Document   : header
    Created on : 17/03/2021, 01:41:50
    Author     : anacl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<header>
    <!--Menu-->
    <nav class="cabecalho">
                     
        <li id="logo"><a href="/ecommerceWeb/index.jsp" ><img src="/ecommerceWeb/img/logo.svg"></a></li>
        <li id="tools">
            <div id="login">
                <%if (session.getAttribute("username") == null) { %>
                    <a href="/ecommerceWeb/login-cadastro.jsp">Entre <br>ou cadastre-se</a>
                <%
                } else {   
                %>
                    
                    <a href=<%=(session.getAttribute("admin") == null) ? "/ecommerceWeb/client.jsp" : "/ecommerceWeb/admin/index.jsp"%>>
                        <%= session.getAttribute("username") %>
                    </a>
                <%
                }
                %>
            </div>
            <a href="/ecommerceWeb/cart.jsp" ><img src="/ecommerceWeb/img/carrinho.svg" alt="carrihnos de compras"> </a>
        </li>
                
    </nav>

</header>
