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
    
    public void atualizar(Product p, int id) throws Exception {
        ProductDAO productDAO = new ProductDAO();
        productDAO.atualizar(p, id);
    }
    public void excluir(int id) throws Exception {
        ProductDAO productDAO = new ProductDAO();
        productDAO.excluir(id);
    }
    public void obter(int id) throws Exception {
        ProductDAO productDAO = new ProductDAO();
        productDAO.obter(id);
        
    }
    
    public List<Product> obterTodos() throws Exception {
       ProductDAO productDAO = new ProductDAO();
       return productDAO.obterTodos(); 
       
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
    
}
