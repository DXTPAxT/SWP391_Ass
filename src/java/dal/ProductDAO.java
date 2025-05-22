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

public class ProductDAO extends DBContext{
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
                        rs.getDouble(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getDate(7),
                        rs.getInt(8),
                        rs.getInt(9));
                listProduct.add(p);
            }
        } catch (SQLException ex) {

        }
        return listProduct;
    }
    public static void main(String[] args) {
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
}
 
    
}
