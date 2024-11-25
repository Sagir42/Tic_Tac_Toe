package com.example.tictacktoe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

    Context context;
    String []num;

    public MyAdapter(Context context, String []num){
        this.context = context;
        this.num = num;
    }

    @Override
    public int getCount() {
        return num.length;
    }

    @Override
    public Object getItem(int position) {
        return num[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item,parent,false);
            holder = new ViewHolder();
            holder.textView = convertView.findViewById(R.id.textView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText("   "+num[position]+"   ");
        return convertView;
    }
    private class ViewHolder{
        TextView textView;
    }
}
