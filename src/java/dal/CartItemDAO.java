package dal;

import java.sql.*;
import java.util.ArrayList;
import models.CartItem;
import models.Products;

public class CartItemDAO extends DBContext {

    public ArrayList<CartItem> getCartItemsByUserId(int userId) {
        ArrayList<CartItem> itemList = new ArrayList<>();
        String sql = "SELECT CartItemID, UserID, CategoryID, WarrantyDetailID, Quantity, Status FROM CartItems WHERE UserID = ?";

        WarrantyDetailDAO warrantyDetailDAO = new WarrantyDetailDAO();  // Gọi lại DAO phụ nếu cần
        CategoriesDAO categoriesDAO = new CategoriesDAO();              // Giả định bạn có DAO này

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int cartItemID = rs.getInt("CartItemID");
                int categoryID = rs.getInt("CategoryID");
                int warrantyDetailID = rs.getInt("WarrantyDetailID");

                CartItem item = new CartItem();
                item.setCartItemID(cartItemID);
                item.setUserID(userId);
                item.setQuantity(rs.getInt("Quantity"));
                item.setStatus(rs.getInt("Status"));

                item.setCategory(categoriesDAO.getCategoryByID(categoryID).get(0));
                item.setWarranty(warrantyDetailDAO.getWarrantyDetailById(warrantyDetailID));

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

    public boolean addCartItem(int userID, int CategoryID, int quantity, int WarrantyDetailID) {
        // Bước 1: Lấy cart của user
        String cartItemSql = """
                               MERGE CartItems AS target
                               USING (SELECT ? AS UserID, ? AS CategoryID, ? AS Quantity, ? AS WarrantyDetailID) AS source
                               ON target.UserID = source.UserID AND target.CategoryID = source.CategoryID AND target.WarrantyDetailID = source.WarrantyDetailID
                               WHEN MATCHED THEN
                                   UPDATE SET Quantity = target.Quantity + source.Quantity
                               WHEN NOT MATCHED THEN
                                   INSERT (UserID, CategoryID, Quantity, WarrantyDetailID)
                                   VALUES (source.UserID, source.CategoryID, source.Quantity, source.WarrantyDetailID);
                             """;
        try (PreparedStatement ps = connection.prepareStatement(cartItemSql)) {
            ps.setInt(1, userID);
            ps.setInt(2, CategoryID);
            ps.setInt(3, quantity);
            ps.setInt(4, WarrantyDetailID);
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

    public static void main(String[] args) {
        CartItemDAO dao = new CartItemDAO();
        int testUserId = 3; // Thay bằng ID người dùng phù hợp trong CSDL

        ArrayList<CartItem> cartItems = dao.getCartItemsByUserId(testUserId);

        if (cartItems.isEmpty()) {
            System.out.println("❌ Không tìm thấy sản phẩm nào trong giỏ hàng của userID = " + testUserId);
        } else {
            System.out.println("🛒 Danh sách sản phẩm trong giỏ hàng của userID = " + testUserId);
            for (CartItem item : cartItems) {
                System.out.println("CartItem ID: " + item.getCartItemID());
                System.out.println("User ID: " + item.getUserID());
                System.out.println("Category: " + item.getCategory().getCategoryName());
                System.out.println("Category Price: " + item.getCategory().getPrice());
                System.out.println("Warranty Period: " + item.getWarranty().getWarranty().getWarrantyPeriod() + " months");
                System.out.println("Warranty Description: " + item.getWarranty().getWarranty().getDescription());
                System.out.println("Warranty Price: " + item.getWarranty().getPrice());
                System.out.println("Quantity: " + item.getQuantity());
                System.out.println("Status: " + item.getStatus());
                System.out.println("--------------------------------------------------");
            }
        }
    }

}
