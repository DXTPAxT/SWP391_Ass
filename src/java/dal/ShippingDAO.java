package dal;

import models.Shipping;
import models.User; // Thêm import này để tham chiếu lớp User
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShippingDAO extends DBContext {

    private static final Logger LOGGER = Logger.getLogger(ShippingDAO.class.getName());
    private final UserDAO userDAO = new UserDAO();

    public List<Shipping> getCustomerShipments(int userID) {
        List<Shipping> shipments = new ArrayList<>();
        String sql = """
            SELECT o.OrderID, o.OrderDate, o.Address, o.TotalAmount, s.ShippingID, s.ShipperID, s.ShippingStatus, s.Feedback,
                   oi.OrderItemID, oi.Quantity, oi.Price, c.CategoryName, c.ImageURL
            FROM Orders o
            JOIN Shipping s ON o.OrderID = s.OrderID
            JOIN OrderItems oi ON o.OrderID = oi.OrderID
            JOIN Categories c ON oi.CategoryID = c.CategoryID
            WHERE o.CustomerID = ?
        """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Shipping shipment = new Shipping(
                            rs.getInt("ShippingID"),
                            rs.getInt("OrderID"),
                            rs.getInt("ShipperID"),
                            rs.getTimestamp("OrderDate"),
                            rs.getString("Address"),
                            rs.getInt("TotalAmount"),
                            rs.getString("ShippingStatus"),
                            rs.getString("Feedback"),
                            rs.getInt("OrderItemID"),
                            rs.getInt("Quantity"),
                            rs.getInt("Price"),
                            rs.getString("CategoryName"),
                            rs.getString("ImageURL")
                    );
                    shipments.add(shipment);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching customer shipments", e);
        }
        return shipments;
    }

    public List<Shipping> getShipperAssignments(int shipperID) {
        List<Shipping> shipments = new ArrayList<>();
        // Kiểm tra shipperID có RoleID = 2
        User shipper = userDAO.getUserByID(shipperID); // Dòng 59 (theo lỗi)
        if (shipper == null || shipper.getRole().getRoleID()!= 2) {
            return shipments; // Trả về danh sách rỗng nếu không phải shipper
        }
        String sql = """
            SELECT o.OrderID, o.OrderDate, o.Address, o.TotalAmount, s.ShippingID, s.ShipperID, s.ShippingStatus, s.Feedback,
                   oi.OrderItemID, oi.Quantity, oi.Price, c.CategoryName, c.ImageURL
            FROM Orders o
            JOIN Shipping s ON o.OrderID = s.OrderID
            JOIN OrderItems oi ON o.OrderID = oi.OrderID
            JOIN Categories c ON oi.CategoryID = c.CategoryID
            WHERE s.ShipperID = ?
        """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, shipperID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Shipping shipment = new Shipping(
                            rs.getInt("ShippingID"),
                            rs.getInt("OrderID"),
                            rs.getInt("ShipperID"),
                            rs.getTimestamp("OrderDate"),
                            rs.getString("Address"),
                            rs.getInt("TotalAmount"),
                            rs.getString("ShippingStatus"),
                            rs.getString("Feedback"),
                            rs.getInt("OrderItemID"),
                            rs.getInt("Quantity"),
                            rs.getInt("Price"),
                            rs.getString("CategoryName"),
                            rs.getString("ImageURL")
                    );
                    shipments.add(shipment);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching shipper assignments", e);
        }
        return shipments;
    }

    public void updateShippingStatus(int shippingID, String status, String feedback) {
        String sql = "UPDATE Shipping SET ShippingStatus = ?, Feedback = ? WHERE ShippingID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setString(2, feedback);
            ps.setInt(3, shippingID);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating shipping status", e);
        }
    }
}
