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
    
    public void atualizar(Category c, int id) throws Exception {
        CategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.atualizar(c, id);
    }
    
    public void excluir(int id) throws Exception {
        CategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.excluir(id);
    }
    
    public void obter(int id) throws Exception {
        CategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.obter(id);
    }
    
    public List<Category> obterTodos() throws Exception {
       CategoryDAO categoryDAO = new CategoryDAO();
       return categoryDAO.obterTodos(); 
    }
}
