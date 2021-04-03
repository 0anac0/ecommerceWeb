/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.client.model;

import java.util.List;

/**
 *
 * @author anacl
 */
public class ClientNegocio {
    

    public Client findFromLogin(String login) throws Exception {
        ClientDAO clientDAO = new ClientDAO();
        return clientDAO.findFromLogin(login);
    }

    public void insert(Client c) throws Exception {
        ClientDAO clientDAO = new ClientDAO();
        clientDAO.insert(c);
    }
    
    public void update(Client c, int id) throws Exception {
        ClientDAO clientDAO = new ClientDAO();
        clientDAO.update(c, id);
    }
    public void delete(int id) throws Exception {
        ClientDAO clientDAO = new ClientDAO();
        clientDAO.delete(id);
    }
    public Client find(int id) throws Exception {
        ClientDAO clientDAO = new ClientDAO();
        return clientDAO.find(id);
        
    }
    
    public List<Client> findAll() throws Exception {
       ClientDAO clientDAO = new ClientDAO();
       return clientDAO.findAll(); 
    }
}
