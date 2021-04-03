/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.order_item.model;

import static ecommerce.config.Config.JDBC_SENHA;
import static ecommerce.config.Config.JDBC_URL;
import static ecommerce.config.Config.JDBC_USUARIO;
import ecommerce.order.model.Order;
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
public class OrderItemDAO {

    public void insert(OrderItem i) throws Exception {
        Class.forName("org.postgresql.Driver");
        
        int resultado;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA)) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO order_items (product_id, order_id, quantity, price) VALUES (?, ?, ?, ?)"
            );  
            preparedStatement.setInt(1, i.getProductId());
            preparedStatement.setInt(2, i.getOrderId());
            preparedStatement.setInt(3, i.getQuantity());
            preparedStatement.setFloat(4, i.getPrice());
            resultado = preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        if (resultado != 1) {
            throw new Exception("Produto não foi inserido!");
        }
    }

    public void delete(Integer id) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM order_items WHERE id = ?"
        );
        preparedStatement.setInt(1, id);
        int resultado = preparedStatement.executeUpdate();
        
        preparedStatement.close();
        connection.close();
        
        if (resultado != 1) {
            throw new Exception("Item não foi deletado!");
        }
    }
    
    public void deleteAllFromOrder(Integer orderId) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM order_items WHERE order_id = ?"
        );
        preparedStatement.setInt(1, orderId);
        int resultado = preparedStatement.executeUpdate();
        
        preparedStatement.close();
        connection.close();
        
    }
    
    public OrderItem find(Integer id) throws Exception {
        OrderItem i = null;
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM order_items WHERE id = ?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            i = new OrderItem();
            i.setId(resultSet.getInt("id"));
            i.setProductId(resultSet.getInt("product_id"));
            i.setOrderId(resultSet.getInt("order_id"));
            i.setQuantity(resultSet.getInt("quantity"));
            i.setPrice(resultSet.getFloat("price"));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        
        if (i == null) {
            throw new Exception("Produto não foi encontrado!");
        }
        return i;
    }

    
    public List<OrderItem> findAll() throws Exception {
        List<OrderItem> resultado = new ArrayList<>();
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM order_items"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            
            OrderItem i = new OrderItem();
            i.setId(resultSet.getInt("id"));
            i.setProductId(resultSet.getInt("product_id"));
            i.setOrderId(resultSet.getInt("order_id"));
            i.setQuantity(resultSet.getInt("quantity"));
            i.setPrice(resultSet.getFloat("price"));
            resultado.add(i);
                    
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        
        if (resultado.isEmpty()) {
            throw new Exception("Nenhum item encontrado!");
        }
        return resultado;
    }
    
    
    public List<OrderItem> findAllFromOrder(Integer order_id) throws Exception {
        List<OrderItem> resultado = new ArrayList<>();
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM order_items where order_id = ?"
        );
        
        preparedStatement.setInt(1, order_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            
            OrderItem i = new OrderItem();
            i.setId(resultSet.getInt("id"));
            i.setProductId(resultSet.getInt("product_id"));
            i.setOrderId(resultSet.getInt("order_id"));
            i.setQuantity(resultSet.getInt("quantity"));
            i.setPrice(resultSet.getFloat("price"));
            resultado.add(i);
                    
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        
        if (resultado.isEmpty()) {
            throw new Exception("Nenhum item encontrado para esse pedido!");
        }
        return resultado;
    }
}
