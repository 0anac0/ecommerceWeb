/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.category.model;

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

    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/ecommerce";
    private static final String JDBC_USUARIO = "postgres";
    private static final String JDBC_SENHA = "anac123";

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

    public void atualizar(Category c, int id) throws Exception {
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
    
    public void excluir(int id) throws Exception {
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
    
    public Category obter(int id) throws Exception {
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
    
    public List<Category> obterTodos() throws Exception {
        List<Category> resultado = new ArrayList<Category>();
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM categories"
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
        
        if (resultado.isEmpty()) {
            throw new Exception("Nenhuma categoria encontrada!");
        }
        return resultado;
    }
    
    public List<Product> obterTodosProdutos(int id) throws Exception {
        List<Product> resultado = new ArrayList<Product>();
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT p.* FROM products p "
                        + "INNER JOIN product_categories pc ON pc.product_id = p.id"
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
        
        if (resultado.isEmpty()) {
            throw new Exception("Nenhum produto encontrado para a categoria!");
        }
        return resultado;
    }
}
