/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.product.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anacl
 */
public class ProductNegocio {
    
    public void insert(Product p) throws Exception {
        ProductDAO productDAO = new ProductDAO();
        productDAO.insert(p);
    }
    
    public void update(Product p, int id) throws Exception {
        ProductDAO productDAO = new ProductDAO();
        productDAO.update(p, id);
    }
    
    
    public void updateImage(String imagePath, int id) throws Exception {
        ProductDAO productDAO = new ProductDAO();
        productDAO.updateImage(imagePath, id);
    }
    public void delete(int id) throws Exception {
        ProductDAO productDAO = new ProductDAO();
        productDAO.delete(id);
    }
    public Product find(int id) throws Exception {
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.find(id);
        return product;
        
    }
    
    public List<Product> findAll() throws Exception {
       ProductDAO productDAO = new ProductDAO();
       return productDAO.findAll(); 
       
    }
    public List<Product> mockObter() {
        List<Product> resultado = new ArrayList<Product>();
            Product p = new Product();
            p.setId(1);
            p.setDescription("descricao do produto");
            p.setImage("url da imagem");
            p.setName("name");
            p.setQuantity(100);
            p.setPrice(10.0);
            resultado.add(p);
        return resultado;
    }
    
    public void consumeQuantity(Product product, int quantity) throws Exception{
        int oldProductQuantity = product.getQuantity();
            
        product.setQuantity(oldProductQuantity - quantity);
        
        this.update(product, product.getId());
            
    }

}
