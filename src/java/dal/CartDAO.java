package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.Cart;
import models.CartItem;
import models.Products;

public class CartDAO extends DBContext {

    public Cart getCartByUserId(int userId) {
        Cart cart = null;

        // Bước 1: Lấy cart của user
        String cartSql = "SELECT * FROM Cart WHERE UserID = ?";
        try (PreparedStatement ps = connection.prepareStatement(cartSql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cart = new Cart();
                int cartId = rs.getInt("CartID");

                cart.setCartID(cartId);
                cart.setUserID(rs.getInt("UserID"));

                // Bước 2: Lấy danh sách item trong giỏ (JOIN với Product)
                String itemSql = "SELECT ci.CartItemID, ci.Quantity, "
                        + "p.ProductID, p.Name, p.Price "
                        + "FROM CartItems ci "
                        + "JOIN Products p ON ci.ProductID = p.ProductID "
                        + "WHERE ci.CartID = ?";

                try (PreparedStatement itemPs = connection.prepareStatement(itemSql)) {
                    itemPs.setInt(1, cartId);
                    ResultSet itemRs = itemPs.executeQuery();

                    ArrayList<CartItem> itemList = new ArrayList<>();

                    while (itemRs.next()) {
                        Products p = new Products();
                        p.setProductID(itemRs.getInt("ProductID"));
                        p.setName(itemRs.getString("Name"));
                        p.setPrice(itemRs.getDouble("Price"));

                        CartItem item = new CartItem();
                        item.setCartItemID(itemRs.getInt("CartItemID"));
                        item.setQuantity(itemRs.getInt("Quantity"));
                        item.setProduct(p);

                        itemList.add(item);
                    }

                    cart.setItems(itemList);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cart;
    }

    public static void main(String[] args) {
        CartDAO dao = new CartDAO();
        int testUserId = 2; // bạn có thể đổi userID nếu cần

        Cart cart = dao.getCartByUserId(testUserId);

        if (cart != null) {
            System.out.println("CartID: " + cart.getCartID());
            System.out.println("UserID: " + cart.getUserID());
            System.out.println("CreatedAt: " + cart.getCreatedAt());

            System.out.println("---- Cart Items ----");
            for (CartItem item : cart.getItems()) {
                System.out.println("Item ID: " + item.getCartItemID());
                System.out.println("Product: " + item.getProduct().getName());
                System.out.println("Price: " + item.getProduct().getPrice());
                System.out.println("Quantity: " + item.getQuantity());
                System.out.println("Total: " + (item.getQuantity() * item.getProduct().getPrice()));
                System.out.println("-------------------");
            }
        } else {
            System.out.println("No cart found for UserID = " + testUserId);
        }
    }
}
