/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.order.model;

import ecommerce.client.model.Client;
import ecommerce.client.model.ClientNegocio;
import ecommerce.order_item.model.OrderItem;
import ecommerce.order_item.model.OrderItemNegocio;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author anacl
 */
public class Order {
    
    private Integer id;
    private Integer client_id;
    private Date created_at;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getClientId() {
        return client_id;
    }
    
    public void setClientId(Integer clientId) {
        this.client_id = clientId;
    }

    public Date getCreatedAt() {
        return created_at;
    }
    
    public void setCreatedAt(Date createdAt) {
        this.created_at = createdAt;
    }
    
    public String getCreatedAtFormatted() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(created_at);
    }
    
    public Client getClient () throws Exception {
        ClientNegocio clientNegocio = new ClientNegocio();
        Client client = clientNegocio.obter(client_id);
        return client;
    }
    
    public List<OrderItem> getOrderItems () throws Exception {
        OrderItemNegocio orderItemNegocio = new OrderItemNegocio();
        List<OrderItem> orderItems = orderItemNegocio.obterTodosPedido(id);
        return orderItems;
    }

    

}
