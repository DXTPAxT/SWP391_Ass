package dal;

import java.sql.*;
import java.util.ArrayList;
import models.CartItem;
import models.Categories;
import models.Products;
import models.Warranties;
import models.WarrantyDetails;

public class CartItemDAO extends DBContext {

    public ArrayList<CartItem> getCartItemsByUserId(int userId) {
        ArrayList<CartItem> itemList = new ArrayList<>();

        String sql = """
        SELECT 
            ci.CartItemID,
            ci.UserID,
            ci.Quantity,
            ci.Status AS CartStatus,

            c.CategoryID,
            c.CategoryName,
            c.BrandComID,
            c.Price AS CategoryPrice,
            c.Quantity AS CategoryQuantity,
            c.Description AS CategoryDescription,
            c.Status AS CategoryStatus,
            c.ImageURL,

            wd.WarrantyDetailID,
            wd.WarrantyID,
            wd.Price AS WarrantyPrice,
            wd.Status AS WarrantyStatus,
            w.WarrantyPeriod,
            w.Description AS WarrantyText

        FROM CartItems ci
        JOIN Categories c ON ci.CategoryID = c.CategoryID
        JOIN WarrantyDetails wd ON ci.WarrantyDetailID = wd.WarrantyDetailID
        JOIN Warranties w ON wd.WarrantyID = w.WarrantyID
        WHERE ci.UserID = ?
    """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Build Category
                Categories cat = new Categories(
                        rs.getInt("CategoryID"),
                        rs.getString("CategoryName"),
                        rs.getInt("BrandComID"),
                        rs.getInt("CategoryQuantity"),
                        rs.getInt("CategoryPrice"),
                        rs.getString("CategoryDescription"),
                        rs.getInt("CategoryStatus"),
                        rs.getString("ImageURL")
                );

                // Build Warranties
                Warranties w = new Warranties();
                w.setWarrantyID(rs.getInt("WarrantyID"));
                w.setWarrantyPeriod(rs.getInt("WarrantyPeriod"));
                w.setDescription(rs.getString("WarrantyText"));

                // Build WarrantyDetails
                WarrantyDetails wd = new WarrantyDetails();
                wd.setWarrantyDetailID(rs.getInt("WarrantyDetailID"));
                wd.setWarrantyID(rs.getInt("WarrantyID"));
                wd.setPrice(rs.getInt("WarrantyPrice"));
                wd.setStatus(rs.getInt("WarrantyStatus"));
                wd.setWarranty(w);

                // Build CartItem
                CartItem item = new CartItem(
                        rs.getInt("CartItemID"),
                        rs.getInt("UserID"),
                        cat,
                        wd,
                        rs.getInt("Quantity"),
                        rs.getInt("CartStatus")
                );

                itemList.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itemList;
    }

    public boolean updateQuantity(int cartItemID, int quantity) {
        // Bước 1: Lấy cart của user
        String cartItemSql = """
                               Update CartItems 
                               Set Quantity = ?
                               Where CartItemID = ?
                             """;
        try (PreparedStatement ps = connection.prepareStatement(cartItemSql)) {
            ps.setInt(1, quantity);
            ps.setInt(2, cartItemID);
            int n = ps.executeUpdate();
            if (n == 0) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean addCartItem(int userID, int productID, int warrantyDetailID, int quantity) {
        // Bước 1: Lấy cart của user
        String cartItemSql = """
                               MERGE CartItems AS target
                               USING (SELECT ? AS UserID, ? AS CategoryID, ? AS WarrantyDetailID, ? AS Quantity) AS source
                               ON target.UserID = source.UserID AND target.CategoryID = source.CategoryID AND target.WarrantyDetailID = source.WarrantyDetailID
                               WHEN MATCHED THEN
                                   UPDATE SET Quantity = target.Quantity + source.Quantity
                               WHEN NOT MATCHED THEN
                                   INSERT (UserID, CategoryID, WarrantyDetailID, Quantity)
                                   VALUES (source.UserID, source.CategoryID, source.WarrantyDetailID, source.Quantity);
                             """;
        try (PreparedStatement ps = connection.prepareStatement(cartItemSql)) {
            ps.setInt(1, userID);
            ps.setInt(2, productID);
            ps.setInt(3, warrantyDetailID);
            ps.setInt(4, quantity);
            int n = ps.executeUpdate();
            if (n == 0) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean deleteCartItem(int CartItemID) {
        String sql = """
                       Delete CartItems
                       where CartItemID = ?
                     """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, CartItemID);
            int n = ps.executeUpdate();
            if (n == 0) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
