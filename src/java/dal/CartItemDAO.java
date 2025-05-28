package dal;

import java.sql.*;

public class CartItemDAO extends DBContext {

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
