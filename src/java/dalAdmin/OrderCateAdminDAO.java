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

            prepareStaff.UserID AS PrepareStaffID,
            prepareStaff.FullName AS PrepareStaffName,
            op.PrepareTime,

            shipper.UserID AS ShipperID,
            shipper.FullName AS ShipperName,
            shipper.PhoneNumber AS ShipperPhone,         
            s.ShipTime,

            warrantyStaff.UserID AS WarrantyStaffID,
            warrantyStaff.FullName AS WarrantyStaffName,
            wa.AssignedDate

        FROM Orders o
        JOIN Users customer ON o.CustomerID = customer.UserID

        LEFT JOIN OrderPreparements op ON o.OrderID = op.OrderID
        LEFT JOIN Users prepareStaff ON op.UserID = prepareStaff.UserID

        LEFT JOIN Shipping s ON o.OrderID = s.OrderID
        LEFT JOIN Users shipper ON s.ShipperID = shipper.UserID

        LEFT JOIN WarrantyAssignments wa ON o.OrderID = wa.OrderID
        LEFT JOIN Users warrantyStaff ON wa.UserID = warrantyStaff.UserID

        WHERE o.Product_Type = 0 AND o.Status = ?
        ORDER BY o.OrderDate DESC;
    """;

        try (Connection conn = new DBAdminContext().connection; PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, status);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrderCate order = new OrderCate();

                    // Order
                    order.setOrderID(rs.getInt("OrderID"));
                    order.setOrderCode(rs.getString("OrderCode"));
                    order.setProduct_Type((Integer) rs.getObject("Product_Type"));
                    order.setFullName(rs.getNString("Consignee"));
                    order.setOrderDate(rs.getTimestamp("OrderDate"));
                    order.setAddress(rs.getString("OrderAddress"));
                    order.setTotalAmount(rs.getInt("TotalAmount"));
                    order.setStatus(rs.getInt("OrderStatus"));

                    // Customer
                    order.setCustomerID(rs.getInt("CustomerUserID"));
                    order.setCustomerName(rs.getString("CustomerName"));

                    // Staff chuẩn bị
                    order.setStaffID(rs.getInt("PrepareStaffID"));
                    order.setStaffName(rs.getString("PrepareStaffName"));
                    order.setPrepareTime(rs.getTimestamp("PrepareTime"));

                    // Shipper
                    order.setShipperID(rs.getInt("ShipperID"));
                    order.setShipperName(rs.getString("ShipperName"));
                    order.setShipperPhone(rs.getString("ShipperPhone"));
                    order.setShipTime(rs.getDate("ShipTime"));

                    // Warranty staff
                    order.setWarrantyStaffID(rs.getInt("WarrantyStaffID"));
                    order.setWarrantyStaffName(rs.getString("WarrantyStaffName"));
                    order.setWarrantyAssignedDate(rs.getTimestamp("AssignedDate"));

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
