package ecommerce.product.control;

import ecommerce.product.model.Product;
import ecommerce.product.model.ProductNegocio;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

/**
 *
 * @author anacl
 */
@WebServlet
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, //1MB
        maxFileSize = 1024 * 1024 * 10, //10MB
        maxRequestSize = 1024 * 1024 * 100 //100MB
)
public class UploadProductImageServlet extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Entrada
        String productId = (String) request.getParameter("id");
        
        boolean success = false;
        String message = "Imagem atualizada!";
        // processamento
        try {
            // salvar arquivo
            String filePath = "C:\\upload\\"+productId+".png";
            for (Part part : request.getParts()) {
                part.write(filePath);
            }
            
            //salvar path no banco
            ProductNegocio productNegocio = new ProductNegocio();
            Product product = productNegocio.find(Integer.parseInt(productId));
            product.setImage(filePath);
            productNegocio.update(product, Integer.parseInt(productId));
            success = true;
        } catch (Exception ex) {
            message = "Algo correu errado: "+ ex.getMessage();
        }
        
        //saída
        request.setAttribute("message", message);
        request.setAttribute("status", success);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ShowProductImageServlet");
        dispatcher.forward(request, response);
    }

}
