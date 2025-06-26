/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author PC
 */
import java.sql.*;
import java.util.*;
import models.BuildPCView;
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

            SUM(c.Price) AS TotalPrice,
            MAX(cb.Status) AS CartStatus
        FROM Cart_Build_PC cb
        JOIN Cart_Build_PC_Items cbi ON cb.CartBuildPCID = cbi.CartBuildPCID
        JOIN Categories c ON cbi.CategoryID = c.CategoryID
        JOIN BrandComs bc ON c.BrandComID = bc.BrandComID
        WHERE cb.UserID = ?
          AND bc.ComponentID BETWEEN 2 AND 7
        GROUP BY cb.CartBuildPCID
        ORDER BY cb.CartBuildPCID
    """;

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, userID);
        
        try (ResultSet rs = ps.executeQuery()) {
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
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return list;
}
public List<Integer> getCategoryIDsInCartBuildPC(int cartBuildPCID) {
    List<Integer> list = new ArrayList<>();
    String sql = "SELECT CategoryID FROM Cart_Build_PC_Items WHERE CartBuildPCID = ?";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, cartBuildPCID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(rs.getInt("CategoryID"));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}
public int getCartBuildPCPrice(int cartBuildPCID) {
    String sql = "SELECT Price FROM Cart_Build_PC WHERE CartBuildPCID = ?";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, cartBuildPCID);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("Price");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;
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

    } catch (Exception e) {
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
public boolean insertBuildPC2(List<Integer> categoryIDs, int userID) {
    if (categoryIDs == null || categoryIDs.size() != 6) {
        return false;
    }

    String insertBuildPCSQL = "INSERT INTO Build_PC (Price, Status, UserID) VALUES (?, ?, ?)";
    String insertItemSQL = """
        INSERT INTO Build_PC_Items (BuildPCID, CategoryID, Price, WarrantyDetailID, Status)
        VALUES (?, ?, ?, ?, 1)
    """;

    String getInfoSQL = """
        SELECT TOP 1
            c.Price AS ProductPrice,
            wd.WarrantyDetailID,
            wd.Status AS WarrantyStatus,
            wd.Price AS WarrantyPrice
        FROM Categories c
        JOIN BrandComs bc ON c.BrandComID = bc.BrandComID
        JOIN WarrantyDetails wd ON bc.BrandComID = wd.BrandComID
        WHERE c.CategoryID = ?
        ORDER BY wd.Price ASC
    """;

    try {
        connection.setAutoCommit(false);

        int totalPriceBeforeDeposit = 0;
        List<int[]> itemData = new ArrayList<>(); // [categoryID, tổng giá, warrantyID]

        PreparedStatement psInfo = connection.prepareStatement(getInfoSQL);

        for (int categoryID : categoryIDs) {
            psInfo.setInt(1, categoryID);
            try (ResultSet rs = psInfo.executeQuery()) {
                if (rs.next()) {
                    int productPrice = rs.getInt("ProductPrice");
                    int warrantyID = rs.getInt("WarrantyDetailID");
                    int warrantyPrice = rs.getInt("WarrantyPrice");
                    int warrantyStatus = rs.getInt("WarrantyStatus");

                    int actualWarrantyPrice = (warrantyStatus == 1) ? warrantyPrice : 0;
                    int itemTotal = productPrice + actualWarrantyPrice;
                    totalPriceBeforeDeposit += itemTotal;

                    itemData.add(new int[]{categoryID, itemTotal, warrantyID});
                } else {
                    connection.rollback();
                    return false;
                }
            }
        }

        // Tính tiền cần lưu vào DB sau khi trừ 20% tiền cọc
        double finalTotal = totalPriceBeforeDeposit - totalPriceBeforeDeposit*0.2;
        int totalPrice = (int) Math.round(finalTotal);

        PreparedStatement psBuildPC = connection.prepareStatement(insertBuildPCSQL, PreparedStatement.RETURN_GENERATED_KEYS);
        psBuildPC.setInt(1, totalPrice);
        psBuildPC.setInt(2, 0); // Trạng thái mặc định
        psBuildPC.setInt(3, userID);
        psBuildPC.executeUpdate();

        ResultSet rsPC = psBuildPC.getGeneratedKeys();
        if (!rsPC.next()) {
            connection.rollback();
            return false;
        }
        int buildPCID = rsPC.getInt(1);

        PreparedStatement psItem = connection.prepareStatement(insertItemSQL);
        for (int[] item : itemData) {
            psItem.setInt(1, buildPCID);
            psItem.setInt(2, item[0]);
            psItem.setInt(3, item[1]);
            psItem.setInt(4, item[2]);
            psItem.addBatch();
        }

        psItem.executeBatch();
        connection.commit();
        return true;

    } catch (Exception e) {
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


        }
