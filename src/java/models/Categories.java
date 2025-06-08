package models;

public class Categories {
    private int categoryID;
    private String categoryName;
    private int brandComID;      // FK về BrandComs
    private int componentID;     // Lấy từ BrandComs
    private int brandID;         // Lấy từ BrandComs
    private String brandName;    // Lấy từ bảng Brands
    private int quantity;
    private int price;
    private String description;
    private int status;
    private String imgURL;

    public Categories() {
    }

  
    public Categories(int categoryID, String categoryName,
                      int componentID, int brandID, String brandName,
                      int quantity, int price, String description, int status) {
        this.categoryID   = categoryID;
        this.categoryName = categoryName;
        this.componentID  = componentID;
        this.brandID      = brandID;
        this.brandName    = brandName;
        this.quantity     = quantity;
        this.price        = price;
        this.description  = description;
        this.status       = status;
    }

    public Categories(int categoryID, String categoryName, int brandComID, int componentID, int brandID, String brandName, int quantity, int price, String description, int status, String imgURL) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.brandComID = brandComID;
        this.componentID = componentID;
        this.brandID = brandID;
        this.brandName = brandName;
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

    public int getBrandComID() {
        return brandComID;
    }

    public void setBrandComID(int brandComID) {
        this.brandComID = brandComID;
    }

    public int getComponentID() {
        return componentID;
    }

    public void setComponentID(int componentID) {
        this.componentID = componentID;
    }

    public int getBrandID() {
        return brandID;
    }

    public void setBrandID(int brandID) {
        this.brandID = brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
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
                                                                                                       