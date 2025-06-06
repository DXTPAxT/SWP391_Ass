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
                    rs.getInt("CategoryID"),
                    rs.getTimestamp("CreatedAt"),
                    rs.getInt("Rate"),
                    rs.getInt("Status")
                );
                list.add(f);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in getAllFeedbacks: " + e.getMessage(), e);
            return list; // Trả về danh sách rỗng thay vì ném ngoại lệ
        }
        return list;
    }

    public List<Feedback> getFeedbackByCategoryId(int categoryId) {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT * FROM Feedbacks WHERE CategoryID = ? ORDER BY CreatedAt DESC";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Feedback f = new Feedback(
                        rs.getInt("FeedbackID"),
                        rs.getInt("UserID"),
                        rs.getString("Content"),
                        rs.getInt("CategoryID"),
                        rs.getTimestamp("CreatedAt"),
                        rs.getInt("Rate"),
                        rs.getInt("Status")
                    );
                    list.add(f);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in getFeedbackByCategoryId for categoryId " + categoryId + ": " + e.getMessage(), e);
            return list; // Trả về danh sách rỗng thay vì ném ngoại lệ
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
                        rs.getInt("CategoryID"),
                        rs.getTimestamp("CreatedAt"),
                        rs.getInt("Rate"),
                        rs.getInt("Status")
                    );
                    list.add(f);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in getFeedbackByUserId for userId " + userId + ": " + e.getMessage(), e);
            return list; // Trả về danh sách rỗng thay vì ném ngoại lệ
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
                        rs.getInt("CategoryID"),
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
        if (!isCategoryValid(f.getCategoryID())) {
            LOGGER.warning("Invalid CategoryID: " + f.getCategoryID());
            return false;
        }
        String sql = "INSERT INTO Feedbacks (UserID, Content, CategoryID, CreatedAt, Rate, Status) VALUES (?, ?, ?, ?, ?, 1)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, f.getUserID());
            ps.setString(2, f.getContent());
            ps.setInt(3, f.getCategoryID());
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
            return false; // Trả về false thay vì ném ngoại lệ
        }
    }

    public boolean updateFeedback(Feedback f) {
        String sql = "UPDATE Feedbacks SET Content = ?, Rate = ?, CreatedAt = ? WHERE FeedbackID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, f.getContent());
            ps.setInt(2, f.getRate());
            ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
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

    public boolean isCategoryValid(int categoryId) {
        String sql = "SELECT COUNT(*) FROM Categories WHERE CategoryID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in isCategoryValid for categoryId " + categoryId + ": " + e.getMessage(), e);
            return false;
        }
        return false;
    }
}