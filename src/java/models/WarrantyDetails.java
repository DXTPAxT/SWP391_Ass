package models;

public class WarrantyDetails {

    private int WarrantyDetailID;
    private int WarrantyID;
    private int BraComID;
    private int price;
    private int status;

    public WarrantyDetails() {
    }

    public WarrantyDetails(int WarrantyDetailID, int WarrantyID, int BraComID, int price, int status) {
        this.WarrantyDetailID = WarrantyDetailID;
        this.WarrantyID = WarrantyID;
        this.BraComID = BraComID;
        this.price = price;
        this.status = status;
    }

    public WarrantyDetails(int WarrantyID, int BraComID, int price, int status) {
        this.WarrantyID = WarrantyID;
        this.BraComID = BraComID;
        this.price = price;
        this.status = status;
    }

    public int getWarrantyDetailID() {
        return WarrantyDetailID;
    }

    public void setWarrantyDetailID(int WarrantyDetailID) {
        this.WarrantyDetailID = WarrantyDetailID;
    }

    public int getWarrantyID() {
        return WarrantyID;
    }

    public void setWarrantyID(int WarrantyID) {
        this.WarrantyID = WarrantyID;
    }

    public int getBraComID() {
        return BraComID;
    }

    public void setBraComID(int BraComID) {
        this.BraComID = BraComID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
