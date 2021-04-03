/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.admin.model;

import static ecommerce.config.Config.JDBC_SENHA;
import static ecommerce.config.Config.JDBC_URL;
import static ecommerce.config.Config.JDBC_USUARIO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author anacl
 */
public class AdminDAO {
    
    public void update(Admin a, int id) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE admins SET name = ?, password=?, login=?, email=? "
                        + "WHERE id = ?"
        );
        preparedStatement.setString(1, a.getName());
        preparedStatement.setString(2, a.getPassword());
        preparedStatement.setString(3, a.getLogin());
        preparedStatement.setString(4, a.getEmail());
        preparedStatement.setInt(5, id);
        int resultado = preparedStatement.executeUpdate();
        
        preparedStatement.close();
        connection.close();
        
        if (resultado != 1) {
            throw new Exception("Admin não foi atualizado!");
        }
    }
    
    public void delete(int id) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE from admins WHERE id = ?"
        );
        preparedStatement.setInt(1, id);
        int resultado = preparedStatement.executeUpdate();
        
        preparedStatement.close();
        connection.close();
        
        if (resultado != 1) {
            throw new Exception("Admin não foi deletado!");
        }
    }
    
    public Admin find(int id) throws Exception {
        Admin a = null;
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM admins WHERE id = ?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            a = new Admin();
            a.setId(resultSet.getInt("id"));
            a.setEmail(resultSet.getString("email"));
            a.setPassword(resultSet.getString("password"));
            a.setName(resultSet.getString("name"));
            a.setLogin(resultSet.getString("login"));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        
        if (a == null) {
            throw new Exception("Admin não existe!");
        }
        return a;
    }
    
    public Admin findFromLogin(String login) throws Exception {
        Admin a = null;
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM admins WHERE login = ?"
        );
        preparedStatement.setString(1, login);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            a = new Admin();
            a.setId(resultSet.getInt("id"));
            a.setEmail(resultSet.getString("email"));
            a.setPassword(resultSet.getString("password"));
            a.setName(resultSet.getString("name"));
            a.setLogin(resultSet.getString("login"));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        
        if (a == null) {
            throw new Exception("Cliente não existe!");
        }
        return a;
    }
}
