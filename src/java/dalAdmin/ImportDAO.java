package dalAdmin;

import java.sql.*;
import java.util.*;
import models.Imports;

public class ImportDAO extends DBAdminContext {

    // Lấy tất cả bản ghi nhập hàng
    public List<Imports> getAllImports() {
        List<Imports> list = new ArrayList<>();
        String sql = "SELECT \n"
                + "    i.ImportID,\n"
                + "    i.ImportCode,\n"
                + "    i.CategoryID,\n"
                + "    c.CategoryName,\n"
                + "    i.CreatedAt,\n"
                + "    i.Quantity,\n"
                + "    i.Price\n"
                + "FROM \n"
                + "    Imports i\n"
                + "JOIN \n"
                + "    Categories c ON i.CategoryID = c.CategoryID;";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Imports imp = new Imports(
                        rs.getInt("ImportID"),
                        rs.getString("ImportCode"),
                        rs.getInt("CategoryID"),
                        rs.getString("CategoryName"),
                        rs.getDate("CreatedAt"),
                        rs.getInt("Quantity"),
                        rs.getInt("Price")
                );
                list.add(imp);
            }
        } catch (SQLException e) {
            System.err.println("getAllImports Error: " + e.getMessage());
        }

        return list;
    }

    public List<Imports> getImportsWithProductsByCategoryID(int categoryID) {
        List<Imports> list = new ArrayList<>();
        String sql = "SELECT DISTINCT \n"
                + "    i.ImportID,\n"
                + "    i.ImportCode,\n"
                + "    i.CategoryID,\n"
                + "    c.CategoryName,\n"
                + "    i.CreatedAt,\n"
                + "    i.Quantity AS ImportQuantity,\n"
                + "    i.Price\n"
                + "FROM Imports i\n"
                + "JOIN Products p ON i.ImportID = p.ImportID\n"
                + "JOIN Categories c ON i.CategoryID = c.CategoryID\n"
                + "WHERE i.CategoryID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, categoryID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Imports imp = new Imports(
                            rs.getInt("ImportID"),
                            rs.getString("ImportCode"),
                            rs.getInt("CategoryID"),
                            rs.getString("CategoryName"),
                            rs.getDate("CreatedAt"),
                            rs.getInt("ImportQuantity"),
                            rs.getInt("Price")
                    );
                    list.add(imp);
                }
            }
        } catch (SQLException e) {
            System.err.println("getImportsWithProductsByCategoryID Error: " + e.getMessage());
        }

        return list;
    }

    // Lấy thông tin nhập hàng theo ID
    public Imports getImportByID(int id) {
        String sql = "SELECT * FROM Imports WHERE ImportID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Imports(
                        rs.getInt("ImportID"),
                        rs.getInt("CategoryID"),
                        rs.getDate("CreatAt"),
                        rs.getInt("Quantity"),
                        rs.getInt("Price")
                );
            }
        } catch (SQLException e) {
            System.err.println("getImportByID Error: " + e.getMessage());
        }

        return null;
    }

    public Imports getImportByProductCode(String productCode) {
        String sql = "SELECT i.ImportID, i.ImportCode, i.CategoryID, c.CategoryName, i.CreatedAt, i.Quantity, i.Price "
                + "FROM Products p "
                + "JOIN Imports i ON p.ImportID = i.ImportID "
                + "JOIN Categories c ON i.CategoryID = c.CategoryID "
                + "WHERE p.ProductCode = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, productCode);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Imports(
                            rs.getInt("ImportID"),
                            rs.getString("ImportCode"),
                            rs.getInt("CategoryID"),
                            rs.getString("CategoryName"),
                            rs.getDate("CreatedAt"),
                            rs.getInt("Quantity"),
                            rs.getInt("Price")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("getImportByProductCode Error: " + e.getMessage());
        }

        return null;
    }

    // Thêm bản ghi nhập hàng mới
    public boolean insertImport(Imports imp) {
        String sql = "INSERT INTO Imports (CategoryID, CreatAt, Quantity, Price) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, imp.getCategoryID());
            ps.setDate(2, new java.sql.Date(imp.getCreatAt().getTime()));
            ps.setInt(3, imp.getQuantity());
            ps.setInt(4, imp.getPrice());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("insertImport Error: " + e.getMessage());
        }

        return false;
    }

    // Cập nhật thông tin nhập hàng
    public boolean updateImport(Imports imp) {
        String sql = "UPDATE Imports SET CategoryID = ?, CreatAt = ?, Quantity = ?, Price = ? WHERE ImportID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, imp.getCategoryID());
            ps.setDate(2, new java.sql.Date(imp.getCreatAt().getTime()));
            ps.setInt(3, imp.getQuantity());
            ps.setInt(4, imp.getPrice());
            ps.setInt(5, imp.getImportID());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("updateImport Error: " + e.getMessage());
        }

        return false;
    }

    // Xoá bản ghi nhập hàng
    public boolean deleteImport(int id) {
        String sql = "DELETE FROM Imports WHERE ImportID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("deleteImport Error: " + e.getMessage());
        }

        return false;
    }

    public void updateImportQuantitiesFromProducts() {
        String sql = """
        UPDATE Imports
        SET Quantity = (
            SELECT COUNT(*)
            FROM Products p
            WHERE p.ImportID = Imports.ImportID
        )
    """;

        try (Connection conn = new DBAdminContext().connection; PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            // Tạo connection nếu bạn có lớp DBContext

            ImportDAO dao = new ImportDAO();
            String id = "PRD0409";
            Imports im = dao.getImportByProductCode(id);
            System.out.println(im.getImportID() + " " + im.getCategoryName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
