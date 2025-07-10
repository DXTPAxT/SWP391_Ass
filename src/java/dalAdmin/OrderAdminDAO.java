package dalAdmin;

import java.util.ArrayList;
import java.util.List;
import models.OrderCate;
import java.sql.*;

public class OrderAdminDAO extends DBAdminContext {
    
    public List<OrderCate> getAllOrderItems() {
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
                 WHERE o.Product_Type = 0
                 ORDER BY o.OrderDate DESC;""";
        
        try (Connection conn = new DBAdminContext().connection; PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            
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
                order.setStaffID(rs.getInt("StaffUserID")); // cần có setter tương ứng
                order.setStaffName(rs.getString("StaffName")); // cần có setter tương ứng
                order.setPrepareTime(rs.getTimestamp("PrepareTime"));
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    public List<OrderCate> getAllPendingOrderItems() {
        List<OrderCate> list = new ArrayList<>();
        String sql = """
                 SELECT 
                     o.OrderID,
                     o.OrderCode,
                     o.Product_Type,
                     o.OrderDate,
                     o.Address AS OrderAddress,
                     o.TotalAmount,
                     o.Status AS OrderStatus,
                     
                     customer.UserID AS CustomerUserID,
                     customer.FullName AS CustomerName,
                     
                     staff.UserID AS StaffUserID,
                     staff.FullName AS StaffName
                 
                 FROM Orders o
                 JOIN Users customer ON o.CustomerID = customer.UserID
                 LEFT JOIN OrderPreparements op ON o.OrderID = op.OrderID
                 LEFT JOIN Users staff ON op.UserID = staff.UserID
                 WHERE o.Product_Type = 0 And o.Status = 1
                 ORDER BY o.OrderDate DESC;""";
        
        try (Connection conn = new DBAdminContext().connection; PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                OrderCate order = new OrderCate();
                order.setOrderID(rs.getInt("OrderID"));
                order.setOrderCode(rs.getString("OrderCode"));
                order.setProduct_Type((Integer) rs.getObject("Product_Type"));
                order.setOrderDate(rs.getTimestamp("OrderDate"));
                order.setAddress(rs.getString("OrderAddress"));
                order.setTotalAmount(rs.getInt("TotalAmount"));
                order.setStatus(rs.getInt("OrderStatus"));

                // Customer info
                order.setCustomerID(rs.getInt("CustomerUserID"));
                order.setFullName(rs.getString("CustomerName"));
                // Staff info
                order.setStaffID(rs.getInt("StaffUserID")); // cần có setter tương ứng
                order.setStaffName(rs.getString("StaffName")); // cần có setter tương ứng

                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    public List<OrderCate> getAllOrderBuildPC() {
        List<OrderCate> list = new ArrayList<>();
        String sql = """
                 SELECT 
                     o.OrderID,
                     o.OrderCode,
                     o.Product_Type,
                     o.OrderDate,
                     o.Address AS OrderAddress,
                     o.TotalAmount,
                     o.Status AS OrderStatus,
                     
                     customer.UserID AS CustomerUserID,
                     customer.FullName AS CustomerName,
                     customer.Email AS CustomerEmail,
                     
                     staff.UserID AS StaffUserID,
                     staff.FullName AS StaffName
                     op.PrepareTime
                 
                 FROM Orders o
                 JOIN Users customer ON o.CustomerID = customer.UserID
                 LEFT JOIN OrderPreparements op ON o.OrderID = op.OrderID
                 LEFT JOIN Users staff ON op.UserID = staff.UserID
                 WHERE o.Product_Type = 1
                 ORDER BY o.OrderDate DESC;""";
        
        try (Connection conn = new DBAdminContext().connection; PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                OrderCate order = new OrderCate();
                order.setOrderID(rs.getInt("OrderID"));
                order.setOrderCode(rs.getString("OrderCode"));
                order.setProduct_Type((Integer) rs.getObject("Product_Type"));
                order.setOrderDate(rs.getTimestamp("OrderDate"));
                order.setAddress(rs.getString("OrderAddress"));
                order.setTotalAmount(rs.getInt("TotalAmount"));
                order.setStatus(rs.getInt("OrderStatus"));

                // Customer info
                order.setCustomerID(rs.getInt("CustomerUserID"));

                // Staff info
                order.setStaffID(rs.getInt("StaffUserID")); // cần có setter tương ứng
                order.setStaffName(rs.getString("StaffName")); // cần có setter tương ứng

                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    public static void main(String[] args) {
        OrderAdminDAO dao = new OrderAdminDAO();
        List<OrderCate> orders = dao.getAllOrderItems();
        
        if (orders.isEmpty()) {
            System.out.println("No orders found in the database.");
        } else {
            for (OrderCate order : orders) {
                System.out.println("Order ID: " + order.getOrderID());
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
