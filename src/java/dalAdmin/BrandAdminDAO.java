package dalAdmin;

import dal.DBContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Brands;

public class BrandAdminDAO extends DBAdminContext {

    // Lấy toàn bộ danh sách Brand
    public List<Brands> getAllBrands() {
        List<Brands> list = new ArrayList<>();
        String sql = "SELECT * FROM Brands";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Brands b = new Brands(
                    rs.getInt("BrandID"),
                    rs.getString("BrandName"),
                    rs.getInt("Quantity"),
                    rs.getInt("Status")
                );
                list.add(b);
            }
        } catch (SQLException e) {
            System.err.println("getAllBrands Error: " + e.getMessage());
        }
        return list;
    }
    
    public List<Brands> getAllBrandsByName(String name) {
        List<Brands> list = new ArrayList<>();
        String sql = "SELECT * FROM Brands where BrandName LIKE '%" + name + "%'";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Brands b = new Brands(
                    rs.getInt("BrandID"),
                    rs.getString("BrandName"),
                    rs.getInt("Quantity"),
                    rs.getInt("Status")
                );
                list.add(b);
            }
        } catch (SQLException e) {
            System.err.println("getAllBrands Error: " + e.getMessage());
        }
        return list;
    }

    // Lấy Brand theo ID
    public Brands getBrandByID(int id) {
        String sql = "SELECT * FROM Brands WHERE BrandID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Brands(
                    rs.getInt("BrandID"),
                    rs.getString("BrandName"),
                    rs.getInt("Quantity"),
                    rs.getInt("Status")
                );
            }
        } catch (SQLException e) {
            System.err.println("getBrandByID Error: " + e.getMessage());
        }
        return null;
    }

    // Thêm mới Brand
    public boolean insertBrand(Brands brand) {
        String sql = "INSERT INTO Brands (BrandName, Quantity, Status) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, brand.getBrandName());
            ps.setInt(2, brand.getQuantity());
            ps.setInt(3, brand.getStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("insertBrand Error: " + e.getMessage());
        }
        return false;
    }

    // Cập nhật Brand
    public boolean updateBrand(Brands brand) {
        String sql = "UPDATE Brands SET BrandName = ?, Quantity = ?, Status = ? WHERE BrandID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, brand.getBrandName());
            ps.setInt(2, brand.getQuantity());
            ps.setInt(3, brand.getStatus());
            ps.setInt(4, brand.getBrandID());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("updateBrand Error: " + e.getMessage());
        }
        return false;
    }

    // Xóa Brand
    public boolean deleteBrand(int id) {
        String sql = "DELETE FROM Brands WHERE BrandID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("deleteBrand Error: " + e.getMessage());
        }
        return false;
    }
    
    public static void main(String[] args) {
        BrandAdminDAO dao = new BrandAdminDAO();
        // Truy vấn tất cả
        List<Brands> all = dao.getAllBrands();
        for (Brands c : all) {
            System.out.println(c.getBrandID() + " - " + c.getBrandName() + " - " + c.getStatus());
        }
    }
}
