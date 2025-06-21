/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author PC
 */
public class BuildPCView {
   private int buildPCID;
    private String mainBoard;
    private String cpu;
    private String gpu;
    private String ram;
    private String ssd;
    private String pcCase;
    private int price;
    private int status;

    public BuildPCView() {
    }

    public BuildPCView(int buildPCID, String mainBoard, String cpu, String gpu, String ram, String ssd, String pcCase, int price, int status) {
        this.buildPCID = buildPCID;
        this.mainBoard = mainBoard;
        this.cpu = cpu;
        this.gpu = gpu;
        this.ram = ram;
        this.ssd = ssd;
        this.pcCase = pcCase;
        this.price = price;
        this.status = status;
    }

    public int getBuildPCID() {
        return buildPCID;
    }

    public void setBuildPCID(int buildPCID) {
        this.buildPCID = buildPCID;
    }

    public String getMainBoard() {
        return mainBoard;
    }

    public void setMainBoard(String mainBoard) {
        this.mainBoard = mainBoard;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getSsd() {
        return ssd;
    }

    public void setSsd(String ssd) {
        this.ssd = ssd;
    }

    public String getPcCase() {
        return pcCase;
    }

    public void setPcCase(String pcCase) {
        this.pcCase = pcCase;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
