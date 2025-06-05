package models;

import java.util.Date;

public class Feedback {

    private int feedbackID;
    private int userID;
    private String userName;
    private String content;
    private int categoryID;
    private Date createdAt;
    private int rate;
    private int status;

    public Feedback() {
        this.status = 1;
    }

    public Feedback(int feedbackID, int userID, String content, int categoryID, Date createdAt, int rate, int status) {
        this.feedbackID = feedbackID;
        this.userID = userID;
        setContent(content);
        this.categoryID = categoryID;
        this.createdAt = createdAt;
        setRate(rate);
        this.status = status;
    }

    public Feedback(int feedbackID, int userID, String content, int categoryID, int rate) {
        this(feedbackID, userID, content, categoryID, null, rate, 1);
    }

    public Feedback(int userID, String content, int categoryID, int rate) {
        this(0, userID, content, categoryID, null, rate, 1);
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
        if (userID <= 0) {
            throw new IllegalArgumentException("UserID must be positive");
        }
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        if (categoryID <= 0) {
            throw new IllegalArgumentException("CategoryID must be positive");
        }
        this.categoryID = categoryID;
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
            throw new IllegalArgumentException("Status must be 0 or 1");
        }
        this.status = status;
    }

    @Override
    public String toString() {
        return "Feedback{feedbackID=" + feedbackID + ", userID=" + userName + ", content='" + content + "', categoryID="
                + categoryID + ", createdAt=" + createdAt + ", rate=" + rate + ", status=" + status + '}';
    }
}
