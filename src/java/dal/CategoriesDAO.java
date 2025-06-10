package dal;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import models.BrandByComponentName;
import models.Brands;
import models.Categories;
import models.Components;

public class CategoriesDAO extends DBContext {

    private static final Logger LOGGER = Logger.getLogger(CategoriesDAO.class.getName());

    public List<Brands> getAllBrands() {
        String sql = "SELECT BrandID, BrandName, Quantity, Status FROM Brands";
        List<Brands> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Brands(
                        rs.getInt("BrandID"),
                        rs.getString("BrandName"),
                        rs.getInt("Quantity"),
                        rs.getInt("Status")
                ));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        return list;
    }

    // 2. Lấy danh sách brand theo component (dùng bảng BrandComs)
    public List<BrandByComponentName> getBrandsGroupedByComponent() {
        String sql = """
            SELECT DISTINCT
              bc.ComponentID,
              comp.ComponentName,
              b.BrandName
            FROM BrandComs bc
            JOIN Components comp ON bc.ComponentID = comp.ComponentID
            JOIN Brands b ON bc.BrandID = b.BrandID
            """;
        List<BrandByComponentName> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new BrandByComponentName(
                        rs.getInt("ComponentID"),
                        rs.getString("ComponentName"),
                        rs.getString("BrandName")
                ));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        return list;
    }

    // 3. Lấy toàn bộ Components
    public List<Components> getAllComponents() {
        String sql = "SELECT * FROM Components";
        List<Components> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Components(
                        rs.getInt("ComponentID"),
                        rs.getString("ComponentName"),
                        rs.getInt("Quantity"),
                        rs.getInt("Status")
                ));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        return list;
    }

    // 4. Lấy category theo ID (kèm thông tin brand & component)
    public List<Categories> getCategoryByID(int id) {
        String sql = """
            SELECT
              c.*,
              bc.ComponentID,
              bc.BrandID,
              b.BrandName,
              comp.ComponentName
            FROM Categories c
            JOIN BrandComs bc ON c.BrandComID   = bc.BrandComID
            JOIN Brands b ON bc.BrandID = b.BrandID
            JOIN Components comp ON bc.ComponentID = comp.ComponentID
            WHERE c.CategoryID = ?
            """;
        List<Categories> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(extractCategory(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        return list;
    }

    // 5. Phân trang theo component
    public List<Categories> getCategoriesByComponent(int componentId, int start, int size) {
        String sql = """
            SELECT
              c.*,
              bc.ComponentID,
              bc.BrandID,
              b.BrandName,
              comp.ComponentName
            FROM Categories c
            JOIN BrandComs bc ON c.BrandComID   = bc.BrandComID
            JOIN Brands b ON bc.BrandID = b.BrandID
            JOIN Components comp ON bc.ComponentID = comp.ComponentID
            WHERE bc.ComponentID = ? AND c.Status = 2
            ORDER BY c.CategoryID
            OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
            """;
        List<Categories> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, componentId);
            ps.setInt(2, start);
            ps.setInt(3, size);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(extractCategory(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        return list;
    }

    // 6. Phân trang tất cả categories
    public List<Categories> getAllCategoriesPaginated(int page, int size) {
        String sql = """
            SELECT
              c.*,
              bc.ComponentID,
              bc.BrandID,
              b.BrandName,
              comp.ComponentName
            FROM Categories c
            JOIN BrandComs bc ON c.BrandComID   = bc.BrandComID
            JOIN Brands b ON bc.BrandID  = b.BrandID
            JOIN Components comp ON bc.ComponentID = comp.ComponentID
            ORDER BY c.CategoryID
            OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
            """;
        List<Categories> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, (page - 1) * size);
            ps.setInt(2, size);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(extractCategory(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        return list;
    }

    // 7. Đếm tổng số products theo component
    public int countTotalProducts(int componentId) {
        String sql = """
            SELECT COUNT(*)
            FROM Categories c
            JOIN BrandComs bc ON c.BrandComID = bc.BrandComID
            WHERE bc.ComponentID = ? AND c.Status = 2
            """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, componentId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        return 0;
    }

    // 8. Đếm tất cả categories
    public int countAllCategories() {
        String sql = "SELECT COUNT(*) FROM Categories";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        return 0;
    }

    // 9. Lọc nâng cao với phân trang
    public List<Categories> getCategoriesFiltered(
            String componentName, String brandName,
            Integer minPrice, Integer maxPrice,
            String keyword, int start, int size) {

        StringBuilder sql = new StringBuilder("""
            SELECT
              c.*,
              bc.ComponentID,
              bc.BrandID,
              b.BrandName,
              comp.ComponentName
            FROM Categories c
            JOIN BrandComs bc ON c.BrandComID = bc.BrandComID
            JOIN Brands b ON bc.BrandID= b.BrandID
            JOIN Components comp  ON bc.ComponentID = comp.ComponentID
            WHERE 1=1
            """);
        List<Object> params = buildFilter(sql, componentName, brandName, minPrice, maxPrice, keyword);
        sql.append(" ORDER BY c.CategoryID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");
        params.add(start);
        params.add(size);

        List<Categories> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            setParams(ps, params);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(extractCategory(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        return list;
    }

    // 10. Đếm kết quả lọc
    public int countFiltered(
            String componentName, String brandName,
            Integer minPrice, Integer maxPrice,
            String keyword) {

        StringBuilder sql = new StringBuilder("""
            SELECT COUNT(*)
            FROM Categories c
            JOIN BrandComs bc ON c.BrandComID= bc.BrandComID
            JOIN Brands b ON bc.BrandID = b.BrandID
            JOIN Components comp ON bc.ComponentID = comp.ComponentID
            WHERE 1=1
            """);
        List<Object> params = buildFilter(sql, componentName, brandName, minPrice, maxPrice, keyword);

        try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            setParams(ps, params);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        return 0;
    }

    //  Lấy danh sách sản phẩm theo tên component
   public List<Categories> getCategoriesByComponentID(int componentID) {
    // Nếu componentID là 1 thì bỏ qua
    if (componentID == 1) return new ArrayList<>();     
    String sql = """
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
        WHERE bc.ComponentID = ? AND c.Status = 2
        ORDER BY c.CategoryID
    """;

    List<Categories> list = new ArrayList<>();
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, componentID);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(extractCategory(rs));
            }
        }
    } catch (SQLException e) {
        LOGGER.log(Level.SEVERE, null, e);
    }
    return list;
}


    // Phương pháp tiện ích
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

    private void setParams(PreparedStatement ps, List<Object> params) throws SQLException {
        for (int i = 0; i < params.size(); i++) {
            ps.setObject(i + 1, params.get(i));
        }
    }
}
