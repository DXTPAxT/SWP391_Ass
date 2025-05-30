package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.BrandByCategoriesName;
import models.Categories;
import models.Products;

public class CategoryDAO extends DBContext {

    public List<Products> GetCataByCategory(int categoryId, int start, int pageSize) {
        List<Products> list = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE Status = 2 AND CategoryID = ? ORDER BY ProductID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, categoryId);
            ps.setInt(2, start);
            ps.setInt(3, pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Products p = new Products(
                        rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getDouble(5), rs.getInt(6),
                        rs.getInt(7), rs.getDate(8), rs.getInt(9), rs.getInt(10)
                );
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int countTotalProducts(int categoryId) {
        String sql = "SELECT COUNT(*) FROM Products WHERE Status = 2 AND CategoryID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Categories> getCategoriesName() {
        List<Categories> listCategory = new ArrayList<>();
        String sql = "SELECT * FROM Categories";
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                Categories cat = new Categories(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4)
                );
                listCategory.add(cat);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listCategory;
    }

    public List<BrandByCategoriesName> getBrandWithCategoryName() {
        List<BrandByCategoriesName> listBrand = new ArrayList<>();
        String sql = "SELECT c.CategoryID, c.CategoryName, p.Brand FROM Products p "
                + "JOIN Categories c ON p.CategoryID = c.CategoryID "
                + "ORDER BY c.CategoryID, p.Brand";
        try (PreparedStatement ptm = connection.prepareStatement(sql); ResultSet rs = ptm.executeQuery()) {
            while (rs.next()) {
                BrandByCategoriesName brand = new BrandByCategoriesName(
                        rs.getInt("CategoryID"),
                        rs.getString("CategoryName"),
                        rs.getString("Brand")
                );
                listBrand.add(brand);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listBrand;
    }

    public List<String> getAllBrands() {
        List<String> brands = new ArrayList<>();
        String sql = "SELECT DISTINCT Brand FROM Products";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                brands.add(rs.getString("Brand"));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return brands;
    }

    public void insertCategory(Categories category) {
        String sql = "INSERT INTO Categories (CategoryName, Quantity, Status) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, category.getCategoryName());
            ps.setInt(2, category.getQuantity());
            ps.setInt(3, category.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCategory(Categories category) {
        String sql = "UPDATE Categories SET CategoryName = ?, Quantity = ?, Status = ? WHERE CategoryID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, category.getCategoryName());
            ps.setInt(2, category.getQuantity());
            ps.setInt(3, category.getStatus());
            ps.setInt(4, category.getCategoryID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Categories searchCategoryByID(int id) {
        String sql = "SELECT * FROM Categories WHERE CategoryID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Categories(
                        rs.getInt("CategoryID"),
                        rs.getString("CategoryName"),
                        rs.getInt("Quantity"),
                        rs.getInt("Status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
