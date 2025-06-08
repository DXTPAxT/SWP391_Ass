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
            // Tạm thời không hash password khi đăng nhập, dùng password thường
            // ps.setString(2, utils.PasswordUtils.hashPassword(password));
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
        // Check duplicate email or phone before insert (defensive, though servlet already checks)
        if (isEmailExist(email) || isPhoneNumberExisted(phoneNumber)) {
            return false;
        }
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
            ps.setString(5, password); // password đã hash ở servlet
            ps.setInt(6, roleID);
            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            return false;
        }
        return n > 0;
    }

    public boolean updateUser(int userID, String fullName, String email,
            String phoneNumber, String address, int status) {
        String sql = """
        UPDATE Users
        SET
            FullName = ?,
            Email = ?, 
            PhoneNumber = ?, 
            Address = ?, 
            Status = ?
        WHERE UserID = ?
    """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, fullName);
            ps.setString(2, email);
            ps.setString(3, phoneNumber);
            ps.setString(4, address);
            ps.setInt(5, status);
            ps.setInt(6, userID);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        UserDAO dao = new UserDAO();

        // Dữ liệu test giả định
        int userID = 1; // ID của user muốn cập nhật, phải tồn tại trong DB
        String fullName = "Nguyen Van Test";
        String email = "testuser@example.com";
        String phoneNumber = "0909123456";
        String address = "123 Test Street, HCM City";
        int status = 1;

        boolean result = dao.updateUser(userID, fullName, email, phoneNumber, address, status);

        if (result) {
            System.out.println("✅ Cập nhật user thành công.");
        } else {
            System.out.println("❌ Cập nhật user thất bại hoặc không có dòng nào được thay đổi.");
        }
    }

    public User getUserByID(int userID) {
        String sql = "SELECT * FROM Users WHERE UserID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
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
        }
        return null;
    }

    public boolean resetPassword(int userID, String newPasswordHash) {
        String sql = "UPDATE Users SET PasswordHash = ? WHERE UserID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newPasswordHash);
            ps.setInt(2, userID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean toggleStatus(int userID) {
        String sql = """
        UPDATE Users
        SET Status = 
            CASE 
                WHEN Status = 1 THEN 0 
                ELSE 1 
            END
        WHERE UserID = ?
    """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
