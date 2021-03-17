/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.acess.control;


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
public class LoginAdminServlet extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // entrada
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        AdminNegocio adminNegocio = new AdminNegocio();
        boolean success = false;
        String message = "";
        Admin admin = null;
        try {
            admin = adminNegocio.obterLogin(login);
            if (admin != null && admin.getPassword().equals(password)) {
                success = true;
                HttpSession session = request.getSession(true);
                session.setAttribute("user", admin);
                session.setAttribute("username", admin.getName());
                session.setAttribute("admin", true);
                
            } else {
                success = false;
                message = "Login ou senha inválidos";
            }
        } catch (Exception ex) {
            message = ex.getMessage();
        }
        
        // saída
        String destiny = success ? "admin/index.jsp" : "admin/login.jsp";
        response.sendRedirect(destiny);
    }
}
