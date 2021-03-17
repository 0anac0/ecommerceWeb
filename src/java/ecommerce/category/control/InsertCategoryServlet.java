/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.category.control;

import ecommerce.category.model.Category;
import ecommerce.category.model.CategoryNegocio;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author anacl
 */
public class InsertCategoryServlet extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // entrada
        String description = request.getParameter("description");
        // processamento
        Category category = new Category();
        category.setDescription(description);
        
        boolean success = false;
        String message = "Deu tudo certo!";
        CategoryNegocio categoryNegocio = new CategoryNegocio();
        try {
            categoryNegocio.insert(category);
            success = true;
        } catch (Exception ex) {
            message = ex.getMessage();
        }
        // saída
        request.setAttribute("status", success);
        request.setAttribute("message", message);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/new-category.jsp");
        requestDispatcher.forward(request, response);
    }

}
