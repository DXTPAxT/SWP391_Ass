/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dalAdmin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;
import models.Brands;
import models.BuildPCAdmin;
import models.BuildPCView;
import models.Categories;
import models.Components;

/**
 *
 * @author PC
 */
public class BuildPCAdminDAO extends DBAdminContext {

    public List<Components> getALLComponent() {
        List<Components> list = new ArrayList<>();
        String sql = "Select *From Components ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Components c = new Components(
                        rs.getInt("ComponentID"),
                        rs.getString("ComponentName"),
                        rs.getInt("Quantity"),
                        rs.getInt("Status")
                );
                list.add(c);
            }

        } catch (Exception e) {
        }
        return list;
    }

  public List<BuildPCView> getBuildPCSummaryView() {
    List<BuildPCView> list = new ArrayList<>();
    String sql = """
    SELECT 
        bp.BuildPCID,
        MAX(CASE WHEN bc.ComponentID = 2 THEN c.CategoryName END) AS MainBoard,
        MAX(CASE WHEN bc.ComponentID = 3 THEN c.CategoryName END) AS CPU,
        MAX(CASE WHEN bc.ComponentID = 4 THEN c.CategoryName END) AS GPU,
        MAX(CASE WHEN bc.ComponentID = 5 THEN c.CategoryName END) AS RAM,
        MAX(CASE WHEN bc.ComponentID = 6 THEN c.CategoryName END) AS SSD,
        MAX(CASE WHEN bc.ComponentID = 7 THEN c.CategoryName END) AS PCCase,
        bp.Price AS Price,
        bp.Status AS Status,
        u.UserID,
        u.FullName,
        r.RoleName
    FROM Build_PC bp
    JOIN Build_PC_Items bpi ON bp.BuildPCID = bpi.BuildPCID
    JOIN Categories c ON bpi.CategoryID = c.CategoryID
    JOIN BrandComs bc ON c.BrandComID = bc.BrandComID
    JOIN Users u ON bp.UserID = u.UserID
    JOIN Roles r ON u.RoleID = r.RoleID
    WHERE bc.ComponentID BETWEEN 2 AND 7
    GROUP BY bp.BuildPCID, bp.Price, bp.Status, u.UserID, u.FullName, r.RoleName
    ORDER BY bp.BuildPCID
    """;

    try (PreparedStatement ps = connection.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            BuildPCView b = new BuildPCView();
            b.setBuildPCID(rs.getInt("BuildPCID"));
            b.setMainBoard(rs.getString("MainBoard"));
            b.setCpu(rs.getString("CPU"));
            b.setGpu(rs.getString("GPU"));
            b.setRam(rs.getString("RAM"));
            b.setSsd(rs.getString("SSD"));
            b.setPcCase(rs.getString("PCCase"));
            b.setPrice(rs.getInt("Price")); // Lấy giá từ Build_PC chuẩn đã trừ tiền cọc
            b.setStatus(rs.getInt("Status"));
            b.setUserID(rs.getInt("UserID"));
            b.setFullName(rs.getString("FullName"));
            b.setRole(rs.getString("RoleName"));
            list.add(b);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


    private Categories extractCategory(ResultSet rs) throws SQLException {
        Categories category = new Categories(
                rs.getInt("CategoryID"),
                rs.getString("CategoryName"),
                rs.getInt("ComponentID"),
                rs.getInt("BrandID"),
                rs.getString("BrandName"),
                rs.getInt("Quantity"),
                rs.getInt("Price"),
                rs.getString("Description"),
                rs.getInt("Status")
        );
        try {
            category.setComponentName(rs.getString("ComponentName"));
        } catch (SQLException e) {
            // Optionally log or ignore if not present
        }
        return category;
    }

    private List<Object> buildFilter(
            StringBuilder sql,
            String componentName, String brandName,
            Integer minPrice, Integer maxPrice,
            String keyword) {

        List<Object> params = new ArrayList<>();
        if (componentName != null && !componentName.isEmpty()) {
            sql.append(" AND comp.ComponentName = ? ");
            params.add(componentName);
        }
        if (brandName != null && !brandName.isEmpty()) {
            sql.append(" AND b.BrandName = ? ");
            params.add(brandName);
        }
        if (minPrice != null) {
            sql.append(" AND c.Price >= ? ");
            params.add(minPrice);
        }
        if (maxPrice != null) {
            sql.append(" AND c.Price <= ? ");
            params.add(maxPrice);
        }
        if (keyword != null && !keyword.isEmpty()) {
            sql.append(" AND LOWER(c.CategoryName) LIKE ? ");
            params.add("%" + keyword.toLowerCase() + "%");
        }
        return params;
    }

    // BuilDPC_ListCate
    public List<Categories> getCategoriesFiltered2(
            int componentID, String brand, String keyword,
            int minPrice, int maxPrice, int start, int size) {

        List<Categories> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("""
        SELECT 
          c.*, 
          bc.ComponentID, 
          bc.BrandID, 
          b.BrandName, 
          comp.ComponentName
        FROM Categories c
        JOIN BrandComs bc ON c.BrandComID = bc.BrandComID
        JOIN Brands b ON bc.BrandID = b.BrandID
        JOIN Components comp ON bc.ComponentID = comp.ComponentID
        WHERE  bc.ComponentID = ?
    """);

        List<Object> params = new ArrayList<>();
        params.add(componentID);

        if (brand != null && !brand.trim().isEmpty()) {
            sql.append(" AND b.BrandName = ? ");
            params.add(brand.trim());
        }
        if (keyword != null && !keyword.trim().isEmpty()) {
            sql.append(" AND LOWER(c.CategoryName) LIKE ? ");
            params.add("%" + keyword.trim().toLowerCase() + "%");
        }
        if (minPrice >= 0) {
            sql.append(" AND c.Price >= ? ");
            params.add(minPrice);
        }
        if (maxPrice < Integer.MAX_VALUE) {
            sql.append(" AND c.Price <= ? ");
            params.add(maxPrice);
        }

        sql.append(" ORDER BY c.CategoryID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");
        params.add(start);
        params.add(size);

        try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(extractCategory(rs));
                }
            }
        } catch (SQLException e) {

        }

        return list;
    }

    public int countFilteredByComponent(int componentID, String brand, int minPrice, int maxPrice, String keyword) {
        StringBuilder sql = new StringBuilder("""
        SELECT COUNT(*)
        FROM Categories c
        JOIN BrandComs bc ON c.BrandComID = bc.BrandComID
        JOIN Brands b ON bc.BrandID = b.BrandID
        WHERE bc.ComponentID = ? AND c.Status IN (1, 2)
    """);
        List<Object> params = new ArrayList<>();
        params.add(componentID);

        if (brand != null && !brand.isEmpty()) {
            sql.append(" AND b.BrandName = ?");
            params.add(brand);
        }
        if (minPrice >= 0) {
            sql.append(" AND c.Price >= ?");
            params.add(minPrice);
        }
        if (maxPrice < Integer.MAX_VALUE) {
            sql.append(" AND c.Price <= ?");
            params.add(maxPrice);
        }
        if (keyword != null && !keyword.isEmpty()) {
            sql.append(" AND LOWER(c.CategoryName) LIKE ?");
            params.add("%" + keyword.toLowerCase() + "%");
        }

        try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {

        }
        return 0;
    }

    public List<Brands> getBrandsByComponent(int componentID) {
        List<Brands> list = new ArrayList<>();
        String sql = """
        SELECT DISTINCT b.BrandID, b.BrandName, b.Quantity, b.Status
        FROM BrandComs bc
        JOIN Brands b ON bc.BrandID = b.BrandID
        WHERE bc.ComponentID = ?
    """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, componentID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Brands(
                            rs.getInt("BrandID"),
                            rs.getString("BrandName"),
                            rs.getInt("Quantity"),
                            rs.getInt("Status")
                    ));
                }
            }
        } catch (SQLException e) {

        }
        return list;
    }

  private int getWarrantyDetailIDByCategoryID(int categoryID) {
    String sql = """
    SELECT TOP 1 wd.WarrantyDetailID
    FROM Categories c
    JOIN BrandComs bc ON c.BrandComID = bc.BrandComID
    JOIN WarrantyDetails wd ON bc.BrandComID = wd.BrandComID
    WHERE c.CategoryID = ?
      AND wd.Status = 1
    ORDER BY wd.Price ASC
    """;

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, categoryID);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("WarrantyDetailID");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return 3;  // Trường hợp không có thì mặc định bảo hành ID = 3
}


public boolean insertBuildPC(List<Integer> categoryIDs, int userID) {
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

        int totalPrice = 0;
        List<int[]> itemData = new ArrayList<>(); // Mỗi phần tử [categoryID, tổng giá, warrantyID]

        PreparedStatement psInfo = connection.prepareStatement(getInfoSQL);

        for (int categoryID : categoryIDs) {
            psInfo.setInt(1, categoryID);
            try (ResultSet rs = psInfo.executeQuery()) {
                if (rs.next()) {
                    int productPrice = rs.getInt("ProductPrice");
                    int warrantyID = rs.getInt("WarrantyDetailID");
                    int warrantyPrice = rs.getInt("WarrantyPrice");
                    int warrantyStatus = rs.getInt("WarrantyStatus");

                    // Nếu bảo hành status = 0 thì tính giá bảo hành = 0
                    int actualWarrantyPrice = (warrantyStatus == 1) ? warrantyPrice : 0;

                    int itemTotal = productPrice + actualWarrantyPrice;
                    totalPrice += itemTotal;

                    itemData.add(new int[]{categoryID, itemTotal, warrantyID});
                } else {
                    connection.rollback();
                    return false; // Không tìm thấy dữ liệu hợp lệ
                }
            }
        }

        // Insert vào bảng Build_PC
        PreparedStatement psBuildPC = connection.prepareStatement(insertBuildPCSQL, PreparedStatement.RETURN_GENERATED_KEYS);
        psBuildPC.setInt(1, totalPrice);
        psBuildPC.setInt(2, 1); // Trạng thái mặc định
        psBuildPC.setInt(3, userID);
        psBuildPC.executeUpdate();

        ResultSet rsPC = psBuildPC.getGeneratedKeys();
        if (!rsPC.next()) {
            connection.rollback();
            return false;
        }
        int buildPCID = rsPC.getInt(1);

        // Insert từng linh kiện
        PreparedStatement psItem = connection.prepareStatement(insertItemSQL);
        for (int[] item : itemData) {
            psItem.setInt(1, buildPCID);
            psItem.setInt(2, item[0]); // CategoryID
            psItem.setInt(3, item[1]); // Tổng giá
            psItem.setInt(4, item[2]); // WarrantyDetailID
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




    private int getPriceByCategoryID(int categoryID) {
        String sql = "SELECT Price FROM Categories WHERE CategoryID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, categoryID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("Price");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

   public boolean updateBuildPC(int buildPCID, List<Integer> newCategoryIDs, int newStatus, String role) {
    if (newCategoryIDs == null || newCategoryIDs.size() != 6) {
        return false;
    }

    // Phân quyền theo role
    if ("Admin".equalsIgnoreCase(role)) {
        if (newStatus != 1 && newStatus != 2) {
            return false; // Admin chỉ được phép chuyển trạng thái bán/ngừng bán
        }
    } else {
        if (newStatus < 0 || newStatus > 7) {
            return false; // Khách chỉ được đổi trong khoảng hợp lệ
        }
    }

    String deleteItemsSQL = "DELETE FROM Build_PC_Items WHERE BuildPCID = ?";
    String updatePCSQL = "UPDATE Build_PC SET Price = ?, Status = ? WHERE BuildPCID = ?";
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

        int totalPrice = 0;
        List<int[]> itemData = new ArrayList<>(); // [categoryID, tổng giá, warrantyID]

        PreparedStatement psInfo = connection.prepareStatement(getInfoSQL);

        for (int categoryID : newCategoryIDs) {
            psInfo.setInt(1, categoryID);
            try (ResultSet rs = psInfo.executeQuery()) {
                if (rs.next()) {
                    int productPrice = rs.getInt("ProductPrice");
                    int warrantyID = rs.getInt("WarrantyDetailID");
                    int warrantyPrice = rs.getInt("WarrantyPrice");
                    int warrantyStatus = rs.getInt("WarrantyStatus");

                    int actualWarrantyPrice = (warrantyStatus == 1) ? warrantyPrice : 0;
                    int itemTotal = productPrice + actualWarrantyPrice;
                    totalPrice += itemTotal;

                    itemData.add(new int[]{categoryID, itemTotal, warrantyID});
                } else {
                    connection.rollback();
                    return false; // Không tìm thấy dữ liệu linh kiện hợp lệ
                }
            }
        }

        // Xóa linh kiện cũ
        try (PreparedStatement psDelete = connection.prepareStatement(deleteItemsSQL)) {
            psDelete.setInt(1, buildPCID);
            psDelete.executeUpdate();
        }

        // Cập nhật Build_PC tổng giá và trạng thái
        try (PreparedStatement psUpdate = connection.prepareStatement(updatePCSQL)) {
            psUpdate.setInt(1, totalPrice);
            psUpdate.setInt(2, newStatus);
            psUpdate.setInt(3, buildPCID);
            psUpdate.executeUpdate();
        }

        // Insert item mới
        try (PreparedStatement psInsert = connection.prepareStatement(insertItemSQL)) {
            for (int[] item : itemData) {
                psInsert.setInt(1, buildPCID);
                psInsert.setInt(2, item[0]); // CategoryID
                psInsert.setInt(3, item[1]); // Tổng giá
                psInsert.setInt(4, item[2]); // WarrantyDetailID
                psInsert.addBatch();
            }
            psInsert.executeBatch();
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

    public boolean deleteBuildPC(int buildPCID) {
        String deleteItemsSQL = "DELETE FROM Build_PC_Items WHERE BuildPCID = ?";
        String deletePCSQL = "DELETE FROM Build_PC WHERE BuildPCID = ?";

        try {
            connection.setAutoCommit(false);

            try (PreparedStatement ps1 = connection.prepareStatement(deleteItemsSQL)) {
                ps1.setInt(1, buildPCID);
                ps1.executeUpdate();
            }

            try (PreparedStatement ps2 = connection.prepareStatement(deletePCSQL)) {
                ps2.setInt(1, buildPCID);
                ps2.executeUpdate();
            }

            connection.commit();
            return true;
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
            }
        }

        return false;
    }

    public List<BuildPCAdmin> getBuildPCItemsByBuildPCID(int buildPCID) {
        List<BuildPCAdmin> list = new ArrayList<>();
        String sql = """
        SELECT com.ComponentID, c.CategoryID, c.CategoryName, b.BrandName, i.Price, c.ImageURL
        FROM Build_PC_Items i
        JOIN Categories c ON i.CategoryID = c.CategoryID
        JOIN BrandComs bc ON c.BrandComID = bc.BrandComID
        JOIN Brands b ON bc.BrandID = b.BrandID
        JOIN Components com ON bc.ComponentID = com.ComponentID
        WHERE i.BuildPCID = ?
        """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, buildPCID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BuildPCAdmin item = new BuildPCAdmin();
                item.setComponentID(rs.getInt("ComponentID"));
                item.setCateID(rs.getInt("CategoryID"));
                item.setCateName(rs.getString("CategoryName"));
                item.setBrandName(rs.getString("BrandName"));
                item.setPrice(rs.getInt("Price"));
                item.setImgURL(rs.getString("ImageURL"));
                list.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean isDuplicateBuildPC(List<Integer> categoryIDs, int excludeBuildPCID) {
        if (categoryIDs == null || categoryIDs.size() != 6) {
            return true;
        }

        // Sort list để so sánh cho chuẩn
        List<Integer> sortedIDs = new ArrayList<>(categoryIDs);
        sortedIDs.sort(Integer::compareTo);

        String sql = """
        SELECT BuildPCID
        FROM Build_PC_Items
        WHERE CategoryID IN (?, ?, ?, ?, ?, ?)
        GROUP BY BuildPCID
        HAVING COUNT(*) = 6 AND
               SUM(CASE WHEN CategoryID IN (?, ?, ?, ?, ?, ?) THEN 1 ELSE 0 END) = 6
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (int i = 0; i < 6; i++) {
                ps.setInt(i + 1, sortedIDs.get(i));
            }
            for (int i = 0; i < 6; i++) {
                ps.setInt(i + 7, sortedIDs.get(i));
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int foundID = rs.getInt("BuildPCID");
                if (excludeBuildPCID == -1 || foundID != excludeBuildPCID) {
                    return true; // đã có PC khác có cùng 6 category
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
