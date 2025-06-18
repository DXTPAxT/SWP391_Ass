package dal;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import models.Products;

public class ProductDAO extends DBContext {

//    public List<Products> getAllProduct(String sql) {
//        List<Products> listProduct = new ArrayList<>();
//        try (PreparedStatement ptm = connection.prepareStatement(sql); ResultSet rs = ptm.executeQuery()) {
//            while (rs.next()) {
//                Products p = new Products(
//                        rs.getInt("ProductID"),
//                        rs.getString("Name"),
//                        rs.getDate("CreatedAt"),
//                        rs.getInt("CategoryID"),
//                        rs.getString("ProductCode"),
//                        rs.getInt("Status"));
//                listProduct.add(p);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return listProduct;
//    }
//
//    public Products getProductByID(int id) {
//        String sql = "SELECT * FROM Products WHERE ProductID = ?";
//        try (PreparedStatement ptm = connection.prepareStatement(sql)) {
//            ptm.setInt(1, id);
//            ResultSet rs = ptm.executeQuery();
//            if (rs.next()) {
//                return new Products(
//                        rs.getInt("ProductID"),
//                        rs.getString("Name"),
//                        rs.getDate("CreatedAt"),
//                        rs.getInt("CategoryID"),
//                        rs.getString("ProductCode"),
//                        rs.getInt("Status"));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//
//    public void insertProduct(Products p) {
//        String sql = "INSERT INTO Products (Name, CreatedAt, CategoryID, ProductCode, Status) VALUES (?, ?, ?, ?, ?)";
//        try (PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setString(1, p.getName());
//            ps.setDate(2, p.getCreatedAt());
//            ps.setInt(3, p.getCategoryID());
//            ps.setString(4, p.getProductCode());
//            ps.setInt(5, p.getStatus());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
//        }
//    }
//
//    public void updateProduct(Products p) {
//        String sql = "UPDATE Products SET Name = ?, CreatedAt = ?, CategoryID = ?, ProductCode = ?,  Status = ? WHERE ProductID = ?";
//        try (PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setString(1, p.getName());
//            ps.setDate(2, p.getCreatedAt());
//            ps.setInt(3, p.getCategoryID());
//            ps.setString(4, p.getProductCode());
//            ps.setInt(5, p.getStatus());
//            ps.setInt(6, p.getProductID());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
//        }
//    }

    public void deleteProduct(int id) {
        String sql = "DELETE FROM Products WHERE ProductID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

//    public List<Products> searchByName(String keyword) {
//        List<Products> list = new ArrayList<>();
//        String sql = "SELECT * FROM Products WHERE Name LIKE ?";
//        try (PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setString(1, "%" + keyword + "%");
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                Products p = new Products(
//                        rs.getInt("ProductID"),
//                        rs.getString("Name"),
//                        rs.getDate("CreatedAt"),
//                        rs.getInt("CategoryID"),
//                        rs.getString("ProductCode"),
//                        rs.getInt("Status"));
//                list.add(p);
//            }
//        } catch (SQLException e) {
//            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
//        }
//        return list;
//    }
//
//    public void syncProductNameWithCategoryName() {
//        String sql = "UPDATE Products "
//                + "SET Name = (SELECT CategoryName FROM Categories WHERE Categories.CategoryID = Products.CategoryID)";
//        try (PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
//        }
//    }

}
