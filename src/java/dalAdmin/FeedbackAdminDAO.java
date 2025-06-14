/*
 * Click nb://source://SystemFileSystemAdmin/Templates/LicensesAdmin/license-default.txt to change this license
 * Click nb://source://SystemFileSystemAdmin/Templates/Classes/Class.java to edit this template
 */
package dalAdmin;

import models.Feedback;
import models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FeedbackAdminDAO {

    private DBAdminContext dbContext;

    public FeedbackAdminDAO() {
        dbContext = new DBAdminContext();
    }

    public List<Feedback> getAllFeedbacks() {
        List<Feedback> feedbacks = new ArrayList<>();
        String sql = "SELECT f.feedbackID, f.userID, f.content, f.orderItemID, f.createdAt, f.rate, f.status, u.fullName "
                + "FROM Feedbacks f LEFT JOIN Users u ON f.userID = u.userID WHERE f.status = 1";
        try (Connection conn = dbContext.connection; PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
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
                feedback.setUser(new User(rs.getString("fullName")));
                feedbacks.add(feedback);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackAdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return feedbacks;
    }

    public Feedback getFeedbackById(int feedbackID) {
        String sql = "SELECT f.feedbackID, f.userID, f.content, f.orderItemID, f.createdAt, f.rate, f.status, u.fullName "
                + "FROM Feedbacks f LEFT JOIN Users u ON f.userID = u.userID WHERE f.feedbackID = ? AND f.status = 1";
        try (Connection conn = dbContext.connection; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, feedbackID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Feedback feedback = new Feedback(
                            rs.getInt("feedbackID"),
                            rs.getInt("userID"),
                            rs.getString("content"),
                            rs.getInt("orderItemID"),
                            rs.getString("createdAt"), // Sử dụng String
                            rs.getInt("rate"),
                            rs.getInt("status")
                    );
                    feedback.setUser(new User(rs.getString("fullName")));
                    return feedback;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackAdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean insertFeedback(Feedback feedback) {
        String sql = "INSERT INTO Feedbacks (userID, content, orderItemID, createdAt, rate, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbContext.connection; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, feedback.getUserID());
            stmt.setString(2, feedback.getContent());
            stmt.setInt(3, feedback.getOrderItemID());
            stmt.setString(4, feedback.getCreatedAt()); // Sử dụng String
            stmt.setInt(5, feedback.getRate());
            stmt.setInt(6, feedback.getStatus());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackAdminDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean updateFeedback(int feedbackID, Feedback feedback) {
        String sql = "UPDATE Feedbacks SET content = ?, rate = ?, status = ?, createdAt = ? WHERE feedbackID = ? AND status = 1";
        try (Connection conn = dbContext.connection; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, feedback.getContent());
            stmt.setInt(2, feedback.getRate());
            stmt.setInt(3, feedback.getStatus());
            stmt.setString(4, feedback.getCreatedAt()); // Sử dụng String
            stmt.setInt(5, feedbackID);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackAdminDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean deleteFeedback(int feedbackID) {
        String sql = "UPDATE Feedbacks SET status = 0 WHERE feedbackID = ? AND status = 1";
        try (Connection conn = dbContext.connection; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, feedbackID);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackAdminDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
