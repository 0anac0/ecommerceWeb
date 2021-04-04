/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.order.control;

import ecommerce.cart.model.CartItem;
import ecommerce.cart.model.CartNegocio;
import ecommerce.client.model.Client;
import ecommerce.order.model.Order;
import ecommerce.order.model.OrderNegocio;
import ecommerce.order_item.model.OrderItemNegocio;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
/*import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
*/
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author anacl
 */
public class InsertOrderServlet extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Client currentUser = (Client) session.getAttribute("user");
        boolean success = false;
        String message = "infelizmente a operação não foi finalizada!";
        String destiny = "client.jsp";
        
        if (currentUser != null && currentUser.getId() != null) {
            try {
                // criar o pedido
                OrderNegocio orderNegocio = new OrderNegocio();
                
                Order order = orderNegocio.saveOrder(currentUser.getId());

                // instanciar os itens
                List<CartItem> cartItems = CartNegocio.getCartItemsFromRequest(request);
                OrderItemNegocio orderItemNegocio = new OrderItemNegocio();
                
                // salvar os itens e pegar o valor total do pedido
                float totalValue = orderItemNegocio.insertOrder(order.getId(), cartItems);
                order.setTotal(totalValue);
                orderNegocio.update(order, order.getId());
                
                // limpar o carrinho
                Cookie cookie = CartNegocio.getCookie(request);
                cookie.setValue("");
                cookie.setMaxAge(Integer.MAX_VALUE);
                response.addCookie(cookie);
                
                
                success = true;
                message = "operação finalizada!";
            } catch (Exception ex) {
                destiny = "cart.jsp";
                message = ex.getMessage();
            }
        }

        request.setAttribute("status", success);
        request.setAttribute("message", message);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(destiny);
        requestDispatcher.forward(request, response);
    }

}
