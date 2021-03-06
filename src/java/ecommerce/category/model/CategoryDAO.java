/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.category.model;

import static ecommerce.config.Config.JDBC_SENHA;
import static ecommerce.config.Config.JDBC_URL;
import static ecommerce.config.Config.JDBC_USUARIO;
import ecommerce.product.model.Product;
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
public class CategoryDAO {

    public void insert(Category c) throws Exception {
        Class.forName("org.postgresql.Driver");
        
        int resultado;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA)) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO categories (description) VALUES (?)"
            );  
            preparedStatement.setString(1, c.getDescription());
            resultado = preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        if (resultado != 1) {
            throw new Exception("Categoria não foi inserida!");
        }
    }

    public void update(Category c, int id) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE categories SET description = ? WHERE id = ?"
        );
        preparedStatement.setString(1, c.getDescription());
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
                "DELETE FROM categories WHERE id = ?"
        );
        preparedStatement.setInt(1, id);
        int resultado = preparedStatement.executeUpdate();
        
        preparedStatement.close();
        connection.close();
        
        if (resultado != 1) {
            throw new Exception("Categoria não foi deletada!");
        }
    }
    
    public Category find(int id) throws Exception {
        Category c = null;
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM categories WHERE id = ?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            c = new Category();
            c.setId(resultSet.getInt("id"));
            c.setDescription(resultSet.getString("description"));
                    
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        
        if (c == null) {
            throw new Exception("Categoria não foi encontrada!");
        }
        return c;
    }
    
    public List<Category> findAll() throws Exception {
        List<Category> resultado = new ArrayList<Category>();
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM categories ORDER BY id"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            Category c = new Category();
            c.setId(resultSet.getInt("id"));
            c.setDescription(resultSet.getString("description"));
            resultado.add(c);
                    
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        
        return resultado;
    }
    
    public List<Product> findAllProducts(int id) throws Exception {
        List<Product> resultado = new ArrayList<>();
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT p.* FROM products p "
                        + "INNER JOIN product_categories pc ON pc.product_id = p.id "
                        + "WHERE pc.category_id = ?"
        );
                
        preparedStatement.setInt(1, id);
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
        
        return resultado;
    }

}
