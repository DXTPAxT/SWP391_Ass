package dal;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import models.Categories;

public class CategoryAdminDAO extends DBContext {

    public List<Categories> getAllCategories(String sql) {
        List<Categories> list = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Categories c = new Categories(
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
                list.add(c);
            }

        } catch (SQLException e) {
         
        }

        return list;
    }

    public List<Categories> getCategoriesByComponentID(int id) {
        List<Categories> list = new ArrayList<>();
        String sql = "SELECT * FROM Categories WHERE ComponentID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Categories c = new Categories(
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
                list.add(c);
            }

        } catch (SQLException e) {
            
        }

        return list;
    }

    public Categories getCategoryByID(int id) {
        String sql = "SELECT * FROM Categories WHERE CategoryID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Categories(
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
            }
        } catch (SQLException e) {
         
        }
        return null;
    }

    public void insertCategory(Categories c) {
        String sql = "INSERT INTO Categories (CategoryName, ComponentID, BrandID, Quantity, Price, Description, Status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, c.getCategoryName());
            ps.setInt(2, c.getComponentID());
            ps.setInt(3, c.getBrandID());
            ps.setInt(4, c.getQuantity());
            ps.setInt(5, c.getPrice());
            ps.setString(6, c.getDescription());
            ps.setInt(7, c.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
           
        }
    }

    public void updateCategory(Categories c) {
        String sql = "UPDATE Categories SET CategoryName = ?, ComponentID = ?, BrandID = ?, Quantity = ?, Price = ?, Description = ?, Status = ? "
                + "WHERE CategoryID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, c.getCategoryName());
            ps.setInt(2, c.getComponentID());
            ps.setInt(3, c.getBrandID());
            ps.setInt(4, c.getQuantity());
            ps.setInt(5, c.getPrice());
            ps.setString(6, c.getDescription());
            ps.setInt(7, c.getStatus());
            ps.setInt(8, c.getCategoryID());
            ps.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void deleteCategory(int id) {
        String sql = "DELETE FROM Categories WHERE CategoryID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
          
        }
    }

    public void updateAllCategoryQuantities() {
        String sql = """
        UPDATE Categories
        SET Quantity = (
            SELECT COUNT(*) 
            FROM Products 
            WHERE Products.CategoryID = Categories.CategoryID
        )
    """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate();
            System.out.println("Cập nhật số lượng sản phẩm cho các Category thành công.");
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật số lượng sản phẩm: " + e.getMessage());
        }
    }

    public void updateStatus(int categoryID, int newStatus) {
        String sql = "UPDATE Categories SET Status = ? WHERE CategoryID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, newStatus);
            ps.setInt(2, categoryID);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CategoryAdminDAO dao = new CategoryAdminDAO(); // hoặc BrandAdminDAO nếu tên vậy

        int testComponentID = 2; // thay đổi ID này tùy dữ liệu có trong DB
        Categories categories = dao.getCategoryByID(testComponentID);

        System.out.println(categories.getCategoryName());
    }
}
