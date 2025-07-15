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
    private int post_id;
    private Date startDate;
    private Date endDate;
    private double discountPercent;
    private int status;
    private int createdBy;
    private Integer approvedBy;

    public SaleEvents() {
    }

    public SaleEvents(int eventID, int categoryID, int post_id, Date startDate, Date endDate, double discountPercent, int status, int createdBy, Integer approvedBy) {
        this.eventID = eventID;
        this.categoryID = categoryID;
        this.post_id = post_id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountPercent = discountPercent;
        this.status = status;
        this.createdBy = createdBy;
        this.approvedBy = approvedBy;
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
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
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

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Integer approvedBy) {
        this.approvedBy = approvedBy;
    }

    @Override
    public String toString() {
        return "SaleEvents{" + "eventID=" + eventID + ", categoryID=" + categoryID + ", post_id=" + post_id + ", startDate=" + startDate + ", endDate=" + endDate + ", discountPercent=" + discountPercent + ", status=" + status + ", createdBy=" + createdBy + ", approvedBy=" + approvedBy + '}';
    }
    
}
