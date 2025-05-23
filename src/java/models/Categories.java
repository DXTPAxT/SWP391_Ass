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
    private int id;
    private String CatagoryName;
    private String Brand;
    private int ComponentID;
    private String Description;
    private int Status;

    public Categories() {
    }

    public Categories(int id, String CatagoryName, String Brand, int ComponentID, String Description, int Status) {
        this.id = id;
        this.CatagoryName = CatagoryName;
        this.Brand = Brand;
        this.ComponentID = ComponentID;
        this.Description = Description;
        this.Status = Status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCatagoryName() {
        return CatagoryName;
    }

    public void setCatagoryName(String CatagoryName) {
        this.CatagoryName = CatagoryName;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String Brand) {
        this.Brand = Brand;
    }

    public int getComponentID() {
        return ComponentID;
    }

    public void setComponentID(int ComponentID) {
        this.ComponentID = ComponentID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }
    
    
}
