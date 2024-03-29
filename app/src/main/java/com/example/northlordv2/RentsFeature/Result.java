package com.example.northlordv2.RentsFeature;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.net.URLDecoder;

public class Result {
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("id")
    @Expose
    int  id;
    @SerializedName("carid")
    @Expose
    int carid;
    @SerializedName("startdate")
    @Expose
    String startdate;
    @SerializedName("starttime")
    @Expose
    String starttime;
    @SerializedName("enddate")
    @Expose
    String enddate;
    @SerializedName("endtime")
    @Expose
    String endtime;
    @SerializedName("cost")
    @Expose
    int cost;
    @SerializedName("label")
    @Expose
    String label;
    @SerializedName("model")
    @Expose
    String model;
    public  void decode(){
        try {
            this.name=URLDecoder.decode(name,"UTF-8");
            this.label=URLDecoder.decode(label,"UTF-8");
            this.model=URLDecoder.decode(model,"UTF-8");

        }catch (Exception e){

        }

    }
    public Result(String name, int id, String startdate, String starttime, String enddate, String endtime, int cost, String label, String model) {
        this.name = name;
        this.id = id;
        this.startdate = startdate;
        this.starttime = starttime;
        this.enddate = enddate;
        this.endtime = endtime;
        this.cost = cost;
        this.label = label;
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


}
