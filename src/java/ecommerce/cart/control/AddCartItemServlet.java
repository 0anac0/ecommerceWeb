package ecommerce.cart.control;

import ecommerce.cart.model.CartNegocio;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author anacl
 */
public class AddCartItemServlet extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // entrada
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = 1;
        if (request.getParameter("quantity") != null) {
            quantity = Integer.parseInt(request.getParameter("quantity"));
        }
        Cookie cookie = CartNegocio.getCookie(request);
        try {
            String newCookieValue = CartNegocio.addItem(productId, quantity, cookie.getValue());
            cookie.setValue(newCookieValue);
        } catch (Exception ex) {
            request.setAttribute("message", ex.getMessage());
        }
        
        cookie.setMaxAge(Integer.MAX_VALUE);
        
        // saída
        response.addCookie(cookie);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("cart.jsp");
        requestDispatcher.forward(request, response);
    }


}
