/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.admin.model;

/**
 *
 * @author anacl
 */
public class AdminNegocio {
    
    public Admin findFromLogin(String login) throws Exception {
        AdminDAO adminDAO = new AdminDAO();
        return adminDAO.findFromLogin(login);
    }

    public void update(Admin a, int id) throws Exception {
        AdminDAO adminDAO = new AdminDAO();
        adminDAO.update(a, id);
    }
    public void delete(int id) throws Exception {
        AdminDAO adminDAO = new AdminDAO();
        adminDAO.delete(id);
    }
    public Admin find(int id) throws Exception {
        AdminDAO adminDAO = new AdminDAO();
        return adminDAO.find(id);
        
    }
    
}
