package dal;

import models.Feedback;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO extends DBContext {

    // Lấy tất cả feedback
    public List<Feedback> getAllFeedbacks() {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT * FROM Feedbacks";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Feedback f = new Feedback(
                        rs.getInt("FeedbackID"),
                        rs.getInt("UserID"),
                        rs.getString("Content"),
                        rs.getInt("ProductID"),
                        rs.getTimestamp("CreatedAt"),
                        rs.getInt("Rate")
                );
                list.add(f);
            }
        } catch (SQLException e) {
            System.err.println("Error in getAllFeedbacks: " + e.getMessage());
        }
        return list;
    }

    // Lấy feedback theo productID
    public List<Feedback> getFeedbackByProductId(int productId) {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT * FROM Feedbacks WHERE ProductID = ?";
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
                            rs.getInt("Rate")
                    );
                    list.add(f);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error in getFeedbackByProductId: " + e.getMessage());
        }
        return list;
    }

    // Thêm feedback mới
    public boolean insertFeedback(Feedback f) {
        String sql = "INSERT INTO Feedbacks (UserID, Content, ProductID, Rate) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, f.getUserID());
            ps.setString(2, f.getContent());
            ps.setInt(3, f.getProductID());
            ps.setInt(4, f.getRate());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error in insertFeedback: " + e.getMessage());
        }
        return false;
    }

    // Xóa feedback
    public boolean deleteFeedback(int id) {
        String sql = "DELETE FROM Feedbacks WHERE FeedbackID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error in deleteFeedback: " + e.getMessage());
        }
        return false;
    }

    // Cập nhật nội dung feedback
    public boolean updateFeedback(Feedback f) {
        String sql = "UPDATE Feedbacks SET Content = ?, Rate = ? WHERE FeedbackID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, f.getContent());
            ps.setInt(2, f.getRate());
            ps.setInt(3, f.getFeedbackID());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error in updateFeedback: " + e.getMessage());
        }
        return false;
    }

    // Lấy feedback theo userID
    public List<Feedback> getFeedbackByUserId(int userId) {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT * FROM Feedbacks WHERE UserID = ?";
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
                            rs.getInt("Rate")
                    );
                    list.add(f);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error in getFeedbackByUserId: " + e.getMessage());
        }
        return list;
    }

    public static void main(String[] args) {
        FeedbackDAO dao = new FeedbackDAO();

        // Tạo đối tượng Feedback mới để thêm vào CSDL
        Feedback newFeedback = new Feedback(
                0, // FeedbackID (0 hoặc bất kỳ, sẽ được tự động tạo)
                1, // UserID - giả sử user có ID = 1
                "Sản phẩm rất tốt, giao hàng nhanh!", // Content
                2, // ProductID - giả sử sản phẩm có ID = 2
                null, // CreatedAt - để null vì sẽ do DB tự set (nếu dùng default)
                5 // Rate - đánh giá sao
        );

        // Gọi phương thức insert
        boolean success = dao.insertFeedback(newFeedback);

        // In kết quả ra console
        if (success) {
            System.out.println("Thêm feedback thành công!");
        } else {
            System.out.println("Thêm feedback thất bại.");
        }
    }

}
