package ecommerce.order_item.model;

import ecommerce.cart.model.CartItem;
import ecommerce.product.model.Product;
import java.util.ArrayList;
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
    
    public List<OrderItem> obterTodosPedido(Integer orderId) {
       OrderItemDAO orderItemDAO = new OrderItemDAO();
       List<OrderItem> orderItems = new ArrayList<>();
       try {
        orderItems = orderItemDAO.obterTodosPedido(orderId); 
       } catch (Exception ex) {
           
       }
       return orderItems;
    }
    
    public float salvarItemsPedido(Integer orderId, List<CartItem> cartItems) throws Exception{
        float orderTotalValue=0;
        for (CartItem item : cartItems){
            OrderItem orderItem = new OrderItem();
            Product product = item.getProduct();
            orderItem.setProductId(product.getId());
            orderItem.setOrderId(orderId);
            orderItem.setQuantity(item.getQuantity());
            float itemPrice = (float) product.getPrice()*item.getQuantity();
            orderItem.setPrice(itemPrice);
            this.insert(orderItem);
            orderTotalValue += itemPrice;
        }
        return orderTotalValue;
    }

}
