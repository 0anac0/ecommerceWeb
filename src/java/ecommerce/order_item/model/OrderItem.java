package ecommerce.order_item.model;

import ecommerce.order.model.Order;
import ecommerce.order.model.OrderNegocio;
import ecommerce.product.model.Product;
import ecommerce.product.model.ProductNegocio;

/**
 *
 * @author anacl
 */
public class OrderItem {
    private int id;
    private int order_id;
    private int product_id;
    private int quantity;
    private float price;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return order_id;
    }
    
    public void setOrderId(int orderId) {
        this.order_id = orderId;
    }

    public Integer getProductId() {
        return product_id;
    }
    
    public void setProductId(int productId) {
        this.product_id = productId;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }
    
    public void setPrice(float price) {
        this.price = price;
    }

    public Product getProduct() throws Exception {
        ProductNegocio productNegocio = new ProductNegocio();
        return productNegocio.obter(product_id);
    }
    
    public Order getOrder() throws Exception {
        OrderNegocio orderNegocio = new OrderNegocio();
        return orderNegocio.obter(order_id);
    }
}
