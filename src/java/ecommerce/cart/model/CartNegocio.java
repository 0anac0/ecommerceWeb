package ecommerce.cart.model;

import ecommerce.product.model.Product;
import ecommerce.product.model.ProductNegocio;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anacl
 * Controller class for the Cart
 */
public class CartNegocio {
    public static final String CART_KEY = "ecommerceCart";
    public static final String PRODUCT_SPLITTER = "&";
    public static final String QUANTITY_SPLITTER = ":";
    
    public static final List<CartItem> getCartItemsFromRequest(HttpServletRequest request) throws Exception {
        Cookie cookie = getCookie(request);
        return getCartItems(cookie.getValue());
    }
    
    public static final Cookie getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        
        Cookie cookie = null;
        for (int i =0; cookies != null && i < cookies.length; i++){
            if (cookies[i].getName().equals(CART_KEY)){
                cookie = cookies[i];
                break;
            }
        }
        return cookie;
    }
    
    public static final List<CartItem> getCartItems(String value) throws Exception{
        List<CartItem> cartItems = new ArrayList<>();
        if (value == null || value.trim().length() == 0 || !value.contains(QUANTITY_SPLITTER) ){
            return cartItems;
        }
        if (value.contains(PRODUCT_SPLITTER)) {
            String[] products = value.split(PRODUCT_SPLITTER);
            for (int i = 0; products != null && i < products.length; i++) {
                CartItem cartItem = getCartItem(products[i]);
                cartItems.add(cartItem);
            }
        } else {
            CartItem cartItem = getCartItem(value);
            cartItems.add(cartItem);
        }
        return cartItems;
    }
    
    public static CartItem getCartItem(String cartItemString) throws Exception {
        String[] item = cartItemString.split(QUANTITY_SPLITTER);
        ProductNegocio productNegocio = new ProductNegocio();
        Product product = productNegocio.find(Integer.parseInt(item[0]));
        CartItem cartItem = new CartItem();
                
        cartItem.setProduct(product);
        cartItem.setQuantity(Integer.parseInt(item[1]));
        return cartItem;
    }
    
    public static String getCookieString(List<CartItem> cartItems) {
        String result = "";
        for (CartItem cartItem : cartItems) {
            result += cartItem.getProduct().getId() + QUANTITY_SPLITTER + cartItem.getQuantity();
            result += PRODUCT_SPLITTER;
        }
        return result;
    }
    
    public static final String addItem(int productId, int quantity, String value) throws Exception {
        List<CartItem> cartItems = getCartItems(value);
        ProductNegocio productNegocio = new ProductNegocio();
        if (cartItems.isEmpty()) {
            return productId + QUANTITY_SPLITTER + quantity;
        }
        boolean alreadyOnCart = false;
        for (CartItem cartItem : cartItems) {
            if(cartItem.getProduct().getId() == productId) {
                int formerQuantity = cartItem.getQuantity();
                int finalQuantity = quantity + formerQuantity;
                if (finalQuantity <= 0) {
                    return removeItem(productId, value);
                } else {
                    cartItem.setQuantity(finalQuantity);
                }
                alreadyOnCart = true;
            }
        }
        if (!alreadyOnCart) {
            CartItem cartItem = new CartItem();
            cartItem.setQuantity(quantity);
            cartItem.setProduct(productNegocio.find(productId));
            cartItems.add(cartItem);
        }
        return getCookieString(cartItems);
    }
    
    public static final String removeItem(int productId, String value) throws Exception {
        List<CartItem> cartItems = getCartItems(value);
        if (cartItems.isEmpty()) {
            return "";
        }
        for (CartItem cartItem : cartItems) {
            if(cartItem.getProduct().getId() == productId) {
                cartItems.remove(cartItem);
                break;
            }
        }
        return getCookieString(cartItems);
    }
}
