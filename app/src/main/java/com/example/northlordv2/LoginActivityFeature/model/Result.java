package com.example.northlordv2.LoginActivityFeature.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.net.URLDecoder;

public class Result {
    @SerializedName("login")
    @Expose
    private String log;
    @SerializedName("password")
    @Expose
    private String pas;
    @SerializedName("result")
    @Expose
    private String res;
    @SerializedName("id")
    @Expose
    private int id;

    public String getLog() {
        return log;
    }
    public String getRes() {
        return res;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setRes(String res) {
        try {
            this.res = URLDecoder.decode(res,"UTF-8");
            return;
        }catch (Exception e){
            e.printStackTrace();
        }
        this.res="";
    }
    public String getPas() {
        return pas;
    }
    public void setPas(String pas) {
        try {
            this.pas = URLDecoder.decode(pas,"UTF-8");
            return;
        }catch (Exception e){
            e.printStackTrace();
        }
        this.pas="";
    }
    public Result(String log, String pas, String res) {
        this.log = log;
        this.pas = pas;
        this.res = res;
    }

    public void setLog(String log) {
        try {
            this.log = URLDecoder.decode(log,"UTF-8");
            return;
        }catch (Exception e){
            e.printStackTrace();
        }
        this.log="";
    }




}
