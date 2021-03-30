
package ecommerce.cart.model;

import ecommerce.product.model.Product;

/**
 *
 * @author anacl
 * Cart item class
 */
public class CartItem {
    private Product product;
    private int quantity;
    
    
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    
    
}
