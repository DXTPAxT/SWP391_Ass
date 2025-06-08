
package models;


public class BraComs {
    private int BraComID;
    private int BrandID;
    private String BrandName;
    private int ComponentID;
    private String ComponentName;
    private int Quantity;

    public BraComs(int BraComID, int BrandID, int ComponentID, int Quantity) {
        this.BraComID = BraComID;
        this.BrandID = BrandID;
        this.ComponentID = ComponentID;
        this.Quantity = Quantity;
    }

    public BraComs(int BraComID, int BrandID, String BrandName, int ComponentID, String ComponentName, int Quantity) {
        this.BraComID = BraComID;
        this.BrandID = BrandID;
        this.BrandName = BrandName;
        this.ComponentID = ComponentID;
        this.ComponentName = ComponentName;
        this.Quantity = Quantity;
    }
    
    
    public BraComs(int BrandID, int ComponentID, int Quantity) {
        this.BrandID = BrandID;
        this.ComponentID = ComponentID;
        this.Quantity = Quantity;
    }

    public BraComs() {
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String BrandName) {
        this.BrandName = BrandName;
    }

    public String getComponentName() {
        return ComponentName;
    }

    public void setComponentName(String ComponentName) {
        this.ComponentName = ComponentName;
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
