/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date;

public class Products {
    private int ProductID ;
    private String Name ;
    private String Description ;
    private String Brand;
    private Double Price ;
    private int Quantity ;
    private int WarrantyPeriod ;
    private Date CreatedAt ;
    private int CategoryID ;
    private int Status ;

    public Products() {
    }
    
    public Products(int ProductID, String Name, String Description, String Brand, Double Price, int Quantity, int WarrantyPeriod, Date CreatedAt, int CategoryID, int Status) {
        this.ProductID = ProductID;
        this.Name = Name;
        this.Description = Description;
        this.Brand = Brand;
        this.Price = Price;
        this.Quantity = Quantity;
        this.WarrantyPeriod = WarrantyPeriod;
        this.CreatedAt = CreatedAt;
        this.CategoryID = CategoryID;
        this.Status = Status;
    }

    public Products(String Name, String Description, String Brand, Double Price, int Quantity, int WarrantyPeriod, Date CreatedAt, int CategoryID, int Status) {
        this.Name = Name;
        this.Description = Description;
        this.Brand = Brand;
        this.Price = Price;
        this.Quantity = Quantity;
        this.WarrantyPeriod = WarrantyPeriod;
        this.CreatedAt = CreatedAt;
        this.CategoryID = CategoryID;
        this.Status = Status;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String Brand) {
        this.Brand = Brand;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double Price) {
        this.Price = Price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public int getWarrantyPeriod() {
        return WarrantyPeriod;
    }

    public void setWarrantyPeriod(int WarrantyPeriod) {
        this.WarrantyPeriod = WarrantyPeriod;
    }

    public Date getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Date CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int CategoryID) {
        this.CategoryID = CategoryID;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }
  

   
    
    
}
