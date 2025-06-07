package dal;

import java.sql.*;
import java.util.*;
import models.Shipping;

public class ShippingDAO extends DBContext {

    public List<Shipping> getShippingByUserID(int userID) {
        List<Shipping> list = new ArrayList<>();
        String sql = "SELECT s.* FROM Shipping s JOIN Orders o ON s.OrderID = o.OrderID WHERE o.CustomerID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Shipping ship = new Shipping(
                        rs.getInt("ShippingID"),
                        rs.getInt("OrderID"),
                        rs.getInt("ShipperID"),
                        rs.getString("ShippingStatus"),
                        rs.getString("Feedback")
                );
                list.add(ship);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Shipping> getAllShipping() {
        List<Shipping> list = new ArrayList<>();
        String sql = "SELECT * FROM Shipping";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Shipping ship = new Shipping(
                        rs.getInt("ShippingID"),
                        rs.getInt("OrderID"),
                        rs.getInt("ShipperID"),
                        rs.getString("ShippingStatus"),
                        rs.getString("Feedback")
                );
                list.add(ship);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
