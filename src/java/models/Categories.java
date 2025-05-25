/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author PC
 */
public class Categories {
    private int categoryID;
    private String categoryName;
    private int quantity;
    private int status;

    public Categories() {
    }

    public Categories(int categoryID, String categoryName, int quantity, int status) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.quantity = quantity;
        this.status = status;
    }

    public int getCatagoryID() {
        return categoryID;
    }

    public void setCatagoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCatagoryName() {
        return categoryName;
    }

    public void setCatagoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Categories{" + "categoryID=" + categoryID + ", categoryName=" + categoryName + ", quantity=" + quantity + ", status=" + status + '}';
    }
    
}
