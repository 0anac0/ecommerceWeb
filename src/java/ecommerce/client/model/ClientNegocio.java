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
    

    public Client obterLogin(String login) throws Exception {
        ClientDAO clientDAO = new ClientDAO();
        return clientDAO.obterLogin(login);
    }

    public void insert(Client c) throws Exception {
        ClientDAO clientDAO = new ClientDAO();
        clientDAO.insert(c);
    }
    
    public void atualizar(Client c, int id) throws Exception {
        ClientDAO clientDAO = new ClientDAO();
        clientDAO.atualizar(c, id);
    }
    public void excluir(int id) throws Exception {
        ClientDAO clientDAO = new ClientDAO();
        clientDAO.excluir(id);
    }
    public Client obter(int id) throws Exception {
        ClientDAO clientDAO = new ClientDAO();
        return clientDAO.obter(id);
        
    }
    
    public List<Client> obterTodos() throws Exception {
       ClientDAO clientDAO = new ClientDAO();
       return clientDAO.obterTodos(); 
    }
}
