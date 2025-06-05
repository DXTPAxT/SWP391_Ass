package dal;

import models.Feedback;
import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

public class FeedbackDAO extends DBContext {

    private static final Logger LOGGER = Logger.getLogger(FeedbackDAO.class.getName());

    public List<Feedback> getAllFeedbacks() {
        List<Feedback> feedbackList = new ArrayList<>();
        String sql = "SELECT f.*, u.FullName FROM Feedbacks f JOIN Users u ON f.UserID = u.UserID WHERE f.Status = 1 ORDER BY f.CreatedAt DESC";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Feedback f = extractFeedbackFromResultSet(rs);
                f.setUserName(rs.getString("FullName"));
                feedbackList.add(f);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in getAllFeedbacks: " + e.getMessage(), e);
            throw new RuntimeException("Failed to retrieve feedbacks", e);
        }
        return feedbackList;
    }

    public List<Feedback> getFeedbackByCategoryId(int categoryId) {
        if (categoryId <= 0) {
            throw new IllegalArgumentException("CategoryID must be positive");
        }
        List<Feedback> feedbackList = new ArrayList<>();
        String sql = "SELECT f.*, u.FullName FROM Feedbacks f JOIN Users u ON f.UserID = u.UserID WHERE f.CategoryID = ? AND f.Status = 1 ORDER BY f.CreatedAt DESC";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Feedback f = extractFeedbackFromResultSet(rs);
                    f.setUserName(rs.getString("FullName"));
                    feedbackList.add(f);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in getFeedbackByCategoryId: " + e.getMessage(), e);
            throw new RuntimeException("Failed to retrieve feedbacks for categoryID: " + categoryId, e);
        }
        return feedbackList;
    }

    public boolean insertFeedback(Feedback f) {
        if (f == null) {
            throw new IllegalArgumentException("Feedback cannot be null");
        }
        String sql = "INSERT INTO Feedbacks (UserID, Content, CategoryID, CreatedAt, Rate, Status) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, f.getUserID());
            ps.setString(2, f.getContent());
            ps.setInt(3, f.getCategoryID());
            ps.setTimestamp(4, new Timestamp(f.getCreatedAt() != null ? f.getCreatedAt().getTime() : System.currentTimeMillis()));
            ps.setInt(5, f.getRate());
            ps.setInt(6, f.getStatus());
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

    public boolean deleteFeedbackById(int id) {
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

    public List<Feedback> getFeedbackByUserId(int userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("UserID must be positive");
        }
        List<Feedback> feedbackList = new ArrayList<>();
        String sql = "SELECT f.*, u.FullName FROM Feedbacks f JOIN Users u ON f.UserID = u.UserID WHERE f.UserID = ? AND f.Status = 1 ORDER BY f.CreatedAt DESC";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Feedback f = extractFeedbackFromResultSet(rs);
                    f.setUserName(rs.getString("FullName"));
                    feedbackList.add(f);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in getFeedbackByUserId: " + e.getMessage(), e);
            throw new RuntimeException("Failed to retrieve feedbacks for userID: " + userId, e);
        }
        return feedbackList;
    }

    public Feedback getFeedbackById(int feedbackId) {
        if (feedbackId <= 0) {
            throw new IllegalArgumentException("FeedbackID must be positive");
        }
        String sql = "SELECT f.*, u.FullName FROM Feedbacks f JOIN Users u ON f.UserID = u.UserID WHERE f.FeedbackID = ? AND f.Status = 1";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, feedbackId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Feedback f = extractFeedbackFromResultSet(rs);
                    f.setUserName(rs.getString("FullName"));
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

    private Feedback extractFeedbackFromResultSet(ResultSet rs) throws SQLException {
        Feedback f = new Feedback(
                rs.getInt("FeedbackID"),
                rs.getInt("UserID"),
                rs.getString("Content"),
                rs.getInt("CategoryID"),
                rs.getTimestamp("CreatedAt"),
                rs.getInt("Rate"),
                rs.getInt("Status")
        );
        return f;
    }
}
