package ecommerce.product.control;

import ecommerce.product.model.Product;
import ecommerce.product.model.ProductNegocio;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
//import org.apache.tomcat.util.http.fileupload.FileItem;
//import org.apache.tomcat.util.http.fileupload.RequestContext;
//import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
//import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;


/*import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*

/**
 *
 * @author anacl
 */
public class UploadProductImageServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = -1;
        FileItem image = null;
        System.out.println("entrouuuuuuuuuuuuuuu");
        
        String message = "deu errado mlr";
        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ShowProductImageServlet");
        dispatcher.forward(request, response);
        boolean success = false;
        
        if (true) {
            message = "pelo menos eh multipart";
            try {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletContext servletContext = this.getServletConfig().getServletContext();
                File repository = (File) servletContext.getAttribute("jakarta.servlet.context.tempdir");
                factory.setRepository(repository);
                
                ServletFileUpload upload = new ServletFileUpload(factory);
                List<FileItem> items = upload.parseRequest((RequestContext) request);
                Iterator<FileItem> iter = items.iterator();
                while (iter.hasNext()) {
                    FileItem item = iter.next();
                    if (!item.isFormField() && item.getFieldName().equals("image")) {
                        image = item;
                        
                    }
                    if (item.isFormField() && item.getFieldName().equals("id")){
                        id = Integer.parseInt(item.getString());
                    }
                    if(id != -1 && image != null) {
                        message = "mlr entrou ne agr o q deu errado nsei";
                        
                        System.out.println("oientrou");
                        String extension = image.getName().substring(image.getName().lastIndexOf('.'));
                        String filePath = "C:\\Users\\anacl\\Downloads\\boxta.png";
                        
                        System.out.println("filePath "+ filePath);
                        image.write(new File(filePath));
                        
                        ProductNegocio productNegocio = new ProductNegocio();
                        productNegocio.atualizarImage(filePath, id);
                    
                        success= true; 
                        
                    }
                }
            } catch (Exception ex) {
                success = false;
            }
        }
        //String message = success ? "Imagem atualizada" : "Não foi possível salvar a imagem";
        request.setAttribute("message", message);
        //RequestDispatcher dispatcher = request.getRequestDispatcher("ShowProductImageServlet");
        dispatcher.forward(request, response);
    }

}
