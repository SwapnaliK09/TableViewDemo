package com.example.tableviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LIstViewDemo extends AppCompatActivity {
    private ArrayList<DataModel> dataModelArrayList = new ArrayList<>();
    private  MyApi myApi;
    private ListView listView;
    private String BASE_URL = "https://jsonplaceholder.typicode.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_demo);
        listView = findViewById(R.id.listView);


        getRetrofitData();
    }

    private void getRetrofitData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myApi =retrofit.create(MyApi.class);
        Call<ArrayList<DataModel>> arrayListCall = myApi.callModel();
        arrayListCall.enqueue(new Callback<ArrayList<DataModel>>() {
            @Override
            public void onResponse(Call<ArrayList<DataModel>> call, Response<ArrayList<DataModel>> response) {
                final ProgressDialog dialog = new ProgressDialog(LIstViewDemo.this);
                dialog.setMessage("Please Wait...");
                dialog.setCancelable(false);
                dialog.show();
                dataModelArrayList = response.body();

                for (int i = 0 ;i<dataModelArrayList.size();i++);

                CustomAdapter customAdapter = new CustomAdapter(dataModelArrayList,R.layout.list_item,LIstViewDemo.this);
                listView.setAdapter(customAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<DataModel>> call, Throwable t) {

                Toast.makeText(LIstViewDemo.this,"Failed to load data",Toast.LENGTH_SHORT).show();

            }
        });
    }
}