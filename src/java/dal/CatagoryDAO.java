package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Catagory;

public class CatagoryDAO extends DBContext {
   

    public List<Catagory> getComponents() {
        List<Catagory> list = new ArrayList<>();
        String sql = "SELECT ComponentID, ComponentName FROM Components;";

        try (PreparedStatement st = connection.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            
            while (rs.next()) {
                Catagory c = new Catagory(
                    rs.getInt("ComponentID"),
                    rs.getString("ComponentName")
                 
                );
                list.add(c);
            }
           
        } catch (SQLException e) {
       
        }

        return list;
    }
    public Catagory getComponentByName(String name) {
    String sql = "SELECT * FROM Components WHERE ComponentName = ?";
    try (PreparedStatement st = connection.prepareStatement(sql)) {
        st.setString(1, name);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return new Catagory(
                rs.getInt("ComponentID"),
                rs.getString("ComponentName")
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

      public static void main(String[] args) {
        CatagoryDAO dao = new CatagoryDAO();
        List<Catagory> components = dao.getComponents();

        if (components.isEmpty()) {
            System.out.println("Không có dữ liệu trong bảng Components.");
        } else {
            for (Catagory c : components) {
                System.out.println(c);
            }
        }
    }
}