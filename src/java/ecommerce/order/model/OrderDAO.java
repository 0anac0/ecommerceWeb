package ecommerce.order.model;

import ecommerce.order.model.Order;
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
public class OrderDAO {

    public void insert(Order o) throws Exception {
        Class.forName("org.postgresql.Driver");
        
        int resultado;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA)) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO orders (client_id, created_at) VALUES (?, ?, ?)"
            );  
            java.sql.Date sqlDate = new java.sql.Date(o.getCreatedAt().getTime());
            preparedStatement.setInt(1, o.getClientId());
            preparedStatement.setDate(2, sqlDate);
            resultado = preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        if (resultado != 1) {
            throw new Exception("Produto não foi inserido!");
        }
    }

    public void excluir(int id) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM orders WHERE id = ?"
        );
        preparedStatement.setInt(1, id);
        int resultado = preparedStatement.executeUpdate();
        
        preparedStatement.close();
        connection.close();
        
        if (resultado != 1) {
            throw new Exception("Pedido não foi deletado!");
        }
    }
    
    public Order obter(int id) throws Exception {
        Order o = null;
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM orders WHERE id = ?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            o = new Order();
            o.setId(resultSet.getInt("id"));
            o.setClientId(resultSet.getInt("client_id"));
            o.setCreatedAt(resultSet.getDate("created_at"));
                                
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        
        if (o == null) {
            throw new Exception("Produto não foi encontrado!");
        }
        return o;
    }

    
    public List<Order> obterTodos() throws Exception {
        List<Order> resultado = new ArrayList<>();
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM orders"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            Order o = new Order();
            o.setId(resultSet.getInt("id"));
            o.setClientId(resultSet.getInt("client_id"));
            o.setCreatedAt(resultSet.getDate("created_at"));
                                
            resultado.add(o);
                    
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        
        if (resultado.isEmpty()) {
            throw new Exception("Nenhum pedido encontrado!");
        }
        return resultado;
    }
}