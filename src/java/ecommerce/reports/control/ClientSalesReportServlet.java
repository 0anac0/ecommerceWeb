/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.reports.control;

import ecommerce.reports.model.ClientSalesReport;
import ecommerce.reports.model.ReportDAO;
import ecommerce.reports.model.SalesReport;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

/**
 *
 * @author anacl
 */
public class ClientSalesReportServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        List<ClientSalesReport> result = null;
        Date begin = null;
        Date end = null;
        boolean success = false;
        String message = "Algo ocorreu de errado!";
        try {
            begin = dateFormat.parse(request.getParameter("begin"));
            end = dateFormat.parse(request.getParameter("end"));

            ReportDAO reportDAO = new ReportDAO();
            result = reportDAO.clientSalesReport(begin, end);
            success = result != null;
        } catch (Exception ex) {
            message = ex.getMessage();
        }

        if (success) {
            response.setContentType("text/plain;charset=UTF-8");
            response.setHeader("Content-disposition", "attachment;"
                                + " filename=relatorio-de-vendas-por-cliente.txt" );
            try (PrintWriter out = response.getWriter()) {
                out.println("Relatório de Vendas por cliente e período");
                out.println("Período: "+ outputDateFormat.format(begin) + " até "+ outputDateFormat.format(end));
                if (result.isEmpty()){
                    out.println("Nenhuma compra feita no período especificado");
                } else {
                    out.println("ID" + "\t" + "NOME" + "\t \t" + "COMPRAS" + "\t \t" + "TOTAL");
                    for (ClientSalesReport report : result) {
                        out.println(report.getId() + "\t" + report.getName()+
                                "\t" + report.getSales() + "\t \t"+ report.getAmount());
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
