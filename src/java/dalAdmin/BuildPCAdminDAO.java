/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dalAdmin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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
            MAX(CASE WHEN bc.ComponentID = 7 THEN c.CategoryName END) AS CASE_,
            SUM(bpi.Price) AS Price,
            MAX(bp.Status) AS Status
        FROM 
            Build_PC bp
        JOIN Build_PC_Items bpi ON bp.BuildPCID = bpi.BuildPCID
        JOIN Categories c ON bpi.CategoryID = c.CategoryID
        JOIN BrandComs bc ON c.BrandComID = bc.BrandComID
        WHERE bc.ComponentID BETWEEN 2 AND 7
        GROUP BY bp.BuildPCID
        ORDER BY bp.BuildPCID
    """;

        try (
                PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                BuildPCView b = new BuildPCView();
                b.setBuildPCID(rs.getInt("BuildPCID"));
                b.setMainBoard(rs.getString("MainBoard"));
                b.setCpu(rs.getString("CPU"));
                b.setGpu(rs.getString("GPU"));
                b.setRam(rs.getString("RAM"));
                b.setSsd(rs.getString("SSD"));
                b.setPcCase(rs.getString("CASE_")); // lưu ý alias là CASE_ thay vì CASE (SQL keyword)
                b.setPrice(rs.getInt("Price"));
                b.setStatus(rs.getInt("Status"));
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
        WHERE bc.ComponentID = ? AND c.Status = 2
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
}
