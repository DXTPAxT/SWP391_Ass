package dal;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import models.OrderCate; // Đảm bảo đã có class OrderCate
import models.OrderItems;

public class OrderDAO extends DBContext {

// Hàm tạo order và trả về OrderID vừa tạo, trả về -1 nếu lỗi
    public int createOrderAndReturnId(OrderCate order) {
        String sql = "INSERT INTO Orders (OrderCode, Product_Type, CustomerID, Address, FullName, PhoneNumber, TotalAmount, PaymentStatusID, Status, Note) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setObject(1, order.getOrderCode());
            ps.setObject(2, order.getProduct_Type());
            ps.setInt(3, order.getCustomerID());
            ps.setString(4, order.getAddress());
            ps.setString(5, order.getFullName());
            ps.setString(6, order.getPhoneNumber());
            ps.setInt(7, order.getTotalAmount());
            ps.setInt(8, order.getPaymentStatusID());
            ps.setInt(9, order.getStatus());
            ps.setString(10, order.getNote()); // Thêm Note
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

    // Tạo code ngẫu nhiên & đảm bảo không trùng
    public String generateRandomOrderCode() {
        Random rand = new Random();
        String code;
        do {
            int randomNum = 100000 + rand.nextInt(900000); // 6 số
            code = String.valueOf(randomNum);
        } while (isOrderCodeExist(code));
        return code;
    }

    public boolean isOrderCodeExist(String code) {
        String sql = "SELECT COUNT(*) FROM Orders WHERE OrderCode = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public OrderCate getOrderByID(int orderID) {
        String sql = """
        SELECT o.OrderID, o.OrderCode, o.Product_Type, o.CustomerID, o.FullName, o.OrderDate, o.Address, o.PhoneNumber, o.TotalAmount, o.Status, o.PaymentStatusID, os.StatusID, os.Status
        FROM Orders o
        LEFT JOIN OrderStatus os ON o.Status = os.StatusID
        WHERE o.OrderID = ?
        """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, orderID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    OrderCate order = new OrderCate();
                    order.setOrderID(rs.getInt("OrderID"));
                    order.setOrderCode(rs.getString("OrderCode"));
                    order.setProduct_Type(rs.getInt("Product_Type"));
                    order.setCustomerID(rs.getInt("CustomerID"));
                    order.setFullName(rs.getString("FullName"));
                    order.setOrderDate(rs.getTimestamp("OrderDate"));
                    order.setAddress(rs.getString("Address"));
                    order.setPhoneNumber(rs.getString("PhoneNumber"));
                    order.setTotalAmount(rs.getInt("TotalAmount"));
                    order.setStatus(rs.getInt("Status"));
                    order.setPaymentStatusID(rs.getInt("PaymentStatusID"));
                    // Thêm thông tin status chi tiết
                    models.OrderStatus status = new models.OrderStatus(rs.getInt("StatusID"), rs.getString("Status"));
                    order.setOrderStatus(status);
                    return order;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public OrderCate getOrderCateByID(int orderID) {
        OrderCate order = null;
        String sql = "SELECT o.*, os.StatusID, os.Status FROM Orders o LEFT JOIN OrderStatus os ON o.Status = os.StatusID WHERE o.OrderID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                order = new OrderCate();
                order.setOrderID(rs.getInt("OrderID"));
                order.setOrderCode(rs.getString("OrderCode"));
                order.setProduct_Type(rs.getInt("Product_Type"));
                order.setCustomerID(rs.getInt("CustomerID"));
                order.setOrderDate(rs.getTimestamp("OrderDate"));
                order.setAddress(rs.getString("Address"));
                order.setPhoneNumber(rs.getString("PhoneNumber"));
                order.setFullName(rs.getString("Fullname"));
                order.setNote(rs.getString("Note"));
                order.setPaymentStatusID(rs.getInt("PaymentStatusID"));
                order.setTotalAmount(rs.getInt("TotalAmount"));
                order.setStatus(rs.getInt("Status"));
                // Thêm thông tin status chi tiết
                models.OrderStatus status = new models.OrderStatus(rs.getInt("StatusID"), rs.getString("Status"));
                order.setOrderStatus(status);
                // Gọi OrderItemDAO để lấy danh sách OrderItems kèm OrderDetail
                OrderItemDAO orderItemDAO = new OrderItemDAO();
                ArrayList<OrderItems> items = orderItemDAO.getOrderItemsByOrderID(orderID);
                order.setOrderItems(items);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }

    public ArrayList<OrderCate> getOrdersByCustomerID(int customerID) {
        ArrayList<OrderCate> orders = new ArrayList<>();
        String sql = "SELECT o.*, os.StatusID, os.Status FROM Orders o LEFT JOIN OrderStatus os ON o.Status = os.StatusID WHERE o.CustomerID = ? AND o.Product_Type = 0 ORDER BY o.OrderID DESC";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, customerID);
            ResultSet rs = ps.executeQuery();
            OrderItemDAO orderItemDAO = new OrderItemDAO();
            while (rs.next()) {
                OrderCate order = new OrderCate();
                order.setOrderID(rs.getInt("OrderID"));
                order.setOrderCode(rs.getString("OrderCode"));
                order.setProduct_Type(rs.getInt("Product_Type"));
                order.setCustomerID(rs.getInt("CustomerID"));
                order.setOrderDate(rs.getTimestamp("OrderDate"));
                order.setAddress(rs.getString("Address"));
                order.setPhoneNumber(rs.getString("PhoneNumber"));
                order.setFullName(rs.getString("Fullname"));
                order.setNote(rs.getString("Note"));
                order.setPaymentStatusID(rs.getInt("PaymentStatusID"));
                order.setTotalAmount(rs.getInt("TotalAmount"));
                order.setStatus(rs.getInt("Status"));
                // Thêm thông tin status chi tiết
                models.OrderStatus status = new models.OrderStatus(rs.getInt("StatusID"), rs.getString("Status"));
                order.setOrderStatus(status);
                // Gọi DAO để lấy danh sách OrderItems của OrderID hiện tại
                ArrayList<OrderItems> items = orderItemDAO.getOrderItemsByOrderID(order.getOrderID());
                order.setOrderItems(items);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public static void main(String[] args) {
        // 2. Tạo DAO
        OrderDAO dao = new OrderDAO();

        // 3. Tạo OrderCate mẫu
        OrderCate order = new OrderCate();
        order.setOrderCode("1");
        order.setProduct_Type(0);
        order.setCustomerID(1);
        order.setAddress("123 Main Street");
        order.setFullName("John Doe");
        order.setPhoneNumber("0123456789");
        order.setTotalAmount(5000000);
        order.setPaymentStatusID(1);
        order.setStatus(1);

        // 4. Gọi hàm insert và lấy ID
        int orderId = dao.createOrderAndReturnId(order);

        System.out.println("New Order ID: " + orderId);
        System.out.println(dao.getOrderByID(8));
        System.out.println(dao.getOrdersByCustomerID(5));
    }
}
