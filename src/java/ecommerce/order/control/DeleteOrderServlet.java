/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.order.control;

import ecommerce.order.model.OrderNegocio;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
/**
 *
 * @author anacl
 */
public class DeleteOrderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        OrderNegocio orderNegocio = new OrderNegocio();
        String message = null;
        boolean success = false;
        
            message = "ixe?";
        try {
            
            message = "entrou no try";
            orderNegocio.excluir(Integer.parseInt(id));
            success = true;
            message = "Pedido excluído com sucesso";
        } catch (Exception ex) {
            message = ex.getMessage();
        }
        
        request.setAttribute("status", success);
        request.setAttribute("message", message);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("ListOrdersServlet");
        requestDispatcher.forward(request, response);
    }

}
