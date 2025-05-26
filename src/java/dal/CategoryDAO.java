/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Categories;
import models.Products;

/**
 *
 * @author PC
 */
public class CategoryDAO extends DBContext{
    public List<Categories> getAllCategoryNames() {
        List<Categories> categoryList = new ArrayList<>();
        String sql = "SELECT CategoryName FROM Categories";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Categories cat = new Categories();
                cat.setCatagoryName(rs.getString("CategoryName"));
                categoryList.add(cat);
            }

        } catch (SQLException e) {
        }

        return categoryList;
    }
 public List<Products> GetCata2(int start, int pageSize) {
    List<Products> list = new ArrayList<>();
    String sql = "SELECT * FROM Products WHERE Status = 2 AND CategoryID = 1 ORDER BY ProductID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, start);
        ps.setInt(2, pageSize);
        ResultSet rs = ps.executeQuery();

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
                rs.getInt(10)
            );
            list.add(p);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}
 public List<Products> GetCata3(int start, int pageSize) {
    List<Products> list = new ArrayList<>();
    String sql = "SELECT * FROM Products WHERE Status = 2 AND CategoryID = 2 ORDER BY ProductID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, start);
        ps.setInt(2, pageSize);
        ResultSet rs = ps.executeQuery();

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
                rs.getInt(10)
            );
            list.add(p);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


    public int countTotalProducts() {
        String sql = "SELECT COUNT(*) FROM Products WHERE Status = 2 AND CategoryID = 1";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
   /* public List<Products> getTypeOfProduct( String Brand){
        List<Products>list = new ArrayList<>();
        String sql = "Select * From Products Where Cat "
    }
    
   */ 
    
    
    
    
    
    
}
