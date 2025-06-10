package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Feedback;

public class FeedbackDAO extends DBContext {

    private static final Logger LOGGER = Logger.getLogger(FeedbackDAO.class.getName());

    public List<Feedback> getAllFeedbacks() {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT * FROM Feedbacks ORDER BY CreatedAt DESC";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Feedback f = new Feedback(
                    rs.getInt("FeedbackID"),
                    rs.getInt("UserID"),
                    rs.getString("Content"),
                    rs.getInt("OrderItemID"),
                    rs.getTimestamp("CreatedAt"),
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
                        rs.getTimestamp("CreatedAt"),
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

    public List<Feedback> getFeedbackByUserId(int userId) {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT * FROM Feedbacks WHERE UserID = ? ORDER BY CreatedAt DESC";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Feedback f = new Feedback(
                        rs.getInt("FeedbackID"),
                        rs.getInt("UserID"),
                        rs.getString("Content"),
                        rs.getInt("OrderItemID"),
                        rs.getTimestamp("CreatedAt"),
                        rs.getInt("Rate"),
                        rs.getInt("Status")
                    );
                    list.add(f);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in getFeedbackByUserId for userId " + userId + ": " + e.getMessage(), e);
            return list;
        }
        return list;
    }

    public Feedback getFeedbackById(int feedbackId) {
        String sql = "SELECT * FROM Feedbacks WHERE FeedbackID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, feedbackId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Feedback(
                        rs.getInt("FeedbackID"),
                        rs.getInt("UserID"),
                        rs.getString("Content"),
                        rs.getInt("OrderItemID"),
                        rs.getTimestamp("CreatedAt"),
                        rs.getInt("Rate"),
                        rs.getInt("Status")
                    );
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in getFeedbackById for feedbackId " + feedbackId + ": " + e.getMessage(), e);
        }
        return null;
    }

    public boolean insertFeedback(Feedback f) {
        if (f == null) {
            LOGGER.warning("Feedback object is null");
            return false;
        }
        if (!isOrderItemValid(f.getOrderItemID())) {
            LOGGER.warning("Invalid OrderItemID: " + f.getOrderItemID());
            return false;
        }
        String sql = "INSERT INTO Feedbacks (UserID, Content, OrderItemID, CreatedAt, Rate, Status) VALUES (?, ?, ?, ?, ?, 1)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, f.getUserID());
            ps.setString(2, f.getContent());
            ps.setInt(3, f.getOrderItemID());
            // Chuyển sang lưu String cho createdAt
            ps.setString(4, f.getCreatedAt() != null ? f.getCreatedAt() : "2025-06-10 00:00:00");
            ps.setInt(5, f.getRate());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                LOGGER.info("Feedback inserted successfully for userID: " + f.getUserID());
                return true;
            } else {
                LOGGER.warning("Failed to insert feedback for userID: " + f.getUserID());
                return false;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in insertFeedback: " + e.getMessage(), e);
            return false;
        }
    }

    public boolean updateFeedback(Feedback f) {
        String sql = "UPDATE Feedbacks SET Content = ?, Rate = ?, CreatedAt = ? WHERE FeedbackID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, f.getContent());
            ps.setInt(2, f.getRate());
            ps.setString(3, f.getCreatedAt() != null ? f.getCreatedAt() : "2025-06-10 00:00:00");
            ps.setInt(4, f.getFeedbackID());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in updateFeedback: " + e.getMessage(), e);
            return false;
        }
    }

    public boolean deleteFeedback(int feedbackId) {
        String sql = "DELETE FROM Feedbacks WHERE FeedbackID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, feedbackId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in deleteFeedback: " + e.getMessage(), e);
            return false;
        }
    }

    public boolean isOrderItemValid(int orderItemId) {
        String sql = "SELECT COUNT(*) FROM OrderItems WHERE OrderItemID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, orderItemId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in isOrderItemValid for orderItemId " + orderItemId + ": " + e.getMessage(), e);
            return false;
        }
        return false;
    }

    public List<Feedback> getFeedbackByCategoryId(int categoryId) {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT f.*, u.Fullname FROM Feedbacks f " +
                     "JOIN OrderItems oi ON f.OrderItemID = oi.OrderItemID " +
                     "JOIN Users u ON f.UserID = u.UserID " +
                     "WHERE oi.CategoryID = ? ORDER BY f.CreatedAt DESC";
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
}