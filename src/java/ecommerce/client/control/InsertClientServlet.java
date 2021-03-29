/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.client.control;

import ecommerce.client.model.Client;
import ecommerce.client.model.ClientNegocio;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author anacl
 * Classe de controle que realiza a inserção do cliente
 */
public class InsertClientServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // entrada
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        // processamento
        Client client = new Client();
        client.setName(name);
        client.setAddress(address);
        client.setEmail(email);
        client.setLogin(login);
        client.setPassword(password);

        boolean success = false;
        String message = "Deu tudo certo!";
        ClientNegocio clientNegocio = new ClientNegocio();
        try {
            clientNegocio.insert(client);
            success = true;
        } catch (Exception ex) {
            message = ex.getMessage();
        }
        // saída
        request.setAttribute("status", success);
        request.setAttribute("message", message);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("login-cadastro.jsp");
        requestDispatcher.forward(request, response);
    }

}
