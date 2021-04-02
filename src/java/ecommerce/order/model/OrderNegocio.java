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
    
    public void excluir(Integer id) throws Exception {
        OrderDAO orderDAO = new OrderDAO();
        OrderItemDAO orderItemDAO = new OrderItemDAO();
        orderItemDAO.excluirTodosPedido(id);
        orderDAO.excluir(id);
    }
    public Order obter(Integer id) throws Exception {
        OrderDAO orderDAO = new OrderDAO();
        Order order = orderDAO.obter(id);
        return order;
    }
    
    public List<Order> obterTodos() throws Exception {
       OrderDAO orderDAO = new OrderDAO();
       return orderDAO.obterTodos(); 
       
    }
    
    public void atualizar(Order o, int id) throws Exception {
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.atualizar(o, id);
    }
    
    public List<Order> obterTodosDoCliente(int clientId) throws Exception {
       OrderDAO orderDAO = new OrderDAO();
       return orderDAO.obterTodosCliente(clientId);
    }
    
    public Order obterUltimoDoCliente(int clientId) throws Exception {
       OrderDAO orderDAO = new OrderDAO();
       return orderDAO.obterUltimoDoCliente(clientId);  
    }
    
    public Order salvarPedido(int clientId) throws Exception{
        Order order = new Order();
        order.setTotal((float) 0);
        
        order.setClientId(clientId);
        this.insert(order);
        
        order = this.obterUltimoDoCliente(clientId);
        return order;
    }

}
