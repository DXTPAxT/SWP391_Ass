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
