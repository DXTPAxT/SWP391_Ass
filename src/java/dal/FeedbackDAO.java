/*
 * Click nb://source://SystemFileSystemAdmin/Templates/LicensesAdmin/license-default.txt to change this license
 * Click nb://source://SystemFileSystemAdmin/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import models.Feedback;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FeedbackDAO extends DBContext {

    private static final Logger LOGGER = Logger.getLogger(FeedbackDAO.class.getName());

    public List<Feedback> getAllFeedbacks() {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT * FROM Feedbacks ORDER BY CreatedAt DESC";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Feedback f = new Feedback(
                        rs.getInt("FeedbackID"),
                        rs.getInt("UserID"),
                        rs.getString("Content"),
                        rs.getInt("OrderItemID"),
                        rs.getString("CreatedAt"),
                        rs.getInt("Rate"),
                        rs.getInt("Status")
                );
                list.add(f);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in getAllFeedbacks: " + e.getMessage(), e);
            return list;
        }
        return list;
    }

    public List<Feedback> getFeedbackByOrderItemId(int orderItemId) {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT * FROM Feedbacks WHERE OrderItemID = ? ORDER BY CreatedAt DESC";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, orderItemId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Feedback f = new Feedback(
                            rs.getInt("FeedbackID"),
                            rs.getInt("UserID"),
                            rs.getString("Content"),
                            rs.getInt("OrderItemID"),
                            rs.getString("CreatedAt"),
                            rs.getInt("Rate"),
                            rs.getInt("Status")
                    );
                    list.add(f);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in getFeedbackByOrderItemId for orderItemId " + orderItemId + ": " + e.getMessage(), e);
            return list;
        }
        return list;
    }

    public List<Feedback> getFeedbacksByUser(int userID) {
        List<Feedback> userFeedbacks = new ArrayList<>();
        String sql = "SELECT feedbackID, userID, content, orderItemID, createdAt, rate, status "
                + "FROM Feedbacks WHERE userID = ? AND status = 1";
        try (Connection conn = connection; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Feedback feedback = new Feedback(
                            rs.getInt("feedbackID"),
                            rs.getInt("userID"),
                            rs.getString("content"),
                            rs.getInt("orderItemID"),
                            rs.getString("createdAt"), // Sử dụng String
                            rs.getInt("rate"),
                            rs.getInt("status")
                    );
                    userFeedbacks.add(feedback);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userFeedbacks;
    }

    public List<Feedback> getFeedbackByCategoryId(int categoryId) {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT f.*, u.Fullname FROM Feedbacks f "
                + "JOIN OrderItems oi ON f.OrderItemID = oi.OrderItemID "
                + "JOIN Users u ON f.UserID = u.UserID "
                + "WHERE oi.CategoryID = ? ORDER BY f.CreatedAt DESC";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String createdAt = rs.getString("CreatedAt");
                    Feedback f = new Feedback(
                            rs.getInt("FeedbackID"),
                            rs.getInt("UserID"),
                            rs.getString("Content"),
                            rs.getInt("OrderItemID"),
                            createdAt,
                            rs.getInt("Rate"),
                            rs.getInt("Status")
                    );
                    // Set user full name
                    models.User user = new models.User(rs.getString("Fullname"));
                    f.setUser(user);
                    list.add(f);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in getFeedbackByCategoryId for categoryId " + categoryId + ": " + e.getMessage(), e);
            return list;
        }
        return list;
    }

    public boolean insertFeedback(Feedback feedback) {
        String sql = "INSERT INTO Feedbacks (userID, content, orderItemID, createdAt, rate, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = connection; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, feedback.getUserID());
            stmt.setString(2, feedback.getContent());
            stmt.setInt(3, feedback.getOrderItemID());
            stmt.setString(4, feedback.getCreatedAt()); // Sử dụng String
            stmt.setInt(5, feedback.getRate());
            stmt.setInt(6, feedback.getStatus());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
