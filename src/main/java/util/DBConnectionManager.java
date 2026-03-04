package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {

    private static DBConnectionManager instance;

    private static final String DB_URL  = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
    private static final String DB_USER = "SYSTEM";
    private static final String DB_PASS = "Oracle123";

    private DBConnectionManager() {
        try {
            // Use ojdbc6.jar / ojdbc7.jar / ojdbc8.jar depending on your environment
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Oracle JDBC Driver not found in classpath", e);
        }
    }

    public static synchronized DBConnectionManager getInstance() {
        if (instance == null) {
            instance = new DBConnectionManager();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
}