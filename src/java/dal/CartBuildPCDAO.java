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
                WHERE cb.UserID = ?
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

    // Sửa: lấy CategoryID theo đúng ComponentID thứ tự 2 -> 7
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

    // Sửa: lấy WarrantyID theo đúng thứ tự ComponentID
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
                    list.add(0); // Không chọn bảo hành
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
            try { connection.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
        } finally {
            try { connection.setAutoCommit(true); } catch (SQLException ex) { ex.printStackTrace(); }
        }
        return false;
    }

    public boolean insertBuildPC2(List<Integer> categoryIDs, List<Integer> warrantyIDs, int userID) {
        if (categoryIDs == null || categoryIDs.size() != 6) return false;
        if (warrantyIDs == null) warrantyIDs = new ArrayList<>();

        String insertPCSQL = "INSERT INTO Build_PC (Price, Status, UserID) VALUES (?, 0, ?)";
        String insertItemSQL = "INSERT INTO Build_PC_Items (BuildPCID, CategoryID, Price, WarrantyDetailID, Status) VALUES (?, ?, ?, ?, 1)";
        String getPriceSQL = "SELECT Price FROM Categories WHERE CategoryID = ?";
        String getWarrantySQL = """
            SELECT wd.Price, wd.Status 
            FROM WarrantyDetails wd
            JOIN Categories c ON c.BrandComID = wd.BrandComID
            WHERE wd.WarrantyDetailID = ? AND c.CategoryID = ?
        """;

        try {
            connection.setAutoCommit(false);
            int totalPrice = 0;
            List<Object[]> itemData = new ArrayList<>();

            PreparedStatement psPrice = connection.prepareStatement(getPriceSQL);
            PreparedStatement psWarranty = connection.prepareStatement(getWarrantySQL);

            for (int i = 0; i < 6; i++) {
                int categoryID = categoryIDs.get(i);
                int warrantyID = (i < warrantyIDs.size()) ? warrantyIDs.get(i) : 0;

                int productPrice = 0, warrantyPrice = 0;
                Integer finalWarrantyID = null;

                psPrice.setInt(1, categoryID);
                ResultSet rs = psPrice.executeQuery();
                if (rs.next()) {
                    productPrice = rs.getInt("Price");
                } else {
                    connection.rollback();
                    return false;
                }

                if (warrantyID > 0) {
                    psWarranty.setInt(1, warrantyID);
                    psWarranty.setInt(2, categoryID);
                    rs = psWarranty.executeQuery();
                    if (rs.next() && rs.getInt("Status") == 1) {
                        warrantyPrice = rs.getInt("Price");
                        finalWarrantyID = warrantyID;
                    }
                }

                int itemTotal = productPrice + warrantyPrice;
                totalPrice += itemTotal;
                itemData.add(new Object[]{categoryID, itemTotal, finalWarrantyID});
            }

            int finalPrice = (int) Math.round(totalPrice * 0.8);
            PreparedStatement psInsertPC = connection.prepareStatement(insertPCSQL, Statement.RETURN_GENERATED_KEYS);
            psInsertPC.setInt(1, finalPrice);
            psInsertPC.setInt(2, userID);
            psInsertPC.executeUpdate();

            ResultSet rsPC = psInsertPC.getGeneratedKeys();
            if (!rsPC.next()) {
                connection.rollback();
                return false;
            }
            int buildPCID = rsPC.getInt(1);

            PreparedStatement psInsertItem = connection.prepareStatement(insertItemSQL);
            for (Object[] item : itemData) {
                psInsertItem.setInt(1, buildPCID);
                psInsertItem.setInt(2, (int) item[0]);
                psInsertItem.setInt(3, (int) item[1]);
                if (item[2] != null)
                    psInsertItem.setInt(4, (int) item[2]);
                else
                    psInsertItem.setNull(4, Types.INTEGER);
                psInsertItem.addBatch();
            }

            psInsertItem.executeBatch();
            connection.commit();
            return true;

        } catch (SQLException e) {
            try { connection.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
        } finally {
            try { connection.setAutoCommit(true); } catch (SQLException ex) { ex.printStackTrace(); }
        }
        return false;
    }

    public static void main(String[] args) {
        CartBuildPCDAO dao = new CartBuildPCDAO();
        List<Integer> categoryIDs = Arrays.asList(9, 18, 37, 55, 57, 69); // 6 thành phần
        List<Integer> warrantyIDs = Arrays.asList(7, 14, 18, 0, 23, 28);  // có thể có bảo hành hoặc không
        int userID = 5;

        boolean result = dao.insertBuildPC2(categoryIDs, warrantyIDs, userID);
        System.out.println(result ? "INSERT BUILD PC SUCCESS!" : "INSERT BUILD PC FAILED!");
    }
}
