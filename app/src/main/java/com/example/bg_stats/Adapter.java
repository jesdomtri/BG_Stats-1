package com.example.bg_stats;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    private ArrayList<Entidad> listItems;
    private Context context;

    public Adapter(ArrayList<Entidad> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }


    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int i) {
        return listItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Entidad item = (Entidad) getItem(i);

        view = LayoutInflater.from(context).inflate(R.layout.image_dice, null);
        ImageView image = (ImageView) view.findViewById(R.id.imgDice);

        image.setImageResource(item.getImage());

        return view;
    }
}
