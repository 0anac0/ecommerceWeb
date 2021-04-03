/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.client.control;

import ecommerce.client.model.Client;
import ecommerce.client.model.ClientNegocio;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
/*import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
*/


/**
 *
 * @author anacl
 */
public class DeleteClientServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //entrada
        HttpSession session = request.getSession(true);
        Boolean success = false;
        String message = "";
        if (session.getAttribute("user") == null) {
            message = "Cliente não encontrado!";
        } else {
            Client client = (Client) session.getAttribute("user");
            int id = client.getId();
            ClientNegocio clientNegocio = new ClientNegocio();
            try {
                clientNegocio.delete(id);
                success = true;
                message = "Cliente apagado!";
            } catch (Exception ex) {
                message = ex.getMessage();
            }
        }
        request.setAttribute("status", success);
        request.setAttribute("message", message);
        session.invalidate();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("login-cadastro.jsp");
        requestDispatcher.forward(request, response); 
        
        
    }

}
