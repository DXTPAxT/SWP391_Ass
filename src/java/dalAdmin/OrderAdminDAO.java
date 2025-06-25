package dalAdmin;

import java.util.ArrayList;
import java.util.List;
import models.Order;
import java.sql.*;

public class OrderAdminDAO extends DBAdminContext {

    public List<Order> getAllOrders() {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT o.*, u.FullName FROM Orders o JOIN Users u ON o.CustomerID = u.UserID";

        try (Connection conn = new DBAdminContext().connection; PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Order order = new Order();
                order.setOrderID(rs.getInt("OrderID"));
                order.setProduct_Type((Integer) rs.getObject("Product_Type"));
                order.setCustomerID(rs.getInt("CustomerID"));
                order.setOrderDate(rs.getDate("OrderDate"));
                order.setAddress(rs.getString("Address"));
                order.setTotalAmount(rs.getInt("TotalAmount"));
                order.setStatus(rs.getInt("Status"));
                order.setFullName(rs.getString("FullName")); // lấy từ bảng Users
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void updateOrderStatus(int orderId, int newStatus) {
        String sql = "UPDATE Orders SET Status = ? WHERE OrderID = ?";

        try (Connection conn = new DBAdminContext().connection; PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, newStatus);
            ps.setInt(2, orderId);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Order " + orderId + " status updated to " + newStatus);
            } else {
                System.out.println("No order found with ID " + orderId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Order getOrderById(int orderId) {
        String sql = "SELECT o.*, u.FullName "
                + "FROM Orders o JOIN Users u ON o.CustomerID = u.UserID "
                + "WHERE o.OrderID = ?";

        try (Connection conn = new DBAdminContext().connection; PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Order order = new Order();
                    order.setOrderID(rs.getInt("OrderID"));
                    order.setProduct_Type((Integer) rs.getObject("Product_Type")); // có thể null
                    order.setCustomerID(rs.getInt("CustomerID"));
                    order.setOrderDate(rs.getDate("OrderDate"));
                    order.setAddress(rs.getString("Address"));
                    order.setTotalAmount(rs.getInt("TotalAmount"));
                    order.setStatus(rs.getInt("Status"));
                    order.setFullName(rs.getString("FullName")); // từ bảng Users
                    return order;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        OrderAdminDAO dao = new OrderAdminDAO();
        List<Order> orders = dao.getAllOrders();

        if (orders.isEmpty()) {
            System.out.println("No orders found in the database.");
        } else {
            for (Order order : orders) {
                System.out.println("Order ID: " + order.getOrderID());
                System.out.println("Customer Name: " + order.getFullName());
                System.out.println("Order Date: " + order.getOrderDate());
                System.out.println("Product Type: " + (order.getProduct_Type() == 0 ? "Category"
                        : order.getProduct_Type() == 1 ? "Build PC" : "Unknown"));
                System.out.println("Total Amount: " + order.getTotalAmount());
                System.out.println("Status: " + switch (order.getStatus()) {
                    case 0 ->
                        "Canceled";
                    case 1 ->
                        "Processing";
                    case 2 ->
                        "Shipping";
                    case 3 ->
                        "Completed";
                    default ->
                        "Unknown";
                });
                System.out.println("--------------------------");
            }
        }
    }

}
