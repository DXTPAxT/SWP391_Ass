
package models;


public class BraComs {
    private int BraComID;
    private int BrandID;
    private int ComponentID;
    private int Quantity;

    public BraComs(int BraComID, int BrandID, int ComponentID, int Quantity) {
        this.BraComID = BraComID;
        this.BrandID = BrandID;
        this.ComponentID = ComponentID;
        this.Quantity = Quantity;
    }

    public BraComs(int BrandID, int ComponentID, int Quantity) {
        this.BrandID = BrandID;
        this.ComponentID = ComponentID;
        this.Quantity = Quantity;
    }

    public BraComs() {
    }

    public int getBraComID() {
        return BraComID;
    }

    public void setBraComID(int BraComID) {
        this.BraComID = BraComID;
    }

    public int getBrandID() {
        return BrandID;
    }

    public void setBrandID(int BrandID) {
        this.BrandID = BrandID;
    }

    public int getComponentID() {
        return ComponentID;
    }

    public void setComponentID(int ComponentID) {
        this.ComponentID = ComponentID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }
         
}
