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
    
    public Admin obterLogin(String login) throws Exception {
        AdminDAO adminDAO = new AdminDAO();
        return adminDAO.obterLogin(login);
    }

    public void atualizar(Admin a, int id) throws Exception {
        AdminDAO adminDAO = new AdminDAO();
        adminDAO.atualizar(a, id);
    }
    public void excluir(int id) throws Exception {
        AdminDAO adminDAO = new AdminDAO();
        adminDAO.excluir(id);
    }
    public Admin obter(int id) throws Exception {
        AdminDAO adminDAO = new AdminDAO();
        return adminDAO.obter(id);
        
    }
    
}
