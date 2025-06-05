package dal;

import models.Feedback;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
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
            LOGGER.log(Level.INFO, "Retrieved {0} feedbacks successfully", feedbackList.size());
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in getAllFeedbacks: " + e.getMessage(), e);
            throw new RuntimeException("Failed to retrieve feedbacks", e);
        }
        return feedbackList;
    }

    public List<Feedback> getFeedbackByCategoryId(int categoryId) {
        if (categoryId <= 0) {
            LOGGER.log(Level.WARNING, "Invalid CategoryID: {0}", categoryId);
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
                LOGGER.log(Level.INFO, "Retrieved {0} feedbacks for CategoryID: {1}", new Object[]{feedbackList.size(), categoryId});
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in getFeedbackByCategoryId: " + e.getMessage(), e);
            throw new RuntimeException("Failed to retrieve feedbacks for categoryID: " + categoryId, e);
        }
        return feedbackList;
    }

    public boolean insertFeedback(Feedback f) {
        if (f == null) {
            LOGGER.log(Level.WARNING, "Feedback object is null");
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
                LOGGER.log(Level.INFO, "Feedback inserted successfully for userID: {0}", f.getUserID());
                return true;
            } else {
                LOGGER.log(Level.WARNING, "Failed to insert feedback for userID: {0}", f.getUserID());
                return false;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in insertFeedback: " + e.getMessage(), e);
            throw new RuntimeException("Failed to insert feedback", e);
        }
    }

    public boolean deleteFeedbackById(int id) {
        if (id <= 0) {
            LOGGER.log(Level.WARNING, "Invalid FeedbackID: {0}", id);
            throw new IllegalArgumentException("FeedbackID must be positive");
        }
        String sql = "UPDATE Feedbacks SET Status = 0 WHERE FeedbackID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                LOGGER.log(Level.INFO, "Feedback with ID {0} deleted successfully", id);
                return true;
            } else {
                LOGGER.log(Level.WARNING, "No feedback found with ID {0}", id);
                return false;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in deleteFeedbackById: " + e.getMessage(), e);
            throw new RuntimeException("Failed to delete feedback with ID: " + id, e);
        }
    }

    public boolean updateFeedback(Feedback f) {
        if (f == null || f.getFeedbackID() <= 0) {
            LOGGER.log(Level.WARNING, "Invalid Feedback or FeedbackID: {0}", f == null ? "null" : f.getFeedbackID());
            throw new IllegalArgumentException("Feedback or FeedbackID is invalid");
        }
        String sql = "UPDATE Feedbacks SET Content = ?, Rate = ? WHERE FeedbackID = ? AND Status = 1";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, f.getContent());
            ps.setInt(2, f.getRate());
            ps.setInt(3, f.getFeedbackID());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                LOGGER.log(Level.INFO, "Feedback with ID {0} updated successfully", f.getFeedbackID());
                return true;
            } else {
                LOGGER.log(Level.WARNING, "No active feedback found with ID {0}", f.getFeedbackID());
                return false;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in updateFeedback: " + e.getMessage(), e);
            throw new RuntimeException("Failed to update feedback with ID: " + f.getFeedbackID(), e);
        }
    }

    public List<Feedback> getFeedbackByUserId(int userId) {
        if (userId <= 0) {
            LOGGER.log(Level.WARNING, "Invalid UserID: {0}", userId);
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
                LOGGER.log(Level.INFO, "Retrieved {0} feedbacks for UserID: {1}", new Object[]{feedbackList.size(), userId});
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in getFeedbackByUserId: " + e.getMessage(), e);
            throw new RuntimeException("Failed to retrieve feedbacks for userID: " + userId, e);
        }
        return feedbackList;
    }

    public Feedback getFeedbackById(int feedbackId) {
        if (feedbackId <= 0) {
            LOGGER.log(Level.WARNING, "Invalid FeedbackID: {0}", feedbackId);
            throw new IllegalArgumentException("FeedbackID must be positive");
        }
        String sql = "SELECT f.*, u.FullName FROM Feedbacks f JOIN Users u ON f.UserID = u.UserID WHERE f.FeedbackID = ? AND f.Status = 1";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, feedbackId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Feedback f = extractFeedbackFromResultSet(rs);
                    f.setUserName(rs.getString("FullName"));
                    LOGGER.log(Level.INFO, "Feedback with ID {0} retrieved successfully", feedbackId);
                    return f;
                } else {
                    LOGGER.log(Level.WARNING, "No active feedback found with ID {0}", feedbackId);
                    return null;
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in getFeedbackById: " + e.getMessage(), e);
            throw new RuntimeException("Failed to retrieve feedback with ID: " + feedbackId, e);
        }
    }

    private Feedback extractFeedbackFromResultSet(ResultSet rs) throws SQLException {
        Feedback f = new Feedback();
        f.setFeedbackID(rs.getInt("FeedbackID"));
        f.setUserID(rs.getInt("UserID"));
        f.setContent(rs.getString("Content"));
        f.setCategoryID(rs.getInt("CategoryID"));
        f.setCreatedAt(rs.getTimestamp("CreatedAt"));
        f.setRate(rs.getInt("Rate"));
        f.setStatus(rs.getInt("Status"));
        return f;
    }
}
