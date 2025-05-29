package dal;

import models.Feedback;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO extends DBContext {

    public List<Feedback> getAllFeedbacks() {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT * FROM Feedbacks WHERE Status = 1";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Feedback f = new Feedback(
                        rs.getInt("FeedbackID"),
                        rs.getInt("UserID"),
                        rs.getString("Content"),
                        rs.getInt("ProductID"),
                        rs.getTimestamp("CreatedAt"),
                        rs.getInt("Rate"),
                        rs.getInt("Status")
                );
                list.add(f);
            }
        } catch (SQLException e) {
            System.err.println("Error in getAllFeedbacks: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    public List<Feedback> getFeedbackByProductId(int productId) {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT * FROM Feedbacks WHERE ProductID = ? AND Status = 1";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Feedback f = new Feedback(
                            rs.getInt("FeedbackID"),
                            rs.getInt("UserID"),
                            rs.getString("Content"),
                            rs.getInt("ProductID"),
                            rs.getTimestamp("CreatedAt"),
                            rs.getInt("Rate"),
                            rs.getInt("Status")
                    );
                    list.add(f);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error in getFeedbackByProductId: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    public boolean insertFeedback(Feedback f) {
        String sql = "INSERT INTO Feedbacks (UserID, Content, ProductID, CreatedAt, Rate, Status) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, f.getUserID());
            ps.setString(2, f.getContent());
            ps.setInt(3, f.getProductID());
            ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            ps.setInt(5, f.getRate());
            ps.setInt(6, f.getStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error in insertFeedback: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteFeedback(int id) {
        String sql = "UPDATE Feedbacks SET Status = 0 WHERE FeedbackID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error in deleteFeedback: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateFeedback(Feedback f) {
        String sql = "UPDATE Feedbacks SET Content = ?, Rate = ?, Status = ? WHERE FeedbackID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, f.getContent());
            ps.setInt(2, f.getRate());
            ps.setInt(3, f.getStatus());
            ps.setInt(4, f.getFeedbackID());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error in updateFeedback: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public List<Feedback> getFeedbackByUserId(int userId) {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT * FROM Feedbacks WHERE UserID = ? AND Status = 1";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Feedback f = new Feedback(
                            rs.getInt("FeedbackID"),
                            rs.getInt("UserID"),
                            rs.getString("Content"),
                            rs.getInt("ProductID"),
                            rs.getTimestamp("CreatedAt"),
                            rs.getInt("Rate"),
                            rs.getInt("Status")
                    );
                    list.add(f);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error in getFeedbackByUserId: " + e.getMessage());
            e.printStackTrace();
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
                            rs.getInt("ProductID"),
                            rs.getTimestamp("CreatedAt"),
                            rs.getInt("Rate"),
                            rs.getInt("Status")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error in getFeedbackById: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public boolean isValidUserId(int userId) {
        String sql = "SELECT COUNT(*) FROM Users WHERE UserID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error in isValidUserId: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean isValidProductId(int productId) {
        String sql = "SELECT COUNT(*) FROM Products WHERE ProductID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error in isValidProductId: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
