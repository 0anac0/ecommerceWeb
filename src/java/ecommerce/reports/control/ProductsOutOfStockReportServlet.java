/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.reports.control;

import ecommerce.product.model.Product;
import ecommerce.reports.model.ReportDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import java.util.List;

/**
 *
 * @author anacl
 */
public class ProductsOutOfStockReportServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> result = null;
        boolean success = false;
        String message = "Algo ocorreu de errado!";
        try {
            ReportDAO reportDAO = new ReportDAO();
            result = reportDAO.productsOutOfStockReport();
            success = result != null;
        } catch (Exception ex) {
            message = ex.getMessage();
        }

        if (success) {
            response.setContentType("text/plain;charset=UTF-8");
            response.setHeader("Content-disposition", "attachment;"
                                + " filename=produtos-fora-de-estoque.txt" );
            try (PrintWriter out = response.getWriter()) {
                out.println("Relatório de Produtos fora de estoque");
                if (result.isEmpty()){
                    out.println("Nenhum produto fora de estoque");
                } else {
                    out.println("ID" + "\t" +"NOME" + "\t \t \t " + "QUANTIDADE");
                    for (Product product : result) {
                        out.println(product.getId() + "\t" + product.getName() 
                                    + "\t" + product.getQuantity());
                    }
                }
            }
        } else {
        request.setAttribute("status", success);
        request.setAttribute("message", message);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/relatorios.jsp");
        requestDispatcher.forward(request, response);
        }
        

        
    }

}
