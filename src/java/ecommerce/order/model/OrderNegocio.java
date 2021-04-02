package ecommerce.order.model;

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

}
