/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.category.control;

import ecommerce.category.model.Category;
import ecommerce.category.model.CategoryNegocio;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author anacl
 */
public class UpdateCategoryServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Entrada
        String categoryId = (String) request.getParameter("id");
        
        boolean success = false;
        
        request.setAttribute("status", success);
        String message = "Categoria não localizada";
        
        String previousMessage = (String) request.getAttribute("message");
        if (previousMessage != null){
            message = previousMessage;
        }
        //Processamento
        if (categoryId != null) {
            try {
                int id = Integer.parseInt(categoryId);
                CategoryNegocio categoryNegocio = new CategoryNegocio();
                Category c = categoryNegocio.find(id);
                request.setAttribute("category", c);
                success = true;
                message = null;
            } catch (Exception ex) {
                message = ex.getMessage();
            }
        }
        
        //Saída        
        request.setAttribute("status", success);
        request.setAttribute("message", message);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/edit-category.jsp");
        requestDispatcher.forward(request, response);
    }

}

