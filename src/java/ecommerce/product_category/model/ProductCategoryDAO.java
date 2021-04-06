package ecommerce.product_category.model;

import static ecommerce.config.Config.JDBC_SENHA;
import static ecommerce.config.Config.JDBC_URL;
import static ecommerce.config.Config.JDBC_USUARIO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anacl
 */
public class ProductCategoryDAO {
    
    public void insert(ProductCategory pc) throws Exception {
        Class.forName("org.postgresql.Driver");
        
        int resultado;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA)) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO product_categories (product_id, category_id) VALUES (?, ?)"
            );

            preparedStatement.setInt(1, pc.getProductId());
            preparedStatement.setInt(2, pc.getCategoryId());
            resultado = preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        if (resultado != 1) {
            throw new Exception("Producto-Categoria não foi inserida!");
        }
    }
    
    
    public void delete(int productId, int categoryId) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM product_categories WHERE product_id = ? "
                        + "AND category_id = ?"
        );
        preparedStatement.setInt(1, productId);
        preparedStatement.setInt(2, categoryId);
        int resultado = preparedStatement.executeUpdate();
        
        preparedStatement.close();
        connection.close();
        
        if (resultado != 1) {
            throw new Exception("Categoria não foi deletada!");
        }
    }
    
    public List<ProductCategory> findAllFromCategory(int categoryId) throws Exception {
        List<ProductCategory> resultado = new ArrayList<>();
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM product_categories WHERE category_id = ?"
        );
        
        preparedStatement.setInt(1, categoryId);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            ProductCategory pc = new ProductCategory();
            pc.setProductId(resultSet.getInt("product_id"));
            pc.setCategoryId(resultSet.getInt("category_id"));
            resultado.add(pc);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        
        return resultado;
    }
}
