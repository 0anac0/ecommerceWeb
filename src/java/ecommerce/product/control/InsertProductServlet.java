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

/*
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
*/

/**
 *
 * @author anacl
 */
public class InsertProductServlet extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // entrada
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        double price = Double.valueOf(request.getParameter("price")) ;
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        // processamento
        Product product = new Product();
        product.setName(name);
        product.setQuantity(quantity);
        product.setPrice(price);
        product.setDescription(description);
        product.setImage(image);
        
        boolean success = false;
        String message = "Deu tudo certo!";
        ProductNegocio productNegocio = new ProductNegocio();
        try {
            productNegocio.insert(product);
            success = true;
        } catch (Exception ex) {
            message = ex.getMessage();
        }
        // saída
        request.setAttribute("status", success);
        request.setAttribute("message", message);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/new-product.jsp");
        requestDispatcher.forward(request, response);
    }
}
