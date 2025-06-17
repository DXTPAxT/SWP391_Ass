package models;

public class Feedback {
    private int feedbackID;
    private int userID;
    private String content;
    private int orderItemID;
    private String createdAt;
    private int rate;
    private int status;
    private String fullname;

    public Feedback(int feedbackID, int userID, String content, int orderItemID, String createdAt, int rate, int status, String fullname) {
        this.feedbackID = feedbackID;
        this.userID = userID;
        this.content = content;
        this.orderItemID = orderItemID;
        this.createdAt = createdAt;
        this.rate = rate;
        this.status = status;
        this.fullname = fullname;
    }

    // Constructor cho insert nhanh
    public Feedback(int userID, String content, int orderItemID, int rate) {
        this.userID = userID;
        this.content = content;
        this.orderItemID = orderItemID;
        this.rate = rate;
        this.status = 1;
    }

    // Getter/setter
    public int getFeedbackID() { return feedbackID; }
    public void setFeedbackID(int feedbackID) { this.feedbackID = feedbackID; }
    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public int getOrderItemID() { return orderItemID; }
    public void setOrderItemID(int orderItemID) { this.orderItemID = orderItemID; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    public int getRate() { return rate; }
    public void setRate(int rate) { this.rate = rate; }
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }

    @Override
    public String toString() {
        return "Feedback{" +
                "feedbackID=" + feedbackID +
                ", userID=" + userID +
                ", content='" + content + '\'' +
                ", orderItemID=" + orderItemID +
                ", createdAt='" + createdAt + '\'' +
                ", rate=" + rate +
                ", status=" + status +
                ", fullname='" + fullname + '\'' +
                '}';
    }
}