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
    private int catagoryID;
    private String catagoryName;
    private int quantity;
    private int status;

    public Categories() {
    }

    public Categories(int catagoryID, String catagoryName, int quantity, int status) {
        this.catagoryID = catagoryID;
        this.catagoryName = catagoryName;
        this.quantity = quantity;
        this.status = status;
    }

    public int getCatagoryID() {
        return catagoryID;
    }

    public void setCatagoryID(int catagoryID) {
        this.catagoryID = catagoryID;
    }

    public String getCatagoryName() {
        return catagoryName;
    }

    public void setCatagoryName(String catagoryName) {
        this.catagoryName = catagoryName;
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
        return "Catagorys{" + "catagoryID=" + catagoryID + ", catagoryName=" + catagoryName + ", quantity=" + quantity + ", status=" + status + '}';
    }
    
}
