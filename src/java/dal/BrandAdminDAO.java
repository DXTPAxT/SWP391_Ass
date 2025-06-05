/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Brands;

public class BrandAdminDAO extends DBContext {

    // Lấy toàn bộ danh sách Brand
    public List<Brands> getAllBrands() {
        List<Brands> list = new ArrayList<>();
        String sql = "SELECT * FROM Brands";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Brands b = new Brands(rs.getInt("BrandID"), rs.getString("BrandName"));
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
                return new Brands(rs.getInt("BrandID"), rs.getString("BrandName"));
            }
        } catch (SQLException e) {
            System.err.println("getBrandByID Error: " + e.getMessage());
        }
        return null;
    }

    // Thêm mới Brand
    public boolean insertBrand(Brands brand) {
        String sql = "INSERT INTO Brands (BrandName) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, brand.getBrandName());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("insertBrand Error: " + e.getMessage());
        }
        return false;
    }

    // Cập nhật Brand
    public boolean updateBrand(Brands brand) {
        String sql = "UPDATE Brands SET BrandName = ? WHERE BrandID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, brand.getBrandName());
            ps.setInt(2, brand.getBrandID());
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
}
