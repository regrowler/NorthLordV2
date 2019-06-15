package com.example.northlordv2.application;



public class DataModule {


    int id;
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
    public int getId() {
        return id;
    }

    public void setData(DataModule module){
        this.id = module.id;
        this.login = module.login;
        this.password = module.password;
    }
    public DataModule(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }





}
