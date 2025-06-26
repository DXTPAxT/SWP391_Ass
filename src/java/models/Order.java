package models;

import java.util.Date;

public class Order {

    private int OrderID;
    private int Product_Type;
    private int CustomerID;
    private String FullName;
    private Date OrderDate;
    private String Address;
    private int TotalAmount;
    private int Status;

    public Order() {
    }

    public Order(int OrderID, int Product_Type, int CustomerID, Date OrderDate, String Address, int TotalAmount, int Status) {
        this.OrderID = OrderID;
        this.Product_Type = Product_Type;
        this.CustomerID = CustomerID;
        this.OrderDate = OrderDate;
        this.Address = Address;
        this.TotalAmount = TotalAmount;
        this.Status = Status;
    }

    public Order(int Product_Type, int CustomerID, Date OrderDate, String Address, int TotalAmount, int Status) {
        this.Product_Type = Product_Type;
        this.CustomerID = CustomerID;
        this.OrderDate = OrderDate;
        this.Address = Address;
        this.TotalAmount = TotalAmount;
        this.Status = Status;
    }

    public Order(int OrderID, int Product_Type, int CustomerID, String FullName, Date OrderDate, String Address, int TotalAmount, int Status) {
        this.OrderID = OrderID;
        this.Product_Type = Product_Type;
        this.CustomerID = CustomerID;
        this.FullName = FullName;
        this.OrderDate = OrderDate;
        this.Address = Address;
        this.TotalAmount = TotalAmount;
        this.Status = Status;
    }

    public Order(int Product_Type, int CustomerID, String FullName, Date OrderDate, String Address, int TotalAmount, int Status) {
        this.Product_Type = Product_Type;
        this.CustomerID = CustomerID;
        this.FullName = FullName;
        this.OrderDate = OrderDate;
        this.Address = Address;
        this.TotalAmount = TotalAmount;
        this.Status = Status;
    }

    public int getProduct_Type() {
        return Product_Type;
    }

    public void setProduct_Type(int Product_Type) {
        this.Product_Type = Product_Type;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
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
