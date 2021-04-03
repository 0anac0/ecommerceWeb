package ecommerce.product.model;

import ecommerce.client.model.Client;
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
public class ProductDAO {
    

    public void insert(Product p) throws Exception {
        Class.forName("org.postgresql.Driver");
        
        int resultado;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA)) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO products (description, price, image, quantity, name) VALUES (?, ?, ?, ?, ?)"
            );  
            preparedStatement.setString(1, p.getDescription());
            preparedStatement.setDouble(2, p.getPrice());
            preparedStatement.setString(3, p.getImage());
            preparedStatement.setInt(4, p.getQuantity());
            preparedStatement.setString(5, p.getName());
            resultado = preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        if (resultado != 1) {
            throw new Exception("Produto não foi inserido!");
        }
    }

    public void update(Product p, int id) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE products SET description = ?, price = ?, image=?"
                        + ", quantity=? , name = ? WHERE id = ?"
        );
        preparedStatement.setString(1, p.getDescription());
        preparedStatement.setDouble(2, p.getPrice());
        preparedStatement.setString(3, p.getImage());
        preparedStatement.setInt(4, p.getQuantity());
        preparedStatement.setString(5, p.getName());
        preparedStatement.setInt(6, id);
        int resultado = preparedStatement.executeUpdate();
        
        preparedStatement.close();
        connection.close();
        
        if (resultado != 1) {
            throw new Exception("Produto não foi atualizado!");
        }
    }
    
    
    public void updateImage(String imagePath, int id) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE products SET image = ? WHERE id = ?"
        );
        preparedStatement.setString(1, imagePath);
        preparedStatement.setInt(2, id);
        int resultado = preparedStatement.executeUpdate();
        
        preparedStatement.close();
        connection.close();
        
        if (resultado != 1) {
            throw new Exception("Produto não foi atualizado!");
        }
    }
    
    public void delete(int id) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM products WHERE id = ?"
        );
        preparedStatement.setInt(1, id);
        int resultado = preparedStatement.executeUpdate();
        
        preparedStatement.close();
        connection.close();
        
        if (resultado != 1) {
            throw new Exception("Produto não foi deletado!");
        }
    }
    
    public Product find(int id) throws Exception {
        Product p = null;
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM products WHERE id = ?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            p = new Product();
            p.setId(resultSet.getInt("id"));
            p.setDescription(resultSet.getString("description"));
            p.setImage(resultSet.getString("image"));
            p.setName(resultSet.getString("name"));
            p.setQuantity(resultSet.getInt("quantity"));
            p.setPrice(resultSet.getDouble("price"));
            
                    
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        
        if (p == null) {
            throw new Exception("Produto não foi encontrado!");
        }
        return p;
    }

    
    public List<Product> findAll() throws Exception {
        List<Product> resultado = new ArrayList<Product>();
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM products"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            Product p = new Product();
            p.setId(resultSet.getInt("id"));
            p.setDescription(resultSet.getString("description"));
            p.setImage(resultSet.getString("image"));
            p.setName(resultSet.getString("name"));
            p.setQuantity(resultSet.getInt("quantity"));
            p.setPrice(resultSet.getDouble("price"));
            resultado.add(p);
                    
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        
        if (resultado.isEmpty()) {
            throw new Exception("Nenhum produto encontrado!");
        }
        return resultado;
    }
}
