
package models;

public class OrderItems {
    private int OrderItemID;
    private int OrderID;
    private int CategoryID;
    private int Quantity;
    private int Price;

    public OrderItems() {
    }

    public OrderItems(int OrderItemID, int OrderID, int CategoryID, int Quantity, int Price) {
        this.OrderItemID = OrderItemID;
        this.OrderID = OrderID;
        this.CategoryID = CategoryID;
        this.Quantity = Quantity;
        this.Price = Price;
    }

    public OrderItems(int OrderID, int CategoryID, int Quantity, int Price) {
        this.OrderID = OrderID;
        this.CategoryID = CategoryID;
        this.Quantity = Quantity;
        this.Price = Price;
    }

    public int getOrderItemID() {
        return OrderItemID;
    }

    public void setOrderItemID(int OrderItemID) {
        this.OrderItemID = OrderItemID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int CategoryID) {
        this.CategoryID = CategoryID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }
    
    
}
