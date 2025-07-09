package models;

import java.util.Date;

public class Order {

    private int OrderID;
    private String OrderCode;
    
    private int Product_Type;
    private int CustomerID;
    private String FullName;
    private Date OrderDate;
    private String Address;
    private String phoneNumber;
    private int TotalAmount;
    private int Status;
    private int PaymentStatus;
    private int StaffID;
    private String StaffName;

    public Order() {
    }

    public Order(int OrderID, String OrderCode, int Product_Type, int CustomerID, String FullName, Date OrderDate, String Address, String phoneNumber, int TotalAmount, int Status, int PaymentStatus, int StaffID, String StaffName) {
        this.OrderID = OrderID;
        this.OrderCode = OrderCode;
        this.Product_Type = Product_Type;
        this.CustomerID = CustomerID;
        this.FullName = FullName;
        this.OrderDate = OrderDate;
        this.Address = Address;
        this.phoneNumber = phoneNumber;
        this.TotalAmount = TotalAmount;
        this.Status = Status;
        this.PaymentStatus = PaymentStatus;
        this.StaffID = StaffID;
        this.StaffName = StaffName;
    }

    public Order(int OrderID, String OrderCode, int Product_Type, int CustomerID, String FullName, Date OrderDate, String Address, String phoneNumber, int TotalAmount, int Status, int PaymentStatus) {
        this.OrderID = OrderID;
        this.OrderCode = OrderCode;
        this.Product_Type = Product_Type;
        this.CustomerID = CustomerID;
        this.FullName = FullName;
        this.OrderDate = OrderDate;
        this.Address = Address;
        this.phoneNumber = phoneNumber;
        this.TotalAmount = TotalAmount;
        this.Status = Status;
        this.PaymentStatus = PaymentStatus;
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


    public int getStaffID() {
        return StaffID;
    }

    public void setStaffID(int StaffID) {
        this.StaffID = StaffID;
    }

    public String getStaffName() {
        return StaffName;
    }

    public void setStaffName(String StaffName) {
        this.StaffName = StaffName;
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

    public int getPaymentStatus() {
        return PaymentStatus;
    }

    public void setPaymentStatus(int PaymentStatus) {
        this.PaymentStatus = PaymentStatus;
    }

    public String getOrderCode() {
        return OrderCode;
    }

    public void setOrderCode(String OrderCode) {
        this.OrderCode = OrderCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
