package dalAdmin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.BraComs;
import models.Brands;

public class BraComAdminDAO extends DBAdminContext {

    // Lấy tất cả BraCom
    public List<BraComs> getAllBraComs() {
        List<BraComs> list = new ArrayList<>();
        String sql = "SELECT \n"
                + "    bc.BrandComID,\n"
                + "    b.BrandID,\n"
                + "    b.BrandName,\n"
                + "    c.ComponentID,\n"
                + "    c.ComponentName,\n"
                + "    bc.Quantity AS BraComQuantity\n"
                + "FROM BrandComs bc\n"
                + "JOIN Brands b ON bc.BrandID = b.BrandID\n"
                + "JOIN Components c ON bc.ComponentID = c.ComponentID;";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                BraComs b = new BraComs();
                b.setBraComID(rs.getInt("BrandComID"));
                b.setBrandID(rs.getInt("BrandID"));
                b.setBrandName(rs.getString("BrandName"));
                b.setComponentID(rs.getInt("ComponentID"));
                b.setComponentName(rs.getString("ComponentName"));
                b.setQuantity(rs.getInt("BraComQuantity"));  // hoặc setBraComQuantity nếu bạn đặt tên khác
                list.add(b);
            }
        } catch (SQLException e) {
            System.err.println("getAllBraComs Error: " + e.getMessage());
        }

        return list;
    }

    public List<BraComs> getBraComsByBrandID(int brandID) {
        List<BraComs> list = new ArrayList<>();
        String sql = "SELECT bc.BrandComID, b.BrandID, b.BrandName, c.ComponentID, c.ComponentName, bc.Quantity AS BraComQuantity "
                + "FROM BrandComs bc "
                + "JOIN Brands b ON bc.BrandID = b.BrandID "
                + "JOIN Components c ON bc.ComponentID = c.ComponentID "
                + "WHERE bc.BrandID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, brandID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                BraComs b = new BraComs();
                b.setBraComID(rs.getInt("BrandComID"));
                b.setBrandID(rs.getInt("BrandID"));
                b.setBrandName(rs.getString("BrandName"));
                b.setComponentID(rs.getInt("ComponentID"));
                b.setComponentName(rs.getString("ComponentName"));
                b.setQuantity(rs.getInt("BraComQuantity"));
                list.add(b);
            }

        } catch (SQLException e) {
            System.err.println("getBraComsByBrandID Error: " + e.getMessage());
        }

        return list;
    }

    public List<BraComs> getBraComsByComponentID(int componentID) {
        List<BraComs> list = new ArrayList<>();
        String sql = "SELECT bc.BrandComID, b.BrandID, b.BrandName, c.ComponentID, c.ComponentName, bc.Quantity AS BraComQuantity "
                + "FROM BrandComs bc "
                + "JOIN Brands b ON bc.BrandID = b.BrandID "
                + "JOIN Components c ON bc.ComponentID = c.ComponentID "
                + "WHERE bc.ComponentID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, componentID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                BraComs b = new BraComs();
                b.setBraComID(rs.getInt("BrandComID"));
                b.setBrandID(rs.getInt("BrandID"));
                b.setBrandName(rs.getString("BrandName"));
                b.setComponentID(rs.getInt("ComponentID"));
                b.setComponentName(rs.getString("ComponentName"));
                b.setQuantity(rs.getInt("BraComQuantity"));
                list.add(b);
            }

        } catch (SQLException e) {
            System.err.println("getBraComsByComponentID Error: " + e.getMessage());
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

    public static void main(String[] args) {
        BraComAdminDAO dao = new BraComAdminDAO();
        int id = 1;
        List<BraComs> all = dao.getBraComsByComponentID(id);

        for (BraComs c : all) {
            System.out.println(c.getBrandName() + " - " + c.getBraComID() + " - " + c.getComponentName());
        }
    }

}
