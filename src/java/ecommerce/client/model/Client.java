/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.client.model;

import ecommerce.order.model.Order;
import ecommerce.order.model.OrderNegocio;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anacl
 */
public class Client {
    private Integer id;
    private String address;
    private String login;
    private String name;
    private String email;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public List<Order> getOrders() {
        OrderNegocio orderNegocio = new OrderNegocio();
        List<Order> orders = new ArrayList<>();
        try {
            orders = orderNegocio.obterTodosDoCliente(id);
        } catch (Exception ex) {
            
        }
        return orders;
    }
    
}
