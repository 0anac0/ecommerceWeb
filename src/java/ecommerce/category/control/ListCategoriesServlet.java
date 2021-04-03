/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.category.control;

import ecommerce.category.model.Category;
import ecommerce.category.model.CategoryNegocio;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anacl
 */
public class ListCategoriesServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoryNegocio categoryNegocio = new CategoryNegocio();
        List<Category> categories = new ArrayList<>();
        String message = "";
        try {
            categories = categoryNegocio.findAll();
        } catch (Exception ex) {
            message = ex.getMessage();
        }
        request.setAttribute("message", message);
        request.setAttribute("categories", categories);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/categories.jsp");
        requestDispatcher.forward(request, response);
    }

}
