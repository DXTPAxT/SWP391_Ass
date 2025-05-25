/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Catagorys;

/**
 *
 * @author PC
 */
public class CategoryDAO extends DBContext{
    public List<Catagorys> getAllCategoryNames() {
        List<Catagorys> categoryList = new ArrayList<>();
        String sql = "SELECT CategoryName FROM Categories";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Catagorys cat = new Catagorys();
                cat.setCatagoryName(rs.getString("CategoryName"));
                categoryList.add(cat);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return categoryList;
    }
   
    
}
