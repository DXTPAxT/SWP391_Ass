package models;

import java.util.Date;

public class Feedback {

    private int feedbackID;
    private int userID;
    private String content;
    private int productID;
    private Date createdAt;
    private int rate; // ✅ Thêm trường rate

    public Feedback() {
    }

    // ✅ Constructor đầy đủ
    public Feedback(int feedbackID, int userID, String content, int productID, Date createdAt, int rate) {
        this.feedbackID = feedbackID;
        this.userID = userID;
        this.content = content;
        this.productID = productID;
        this.createdAt = createdAt;
        this.rate = rate;
    }

    // ✅ Constructor dùng cho insert (không cần ID, createdAt)
    public Feedback(int userID, String content, int productID, int rate) {
        this.userID = userID;
        this.content = content;
        this.productID = productID;
        this.rate = rate;
    }

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
        this.userID = userID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
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
        this.rate = rate;
    }
}
