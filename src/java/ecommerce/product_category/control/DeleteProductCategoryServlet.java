/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.product_category.control;

import ecommerce.product_category.model.ProductCategoryDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anacl
 */
public class DeleteProductCategoryServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Entrada
        String productId = request.getParameter("productId");
        String categoryId = request.getParameter("categoryId");
        ProductCategoryDAO productCategoryDAO = new ProductCategoryDAO();
        String message = "algo não correu corretamente!";
        boolean success = false;
        // Processamento
        try {
            productCategoryDAO.delete(Integer.parseInt(productId),
                                      Integer.parseInt(categoryId));
            success = true;
            message = "Removido com sucesso!";
        } catch (Exception ex) {
            message = ex.getMessage();
        }
        
        // Saída
        request.setAttribute("status", success);
        request.setAttribute("message", message);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("UpdateCategoryServlet?id="+categoryId);
        requestDispatcher.forward(request, response);
    
    }
}
