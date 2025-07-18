/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author PC
 */
public class BuildPCAdmin {

    private int BuildPCID;
    private int BuildPCIterm;
    private int CateID;
    private String CateName;
    private int ComponentID;
    private int WarrantyPrice;
    private String WarrantyDesc;
    private int WarrantyDetailID;
    private int Price;
    private int Status;
    private String brandName;
    private String imgURL;
private int queue;
  private int orderBuildPCItemID;
   private int inventory;
    public BuildPCAdmin() {
    }

    public BuildPCAdmin(int ComponentID, int CateID, String CateName, String brandName, int Price, String imgURL) {
        this.ComponentID = ComponentID;
        this.CateID = CateID;
        this.CateName = CateName;
        this.brandName = brandName;
        this.Price = Price;
        this.imgURL = imgURL;
    }

    public BuildPCAdmin(int BuildPCIterm, int CateID, String CateName, int ComponentID, int Price, int Status) {
        this.BuildPCIterm = BuildPCIterm;
        this.CateID = CateID;
        this.CateName = CateName;
        this.ComponentID = ComponentID;
        this.Price = Price;
        this.Status = Status;
    }

    public BuildPCAdmin(int BuildPCIterm, int CateID, String CateName, int Price, int Status) {
        this.BuildPCIterm = BuildPCIterm;
        this.CateID = CateID;
        this.CateName = CateName;
        this.Price = Price;
        this.Status = Status;
    }

    public BuildPCAdmin(int BuildPCID, int BuildPCIterm, int CateID, String CateName, int Price, int Status) {
        this.BuildPCID = BuildPCID;
        this.BuildPCIterm = BuildPCIterm;
        this.CateID = CateID;
        this.CateName = CateName;
        this.Price = Price;
        this.Status = Status;
    }

    public BuildPCAdmin(int BuildPCID, int BuildPCIterm, int CateID, String CateName, int WarrantyDetailID, int Price, int Status) {
        this.BuildPCID = BuildPCID;
        this.BuildPCIterm = BuildPCIterm;
        this.CateID = CateID;
        this.CateName = CateName;
        this.WarrantyDetailID = WarrantyDetailID;
        this.Price = Price;
        this.Status = Status;
    }

    public BuildPCAdmin(int ComponentID, int CateID, String CateName, String brandName, int Price, String imgURL, int WarrantyDetailID, int WarrantyPrice, String WarrantyDesc) {
        this.ComponentID = ComponentID;
        this.CateID = CateID;
        this.CateName = CateName;
        this.brandName = brandName;
        this.Price = Price;
        this.imgURL = imgURL;
        this.WarrantyDetailID = WarrantyDetailID;
        this.WarrantyPrice = WarrantyPrice;
        this.WarrantyDesc = WarrantyDesc;
    }
public int getQueue() {
    return queue;
}

    public int getOrderBuildPCItemID() {
        return orderBuildPCItemID;
    }

    public void setOrderBuildPCItemID(int orderBuildPCItemID) {
        this.orderBuildPCItemID = orderBuildPCItemID;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

public void setQueue(int queue) {
    this.queue = queue;
}
    public String getWarrantyDesc() {
        return WarrantyDesc;
    }

    public void setWarrantyDesc(String WarrantyDesc) {
        this.WarrantyDesc = WarrantyDesc;
    }

    public int getWarrantyPrice() {
        return WarrantyPrice;
    }

    public void setWarrantyPrice(int WarrantyPrice) {
        this.WarrantyPrice = WarrantyPrice;
    }

    public int getBuildPCID() {
        return BuildPCID;
    }

    public void setBuildPCID(int BuildPCID) {
        this.BuildPCID = BuildPCID;
    }

    public int getBuildPCIterm() {
        return BuildPCIterm;
    }

    public void setBuildPCIterm(int BuildPCIterm) {
        this.BuildPCIterm = BuildPCIterm;
    }

    public int getCateID() {
        return CateID;
    }

    public void setCateID(int CateID) {
        this.CateID = CateID;
    }

    public String getCateName() {
        return CateName;
    }

    public void setCateName(String CateName) {
        this.CateName = CateName;
    }

    public int getWarrantyDetailID() {
        return WarrantyDetailID;
    }

    public void setWarrantyDetailID(int WarrantyDetailID) {
        this.WarrantyDetailID = WarrantyDetailID;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public int getComponentID() {
        return ComponentID;
    }

    public void setComponentID(int ComponentID) {
        this.ComponentID = ComponentID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

}
