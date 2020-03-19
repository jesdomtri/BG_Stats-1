package com.example.bg_stats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context ctx;
    private ArrayList<ArrayList<String>> listOfRows;

    public GridAdapter(Context ctx, ArrayList<ArrayList<String>> listOfRows) {

        this.ctx = ctx;
        this.listOfRows = listOfRows;
    }
    @Override
    public int getCount() {
        return listOfRows.size();
    }

    @Override
    public Object getItem(int position) {
        return listOfRows.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);


        return itemView;
    }
}
