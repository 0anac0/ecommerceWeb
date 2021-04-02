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
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anacl
 * Classe de controle que realiza a inserção do cliente
 */
public class AlterClientServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //incializando
        String message = null;
        Boolean success = false;
        HttpSession session = request.getSession(true);
        // entrada
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        // processamento
        if (session.getAttribute("user") == null) {
            message = "Cliente não encontrado!";
        } else {
            Client sessionClient = (Client) session.getAttribute("user");
            int id = sessionClient.getId();
            

            Client client = new Client();
            client.setName(name);
            client.setAddress(address);
            client.setEmail(email);
            client.setLogin(login);
            client.setPassword(password);

            ClientNegocio clientNegocio = new ClientNegocio();
            try {
                clientNegocio.atualizar(client, id);
                success = true;
                message = "Deu tudo certo!";
            } catch (Exception ex) {
                message = ex.getMessage();
            }
        }
        // saída
        request.setAttribute("status", success);
        request.setAttribute("message", message);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("client.jsp");
        requestDispatcher.forward(request, response);
    }

}
