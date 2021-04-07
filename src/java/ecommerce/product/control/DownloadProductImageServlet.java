/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.product.control;

import ecommerce.product.model.Product;
import ecommerce.product.model.ProductNegocio;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anacl
 */
public class DownloadProductImageServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Entrada
        String productId = (String) request.getParameter("id");
        ProductNegocio productNegocio = new ProductNegocio();
        ServletContext context = this.getServletContext();
        String filePath = "C:\\upload\\1.png";
        
        boolean success = true;
        String message = "Não ocorreram erros";
        try {
            Product product = productNegocio.find(Integer.parseInt(productId));
            //filePath = product.getImage();
        } catch (Exception ex) {
            message = "Ocorreu um erro no produto" + ex.getMessage();
            success = false;
        }
        
        //saída e processamento
        if (success == true){
            try {
                File downloadFile = new File(filePath);
                FileInputStream in = new FileInputStream(downloadFile);
                String mimeType = context.getMimeType(filePath);
                if (mimeType == null) mimeType = "application/octet-stream";


                response.setContentType(mimeType);
                response.setContentLength((int) downloadFile.length());
                String key = "Content-Disposition";
                String value = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
                response.setHeader(key, value);
                OutputStream out = response.getOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                while ((bytesRead = in.read(buffer)) != -1){ 
                    out.write(buffer, 0, bytesRead);
                }
                in.close();
                out.close();
            } catch (Exception ex) {
                success = false;
                message = "Ocorreu um erro ao obter a imagem "+ ex.getMessage();
            }
                
        }
        if (!success) {
            request.setAttribute("message", message);
            request.setAttribute("status", success);
            RequestDispatcher dispatcher = request.getRequestDispatcher("ShowProductImageServlet");
            dispatcher.forward(request, response);
        }
    }
}


