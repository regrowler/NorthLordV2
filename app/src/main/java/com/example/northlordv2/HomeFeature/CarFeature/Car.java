package com.example.northlordv2.HomeFeature.CarFeature;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Car  {

    public int getId() {
        return id;
    }
    public String getLabel() {
        return Label;
    }
    public String getModel() {
        return Model;
    }
    public int getCost() {
        return cost;
    }
    public int getRentcost() {
        return rentcost;
    }
    public boolean isChecked(){return checked;}
    public void setChecked(boolean in){checked=in;}
    public void setLabel(String label) {
        Label = label;
    }
    public void setModel(String model) {
        Model = model;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }
    public void setRentcost(int rentcost) {
        this.rentcost = rentcost;
    }
    public void setId(int id) {
        this.id = id;
    }

    boolean checked=false;
    @SerializedName("label")
    @Expose
    private String Label;
    @SerializedName("model")
    @Expose
    private String Model;
    @SerializedName("cost")
    @Expose
    private int cost;
    @SerializedName("rent")
    @Expose
    private int rentcost;
    @SerializedName("id")
    @Expose
    private int id;
    public Car(int id, String label, String model, int cost, int rentcost){
        this.id=id;
        Label=label;
        Model=model;
        this.cost=cost;
        this.rentcost=rentcost;
    }


}