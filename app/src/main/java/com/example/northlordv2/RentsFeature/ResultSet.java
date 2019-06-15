package com.example.northlordv2.RentsFeature;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultSet {
    @SerializedName("result")
    @Expose
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<Result> getRents() {
        return rents;
    }

    public void setRents(List<Result> rents) {
        this.rents = rents;
    }

    public ResultSet(String result, List<Result> rents) {
        this.result = result;
        this.rents = rents;
    }

    @SerializedName("rents")
    @Expose
    private List<Result> rents;
}
