/*
 * Click nb://source://SystemFileSystemAdmin/Templates/Licenses/license-default.txt to change this license
 * Click nb://source://SystemFileSystemAdmin/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package dalAdmin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBAdminContext {

    protected Connection connection;

    public DBAdminContext() {
        try {
            String user = "sa";
            String pass = "123";
            String url = "jdbc:sqlserver://LAPTOP-63C2NPU0\\SQLEXPRESS:1433;databaseName=ComputerOnlineShop";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBAdminContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException ex) {
            Logger.getLogger(DBAdminContext.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
