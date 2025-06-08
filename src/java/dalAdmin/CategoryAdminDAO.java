package dalAdmin;

import dal.DBContext;
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
                        rs.getInt("BraComID"),
                        rs.getInt("Quantity"),
                        rs.getInt("Price"),
                        rs.getString("Description"),
                        rs.getInt("Status"),
                        rs.getString("ImgURL")
                );
                list.add(c);
            }

        } catch (SQLException e) {
            Logger.getLogger(CategoryAdminDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        return list;
    }

    public List<Categories> getCategoriesByBraComID(int id) {
        List<Categories> list = new ArrayList<>();
        String sql = "SELECT * FROM Categories WHERE BraComID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Categories c = new Categories(
                        rs.getInt("CategoryID"),
                        rs.getString("CategoryName"),
                        rs.getInt("BraComID"),
                        rs.getInt("Quantity"),
                        rs.getInt("Price"),
                        rs.getString("Description"),
                        rs.getInt("Status"),
                        rs.getString("ImgURL")
                );
                list.add(c);
            }

        } catch (SQLException e) {
            Logger.getLogger(CategoryAdminDAO.class.getName()).log(Level.SEVERE, null, e);
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
                        rs.getInt("BraComID"),
                        rs.getInt("Quantity"),
                        rs.getInt("Price"),
                        rs.getString("Description"),
                        rs.getInt("Status"),
                        rs.getString("ImgURL")
                );
            }
        } catch (SQLException e) {
            Logger.getLogger(CategoryAdminDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public void insertCategory(Categories c) {
        String sql = "INSERT INTO Categories (CategoryName, BraComID, Quantity, Price, Description, Status, ImgURL) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, c.getCategoryName());
            ps.setInt(2, c.getBraComID());
            ps.setInt(3, c.getQuantity());
            ps.setInt(4, c.getPrice());
            ps.setString(5, c.getDescription());
            ps.setInt(6, c.getStatus());
            ps.setString(7, c.getImgURL());
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(CategoryAdminDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void updateCategory(Categories c) {
        String sql = "UPDATE Categories SET CategoryName = ?, BraComID = ?, Quantity = ?, Price = ?, Description = ?, Status = ?, ImgURL = ? "
                   + "WHERE CategoryID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, c.getCategoryName());
            ps.setInt(2, c.getBraComID());
            ps.setInt(3, c.getQuantity());
            ps.setInt(4, c.getPrice());
            ps.setString(5, c.getDescription());
            ps.setInt(6, c.getStatus());
            ps.setString(7, c.getImgURL());
            ps.setInt(8, c.getCategoryID());
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(CategoryAdminDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void deleteCategory(int id) {
        String sql = "DELETE FROM Categories WHERE CategoryID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(CategoryAdminDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void updateStatus(int categoryID, int newStatus) {
        String sql = "UPDATE Categories SET Status = ? WHERE CategoryID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, newStatus);
            ps.setInt(2, categoryID);
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(CategoryAdminDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // Tùy chọn: bạn có thể thêm phương thức updateAllCategoryQuantities nếu bảng Products vẫn còn liên kết theo CategoryID
}
