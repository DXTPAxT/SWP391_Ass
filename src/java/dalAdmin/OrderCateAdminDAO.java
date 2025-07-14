package dalAdmin;

import java.util.ArrayList;
import java.util.List;
import models.OrderCate;
import java.sql.*;
import models.OrderItems;

public class OrderCateAdminDAO extends DBAdminContext {

    public List<OrderCate> getOrdersByStatus(int status) {
        List<OrderCate> list = new ArrayList<>();
        String sql = """
        SELECT 
            o.OrderID,
            o.OrderCode,
            o.Product_Type,
            o.FullName AS Consignee,
            o.OrderDate,
            o.Address AS OrderAddress,
            o.TotalAmount,
            o.Status AS OrderStatus,
            
            customer.UserID AS CustomerUserID,
            customer.FullName AS CustomerName,
            
            staff.UserID AS StaffUserID,
            staff.FullName AS StaffName,
            op.PrepareTime
        FROM Orders o
        JOIN Users customer ON o.CustomerID = customer.UserID
        LEFT JOIN OrderPreparements op ON o.OrderID = op.OrderID
        LEFT JOIN Users staff ON op.UserID = staff.UserID
        WHERE o.Product_Type = 0 AND o.Status = ?
        ORDER BY o.OrderDate DESC;
    """;

        try (Connection conn = new DBAdminContext().connection; PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, status);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrderCate order = new OrderCate();
                    order.setOrderID(rs.getInt("OrderID"));
                    order.setOrderCode(rs.getString("OrderCode"));
                    order.setProduct_Type((Integer) rs.getObject("Product_Type"));
                    order.setFullName(rs.getNString("Consignee"));
                    order.setOrderDate(rs.getTimestamp("OrderDate"));
                    order.setAddress(rs.getString("OrderAddress"));
                    order.setTotalAmount(rs.getInt("TotalAmount"));
                    order.setStatus(rs.getInt("OrderStatus"));

                    // Customer info
                    order.setCustomerID(rs.getInt("CustomerUserID"));
                    order.setCustomerName(rs.getString("CustomerName"));

                    // Staff info
                    order.setStaffID(rs.getInt("StaffUserID"));
                    order.setStaffName(rs.getString("StaffName"));
                    order.setPrepareTime(rs.getTimestamp("PrepareTime"));

                    list.add(order);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void updateOrderStatus(int orderID, int status) {
        String sql = "UPDATE Orders SET Status = ? WHERE OrderID = ?";
        try (Connection conn = new DBAdminContext().connection; PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, status);
            ps.setInt(2, orderID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*public static void main(String[] args) {
        OrderCateAdminDAO dao = new OrderCateAdminDAO();
        List<OrderItems> orders ;

        if (orders.isEmpty()) {
            System.out.println("No orders found in the database.");
        } else {
            for (OrderItems order : orders) {
                System.out.println("Order ID: " + order.getOrderID());

            }
        }
    }*/
}
