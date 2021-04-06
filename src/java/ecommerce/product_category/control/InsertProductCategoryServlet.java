package ecommerce.product_category.control;

import ecommerce.product_category.model.ProductCategory;
import ecommerce.product_category.model.ProductCategoryDAO;
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
public class InsertProductCategoryServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // entrada
        String productId = request.getParameter("productId");
        String categoryId = request.getParameter("categoryId");
        ProductCategoryDAO productCategoryDAO = new ProductCategoryDAO();
        
        // processamento
        boolean success = false;
        String message = "Algo correu errado!";
        try {
            ProductCategory productCategory = new ProductCategory();
            productCategory.setProductId(Integer.parseInt(productId));
            productCategory.setCategoryId(Integer.parseInt(categoryId));
            productCategoryDAO.insert(productCategory);
            success = true;
            message = "Inserido com sucesso!";
        } catch (Exception ex) {
            message = ex.getMessage();
        }
        // saída
        request.setAttribute("status", success);
        request.setAttribute("message", message);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("ListCategoriesServlet");
        requestDispatcher.forward(request, response);
    }

}
