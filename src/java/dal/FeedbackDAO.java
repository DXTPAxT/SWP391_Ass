/*
 * Click nb://source://SystemFileSystemAdmin/Templates/LicensesAdmin/license-default.txt to change this license
 * Click nb://source://SystemFileSystemAdmin/Templates/Classes/Class.java to edit this template
 */
package dal;

import models.Feedback;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FeedbackDAO {
  
    private DBContext dbContext;


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

    public FeedbackDAO() {
        dbContext = new DBContext();
    }

    public List<Feedback> getFeedbacksByUser(int userID) {
        List<Feedback> userFeedbacks = new ArrayList<>();
        String sql = "SELECT feedbackID, userID, content, orderItemID, createdAt, rate, status "
                + "FROM Feedbacks WHERE userID = ? AND status = 1";
        try (Connection conn = dbContext.connection; PreparedStatement stmt = conn.prepareStatement(sql)) {
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
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
