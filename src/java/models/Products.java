package models;

import java.sql.Date;

public class Products {

    private int productID;
    private String name;
    private Date createdAt;
    private int categoryID;
    private int status;

    public Products() {
    }

    public Products(String name, Date createdAt, int categoryID, int status) {
        this.name = name;
        this.createdAt = createdAt;
        this.categoryID = categoryID;
        this.status = status;
    }

    public Products(int productID, String name, Date createdAt, int categoryID, int status) {
        this.productID = productID;
        this.name = name;
        this.createdAt = createdAt;
        this.categoryID = categoryID;
        this.status = status;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
