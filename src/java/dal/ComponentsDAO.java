package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Components;

public class ComponentsDAO extends DBContext {
   

    public List<Components> getComponents() {
        List<Components> list = new ArrayList<>();
        String sql = "SELECT ComponentID, ComponentName FROM Components;";

        try (PreparedStatement st = connection.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            
            while (rs.next()) {
                Components c = new Components(
                    rs.getInt("ComponentID"),
                    rs.getString("ComponentName")
                 
                );
                list.add(c);
            }
           
        } catch (SQLException e) {
       
        }

        return list;
    }
    public Components getComponentByName(String name) {
    String sql = "SELECT * FROM Components WHERE ComponentName = ?";
    try (PreparedStatement st = connection.prepareStatement(sql)) {
        st.setString(1, name);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return new Components(
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
        ComponentsDAO dao = new ComponentsDAO();
        List<Components> components = dao.getComponents();

        if (components.isEmpty()) {
            System.out.println("Không có dữ liệu trong bảng Components.");
        } else {
            for (Components c : components) {
                System.out.println(c);
            }
        }
    }
}