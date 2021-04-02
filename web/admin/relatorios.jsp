<%-- 
    Document   : relatorios
    Created on : 31/03/2021, 20:51:40
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <div id="relatorios">
           
                    <form action="" class="form-cont">
                        
                        <h3 class="title-relatorio">Total de compras por cliente</h3>
                        
                        <img id="img-relatorio" src="">
                        
                        <label for="start" id="label-start">Data de início </label>
                        <input type="text" id="start" name="início" placeholder="dd/mm/aaaa">
                        
                        <label for="end" id="label-end">Data de Término </label>
                        <input type="end" id="start" name="início" placeholder="dd/mm/aaaa">
                        
                        <input id="bt-rela" type="submit" value="Consultar" >
                    
                    </form>   
                   
       
     
                    <form action="" class="form-cont">
                        
                        <h3 class="title-relatorio">Produtos indisponíveis no estoque</h3>

                        <img id="img-relatorio" src="">
                        
                        <label for="start" id="label-start">Data de início </label>
                        <input type="text" id="start" name="início" placeholder="dd/mm/aaaa">
                        
                        <label for="end" id="label-end">Data de Término </label>
                        <input type="end" id="start" name="início" placeholder="dd/mm/aaaa">
                        
                        <input  id="bt-rela" type="submit" value="Consultar" >
                    
                    </form>     
                        
                    <form action="" class="form-cont">
                    
                        <div class="title-relatorio"> <h3 >Total de compras por data</h3></div>    
                        
                        <img id="img-relatorio" src="">
                        
                        <label for="start" id="label-start">Data de início </label>
                        <input type="text" id="start" name="início" placeholder="dd/mm/aaaa">
                        
                        <label for="end" id="label-end">Data de Término </label>
                        <input type="end" id="start" name="início" placeholder="dd/mm/aaaa">
                        
                        <input  id="bt-rela" type="submit" value="Consultar" >
                    
                    </form>     
                   
           
        </div>

            
        
    </body>
</html>
