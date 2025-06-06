
package models;

public class CartItem {
    private int cartItemID;
    private int userID;
    private int CategoryID;
    private int WarrantyDetailID;
    private int Status;

    public CartItem() {
    }

    public CartItem(int cartItemID, int userID, int CategoryID, int WarrantyDetailID, int Status) {
        this.cartItemID = cartItemID;
        this.userID = userID;
        this.CategoryID = CategoryID;
        this.WarrantyDetailID = WarrantyDetailID;
        this.Status = Status;
    }

    public CartItem(int userID, int CategoryID, int WarrantyDetailID, int Status) {
        this.userID = userID;
        this.CategoryID = CategoryID;
        this.WarrantyDetailID = WarrantyDetailID;
        this.Status = Status;
    }

    public int getCartItemID() {
        return cartItemID;
    }

    public void setCartItemID(int cartItemID) {
        this.cartItemID = cartItemID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int CategoryID) {
        this.CategoryID = CategoryID;
    }

    public int getWarrantyDetailID() {
        return WarrantyDetailID;
    }

    public void setWarrantyDetailID(int WarrantyDetailID) {
        this.WarrantyDetailID = WarrantyDetailID;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }
    
    
    
    

   

}
