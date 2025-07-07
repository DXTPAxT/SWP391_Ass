package dal;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.Order; // Đảm bảo đã có class Order

public class OrderDAO extends DBContext {

    // Hàm tạo order và trả về OrderID vừa tạo, trả về -1 nếu lỗi
    public int createOrderAndReturnId(Order order) {
        String sql = "INSERT INTO Orders (OrderCode, Product_Type, CustomerID, Address, Fullname, PhoneNumber, TotalAmount, PaymentStatusID, Status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setObject(1, order.getOrderCode());
            ps.setObject(2, order.getProduct_Type());
            ps.setInt(3, order.getCustomerID());
            ps.setString(4, order.getAddress());
            ps.setString(5, order.getFullName());
            ps.setString(6, order.getPhoneNumber());
            ps.setInt(7, order.getTotalAmount());
            ps.setInt(8, order.getPaymentStatus());
            ps.setInt(9, order.getStatus());
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // Trả về OrderID vừa tạo
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1; // Lỗi
    }

    public static void main(String[] args) {
            // 2. Tạo DAO
            OrderDAO dao = new OrderDAO();

            // 3. Tạo Order mẫu
            Order order = new Order();
            order.setOrderCode("1");
            order.setProduct_Type(0);
            order.setCustomerID(1);
            order.setAddress("123 Main Street");
            order.setFullName("John Doe");
            order.setPhoneNumber("0123456789");
            order.setTotalAmount(5000000);
            order.setPaymentStatus(1);
            order.setStatus(1);

            // 4. Gọi hàm insert và lấy ID
            int orderId = dao.createOrderAndReturnId(order);

            System.out.println("New Order ID: " + orderId);
    }
}
