package dal;

import java.util.Vector;
import models.Products;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Categories;

public class ProductDAO extends DBContext {

    public List<Products> getAllProduct(String sql) {
        List<Products> listProduct = new ArrayList<>();
        PreparedStatement ptm;
        try {
            ptm = connection.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                Products p = new Products(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDouble(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getDate(8),
                        rs.getInt(9),
                        rs.getInt(10));
                listProduct.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // hoặc log nếu dùng Logger
        }
        return listProduct;
    }

    public Products getProductByID(int id) {
        String sql = "SELECT * FROM Products WHERE ProductID = ?";
        try (PreparedStatement ptm = connection.prepareStatement(sql)) {
            ptm.setInt(1, id);
            ResultSet rs = ptm.executeQuery();
            if (rs.next()) {
                Products p = new Products(
                        rs.getInt("ProductID"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getString("Brand"),
                        rs.getDouble("Price"),
                        rs.getInt("Quantity"),
                        rs.getInt("WarrantyPeriod"),
                        rs.getDate("CreatedAt"),
                        rs.getInt("CategoryID"),
                        rs.getInt("Status")
                );
                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Products> getAllProductsByCategoryName(String categoryName) {
        List<Products> products = new ArrayList<>();
        String sql = "SELECT p.* FROM Products p "
                + "JOIN Categories c ON p.CategoryID = c.CategoryID "
                + "WHERE c.CategoryName = ?";

        try (
                PreparedStatement ptm = connection.prepareStatement(sql)) {
            ptm.setString(1, categoryName);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                Products p = new Products(
                        rs.getInt("ProductID"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getString("Brand"),
                        rs.getDouble("Price"),
                        rs.getInt("Quantity"),
                        rs.getInt("WarrantyPeriod"),
                        rs.getDate("CreatedAt"),
                        rs.getInt("CategoryID"),
                        rs.getInt("Status")
                );
                products.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return products;
    }

    public List<Products> searchByName(String keyword) {
        List<Products> list = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE Name LIKE ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Products p = new Products(
                        rs.getInt("ProductID"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getString("Image"), // hoặc đúng tên cột ảnh
                        rs.getDouble("Price"),
                        rs.getInt("Quantity"),
                        rs.getInt("CategoryID"),
                        rs.getDate("CreatedAt"),
                        rs.getInt("BrandID"),
                        rs.getInt("Status")
                );
                list.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Search error: " + e.getMessage());
        }
        return list;
    }

    public List<Products> getProductsByBrand(String brand) {
        List<Products> list = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE Brand = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, brand);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Products p = new Products(
                        rs.getInt("ProductID"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getString("Brand"),
                        rs.getDouble("Price"),
                        rs.getInt("Quantity"),
                        rs.getInt("WarrantyPeriod"),
                        rs.getDate("CreatedAt"),
                        rs.getInt("CategoryID"),
                        rs.getInt("Status")
                );
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insertProduct(Products p) {
        String sql = "INSERT INTO Products (Name, Description, Brand, Price, Quantity, WarrantyPeriod, CreatedAt, CategoryID, Status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setString(3, p.getBrand());
            ps.setDouble(4, p.getPrice());
            ps.setInt(5, p.getQuantity());
            ps.setInt(6, p.getWarrantyPeriod());
            ps.setDate(7, (Date) p.getCreatedAt());
            ps.setInt(8, p.getCategoryID());
            ps.setInt(9, p.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(Products p) {
        String sql = "UPDATE Products SET Name = ?, Description = ?, Brand = ?, Price = ?, Quantity = ?, WarrantyPeriod = ?, "
                + "CreatedAt = ?, CategoryID = ?, Status = ? WHERE ProductID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setString(3, p.getBrand());
            ps.setDouble(4, p.getPrice());
            ps.setInt(5, p.getQuantity());
            ps.setInt(6, p.getWarrantyPeriod());
            ps.setDate(7, (Date) p.getCreatedAt());
            ps.setInt(8, p.getCategoryID());
            ps.setInt(9, p.getStatus());
            ps.setInt(10, p.getProductID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Products searchProductByID(int id) {
        String sql = "SELECT * FROM Products WHERE ProductID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Products p = new Products();
                p.setProductID(rs.getInt("ProductID"));
                p.setName(rs.getString("Name"));
                p.setDescription(rs.getString("Description"));
                p.setBrand(rs.getString("Brand"));
                p.setPrice(rs.getDouble("Price"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setWarrantyPeriod(rs.getInt("WarrantyPeriod"));
                p.setCreatedAt(rs.getDate("CreatedAt"));
                p.setCategoryID(rs.getInt("CategoryID"));
                p.setStatus(rs.getInt("Status"));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        Products p = new Products();
        p = dao.getProductByID(1);
        System.out.println(p.getName());
    }
}
