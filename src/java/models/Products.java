package models;

import java.sql.Date;

public class Products {

    private int productID;
    private int ImportID;
    private String ImportCode;
    private String ProductCode;
    private int status;
    private int CategoryID;
    private String CategoryName;

    public Products() {
    }

    public Products(int productID, int ImportID, String ProductCode, int status) {
        this.productID = productID;
        this.ImportID = ImportID;
        this.ProductCode = ProductCode;
        this.status = status;
    }

    public Products(int ImportID, String ProductCode, int status) {
        this.ImportID = ImportID;
        this.ProductCode = ProductCode;
        this.status = status;
    }

    public Products(int productID, int ImportID, String ImportCode, String ProductCode, int status) {
        this.productID = productID;
        this.ImportID = ImportID;
        this.ImportCode = ImportCode;
        this.ProductCode = ProductCode;
        this.status = status;
    }

    public Products(int productID, int ImportID, String ImportCode, String ProductCode, int status, int CategoryID, String CategoryName) {
        this.productID = productID;
        this.ImportID = ImportID;
        this.ImportCode = ImportCode;
        this.ProductCode = ProductCode;
        this.status = status;
        this.CategoryID = CategoryID;
        this.CategoryName = CategoryName;
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
    

    public String getImportCode() {
        return ImportCode;
    }

    public void setImportCode(String ImportCode) {
        this.ImportCode = ImportCode;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getImportID() {
        return ImportID;
    }

    public void setImportID(int ImportID) {
        this.ImportID = ImportID;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String ProductCode) {
        this.ProductCode = ProductCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
