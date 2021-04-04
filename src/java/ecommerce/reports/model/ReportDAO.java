/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.reports.model;

import static ecommerce.config.Config.JDBC_SENHA;
import static ecommerce.config.Config.JDBC_URL;
import static ecommerce.config.Config.JDBC_USUARIO;
import ecommerce.product.model.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author anacl
 */
public class ReportDAO {

    public List<ClientSalesReport> clientSalesReport(java.util.Date begin, java.util.Date end) throws Exception {
        List<ClientSalesReport> resultado = new ArrayList<>();
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select c.id as id,\n" +
                "	c.name as name,\n" +
                "	sum(o.total) as total,\n" +
                "	count(o.id) as sales\n" +
                "\n" +
                "from orders o inner join clients c on c.id=o.client_id\n" +
                "where date(o.created_at)>= ? and date(o.created_at) <= ? \n" +
                "group by c.id\n" +
                "order by sales desc"
        );
        
        preparedStatement.setDate(1, new java.sql.Date(begin.getTime()));
        preparedStatement.setDate(2, new java.sql.Date(end.getTime()));
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            ClientSalesReport clientSales = new ClientSalesReport();
            clientSales.setId(resultSet.getInt("id"));
            clientSales.setSales(resultSet.getInt("sales"));
            clientSales.setAmount(resultSet.getFloat("total"));
            clientSales.setName(resultSet.getString("name"));
            resultado.add(clientSales);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        
        return resultado;
    }
    
    public List<Product> productsOutOfStockReport() throws Exception {
        List<Product> resultado = new ArrayList<>();
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from products \n" +
                "	where quantity <=0 \n" +
                "	order by name desc "
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
        
        return resultado;
    }
    
    
    public List<SalesReport> salesReport(java.util.Date begin, java.util.Date end) throws Exception {
        List<SalesReport> resultado = new ArrayList<>();
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select date(created_at) as date, sum(total) as total\n" +
                "from orders\n" +
                "where date(created_at)>= ? and date(created_at) <=?\n" +
                "group by date(created_at)\n" +
                "order by date asc"
        );
        preparedStatement.setDate(1, new java.sql.Date(begin.getTime()));
        preparedStatement.setDate(2, new java.sql.Date(end.getTime()));
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            SalesReport salesReport = new SalesReport();
            salesReport.setDate(resultSet.getDate("date"));
            salesReport.setTotal(resultSet.getDouble("total"));
            resultado.add(salesReport);
                    
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return resultado;
    }    
}
