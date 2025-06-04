package dal;

import models.Feedback;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FeedbackDAO extends DBContext {

    private static final Logger LOGGER = Logger.getLogger(FeedbackDAO.class.getName());

    public List<Feedback> getAllFeedbacks() {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT * FROM Feedbacks WHERE Status = 1";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Feedback f = extractFeedbackFromResultSet(rs);
                list.add(f);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi trong getAllFeedbacks: " + e.getMessage(), e);
            throw new RuntimeException("Không lấy được feedback", e);
        }
        return list;
    }

    public List<Feedback> getFeedbackByCategoryId(int categoryId) {
        if (categoryId <= 0) {
            throw new IllegalArgumentException("CategoryID phải là số dương");
        }
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT * FROM Feedbacks WHERE CategoryID = ? AND Status = 1";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Feedback f = extractFeedbackFromResultSet(rs);
                    list.add(f);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi trong getFeedbackByCategoryId: " + e.getMessage(), e);
            throw new RuntimeException("Không lấy được feedback cho CategoryID: " + categoryId, e);
        }
        return list;
    }

    public boolean insertFeedback(Feedback f) {
        if (f == null) {
            throw new IllegalArgumentException("Feedback không được null");
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
                LOGGER.info("Thêm feedback thành công cho userID: " + f.getUserID());
                return true;
            } else {
                LOGGER.warning("Không thêm được feedback cho userID: " + f.getUserID());
                return false;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi trong insertFeedback: " + e.getMessage(), e);
            throw new RuntimeException("Không thêm được feedback", e);
        }
    }

    public boolean deleteFeedback(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("FeedbackID phải là số dương");
        }
        String sql = "UPDATE Feedbacks SET Status = 0 WHERE FeedbackID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                LOGGER.info("Xóa feedback ID " + id + " thành công");
                return true;
            } else {
                LOGGER.warning("Không tìm thấy feedback ID " + id);
                return false;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi trong deleteFeedback: " + e.getMessage(), e);
            throw new RuntimeException("Không xóa được feedback ID: " + id, e);
        }
    }

    public boolean updateFeedback(Feedback f) {
        if (f == null || f.getFeedbackID() <= 0) {
            throw new IllegalArgumentException("Feedback hoặc FeedbackID không hợp lệ");
        }
        String sql = "UPDATE Feedbacks SET Content = ?, Rate = ? WHERE FeedbackID = ? AND Status = 1";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, f.getContent());
            ps.setInt(2, f.getRate());
            ps.setInt(3, f.getFeedbackID());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                LOGGER.info("Cập nhật feedback ID " + f.getFeedbackID() + " thành công");
                return true;
            } else {
                LOGGER.warning("Không tìm thấy feedback đang hoạt động ID " + f.getFeedbackID());
                return false;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi trong updateFeedback: " + e.getMessage(), e);
            throw new RuntimeException("Không cập nhật được feedback ID: " + f.getFeedbackID(), e);
        }
    }

    public List<Feedback> getFeedbackByUserId(int userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("UserID phải là số dương");
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
            LOGGER.log(Level.SEVERE, "Lỗi trong getFeedbackByUserId: " + e.getMessage(), e);
            throw new RuntimeException("Không lấy được feedback cho userID: " + userId, e);
        }
        return list;
    }

    public Feedback getFeedbackById(int feedbackId) {
        if (feedbackId <= 0) {
            throw new IllegalArgumentException("FeedbackID phải là số dương");
        }
        String sql = "SELECT * FROM Feedbacks WHERE FeedbackID = ? AND Status = 1";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, feedbackId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Feedback f = extractFeedbackFromResultSet(rs);
                    LOGGER.info("Lấy feedback ID " + feedbackId + " thành công");
                    return f;
                } else {
                    LOGGER.warning("Không tìm thấy feedback đang hoạt động ID " + feedbackId);
                    return null;
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi trong getFeedbackById: " + e.getMessage(), e);
            throw new RuntimeException("Không lấy được feedback ID: " + feedbackId, e);
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

    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi kiểm tra kết nối: " + e.getMessage(), e);
            return false;
        }
    }
}