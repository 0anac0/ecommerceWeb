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

public class ApplyUpdateCategoryServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Entrada
        int id = Integer.parseInt(request.getParameter("id"));
        String description = (String) request.getParameter("description");
        
        CategoryNegocio categoryNegocio = new CategoryNegocio();
        boolean success = false;
        String message = "Não foi possível alterar a categoria";
        //Processamento
        try {
            Category c = new Category();
            c.setId(id);
            c.setDescription(description);
            categoryNegocio.update(c, id);
            success = true;
            message = "Categoria atualizada com sucesso";
        } catch (Exception ex) {
            message = ex.getMessage();
        }
        
        //Saída        
        request.setAttribute("status", success);
        request.setAttribute("message", message);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("ListCategoriesServlet");
        requestDispatcher.forward(request, response);
    }

}

