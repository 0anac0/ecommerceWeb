/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.product.control;

import ecommerce.product.model.Product;
import ecommerce.product.model.ProductNegocio;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ApplyUpdateProductServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Entrada
        int id = Integer.parseInt(request.getParameter("id"));
        String name = (String) request.getParameter("name");
        String description = (String) request.getParameter("description");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        float price = Float.parseFloat(request.getParameter("price"));
        String image = (String) request.getParameter("image");
        
        ProductNegocio productNegocio = new ProductNegocio();
        boolean success = false;
        String message = "Não foi possível alterar o produto";
        //Processamento
        try {
            Product p = new Product();
            p.setId(id);
            p.setName(name);
            p.setDescription(description);
            p.setQuantity(quantity);
            p.setPrice(price);
            p.setImage(image);
            productNegocio.update(p, id);
            success = true;
            message = "Produto atualizado com sucesso";
        } catch (Exception ex) {
            message = ex.getMessage();
        }
        
        //Saída        
        request.setAttribute("status", success);
        request.setAttribute("message", message);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("ListProductsServlet");
        requestDispatcher.forward(request, response);
    }

}

