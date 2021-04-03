/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.category.model;
import java.util.List;

/**
 *
 * @author anacl
 */

public class CategoryNegocio {
    
    public void insert(Category c) throws Exception {
        CategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.insert(c);
    }
    
    public void update(Category c, int id) throws Exception {
        CategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.update(c, id);
    }
    
    public void delete(int id) throws Exception {
        CategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.delete(id);
    }
    
    public void find(int id) throws Exception {
        CategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.find(id);
    }
    
    public List<Category> findAll() throws Exception {
       CategoryDAO categoryDAO = new CategoryDAO();
       return categoryDAO.findAll(); 
    }
}
