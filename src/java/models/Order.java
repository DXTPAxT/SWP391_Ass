
package models;

import java.util.Date;

public class Order {
    private int OrderID;
    private int CustomerID;
    private Date OrderDate;
    private String Address;
    private int TotalAmount;
    private int Status;

    public Order() {
    }

    public Order(int OrderID, int CustomerID, Date OrderDate, String Address, int TotalAmount, int Status) {
        this.OrderID = OrderID;
        this.CustomerID = CustomerID;
        this.OrderDate = OrderDate;
        this.Address = Address;
        this.TotalAmount = TotalAmount;
        this.Status = Status;
    }

    public Order(int CustomerID, Date OrderDate, String Address, int TotalAmount, int Status) {
        this.CustomerID = CustomerID;
        this.OrderDate = OrderDate;
        this.Address = Address;
        this.TotalAmount = TotalAmount;
        this.Status = Status;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    public Date getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Date OrderDate) {
        this.OrderDate = OrderDate;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public int getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(int TotalAmount) {
        this.TotalAmount = TotalAmount;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }
    
    
}
