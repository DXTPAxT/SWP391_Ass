package dalAdmin;

import java.sql.*;
import java.util.*;
import models.Products;

public class ProductAdminDAO extends DBContext {

    // Lấy danh sách tất cả sản phẩm
    public List<Products> getAllProducts() {
        List<Products> list = new ArrayList<>();
        String sql = "SELECT * FROM Products";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Products p = new Products(
                    rs.getInt("ProductID"),
                    rs.getInt("ImportID"),
                    rs.getInt("CategoryID"),
                    rs.getString("ProductCode"),
                    rs.getInt("Status")
                );
                list.add(p);
            }
        } catch (SQLException e) {
            System.err.println("getAllProducts Error: " + e.getMessage());
        }

        return list;
    }

    // Lấy sản phẩm theo ID
    public Products getProductByID(int id) {
        String sql = "SELECT * FROM Products WHERE ProductID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Products(
                    rs.getInt("ProductID"),
                    rs.getInt("ImportID"),
                    rs.getInt("CategoryID"),
                    rs.getString("ProductCode"),
                    rs.getInt("Status")
                );
            }
        } catch (SQLException e) {
            System.err.println("getProductByID Error: " + e.getMessage());
        }
        return null;
    }

    // Thêm sản phẩm mới
    public boolean insertProduct(Products p) {
        String sql = "INSERT INTO Products (ImportID, CategoryID, ProductCode, Status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, p.getImportID());
            ps.setInt(2, p.getCategoryID());
            ps.setString(3, p.getProductCode());
            ps.setInt(4, p.getStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("insertProduct Error: " + e.getMessage());
        }
        return false;
    }

    // Cập nhật sản phẩm
    public boolean updateProduct(Products p) {
        String sql = "UPDATE Products SET ImportID = ?, CategoryID = ?, ProductCode = ?, Status = ? WHERE ProductID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, p.getImportID());
            ps.setInt(2, p.getCategoryID());
            ps.setString(3, p.getProductCode());
            ps.setInt(4, p.getStatus());
            ps.setInt(5, p.getProductID());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("updateProduct Error: " + e.getMessage());
        }
        return false;
    }

    // Xóa sản phẩm
    public boolean deleteProduct(int id) {
        String sql = "DELETE FROM Products WHERE ProductID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("deleteProduct Error: " + e.getMessage());
        }
        return false;
    }
}
