package dal;

import java.sql.*;
import java.util.ArrayList;
import models.CartItem;
import models.Products;

public class CartItemDAO extends DBContext {

//    public ArrayList<CartItem> getCartItemsByUserId(int userId) {
//        ArrayList<CartItem> itemList = new ArrayList<>();
//
//        String sql = """
//                     SELECT ci.CartItemID,
//                       ci.UserID,
//                       ci.CategoryID,
//                       ci.WarrantyDetailID,
//                       ci.Quantity,
//                       c.CategoryName,
//                       c.BrandComID,
//                       c.Price,
//                       c.Description as CategoryDescription,
//                       w.Description as WarrantyDescription,
//                       wd.Price
//                       FROM CartItems ci 
//                       LEFT JOIN Categories c on ci.CategoryID = c.CategoryID
//                       LEFT JOIN Users u on u.UserID = ci.UserID
//                       LEFT JOIN WarrantyDetails wd on ci.WarrantyDetailID = wd.WarrantyDetailID
//                       LEFT JOIN Warranties w on w.WarrantyID = wd.WarrantyID
//                     """
//                ;
//
//        try (PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setInt(1, userId);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                Products p = new Products();
//                p.setProductID(rs.getInt("ProductID"));
//                p.setName(rs.getString("Name"));
//                p.setPrice(rs.getDouble("Price"));
//
//                CartItem item = new CartItem();
//                item.setCartItemID(rs.getInt("CartItemID"));
//                item.setQuantity(rs.getInt("Quantity"));
//                item.setProduct(p);
//
//                itemList.add(item);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return itemList;
//    }

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

    public boolean addCartItem(int userID, int productID, int quantity) {
        // Bước 1: Lấy cart của user
        String cartItemSql = """
                               MERGE CartItems AS target
                               USING (SELECT ? AS UserID, ? AS ProductID, ? AS Quantity) AS source
                               ON target.UserID = source.UserID AND target.ProductID = source.ProductID
                               WHEN MATCHED THEN
                                   UPDATE SET Quantity = target.Quantity + source.Quantity
                               WHEN NOT MATCHED THEN
                                   INSERT (UserID, ProductID, Quantity)
                                   VALUES (source.UserID, source.ProductID, source.Quantity);
                             """;
        try (PreparedStatement ps = connection.prepareStatement(cartItemSql)) {
            ps.setInt(1, userID);
            ps.setInt(2, productID);
            ps.setInt(3, quantity);
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
