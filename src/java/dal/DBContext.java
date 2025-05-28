package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {

    protected Connection connection;

    public DBContext() {
        try {
            String user = "sa"; // thay bằng user của bạn
            String pass = "1234"; // thay bằng mật khẩu thật
            String serverName = "localhost"; // hoặc LAPTOP-8RQSUOPU nếu đúng
            String instanceName = "SQLEXPRESS"; // nếu bạn dùng SQL Server Express
            String dbName = "ComputerOnlineShop";

            // Nếu dùng instance
            String url = "jdbc:sqlserver://" + serverName + "\\" + instanceName + ":1433;"
                       + "databaseName=" + dbName + ";"
                       + "encrypt=true;trustServerCertificate=true;";

            // Nếu KHÔNG dùng instance, thử dùng dòng này thay thế:
            // String url = "jdbc:sqlserver://localhost:1433;databaseName=" + dbName + ";encrypt=true;trustServerCertificate=true;";


            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, "Kết nối cơ sở dữ liệu thất bại.", ex);
        }
    }

    public ResultSet getData(String sql) {
        ResultSet rs = null;
        try (Statement state = connection.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            rs = state.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, "Lỗi khi truy vấn dữ liệu", ex);
        }
        return rs;
    }

    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static void main(String[] args) {
        DBContext dbContext = new DBContext();
        if (dbContext.isConnected()) {
            System.out.println("Kết nối cơ sở dữ liệu thành công!");
        } else {
            System.out.println("Kết nối cơ sở dữ liệu thất bại.");
        }
    }
}
