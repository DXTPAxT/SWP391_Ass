package dal;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import models.BrandByComponentName;
import models.Brands;
import models.Categories;
import models.Components;

public class CategoriesDAO extends DBContext {

    public List<Categories> getAllCategories() {
        List<Categories> list = new ArrayList<>();
       String sql = """
        SELECT 
            c.CategoryID, c.CategoryName, c.ComponentID, c.BrandID,
            c.Quantity, c.Price, c.Description, c.Status,
            b.BrandName
        FROM Categories c
        JOIN Brands b ON c.BrandID = b.BrandID
    """;
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
            Logger.getLogger(CategoriesDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    public List<Brands> getBrands() {
        List<Brands> list = new ArrayList<>();
        String sql = "Select*from Brands ";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Brands b = new Brands(
                        rs.getInt("brandID"),
                        rs.getString("BrandName")
                );
                list.add(b);
            }

        } catch (Exception e) {
        }

        return list;
    }

    public List<BrandByComponentName> getBrandInSiteComponents() {
        List<BrandByComponentName> list = new ArrayList<>();
        String sql = """
        SELECT DISTINCT co.ComponentID, co.ComponentName, b.BrandName
        FROM Components co
        JOIN Categories c ON co.ComponentID = c.ComponentID
        JOIN Brands b ON c.BrandID = b.BrandID
    """;
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                BrandByComponentName obj = new BrandByComponentName(
                        rs.getInt("ComponentID"),
                        rs.getString("ComponentName"),
                        rs.getString("BrandName")
                );
                list.add(obj);
            }
        } catch (SQLException e) {
            Logger.getLogger(CategoriesDAO.class.getName()).log(Level.SEVERE, null, e);
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

    public List<Components> GetAllComponents() {
        List<Components> list = new ArrayList<>();
        String sql = " Select*from Components";
        try (
                PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Components c = new Components(
                        rs.getInt("componentID"),
                        rs.getString("componentName"),
                        rs.getInt("quantity"),
                        rs.getInt("status")
                );
                list.add(c);
            }

        } catch (Exception e) {
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
                        null,
                        rs.getInt("Quantity"),
                        rs.getInt("Price"),
                        rs.getString("Description"),
                        rs.getInt("Status")
                );
            }
        } catch (SQLException e) {
            Logger.getLogger(CategoriesDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

  
}
