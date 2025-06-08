package dalAdmin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.BraComs;

public class BraComAdminDAO extends DBContext {

    // Lấy tất cả BraCom
    public List<BraComs> getAllBraComs() {
        List<BraComs> list = new ArrayList<>();
        String sql = "SELECT * FROM BraComs";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                BraComs b = new BraComs(
                    rs.getInt("BraComID"),
                    rs.getInt("BrandID"),
                    rs.getInt("ComponentID"),
                    rs.getInt("Quantity")
                );
                list.add(b);
            }
        } catch (SQLException e) {
            System.err.println("getAllBraComs Error: " + e.getMessage());
        }
        return list;
    }

    // Lấy BraCom theo ID
    public BraComs getBraComByID(int id) {
        String sql = "SELECT * FROM BraComs WHERE BraComID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new BraComs(
                    rs.getInt("BraComID"),
                    rs.getInt("BrandID"),
                    rs.getInt("ComponentID"),
                    rs.getInt("Quantity")
                );
            }
        } catch (SQLException e) {
            System.err.println("getBraComByID Error: " + e.getMessage());
        }
        return null;
    }

    // Thêm mới
    public boolean insertBraCom(BraComs b) {
        String sql = "INSERT INTO BraComs (BrandID, ComponentID, Quantity) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, b.getBrandID());
            ps.setInt(2, b.getComponentID());
            ps.setInt(3, b.getQuantity());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("insertBraCom Error: " + e.getMessage());
        }
        return false;
    }

    // Cập nhật
    public boolean updateBraCom(BraComs b) {
        String sql = "UPDATE BraComs SET BrandID = ?, ComponentID = ?, Quantity = ? WHERE BraComID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, b.getBrandID());
            ps.setInt(2, b.getComponentID());
            ps.setInt(3, b.getQuantity());
            ps.setInt(4, b.getBraComID());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("updateBraCom Error: " + e.getMessage());
        }
        return false;
    }

    // Xoá
    public boolean deleteBraCom(int id) {
        String sql = "DELETE FROM BraComs WHERE BraComID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("deleteBraCom Error: " + e.getMessage());
        }
        return false;
    }
}
