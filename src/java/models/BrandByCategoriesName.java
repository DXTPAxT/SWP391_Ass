/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author PC
 */
public class BrandByCategoriesName {
    private int CategoryID;
    private String CategoryName;
    private String Brand;

    public BrandByCategoriesName() {
    }

    public BrandByCategoriesName(int CategoryID, String CategoryName, String Brand) {
        this.CategoryID = CategoryID;
        this.CategoryName = CategoryName;
        this.Brand = Brand;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int CategoryID) {
        this.CategoryID = CategoryID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String Brand) {
        this.Brand = Brand;
    }
    
}
