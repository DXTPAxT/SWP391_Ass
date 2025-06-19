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

    private final UserDAO userDAO = new UserDAO();

    public int countShips1MonthByID(int UserID) {
        int count = 0;
        String sql = """
                         SELECT COUNT (ShippingID) as numberOfShips
                         FROM Shipping 
                         WHERE ShipperID = ?
                         and ShipTime >= DATEFROMPARTS(YEAR(GETDATE()), MONTH(GETDATE()), 1);
                     """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, UserID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("numberOfShips");
            }
        } catch (SQLException e) {
            System.err.println("getAllBrands Error: " + e.getMessage());
        }
        return count;
    }
}
