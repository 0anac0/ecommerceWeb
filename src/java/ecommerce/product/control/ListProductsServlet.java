/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.product.control;

import ecommerce.product.model.Product;
import ecommerce.product.model.ProductNegocio;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anacl
 */
public class ListProductsServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductNegocio productNegocio = new ProductNegocio();
        List<Product> products = new ArrayList<>();
        String message = "";
        try {
            products = productNegocio.obterTodos();
        } catch (Exception ex) {
            message = ex.getMessage();
        }
        request.setAttribute("message", message);
        request.setAttribute("products", products);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/products.jsp");
        requestDispatcher.forward(request, response);
    }

}
