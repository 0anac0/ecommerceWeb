package ecommerce.order.model;

import ecommerce.order_item.model.OrderItemDAO;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 *
 * @author anacl
 */
public class OrderNegocio {
    
    public void insert(Order o) throws Exception {
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.insert(o);
    }
    
    public void delete(Integer id) throws Exception {
        OrderDAO orderDAO = new OrderDAO();
        OrderItemDAO orderItemDAO = new OrderItemDAO();
        orderItemDAO.deleteAllFromOrder(id);
        orderDAO.delete(id);
    }
    public Order obter(Integer id) throws Exception {
        OrderDAO orderDAO = new OrderDAO();
        Order order = orderDAO.find(id);
        return order;
    }
    
    public List<Order> findAll() throws Exception {
       OrderDAO orderDAO = new OrderDAO();
       return orderDAO.findAll(); 
       
    }
    
    public void update(Order o, int id) throws Exception {
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.update(o, id);
    }
    
    public List<Order> findAllFromClient(int clientId) throws Exception {
       OrderDAO orderDAO = new OrderDAO();
       return orderDAO.findAllFromClient(clientId);
    }
    
    public Order findLastFromClient(int clientId) throws Exception {
       OrderDAO orderDAO = new OrderDAO();
       return orderDAO.findLastFromClient(clientId);  
    }
    
    public Order saveOrder(int clientId) throws Exception{
        Order order = new Order();
        order.setTotal((float) 0);
        
        order.setClientId(clientId);
        this.insert(order);
        
        order = this.findLastFromClient(clientId);
        return order;
    }

}
