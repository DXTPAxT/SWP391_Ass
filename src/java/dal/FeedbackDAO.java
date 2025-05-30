package dal;

import models.Feedback;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FeedbackDAO extends DBContext {

    private static final Logger LOGGER = Logger.getLogger(FeedbackDAO.class.getName());

    // Lấy tất cả feedback
    public List<Feedback> getAllFeedbacks() {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT * FROM Feedbacks WHERE Status = 1";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Feedback f = extractFeedbackFromResultSet(rs);
                list.add(f);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in getAllFeedbacks: " + e.getMessage(), e);
            throw new RuntimeException("Failed to retrieve feedbacks", e);
        }
        return list;
    }

    // Lấy feedback theo productID
    public List<Feedback> getFeedbackByProductId(int productId) {
        if (productId <= 0) {
            throw new IllegalArgumentException("ProductID must be positive");
        }
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT * FROM Feedbacks WHERE ProductID = ? AND Status = 1";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Feedback f = extractFeedbackFromResultSet(rs);
                    list.add(f);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in getFeedbackByProductId: " + e.getMessage(), e);
            throw new RuntimeException("Failed to retrieve feedbacks for productID: " + productId, e);
        }
        return list;
    }

    // Thêm feedback mới
    public boolean insertFeedback(Feedback f) {
        if (f == null) {
            throw new IllegalArgumentException("Feedback cannot be null");
        }
        String sql = "INSERT INTO Feedbacks (UserID, Content, ProductID, CreatedAt, Rate, Status) VALUES (?, ?, ?, ?, ?, 1)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, f.getUserID());
            ps.setString(2, f.getContent());
            ps.setInt(3, f.getProductID());
            ps.setTimestamp(4, new Timestamp(f.getCreatedAt() != null ? f.getCreatedAt().getTime() : System.currentTimeMillis()));
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
            throw new RuntimeException("Failed to insert feedback", e);
        }
    }

    // Xóa feedback
    public boolean deleteFeedback(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("FeedbackID must be positive");
        }
        String sql = "UPDATE Feedbacks SET Status = 0 WHERE FeedbackID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                LOGGER.info("Feedback with ID " + id + " deleted successfully");
                return true;
            } else {
                LOGGER.warning("No feedback found with ID " + id);
                return false;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in deleteFeedback: " + e.getMessage(), e);
            throw new RuntimeException("Failed to delete feedback with ID: " + id, e);
        }
    }

    // Cập nhật nội dung feedback
    public boolean updateFeedback(Feedback f) {
        if (f == null || f.getFeedbackID() <= 0) {
            throw new IllegalArgumentException("Feedback or FeedbackID is invalid");
        }
        String sql = "UPDATE Feedbacks SET Content = ?, Rate = ? WHERE FeedbackID = ? AND Status = 1";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, f.getContent());
            ps.setInt(2, f.getRate());
            ps.setInt(3, f.getFeedbackID());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                LOGGER.info("Feedback with ID " + f.getFeedbackID() + " updated successfully");
                return true;
            } else {
                LOGGER.warning("No active feedback found with ID " + f.getFeedbackID());
                return false;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in updateFeedback: " + e.getMessage(), e);
            throw new RuntimeException("Failed to update feedback with ID: " + f.getFeedbackID(), e);
        }
    }

    // Lấy feedback theo userID
    public List<Feedback> getFeedbackByUserId(int userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("UserID must be positive");
        }
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT * FROM Feedbacks WHERE UserID = ? AND Status = 1";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Feedback f = extractFeedbackFromResultSet(rs);
                    list.add(f);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in getFeedbackByUserId: " + e.getMessage(), e);
            throw new RuntimeException("Failed to retrieve feedbacks for userID: " + userId, e);
        }
        return list;
    }

    // Lấy một feedback theo ID
    public Feedback getFeedbackById(int feedbackId) {
        if (feedbackId <= 0) {
            throw new IllegalArgumentException("FeedbackID must be positive");
        }
        String sql = "SELECT * FROM Feedbacks WHERE FeedbackID = ? AND Status = 1";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, feedbackId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Feedback f = extractFeedbackFromResultSet(rs);
                    LOGGER.info("Feedback with ID " + feedbackId + " retrieved successfully");
                    return f;
                } else {
                    LOGGER.warning("No active feedback found with ID " + feedbackId);
                    return null;
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in getFeedbackById: " + e.getMessage(), e);
            throw new RuntimeException("Failed to retrieve feedback with ID: " + feedbackId, e);
        }
    }

    // Helper method to extract Feedback object from ResultSet
    private Feedback extractFeedbackFromResultSet(ResultSet rs) throws SQLException {
        Feedback f = new Feedback(
                rs.getInt("FeedbackID"),
                rs.getInt("UserID"),
                rs.getString("Content"),
                rs.getInt("ProductID"),
                rs.getTimestamp("CreatedAt"),
                rs.getInt("Rate"),
                rs.getInt("Status")
        );
        return f;
    }
}
