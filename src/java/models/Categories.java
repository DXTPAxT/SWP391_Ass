package models;

public class Categories {
    private int categoryID;
    private String categoryName;
    private int BraComID;
    private int quantity;
    private int price;
    private String description;
    private int status;
    private String imgURL;

    public Categories() {
    }

    public Categories(int categoryID, String categoryName, int BraComID, int quantity, int price, String description, int status, String imgURL) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.BraComID = BraComID;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.status = status;
        this.imgURL = imgURL;
    }

    public Categories(String categoryName, int BraComID, int quantity, int price, String description, int status, String imgURL) {
        this.categoryName = categoryName;
        this.BraComID = BraComID;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.status = status;
        this.imgURL = imgURL;
    }
    
    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getBraComID() {
        return BraComID;
    }

    public void setBraComID(int BraComID) {
        this.BraComID = BraComID;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

}
    