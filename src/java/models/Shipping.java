package models;

import java.sql.Timestamp;

public class Shipping {

    private int shippingID;
    private int orderID;
    private int shipperID;
    private Timestamp orderDate;
    private String address;
    private int totalAmount;
    private String shippingStatus;
    private String feedback;
    private int orderItemID;
    private int quantity;
    private int price;
    private String categoryName;
    private String imageURL;

    public Shipping() {
    }

    public Shipping(int shippingID, int orderID, int shipperID, Timestamp orderDate, String address, int totalAmount,
            String shippingStatus, String feedback, int orderItemID, int quantity, int price,
            String categoryName, String imageURL) {
        this.shippingID = shippingID;
        this.orderID = orderID;
        this.shipperID = shipperID;
        this.orderDate = orderDate;
        this.address = address;
        this.totalAmount = totalAmount;
        this.shippingStatus = shippingStatus;
        this.feedback = feedback;
        this.orderItemID = orderItemID;
        this.quantity = quantity;
        this.price = price;
        this.categoryName = categoryName;
        this.imageURL = imageURL;
    }

    // Getters and Setters
    public int getShippingID() {
        return shippingID;
    }

    public void setShippingID(int shippingID) {
        this.shippingID = shippingID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getShipperID() {
        return shipperID;
    }

    public void setShipperID(int shipperID) {
        this.shipperID = shipperID;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(String shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getOrderItemID() {
        return orderItemID;
    }

    public void setOrderItemID(int orderItemID) {
        this.orderItemID = orderItemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
