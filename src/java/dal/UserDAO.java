/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.User;

/**
 *
 * @author PC ASUS
 */
public class UserDAO extends DBContext {

    public UserDAO() {
        super(); // Gọi constructor DBContext để khởi tạo connection
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        String sql = """
                        Select * from Users
                         """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(
                        rs.getInt("UserID"),
                        rs.getInt("RoleID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Address"),
                        rs.getString("PasswordHash"),
                        rs.getString("CreatedAt"),
                        rs.getInt("Status")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            return null;
        }
        return users;
    }

    public boolean isEmailExist(String email) {
        User user = null;
        String sql = """
                        Select * from Users
                        Where Email = ?
                         """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(
                        rs.getInt("UserID"),
                        rs.getInt("RoleID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Address"),
                        rs.getString("PasswordHash"),
                        rs.getString("CreatedAt"),
                        rs.getInt("Status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            return false;
        }
        return user != null;
    }

    public boolean isPhoneNumberExisted(String phoneNumber) {
        User user = null;
        String sql = """
                        Select * from Users
                        Where phoneNumber = ?
                         """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, phoneNumber);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(
                        rs.getInt("UserID"),
                        rs.getInt("RoleID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Address"),
                        rs.getString("PasswordHash"),
                        rs.getString("CreatedAt"),
                        rs.getInt("Status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            return false;
        }
        return user != null;
    }

    public User getUserByEmailAndPassword(String email, String password) {
        User user = null;
        String sql = """
                        Select * from Users
                        Where Email = ?
                        and  PasswordHash = ?
                         """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(
                        rs.getInt("UserID"),
                        rs.getInt("RoleID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Address"),
                        rs.getString("PasswordHash"),
                        rs.getString("CreatedAt"),
                        rs.getInt("Status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            return null;
        }
        return user;
    }

    public boolean createNewUser(String email, String fullName, String address, String phoneNumber, String password, int roleID) {
        User user = null;
        int n = 0;
        String sql = """
                        Insert into Users(Email, FullName, Address, PhoneNumber, PasswordHash, RoleID, Status) 
                        values(?,?,?,?,?,?,1)
                         """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, fullName);
            ps.setString(3, address);
            ps.setString(4, phoneNumber);
            ps.setString(5, password);
            ps.setInt(6, roleID);
            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            return false;
        }
        return n > 0;
    }

    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        System.out.println(dao.getUsers());
    }

}
