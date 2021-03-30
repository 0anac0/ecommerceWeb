package ecommerce.upload;

import jakarta.servlet.ServletContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Iterator;
import java.util.List;
//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
/**
 *
 * @author anacl
 */
public class UploadServlet extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        
        
        if (isMultipart) {
            try {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletContext servletContext = this.getServletConfig().getServletContext();
                File repository = (File) servletContext.getAttribute("jakarta.servlet.context.tempdir");
                factory.setRepository(repository);
                
                ServletFileUpload upload = new ServletFileUpload(factory);
                List<FileItem> items = upload.parseRequest((RequestContext) request);
                //List<FileItem> items = upload.parseRequest(new ServletRequestContext(request));
                Iterator<FileItem> iter = items.iterator();
                while (iter.hasNext()) {
                    FileItem item = iter.next();
                    if (!item.isFormField() && item.getFieldName().equals("foto")) {
                        item.write(new File("/home/Upload/" + item.getName()));
                    }
                }
            } catch (Exception ex) {
            }
        }
    }

}
