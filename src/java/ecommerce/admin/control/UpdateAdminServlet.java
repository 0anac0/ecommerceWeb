/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.admin.control;

import ecommerce.admin.model.Admin;
import ecommerce.admin.model.AdminNegocio;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author anacl
 */
public class UpdateAdminServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                //incializando
        String message = null;
        Boolean success = false;
        HttpSession session = request.getSession(true);
        
        // entrada
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        // processamento
        if (session.getAttribute("user") == null || (boolean) session.getAttribute("admin") != true) {
            message = "Admin não encontrado!";
        } else {
            Admin sessionAdmin = (Admin) session.getAttribute("user");
            int id = sessionAdmin.getId();
            

            Admin admin = new Admin();
            admin.setId(id);
            admin.setName(name);
            admin.setEmail(email);
            admin.setLogin(login);
            admin.setPassword(password);

            AdminNegocio adminNegocio = new AdminNegocio();
            try {
                adminNegocio.update(admin, id);
                success = true;
                session.setAttribute("user", admin);
                session.setAttribute("username", admin.getName());

                message = "Atualização feita com sucesso!";
            } catch (Exception ex) {
                message = ex.getMessage();
            }
        }
        // saída
        request.setAttribute("status", success);
        request.setAttribute("message", message);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/data.jsp");
        requestDispatcher.forward(request, response);
    }

}
