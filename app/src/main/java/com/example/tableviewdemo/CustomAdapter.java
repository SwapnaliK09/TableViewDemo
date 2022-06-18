package com.example.tableviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private ArrayList<DataModel> dataModelArrayList;
    private int layout;
    private Context context;

    public CustomAdapter(ArrayList<DataModel> dataModelArrayList, int layout, Context context) {
        this.dataModelArrayList = dataModelArrayList;
        this.layout = layout;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dataModelArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataModelArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder {
        TextView t1, t2, t3;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = new ViewHolder();
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       view = layoutInflater.inflate(layout,null);

       viewHolder.t1 = view.findViewById(R.id.getTxt1);
       viewHolder.t2 = view.findViewById(R.id.getTxt2);
       viewHolder.t3 = view.findViewById(R.id.getTxt3);

       DataModel dataModel = dataModelArrayList.get(i);
       viewHolder.t1.setText("Id: "+dataModel.getId()+"\n");
       viewHolder.t2.setText("Title: "+dataModel.getTitle()+"\n");
       viewHolder.t3.setText("Body: "+dataModel.getBody()+"\n");
        return view;


    }
}
