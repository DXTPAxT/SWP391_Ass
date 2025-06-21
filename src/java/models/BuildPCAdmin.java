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
    private int WarrantyDetailID;
    private int Price;
    private int Status;

    public BuildPCAdmin() {
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

}
