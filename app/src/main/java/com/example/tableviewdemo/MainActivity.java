package com.example.tableviewdemo;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TableLayout tb;
 public static ArrayList<EntryModel> entryModelArrayList = new ArrayList<>() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tb = findViewById(R.id.table_layout);
       GetAllData getAllData = new GetAllData();
       getAllData.execute();
       tb.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

           }
       });


    }

    public  class  GetAllData extends AsyncTask<String,String,String> {


        @Override
        protected String doInBackground(String... strings) {

            String responseTxt = null;
            OkHttpClient client = new OkHttpClient();
            try {
                Request request = new Request.Builder()
                        .url("https://api.publicapis.org/entries")
                        .build();

                okhttp3.Response response = null;
                response = client.newCall(request).execute();
                if (!response.isSuccessful()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(MainActivity.this, "data not found ", Toast.LENGTH_SHORT).show();
                        }
                    });
                    throw new IOException("okHTTP Error: " + response);

                } else {

                    responseTxt = response.body().string();
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(responseTxt);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JSONArray jsonArray = jsonObject.getJSONArray("entries");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject = jsonArray.getJSONObject(i);
                        String api = jsonObject.getString("API");
                        String auth = jsonObject.getString("Auth");
                        String cors = jsonObject.getString("Cors");
                        String category = jsonObject.getString("Category");
                        entryModelArrayList.add(new EntryModel(api,auth,cors,category));

                    }
                }

                return null;
            } catch (JSONException | IOException jsonException) {
                jsonException.printStackTrace();
            }
            return responseTxt;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            addHeaders();
            addRows();
        }
    }


    private void addRows() {
        for (int i = 0; i<entryModelArrayList.size();i++){
//            getDataTable();
            TableRow tr = new TableRow(MainActivity.this);
            tr.setLayoutParams(getLayoutParams());

            tr.addView(getRowsTextView(0,entryModelArrayList.get(i).getAPI(),Color.BLACK,Typeface.BOLD,R.drawable.cell_shape));
            tr.addView(getRowsTextView(0,entryModelArrayList.get(i).getAuth(),Color.BLACK,Typeface.BOLD,R.drawable.cell_shape));
            tr.addView(getRowsTextView(0,entryModelArrayList.get(i).getCors(),Color.BLACK,Typeface.BOLD,R.drawable.cell_shape));
            tr.addView(getRowsTextView(0,entryModelArrayList.get(i).getCategory(),Color.BLACK,Typeface.BOLD,R.drawable.cell_shape));
            tb.addView(tr,getTbLayoutParams());


        }
    }

    private TextView getRowsTextView(int id,String title, int color, int typeface, int bgColor) {
        TextView tv = new TextView(this);
        tv.setId(id);
        tv.setText(title.toUpperCase());
        tv.setTextColor(color);
        tv.setPadding(40,40,40,40);
        tv.setTypeface(Typeface.DEFAULT,typeface);
        tv.setBackgroundResource(bgColor);
        tv.setLayoutParams(getLayoutParams());
        tv.setOnClickListener(this);
        return tv;
    }

    private void addHeaders() {
//        TableLayout tableLayout = (TableLayout) findViewById(R.id.table_layout);
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(getLayoutParams());

        tr.addView(getTextView(0,"API", Color.BLUE, Typeface.BOLD,R.drawable.cell_shape));
        tr.addView(getTextView(0,"Auth", Color.BLUE, Typeface.BOLD,R.drawable.cell_shape));
//        tr.addView(getTextView(0,"HTTP", Color.BLACK, Typeface.BOLD,R.drawable.cell_shape));
        tr.addView(getTextView(0,"Cors", Color.BLUE, Typeface.BOLD,R.drawable.cell_shape));
        tr.addView(getTextView(0,"Category", Color.BLUE, Typeface.BOLD,R.drawable.cell_shape));

        tb.addView(tr,getTbLayoutParams());



    }

    private TextView getTextView(int id,String title, int color, int typeface, int bgColor){
        TextView tv = new TextView(this);
        tv.setId(id);
        tv.setText(title.toUpperCase());
        tv.setTextColor(color);
        tv.setPadding(20,20,20,20);
        tv.setBackgroundColor(bgColor);
        tv.setBackgroundResource(bgColor);
        tv.setGravity(View.TEXT_ALIGNMENT_CENTER);
        tv.setLayoutParams(getLayoutParams());
        tv.setOnClickListener(this);
        return tv;
    }
    private TableRow.LayoutParams getLayoutParams() {
        TableRow.LayoutParams params = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(1,1,1,1);
        params.weight = 3;
        return params;
    }


    private TableLayout.LayoutParams getTbLayoutParams() {
        return new TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT
        );
    }

    @Override
    public void onClick(View view) {

    }


}