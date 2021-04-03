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

public class UpdateProductServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Entrada
        String productId = (String) request.getParameter("id");
        
        boolean success = false;
        String message = "Produto não localizado ";
        //Processamento
        if (productId != null) {
            try {
                int id = Integer.parseInt(productId);
                ProductNegocio productNegocio = new ProductNegocio();
                Product p = new Product();
                p = productNegocio.obter(id);
                request.setAttribute("product", p);
                success = true;
                message = null;
            } catch (Exception ex) {
                message = ex.getMessage();
            }
        } else {
            message = "Produto não localizado";
        }
        
        //Saída        
        request.setAttribute("status", success);
        request.setAttribute("message", message);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/edit-product.jsp");
        requestDispatcher.forward(request, response);
    }

}