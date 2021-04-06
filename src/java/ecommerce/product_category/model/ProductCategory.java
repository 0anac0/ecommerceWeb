package ecommerce.product_category.model;

import ecommerce.category.model.Category;
import ecommerce.category.model.CategoryNegocio;
import ecommerce.product.model.Product;
import ecommerce.product.model.ProductNegocio;

/**
 *
 * @author anacl
 */
public class ProductCategory {
    private Integer categoryId;
    private Integer productId;
    
    
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    
    public Product getProduct() throws Exception{
        ProductNegocio productNegocio = new ProductNegocio();
        return productNegocio.find(productId);
    }
    
    public Category getCategory() throws Exception{
        CategoryNegocio categoryNegocio = new CategoryNegocio();
        return categoryNegocio.find(categoryId);
    }

    
}
