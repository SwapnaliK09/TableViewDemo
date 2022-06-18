package com.example.tableviewdemo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyApi {

    @GET("posts")
    Call<ArrayList<DataModel>> callModel();

    @GET("entries")
    Call<String> getData();
}
