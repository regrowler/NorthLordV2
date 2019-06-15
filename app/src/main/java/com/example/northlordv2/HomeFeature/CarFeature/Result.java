package com.example.northlordv2.HomeFeature.CarFeature;

import com.example.northlordv2.HomeFeature.CarFeature.Car;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    @SerializedName("results")
    List<Car>cars;
    @SerializedName("login")
    String login;



    @SerializedName("password")
    String password;
    @SerializedName("result")
    String res;

    public Result(List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Result(List<Car> cars, String login, String password) {
        this.cars = cars;
        this.login = login;
        this.password = password;
    }
}
