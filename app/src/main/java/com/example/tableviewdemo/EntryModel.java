package com.example.tableviewdemo;

import java.io.Serializable;

public class EntryModel implements Serializable {
    private String API,  Auth, Cors,Category;

    public EntryModel() {
    }

    public EntryModel(String API,  String auth, String cors,String category) {
        this.API = API;
        this.Auth = auth;
        this.Cors = cors;
        this.Category = category;
    }


    public String getAPI() {
        return API;
    }

    public void setAPI(String API) {
        this.API = API;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getCors() {
        return Cors;
    }

    public void setCors(String cors) {
        Cors = cors;
    }

    public String getAuth() {
        return Auth;
    }

    public void setAuth(String auth) {
        Auth = auth;
    }

    @Override
    public String toString() {
        return "EntryModel{" +
                "API='" + API + '\'' +
                ", Auth='" + Auth + '\'' +
                ", Cors='" + Cors + '\'' +
                ", Category='" + Category + '\'' +
                '}';
    }
}
