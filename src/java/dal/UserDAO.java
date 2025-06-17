/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;
import models.Role;
import models.CustomerInfo;
import models.StaffInfo;

/**
 * Data Access Object for User-related database operations
 */
public class UserDAO extends DBContext {

    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());
    private final CustomerInfoDAO customerInfoDAO;
    private final StaffInfoDAO staffInfoDAO;
    private final RoleDAO roleDAO;

    public UserDAO() {
        customerInfoDAO = new CustomerInfoDAO();
        staffInfoDAO = new StaffInfoDAO();
        roleDAO = new RoleDAO();
    }

    // Get all users
    public ArrayList<User> getUsers() {
        ArrayList<User> list = new ArrayList<>();
        String sql = "SELECT UserID, RoleID, FullName, Email, PhoneNumber, PasswordHash, CreatedAt, Status FROM Users";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapUser(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting users list", e);
        }
        return list;
    }

    // Get all staff members
    public List<User> getAllStaff() {
        ArrayList<User> staffList = new ArrayList<>();
        String sql = "SELECT UserID, RoleID, FullName, Email, PhoneNumber, PasswordHash, CreatedAt, Status "
                + "FROM Users WHERE RoleID = 2"; // RoleID 2 for staff

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            LOGGER.info("Executing query for getAllStaff()");
            while (rs.next()) {
                User user = mapUser(rs);
                LOGGER.info("Mapped staff - UserID: " + user.getUserId()
                        + ", StaffInfo: " + (user.getStaffInfo() != null ? "present" : "null"));
                staffList.add(user);
            }
            LOGGER.info("Total staff found: " + staffList.size());
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting staff list", e);
        }
        return staffList;
    }

    // Get all customers
    public List<User> getAllCustomers() {
        ArrayList<User> customerList = new ArrayList<>();
        String sql = "SELECT UserID, RoleID, FullName, Email, PhoneNumber, PasswordHash, CreatedAt, Status "
                + "FROM Users WHERE RoleID = 3"; // RoleID 3 for customer

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            LOGGER.info("Executing query for getAllCustomers()");
            while (rs.next()) {
                User user = mapUser(rs);
                LOGGER.info("Mapped customer - UserID: " + user.getUserId()
                        + ", CustomerInfo: " + (user.getCustomerInfo() != null ? "present" : "null"));
                customerList.add(user);
            }
            LOGGER.info("Total customers found: " + customerList.size());
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting customer list", e);
        }
        return customerList;
    }

    // Get user by ID
    public User getUserByID(int userID) {
        String sql = "SELECT UserID, RoleID, FullName, Email, PhoneNumber, PasswordHash, CreatedAt, Status "
                + "FROM Users WHERE UserID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapUser(rs);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting user by ID: " + userID, e);
        }
        return null;
    }

    public boolean deleteUser(int userId) {
        String sql = "DELETE FROM Users WHERE UserID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, String.format("Error deleting user: %d", userId), e);
            return false;
        }
    }

    public boolean createNewUser(String email, String fullName, String phoneNumber, String hashedPassword, int roleId) {
        String sql = "INSERT INTO Users (Email, FullName, PhoneNumber, PasswordHash, RoleID, Status) VALUES (?, ?, ?, ?, ?, 1)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, email);
            ps.setString(2, fullName);
            ps.setString(3, phoneNumber);
            ps.setString(4, hashedPassword);
            ps.setInt(5, roleId);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, "Error rolling back transaction", ex);
            }
            LOGGER.log(Level.SEVERE, "Error creating new user", e);
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error resetting auto-commit", e);
            }
        }
    }

    // Update user with role-specific information
    public boolean updateUser(int userId, String fullName, String email, String phoneNumber, int status, String address, String StartedDate, String EndDate) throws SQLException {
        connection.setAutoCommit(false);
        try {
            // Update Users table
            String sql = "UPDATE Users SET FullName = ?, Email = ?, PhoneNumber = ?, Status = ? WHERE UserID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, fullName);
            ps.setString(2, email);
            ps.setString(3, phoneNumber);
            ps.setInt(4, status);
            ps.setInt(5, userId);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                connection.rollback();
                return false;
            }

            // Get user to check role
            User user = getUserByID(userId);
            if (user != null) {
                boolean infoUpdated = true;

                // Update role-specific information
                if (user.isCustomer() && address != null) {
                    if (user.getCustomerInfo() == null) {
                        infoUpdated = customerInfoDAO.createCustomerInfo(userId, address);
                    } else {
                        infoUpdated = customerInfoDAO.updateCustomerInfo(userId, address);
                    }
                } else {
                    if (user.getStaffInfo()== null) {
                        infoUpdated = staffInfoDAO.createStaffInfo(userId, StartedDate, EndDate);
                    } else {
                        infoUpdated = staffInfoDAO.updateStaffInfo(userId, StartedDate, EndDate);
                    }
                }

                if (!infoUpdated) {
                    connection.rollback();
                    return false;
                }

                connection.commit();
                return true;
            }

            connection.rollback();
            return false;

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, "Error rolling back transaction", ex);
            }
            LOGGER.log(Level.SEVERE, "Error updating user", e);
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error resetting auto-commit", e);
            }
        }
    }

    // Get user by email and password for login
    public User getUserByEmailAndPassword(String email, String hashedPassword) {
        String sql = "SELECT UserID, RoleID, FullName, Email, PhoneNumber, PasswordHash, CreatedAt, Status "
                + "FROM Users WHERE Email = ? AND PasswordHash = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, hashedPassword);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapUser(rs);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting user by email and password", e);
        }
        return null;
    }

    // Check if email exists
    public boolean isEmailExist(String email) {
        String sql = "SELECT COUNT(*) FROM Users WHERE Email = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error checking email existence", e);
        }
        return false;
    }

    // Phone number existence check
    public boolean isPhoneNumberExisted(String phoneNumber) {
        String sql = "SELECT COUNT(*) FROM Users WHERE PhoneNumber = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, phoneNumber);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error checking phone number existence", e);
        }
        return false;
    }

    // Reset user's password
    public boolean resetPassword(int userId, String newPasswordHash) {
        String sql = "UPDATE Users SET PasswordHash = ? WHERE UserID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newPasswordHash);
            ps.setInt(2, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error resetting password", e);
            return false;
        }
    }

    // Toggle user's status (activate/deactivate)
    public boolean toggleStatus(int userId) {
        String sql = "UPDATE Users SET Status = CASE WHEN Status = 1 THEN 0 ELSE 1 END WHERE UserID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error toggling user status", e);
            return false;
        }
    }

    // Get user by email
    public User getUserByEmail(String email) {
        String sql = "SELECT UserID, RoleID, FullName, Email, PhoneNumber, PasswordHash, CreatedAt, Status "
                + "FROM Users WHERE Email = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapUser(rs);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, String.format("Error getting user by email: %s", email), e);
        }
        return null;
    }

    public List<User> getUsersByRole(int roleId) throws Exception {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT UserID, RoleID, FullName, Email, PhoneNumber, PasswordHash, CreatedAt, Status "
                + "FROM Users WHERE RoleID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, roleId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    userList.add(mapUser(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting users by role: {0}", roleId);
            throw new Exception("Failed to get users by role", e);
        }
        return userList;
    }    // Helper method to create User object from ResultSet

    private User mapUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("UserID"));

        // Get Role object
        int roleId = rs.getInt("RoleID");
        Role role = roleDAO.getRoleById(roleId);
        user.setRole(role);

        user.setFullname(rs.getString("FullName"));
        user.setEmail(rs.getString("Email"));
        user.setPhoneNumber(rs.getString("PhoneNumber"));
        user.setPassword(rs.getString("PasswordHash"));
        user.setCreatedAt(rs.getString("CreatedAt"));
        user.setStatus(rs.getInt("Status"));

        LOGGER.info("Mapping user - UserID: " + user.getUserId() + ", Role: " + role.getRoleName());

        // Load additional info based on role
        if (role.getRoleID() == 3) { // Customer
            CustomerInfo customerInfo = customerInfoDAO.getCustomerInfoByUserId(user.getUserId());
            user.setCustomerInfo(customerInfo);
            LOGGER.info("CustomerInfo loaded for customer");
        } else if (role.getRoleID() == 2) { // Staff
            StaffInfo staffInfo = staffInfoDAO.getStaffInfoByUserId(user.getUserId());
            user.setStaffInfo(staffInfo);
            LOGGER.info("StaffInfo loaded for staff");
        }

        return user;
    }

    public boolean isConnected() {
        try {
            if (connection == null || connection.isClosed()) {
                LOGGER.severe("Database connection is null or closed");
                return false;
            }
            // Test connection with a simple query
            try (PreparedStatement ps = connection.prepareStatement("SELECT 1"); ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    LOGGER.info("Database connection test successful");
                    return true;
                }
            }
            LOGGER.severe("Database connection test failed");
            return false;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error testing database connection", e);
            return false;
        }
    }
}
