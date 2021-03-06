/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.acess.control;


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
public class LoginServlet extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // entrada
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        ClientNegocio clientNegocio = new ClientNegocio();
        boolean success = false;
        String message = "";
        Client client = null;
        try {
            client = clientNegocio.findFromLogin(login);
            //client = new Client();
            //client.setName("CARLO");
            //client.setPassword(password);
            if (client != null && client.getPassword().equals(password)) {
                success = true;
                HttpSession session = request.getSession(true);
                session.setAttribute("user", client);
                session.setAttribute("username", client.getName());
                
            } else {
                success = false;
                message = "Login ou senha inválidos";
            }
        } catch (Exception ex) {
            message = ex.getMessage();
        }
        
        // saída
        request.setAttribute("status", success);
        request.setAttribute("message", message);
        String destiny = success ? "client.jsp" : "login-cadastro.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(destiny);
        requestDispatcher.forward(request, response);  
    }
}
