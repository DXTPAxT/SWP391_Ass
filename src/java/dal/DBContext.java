/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author PC ASUS
 */
public class DBContext {

    private static DBContext instance = new DBContext();//singleton pattern
    Connection connection;
    String err;

    public static DBContext getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    private DBContext() {
        try {
            if (connection == null || connection.isClosed()) {
                String user = "sa";
                String password = "123";
//             String url = "jdbc:sqlserver://localhost:1433;databaseName=CMA";
                String url = "jdbc:sqlserver://Laptop-dxt:1433;databaseName=ComputerOnlineShop;TrustServerCertificate=true";
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connection = DriverManager.getConnection(url, user, password);
            }
        } catch (Exception e) {
            err = e.getMessage() ;
            connection = null;
        }
    }
    
    public String getErr () {
        return this.err;
    }
}
