package com.example.bg_stats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ExpandableAdapter extends BaseExpandableListAdapter {
    Context ctx;
    public static ArrayList<ArrayList<String>> childList;
    private String[] parents;

    public ExpandableAdapter(Context ctx, ArrayList<ArrayList<String>> childList,  String[] parents){

        this.ctx = ctx;
        this.childList = childList;
        this.parents = parents;

    }

    @Override
    public int getGroupCount() {
        return childList.size();
    }

    @Override
    public int getChildrenCount(int parent) {
        return childList.get(parent).size();
    }

    @Override
    public Object getGroup(int parent) {

        return parents[parent];
    }

    @Override
    public Object getChild(int parent, int child) {
        return childList.get(parent).get(child);
    }

    @Override
    public long getGroupId(int parent) {
        return parent;
    }

    @Override
    public long getChildId(int parent, int child) {
        return child;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int parent, boolean isExpanded, View convertView, ViewGroup parentview) {

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.parent_layout, parentview, false);

        }

        TextView parent_textview = (TextView) convertView.findViewById(R.id.parent_txt);
        parent_textview.setText(parents[parent]);
        return  convertView;
    }

    @Override
    public View getChildView(int parent, int child, boolean isLastChild, View convertView, ViewGroup parentview) {

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_layout, parentview, false);

        }

        TextView child_textview = (TextView) convertView.findViewById(R.id.child_txt);
        child_textview.setText(getChild(parent,child).toString());
        return  convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        if (childPosition == 0) {
            return false;
        } else {
            return true;
        }
    }
}
