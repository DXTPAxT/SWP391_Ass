package models;

import java.sql.Date;

public class Products {

    private int productID;
    private int ImportID;
    private int categoryID;
    private String ProductCode;
    private int status;

    public Products() {
    }

    public Products(int productID, int ImportID, int categoryID, String ProductCode, int status) {
        this.productID = productID;
        this.ImportID = ImportID;
        this.categoryID = categoryID;
        this.ProductCode = ProductCode;
        this.status = status;
    }

    public Products(int ImportID, int categoryID, String ProductCode, int status) {
        this.ImportID = ImportID;
        this.categoryID = categoryID;
        this.ProductCode = ProductCode;
        this.status = status;
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

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
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
