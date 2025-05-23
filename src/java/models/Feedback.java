package models;

import java.util.Date;

public class Feedback {

    private int feedbackID;
    private int userID;
    private String content;
    private int productID;
    private Date createdAt;

    public Feedback() {
    }

    public Feedback(int feedbackID, int userID, String content, int productID, Date createdAt) {
        this.feedbackID = feedbackID;
        this.userID = userID;
        this.content = content;
        this.productID = productID;
        this.createdAt = createdAt;
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
}
