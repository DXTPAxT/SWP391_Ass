package models;

public class Shipping {

    private int shippingID;
    private int orderID;
    private int shipperID;
    private String shippingStatus;
    private String feedback;

    public Shipping() {
    }

    public Shipping(int shippingID, int orderID, int shipperID, String shippingStatus, String feedback) {
        this.shippingID = shippingID;
        this.orderID = orderID;
        this.shipperID = shipperID;
        this.shippingStatus = shippingStatus;
        this.feedback = feedback;
    }

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
}
