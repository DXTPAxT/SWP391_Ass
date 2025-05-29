package models;

import java.sql.Timestamp;

public class Feedback {

    private int feedbackID;
    private int userID;
    private String content;
    private int productID;
    private Timestamp createdAt;
    private int rate;
    private int status;

    public Feedback() {
    }

    public Feedback(int feedbackID, int userID, String content, int productID, Timestamp createdAt, int rate, int status) {
        this.feedbackID = feedbackID;
        this.userID = userID;
        this.content = content;
        this.productID = productID;
        this.createdAt = createdAt;
        this.rate = rate;
        this.status = status;
    }

    public Feedback(int userID, String content, int productID, int rate, int status) {
        this.userID = userID;
        this.content = content;
        this.productID = productID;
        this.rate = rate;
        this.status = status;
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
