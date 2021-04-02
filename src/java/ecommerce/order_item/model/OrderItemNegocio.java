package ecommerce.order_item.model;

import ecommerce.order.model.Order;
import ecommerce.order.model.OrderDAO;
import java.util.List;

/**
 *
 * @author anacl
 */
public class OrderItemNegocio {
    
    public void insert(OrderItem i) throws Exception {
        OrderItemDAO orderItemDAO = new OrderItemDAO();
        orderItemDAO.insert(i);
    }
    
    public void excluir(Integer id) throws Exception {
        OrderItemDAO orderItemDAO = new OrderItemDAO();
        orderItemDAO.excluir(id);
    }
    public OrderItem obter(Integer id) throws Exception {
        OrderItemDAO orderItemDAO = new OrderItemDAO();
        OrderItem item = orderItemDAO.obter(id);
        return item;
    }
    
    public List<OrderItem> obterTodos() throws Exception {
       OrderItemDAO orderItemDAO = new OrderItemDAO();
       return orderItemDAO.obterTodos(); 
       
    }
    
    public List<OrderItem> obterTodosPedido(Integer orderId) throws Exception {
       OrderItemDAO orderItemDAO = new OrderItemDAO();
       return orderItemDAO.obterTodosPedido(orderId); 
       
    }

}
