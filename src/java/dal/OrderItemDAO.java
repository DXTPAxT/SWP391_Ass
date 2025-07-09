package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.OrderItems;

public class OrderItemDAO extends DBContext {

    // Hàm tạo order và trả về OrderID vừa tạo, trả về -1 nếu lỗi
    public int insertOrderItem(OrderItems orderItem) {
        String sql = """
            INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price)
            VALUES (?, ?, ?, ?)
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, orderItem.getOrderID());
            ps.setInt(2, orderItem.getCategoryID());
            ps.setInt(3, orderItem.getQuantity());
            ps.setInt(4, orderItem.getPrice());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        int newID = rs.getInt(1);
                        return newID;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // failed
    }

    public int getPriceByCategoryID(int categoryID) {
        String sql = "SELECT Price FROM Categories WHERE CategoryID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, categoryID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("Price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public ArrayList<OrderItems> getOrderItemsByOrderID(int orderID) {
        ArrayList<OrderItems> list = new ArrayList<>();
        String sql = """
            SELECT OrderItemID, OrderID, CategoryID, Quantity, Price
            FROM OrderItems
            WHERE OrderID = ?
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderItems item = new OrderItems();
                item.setOrderItemID(rs.getInt("OrderItemID"));
                item.setOrderID(rs.getInt("OrderID"));
                item.setCategoryID(rs.getInt("CategoryID"));
                item.setQuantity(rs.getInt("Quantity"));
                item.setPrice(rs.getInt("Price"));
                list.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        OrderItemDAO dao = new OrderItemDAO();

        // Fake data test
        int orderID = 1;      // giả sử đã có OrderID 1
        int categoryID = 2;   // giả sử CategoryID 2 tồn tại
        int quantity = 3;     // mua 3 cái

        // 1) Lấy Price từ Categories
        int price = dao.getPriceByCategoryID(categoryID);
        if (price == -1) {
            System.out.println("❌ CategoryID không tồn tại: " + categoryID);
            return;
        }
        System.out.println("✅ Price from Categories: " + price);

        // 2) Tạo OrderItems model
        OrderItems orderItem = new OrderItems(orderID, categoryID, quantity, price);

        // 3) Insert
        int newID = dao.insertOrderItem(orderItem);
        if (newID != -1) {
            System.out.println("✅ New OrderItemID: " + newID);
        } else {
            System.out.println("❌ Insert failed!");
        }
    }
}
