package com.example.northlordv2.ProfileFeature;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("name")
    String name;
    @SerializedName("surname")
    String surname;
    @SerializedName("email")
    String email;
    @SerializedName("cars")
    int cars;
    @SerializedName("profit")
    int profit;
    @SerializedName("result")
    String result;

    public Result(String name, String surname, String email, int cars, int profit, String result) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.cars = cars;
        this.profit = profit;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCars() {
        return cars;
    }

    public void setCars(int cars) {
        this.cars = cars;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


}
