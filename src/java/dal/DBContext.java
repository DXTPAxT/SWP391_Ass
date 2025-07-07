package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBContext {

    protected Connection connection;

    public DBContext() {
        try {         
            String user = "root";       // Tài khoản MySQL
            String pass = "123456";     // Mật khẩu MySQL, điều chỉnh theo máy bạn

            String url = "jdbc:mysql://localhost:3306/ComputerOnlineShop?useSSL=false&serverTimezone=UTC";

            Class.forName("com.mysql.cj.jdbc.Driver"); // Driver chuẩn MySQL

            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("✅ Kết nối MySQL thành công!");
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("❌ Lỗi kết nối MySQL: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    
    public ResultSet getData(String sql) {
        ResultSet rs = null;
        try (Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            rs = state.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println("❌ Lỗi khi thực thi truy vấn: " + ex.getMessage());
            ex.printStackTrace();
        }
        return rs;
    }

    
    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
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
