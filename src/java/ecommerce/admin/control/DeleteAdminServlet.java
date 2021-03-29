/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.admin.control;

import ecommerce.admin.model.Admin;
import ecommerce.admin.model.AdminNegocio;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 *
 * @author anacl
 */
public class DeleteAdminServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //entrada
        HttpSession session = request.getSession(true);
        Boolean success = false;
        String message = "";
        if (session.getAttribute("user") == null) {
            message = "Admin não encontrado!";
        } else {
            Admin admin = (Admin) session.getAttribute("user");
            int id = admin.getId();
            AdminNegocio adminNegocio = new AdminNegocio();
            try {
                adminNegocio.excluir(id);
                success = true;
                message = "Admin apagado!";
            } catch (Exception ex) {
                message = ex.getMessage();
            }
        }
        request.setAttribute("status", success);
        request.setAttribute("message", message);
        session.invalidate();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/login.jsp");
        requestDispatcher.forward(request, response); 
        
        
    }

}
