package dal;

import java.sql.*;
import java.util.*;
import models.CartBuildPC;

public class CartBuildPCDAO extends DBContext {

    public List<CartBuildPC> getCartPCView(int userID) {
        List<CartBuildPC> list = new ArrayList<>();
        String sql = """
        SELECT 
            cb.CartBuildPCID,
            MAX(CASE WHEN bc.ComponentID = 2 THEN c.CategoryName END) AS MainBoard,
            MAX(CASE WHEN bc.ComponentID = 3 THEN c.CategoryName END) AS CPU,
            MAX(CASE WHEN bc.ComponentID = 4 THEN c.CategoryName END) AS GPU,
            MAX(CASE WHEN bc.ComponentID = 5 THEN c.CategoryName END) AS RAM,
            MAX(CASE WHEN bc.ComponentID = 6 THEN c.CategoryName END) AS SSD,
            MAX(CASE WHEN bc.ComponentID = 7 THEN c.CategoryName END) AS PCCase,
            SUM(cbi.Price) AS TotalPrice,
            MAX(cb.Status) AS CartStatus
        FROM Cart_Build_PC cb
        JOIN Cart_Build_PC_Items cbi ON cb.CartBuildPCID = cbi.CartBuildPCID
        JOIN Categories c ON cbi.CategoryID = c.CategoryID
        JOIN BrandComs bc ON c.BrandComID = bc.BrandComID
        WHERE cb.UserID = ? And cb.Status = 1
        GROUP BY cb.CartBuildPCID
        ORDER BY cb.CartBuildPCID
    """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CartBuildPC cart = new CartBuildPC();
                cart.setCartBuildPCID(rs.getInt("CartBuildPCID"));
                cart.setMainBoard(rs.getString("MainBoard"));
                cart.setCpu(rs.getString("CPU"));
                cart.setGpu(rs.getString("GPU"));
                cart.setRam(rs.getString("RAM"));
                cart.setSsd(rs.getString("SSD"));
                cart.setPcCase(rs.getString("PCCase"));
                cart.setPrice(rs.getInt("TotalPrice"));
                cart.setStatus(rs.getInt("CartStatus"));
                list.add(cart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //lấy CategoryID theo đúng ComponentID thứ tự 2 -> 7
    public List<Integer> getCategoryIDsInCartBuildPC(int cartBuildPCID) {
        List<Integer> list = new ArrayList<>();
        String sql = """
        SELECT c.CategoryID
        FROM Cart_Build_PC_Items cbi
        JOIN Categories c ON cbi.CategoryID = c.CategoryID
        JOIN BrandComs bc ON c.BrandComID = bc.BrandComID
        WHERE cbi.CartBuildPCID = ?
        ORDER BY bc.ComponentID
    """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cartBuildPCID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("CategoryID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //  lấy WarrantyID theo đúng thứ tự ComponentID
    public List<Integer> getWarrantyIDsInCartBuildPC(int cartBuildPCID) {
        List<Integer> list = new ArrayList<>();
        String sql = """
        SELECT cbi.WarrantyDetailID
        FROM Cart_Build_PC_Items cbi
        JOIN Categories c ON cbi.CategoryID = c.CategoryID
        JOIN BrandComs bc ON c.BrandComID = bc.BrandComID
        WHERE cbi.CartBuildPCID = ?
        ORDER BY bc.ComponentID
    """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cartBuildPCID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int warrantyID = rs.getInt("WarrantyDetailID");
                if (rs.wasNull()) {
                    list.add(0); // không có bảo hành
                } else {
                    list.add(warrantyID);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean deleteCartBuildPC(int cartBuildPCID) {
        String sql1 = "DELETE FROM Cart_Build_PC_Items WHERE CartBuildPCID = ?";
        String sql2 = "DELETE FROM Cart_Build_PC WHERE CartBuildPCID = ?";
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement ps1 = connection.prepareStatement(sql1)) {
                ps1.setInt(1, cartBuildPCID);
                ps1.executeUpdate();
            }
            try (PreparedStatement ps2 = connection.prepareStatement(sql2)) {
                ps2.setInt(1, cartBuildPCID);
                ps2.executeUpdate();
            }
            connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public boolean insertOrderFromCart(int cartBuildPCID, int userID) {
        String getUserSQL = "SELECT FullName, PhoneNumber FROM Users WHERE UserID = ?";
        String getItemsSQL = """
        SELECT cbi.CategoryID, cbi.WarrantyDetailID, cbi.Price
        FROM Cart_Build_PC_Items cbi
        WHERE cbi.CartBuildPCID = ?
    """;
        String insertBuildPCSQL = "INSERT INTO Build_PC (Price, Status, UserID) VALUES (?, 1, ?)";
        String insertBuildPCItemSQL = """
        INSERT INTO Build_PC_Items (BuildPCID, CategoryID, Price, WarrantyDetailID, Status) 
        VALUES (?, ?, ?, ?, 1)
    """;
        String insertOrderSQL = """
        INSERT INTO Orders (OrderCode, Product_Type, CustomerID, OrderDate, Address, PhoneNumber, Fullname, PaymentStatusID, TotalAmount, Status) 
        VALUES (?, 2, ?, NOW(), '', ?, ?, 1, ?, 1)
    """;
        String insertOrderItemSQL = "INSERT INTO Order_BuildPCItems (OrderID, BuildPCID, Price) VALUES (?, ?, ?)";
        String insertDetailSQL = """
        INSERT INTO Order_BuildPCDetails (OrderBuildPCItemID, CategoryID, WarrantyDetailID, Price, Status) 
        VALUES (?, ?, ?, ?, 1)
    """;
        String deleteCartItemsSQL = "DELETE FROM Cart_Build_PC_Items WHERE CartBuildPCID = ?";
        String deleteCartSQL = "DELETE FROM Cart_Build_PC WHERE CartBuildPCID = ?";

        try {
            connection.setAutoCommit(false);

            // 1. Lấy thông tin user
            PreparedStatement psUser = connection.prepareStatement(getUserSQL);
            psUser.setInt(1, userID);
            ResultSet rsUser = psUser.executeQuery();
            if (!rsUser.next()) {
                connection.rollback();
                return false;
            }
            String fullName = rsUser.getString("FullName");
            String phone = rsUser.getString("PhoneNumber");

            // 2. Lấy danh sách linh kiện trong giỏ
            PreparedStatement psItems = connection.prepareStatement(getItemsSQL);
            psItems.setInt(1, cartBuildPCID);
            ResultSet rsItems = psItems.executeQuery();

            List<Object[]> items = new ArrayList<>();
            int totalPrice = 0;
            while (rsItems.next()) {
                int categoryID = rsItems.getInt("CategoryID");
                int warrantyID = rsItems.getInt("WarrantyDetailID");
                int price = rsItems.getInt("Price");
                items.add(new Object[]{categoryID, warrantyID, price});
                totalPrice += price;
            }

            if (items.size() != 6) {
                connection.rollback();
                return false;
            }

            int finalPrice = (int) Math.round(totalPrice * 0.8);

            // 3. Tạo BuildPC
            PreparedStatement psBuildPC = connection.prepareStatement(insertBuildPCSQL, Statement.RETURN_GENERATED_KEYS);
            psBuildPC.setInt(1, finalPrice);
            psBuildPC.setInt(2, userID);
            psBuildPC.executeUpdate();
            ResultSet rsBuildPC = psBuildPC.getGeneratedKeys();
            if (!rsBuildPC.next()) {
                connection.rollback();
                return false;
            }
            int buildPCID = rsBuildPC.getInt(1);
            // 3.5 Insert Build_PC_Items
            PreparedStatement psInsertBuildPCItem = connection.prepareStatement(insertBuildPCItemSQL);
            for (Object[] item : items) {
                psInsertBuildPCItem.setInt(1, buildPCID);
                psInsertBuildPCItem.setInt(2, (int) item[0]); // CategoryID
                psInsertBuildPCItem.setInt(3, (int) item[2]); // Price
                if ((int) item[1] > 0) {
                    psInsertBuildPCItem.setInt(4, (int) item[1]); // WarrantyDetailID
                } else {
                    psInsertBuildPCItem.setNull(4, Types.INTEGER);
                }
                psInsertBuildPCItem.addBatch();
            }
            psInsertBuildPCItem.executeBatch();

            // 4. Tạo đơn hàng
            PreparedStatement psOrder = connection.prepareStatement(insertOrderSQL, Statement.RETURN_GENERATED_KEYS);
            psOrder.setString(1, "OD" + System.currentTimeMillis()); // OrderCode
            psOrder.setInt(2, userID);       // CustomerID
            psOrder.setString(3, phone);     // PhoneNumber
            psOrder.setString(4, fullName);  // Fullname
            psOrder.setInt(5, finalPrice);   // TotalAmount
            psOrder.executeUpdate();

            ResultSet rsOrder = psOrder.getGeneratedKeys();
            if (!rsOrder.next()) {
                connection.rollback();
                return false;
            }
            int orderID = rsOrder.getInt(1);

            // 5. Insert Order_BuildPCItems
            PreparedStatement psInsertOrderItem = connection.prepareStatement(insertOrderItemSQL, Statement.RETURN_GENERATED_KEYS);
            psInsertOrderItem.setInt(1, orderID);
            psInsertOrderItem.setInt(2, buildPCID);
            psInsertOrderItem.setInt(3, finalPrice);
            psInsertOrderItem.executeUpdate();

            ResultSet rsOrderItem = psInsertOrderItem.getGeneratedKeys();
            if (!rsOrderItem.next()) {
                connection.rollback();
                return false;
            }
            int orderBuildPCItemID = rsOrderItem.getInt(1);

            // 6. Insert chi tiết linh kiện
            PreparedStatement psInsertDetail = connection.prepareStatement(insertDetailSQL);
            for (Object[] item : items) {
                psInsertDetail.setInt(1, orderBuildPCItemID);
                psInsertDetail.setInt(2, (int) item[0]);
                if ((int) item[1] > 0) {
                    psInsertDetail.setInt(3, (int) item[1]);
                } else {
                    psInsertDetail.setNull(3, Types.INTEGER);
                }
                psInsertDetail.setInt(4, (int) item[2]);
                psInsertDetail.addBatch();
            }
            psInsertDetail.executeBatch();

            // 7. Xóa giỏ sau khi đặt hàng thành côngF
            PreparedStatement psDeleteItems = connection.prepareStatement(deleteCartItemsSQL);
            psDeleteItems.setInt(1, cartBuildPCID);
            psDeleteItems.executeUpdate();

            PreparedStatement psDeleteCart = connection.prepareStatement(deleteCartSQL);
            psDeleteCart.setInt(1, cartBuildPCID);
            psDeleteCart.executeUpdate();

            connection.commit();
            return true;

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public static void main(String[] args) {
        CartBuildPCDAO dao = new CartBuildPCDAO();

        int cartBuildPCID = 1; // ID giỏ Build PC cần test (đảm bảo tồn tại và đúng cấu trúc dữ liệu)
        int userID = 2;        // ID người dùng tương ứng với giỏ hàng

        boolean success = dao.insertOrderFromCart(cartBuildPCID, userID);

        if (success) {
            System.out.println("✔️ Đặt hàng từ giỏ Build PC thành công!");
        } else {
            System.out.println("❌ Đặt hàng thất bại. Kiểm tra dữ liệu đầu vào hoặc lỗi hệ thống.");
        }
    }

}
