package models;

public class Components {
    private int componentID;
    private String componentName;
    private int quantity; 
    private int status;

    public Components() {
    }

    public Components(int componentID, String componentName, int quantity, int status) {
        this.componentID = componentID;
        this.componentName = componentName;
        this.quantity = quantity;
        this.status = status;
    }

    public Components(int componentID, String componentName) {
        this.componentID = componentID;
        this.componentName = componentName;
        this.quantity = 0; 
        this.status = 1;   
    }

    public int getComponentID() {
        return componentID;
    }

    public void setComponentID(int componentID) {
        this.componentID = componentID;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public int getQuantity() { 
        return quantity;
    }

    public void setQuantity(int quantity) { 
        this.quantity = quantity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Components{" +
                "componentID=" + componentID +
                ", componentName='" + componentName + '\'' +
                ", quantity=" + quantity +
                ", status=" + status +
                '}';
    }
}