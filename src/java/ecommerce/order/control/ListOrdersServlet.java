/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.order.control;

import ecommerce.order.model.Order;
import ecommerce.order.model.OrderNegocio;
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
public class ListOrdersServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrderNegocio orderNegocio = new OrderNegocio();
        List<Order> orders = new ArrayList<>();
        String message = (String) request.getAttribute("message");
        //boolean success = (boolean) request.getAttribute("status");

        try {
            orders = orderNegocio.obterTodos();
        } catch (Exception ex) {
            message = ex.getMessage();
        }
        request.setAttribute("message", message);
        request.setAttribute("orders", orders);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/orders.jsp");
        requestDispatcher.forward(request, response);
    }

}
