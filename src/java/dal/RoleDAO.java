/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Role;

/**
 *
 * @author PC ASUS
 */
public class RoleDAO extends DBContext {

    public RoleDAO() {
        super(); // Gọi constructor DBContext để khởi tạo connection
    }

    public ArrayList<Role> getRoles() {
        ArrayList<Role> roles = new ArrayList<>();
        String sql = """
                        Select * from Roles
                         """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Role role = new Role(
                        rs.getInt("RoleID"),
                        rs.getString("RoleName")
                );
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            return null;
        }
        return roles;
    }
}
