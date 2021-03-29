/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.acess.control;


import ecommerce.admin.model.Admin;
import ecommerce.admin.model.AdminNegocio;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        request.setAttribute("status", success);
        request.setAttribute("message", message);
        String destiny = success ? "admin/index.jsp" : "admin/login.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(destiny);
        requestDispatcher.forward(request, response);
    }
}
