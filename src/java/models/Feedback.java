package models;

import java.util.Date;

public class Feedback {

    private int feedbackID;
    private int userID;
    private String content;
    private int orderItemID; // Sửa từ categoryID thành orderItemID
    private Date createdAt;
    private int rate;
    private int status; // 1: active, 0: inactive/deleted

    // Constructor mặc định
    public Feedback() {
        this.status = 1; // Mặc định status = 1 (active)
    }

    // Constructor đầy đủ (bao gồm status)
    public Feedback(int feedbackID, int userID, String content, int orderItemID,
            Date createdAt, int rate, int status) {
        this.feedbackID = feedbackID;
        this.userID = userID;
        setContent(content); // Sử dụng setter để validate
        this.orderItemID = orderItemID;
        this.createdAt = createdAt;
        setRate(rate); // Sử dụng setter để validate
        this.status = status;
    }

    // Constructor không có createdAt và status
    public Feedback(int feedbackID, int userID, String content, int orderItemID, int rate) {
        this(feedbackID, userID, content, orderItemID, null, rate, 1);
    }

    // Constructor dùng cho insert (không cần ID, createdAt)
    public Feedback(int userID, String content, int orderItemID, int rate) {
        this(0, userID, content, orderItemID, null, rate, 1);
    }

    // Getter và Setter
    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        if (userID <= 0) {
            throw new IllegalArgumentException("UserID must be positive");
        }
        this.userID = userID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or empty");
        }
        if (content.length() > 500) {
            throw new IllegalArgumentException("Content cannot exceed 500 characters");
        }
        this.content = content.trim();
    }

    public int getOrderItemID() { // Sửa từ getCategoryID thành getOrderItemID
        return orderItemID;
    }

    public void setOrderItemID(int orderItemID) { // Sửa từ setCategoryID thành setOrderItemID
        if (orderItemID <= 0) {
            throw new IllegalArgumentException("OrderItemID must be positive");
        }
        this.orderItemID = orderItemID;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        if (rate < 1 || rate > 5) {
            throw new IllegalArgumentException("Rate must be between 1 and 5");
        }
        this.rate = rate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        if (status != 0 && status != 1) {
            throw new IllegalArgumentException("Status must be 0 (inactive) or 1 (active)");
        }
        this.status = status;
    }

    @Override
    public String toString() {
        return "Feedback{"
                + "feedbackID=" + feedbackID
                + ", userID=" + userID
                + ", content='" + content + '\''
                + ", orderItemID=" + orderItemID
                + ", createdAt=" + createdAt
                + ", rate=" + rate
                + ", status=" + status
                + '}';
    }
}