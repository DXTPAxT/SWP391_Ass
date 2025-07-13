package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAdminContext {

    private static Connection sharedConnection;

    protected Connection connection;

    public DBAdminContext() {
        //@Students: You are allowed to edit user, pass, url variables to fit 
        //your system configuration
        //You can also add more methods for Database Interaction tasks. 
        //But we recommend you to do it in another class
        // For example : StudentDBContext extends DBAdminContext , 
        //where StudentDBContext is located in dal package, 
        try {
            String user = "sa";
            String pass = "123";


            String url = "jdbc:sqlserver://LAPTOP-dxt\\SQLEXPRESS:1433;databaseName=ComputerOnlineShop";



 
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("❌ Lỗi kết nối MySQL: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public ResultSet getData(String sql) {
        ResultSet rs = null;
        try (Statement state = connection.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE)) {
            rs = state.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println("❌ Lỗi khi thực thi truy vấn: " + ex.getMessage());
            ex.printStackTrace();
        }
        return rs;
    }

    public boolean isConnected() {
        try {
            return sharedConnection != null && !sharedConnection.isClosed();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        DBContext dbContext = new DBContext();
        if (dbContext.isConnected()) {
            System.out.println("✅ Đã kết nối đến cơ sở dữ liệu MySQL.");
        } else {
            System.out.println("❌ Kết nối cơ sở dữ liệu thất bại.");
        }
    }
}
