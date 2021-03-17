/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.product.model;

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
    
}
