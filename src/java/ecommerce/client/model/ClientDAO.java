/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.client.model;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author anacl
 */
public class ClientDAO {
    
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/ecommerce";
    private static final String JDBC_USUARIO = "postgres";
    private static final String JDBC_SENHA = "anac123";

    public void insert(Client c) throws Exception {
        Class.forName("org.postgresql.Driver");
        
        int resultado;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA)) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO clients (name, address, login, password, email) VALUES (?, ?, ?, ?, ?)"
            );  
            preparedStatement.setString(1, c.getName());
            preparedStatement.setString(2, c.getAddress());
            preparedStatement.setString(3, c.getLogin());
            preparedStatement.setString(4, c.getPassword());
            preparedStatement.setString(5, c.getEmail());
            resultado = preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        if (resultado != 1) {
            throw new Exception("Cliente não foi inserido!");
        }
    }

    public void atualizar(Client c, int id) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE clients SET name = ?, address = ?, password=?, email=? "
                        + "WHERE id = ?"
        );
        preparedStatement.setString(1, c.getName());
        preparedStatement.setString(2, c.getAddress());
        preparedStatement.setString(3, c.getPassword());
        preparedStatement.setString(4, c.getEmail());
        preparedStatement.setInt(5, id);
        int resultado = preparedStatement.executeUpdate();
        
        preparedStatement.close();
        connection.close();
        
        if (resultado != 1) {
            throw new Exception("Cliente não foi atualizado!");
        }
    }
    
    public void excluir(int id) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE clients SET deleted=true WHERE id = ?"
        );
        preparedStatement.setInt(1, id);
        int resultado = preparedStatement.executeUpdate();
        
        preparedStatement.close();
        connection.close();
        
        if (resultado != 1) {
            throw new Exception("Cliente não foi deletado!");
        }
    }
    
    public Client obter(int id) throws Exception {
        Client c = null;
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM clients WHERE id = ? AND deleted is not true"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            c = new Client();
            c.setId(resultSet.getInt("id"));
            c.setEmail(resultSet.getString("email"));
            c.setAddress(resultSet.getString("address"));
            c.setPassword(resultSet.getString("password"));
            c.setName(resultSet.getString("name"));
            c.setLogin(resultSet.getString("login"));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        
        if (c == null) {
            throw new Exception("Cliente não foi existe!");
        }
        return c;
    }
    
    public Client obterLogin(String login) throws Exception {
        Client c = null;
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM clients WHERE login = ? AND deleted is not true"
        );
        preparedStatement.setString(1, login);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            c = new Client();
            c.setId(resultSet.getInt("id"));
            c.setEmail(resultSet.getString("email"));
            c.setAddress(resultSet.getString("address"));
            c.setPassword(resultSet.getString("password"));
            c.setName(resultSet.getString("name"));
            c.setLogin(resultSet.getString("login"));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        
        if (c == null) {
            throw new Exception("Cliente não existe!");
        }
        return c;
    }
    
    public List<Client> obterTodos() throws Exception {
        List<Client> resultado = new ArrayList<Client>();
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM clients WHERE deleted is not true"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            Client c = new Client();
            c = new Client();
            c.setId(resultSet.getInt("id"));
            c.setEmail(resultSet.getString("email"));
            c.setAddress(resultSet.getString("address"));
            c.setPassword(resultSet.getString("password"));
            c.setName(resultSet.getString("name"));
            c.setLogin(resultSet.getString("login"));
            resultado.add(c);
                    
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        
        if (resultado.isEmpty()) {
            throw new Exception("Nenhum cliente encontrado!");
        }
        return resultado;
    }
}
