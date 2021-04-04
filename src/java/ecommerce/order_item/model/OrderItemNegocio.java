package ecommerce.order_item.model;

import ecommerce.cart.model.CartItem;
import ecommerce.product.model.Product;
import ecommerce.product.model.ProductNegocio;
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
    
    public void delete(Integer id) throws Exception {
        OrderItemDAO orderItemDAO = new OrderItemDAO();
        orderItemDAO.delete(id);
    }
    public OrderItem find(Integer id) throws Exception {
        OrderItemDAO orderItemDAO = new OrderItemDAO();
        OrderItem item = orderItemDAO.find(id);
        return item;
    }
    
    public List<OrderItem> findAll() throws Exception {
       OrderItemDAO orderItemDAO = new OrderItemDAO();
       return orderItemDAO.findAll(); 
       
    }
    
    public List<OrderItem> findAllFromOrder(Integer orderId) {
       OrderItemDAO orderItemDAO = new OrderItemDAO();
       List<OrderItem> orderItems = new ArrayList<>();
       try {
        orderItems = orderItemDAO.findAllFromOrder(orderId); 
       } catch (Exception ex) {
           
       }
       return orderItems;
    }
    
    public float insertOrder(Integer orderId, List<CartItem> cartItems) throws Exception{
        float orderTotalValue=0;
        for (CartItem item : cartItems){
            OrderItem orderItem = new OrderItem();
            int itemQuantity = item.getQuantity();
            Product product = item.getProduct();

            // consumir do estoque
            ProductNegocio productNegocio = new ProductNegocio();
            productNegocio.consumeQuantity(product, itemQuantity);

            // instanciar o item
            orderItem.setProductId(product.getId());
            orderItem.setOrderId(orderId);
            orderItem.setQuantity(itemQuantity);
            float itemPrice = (float) product.getPrice()*item.getQuantity();
            orderItem.setPrice(itemPrice);

            //salvar o item
            this.insert(orderItem);
            // iterar o total do item no total da compra
            orderTotalValue += itemPrice;
        }
        return orderTotalValue;
    }

}
