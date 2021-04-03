package ecommerce.category.model;

import ecommerce.product.model.Product;
import java.util.List;

/**
 *
 * @author anacl
 * Category class
 */
public class Category {
    private Integer id;
    private String description;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<Product> getProducts() throws Exception {
        CategoryDAO categoryDAO = new CategoryDAO();
        return categoryDAO.findAllProducts(this.id);
    }
}
