/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.Vector;
import models.Products;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAO extends DBContext {

    public Vector<Products> getAllProduct(String sql) {
        Vector<Products> listProduct = new Vector<>();
        PreparedStatement ptm;
        try {
            ptm = connection.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                Products p = new Products(rs.getInt(1),
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

        }
        return listProduct;
    }

    public Products getProductById(int id) {
        Products p = null;
        try {
            String sql = "SELECT * FROM Products WHERE ProductID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                p = new Products();
                p.setProductID(rs.getInt("ProductID")); // viết đúng tên cột
                p.setName(rs.getString("Name"));
                p.setDescription(rs.getString("Description"));
                p.setBrand(rs.getString("Brand"));
                p.setPrice(rs.getDouble("Price"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setWarrantyPeriod(rs.getInt("WarrantyPeriod"));
                p.setCreatedAt(rs.getDate("CreatedAt")); // nếu bạn dùng LocalDateTime
                p.setCategoryID(rs.getInt("CategoryID"));
                p.setStatus(rs.getInt("Status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    private Products mapProduct(ResultSet rs) throws SQLException {
        return new Products(
                rs.getInt("ProductID"),
                rs.getString("Name"),
                rs.getString("Description"),
                rs.getString("Brand"),
                rs.getDouble("Price"),
                rs.getInt("Quantity"),
                rs.getInt("WarrantyPeriod"),
                rs.getDate("CreatAt"),
                rs.getInt("CategoryID"),
                rs.getInt("Status")
        );
    }
    /*public static void main(String[] args) {
    ProductDAO dao = new ProductDAO();
    String sql = "SELECT * FROM Products"; // Hoặc điều chỉnh theo bảng thật của bạn
    Vector<Products> list = dao.getAllProduct(sql);
    
    if (list.isEmpty()) {
        System.out.println("Không có sản phẩm nào được tải từ CSDL.");
    } else {
        for (Products p : list) {
            System.out.println("ID: " + p.getProductID() + 
                               ", Tên: " + p.getName() + 
                               ", Giá: " + p.getPrice());
        }
    }
}*/

}
