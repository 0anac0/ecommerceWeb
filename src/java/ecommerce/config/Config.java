package ecommerce.config;

import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author anacl
 */
public class Config {
    
    public static final String JDBC_URL = getProperty("jdbc.url");
    public static final String JDBC_USUARIO = getProperty("jdbc.user");
    public static final String JDBC_SENHA = getProperty("jdbc.password");
    private Config() {
        
    }
    private static String getProperty(String key) {
        Properties properties = new Properties();
        try {
            properties.load(Config.class.getResourceAsStream("config.properties"));
            return properties.getProperty(key);
        } catch (IOException ex) {
            return ex.getMessage();
        }
    }
}
