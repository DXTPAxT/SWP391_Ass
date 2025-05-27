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

    public Vector<Products> getAllProduct(String sql) {
        Vector<Products> listProduct = new Vector<>();
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

    public Vector<Products> getAllProductsByCategoryID(int categoryID) {
        Vector<Products> products = new Vector<>();
        String sql = "SELECT * FROM Products WHERE CategoryID = ?";

        try (PreparedStatement ptm = connection.prepareStatement(sql)) {
            ptm.setInt(1, categoryID);
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

    public Vector<Products> searchByName(String keyword) {
        Vector<Products> list = new Vector<>();
        String sql = "SELECT * FROM Products WHERE Name LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
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

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        Products p = new Products();
        p = dao.getProductByID(1);
        System.out.println(p.getName());
    }
}
