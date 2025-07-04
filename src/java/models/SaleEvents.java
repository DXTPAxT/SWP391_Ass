/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date;

/**
 *
 * @author User
 */
public class SaleEvents {

    private int eventID;
    private int categoryID;
    private int Post_id;
    private Date startDate;
    private Date endDate;
    private double discountPercent;
    private int status;

    public SaleEvents() {
    }

    public SaleEvents(int eventID, int categoryID, int Post_id, Date startDate, Date endDate, double discountPercent, int status) {
        this.eventID = eventID;
        this.categoryID = categoryID;
        this.Post_id = Post_id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountPercent = discountPercent;
        this.status = status;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getPost_id() {
        return Post_id;
    }

    public void setPost_id(int Post_id) {
        this.Post_id = Post_id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SaleEvents{" + "eventID=" + eventID + ", categoryID=" + categoryID + ", postID=" + Post_id + ", startDate=" + startDate + ", endDate=" + endDate + ", discountPercent=" + discountPercent + ", status=" + status + '}';
    }
    
}
