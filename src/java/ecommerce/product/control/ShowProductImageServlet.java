package ecommerce.product.control;

import ecommerce.product.model.Product;
import ecommerce.product.model.ProductNegocio;
import jakarta.servlet.RequestDispatcher;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/*import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*

/**
 *
 * @author anacl
 */
public class ShowProductImageServlet extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        ProductNegocio productNegocio = new ProductNegocio();
        Product product = null;
        try {
            product = productNegocio.find(Integer.parseInt(id));
            request.setAttribute("product", product);
        } catch (Exception ex) {
            request.setAttribute("product", product);
        }
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/update-product-image.jsp");
        requestDispatcher.forward(request, response);
    }

}
