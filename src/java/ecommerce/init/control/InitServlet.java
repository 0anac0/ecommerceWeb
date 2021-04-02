/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.init.control;

import ecommerce.cart.model.CartNegocio;
import static ecommerce.cart.model.CartNegocio.CART_KEY;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author anacl
 */
public class InitServlet extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CartNegocio cartNegocio = new CartNegocio();
        Cookie cookie = cartNegocio.getCookie(request);
        
        if (cookie == null){
            cookie = new Cookie(CART_KEY, "");
        }
        cookie.setMaxAge(Integer.MAX_VALUE);
        
        // saída
        response.addCookie(cookie);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);  
    
    }

}
