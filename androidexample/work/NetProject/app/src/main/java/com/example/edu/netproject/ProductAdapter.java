package com.example.edu.netproject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<Product> data = null;

    public ProductAdapter(Context context, int layout, ArrayList<Product> data) {
        this.context = context;
        this.layout = layout;
        this.data = data;
    }

    public void setData(ArrayList<Product> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int positon) {
        return data.get(positon);
    }

    @Override
    public long getItemId(int positon) {
        return positon;
    }

    class ViewHolder{
        TextView tv1;
        TextView tv2;
    }

    @Override
    public View getView(int positon, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            view = View.inflate(context, layout, null);
            holder = new ViewHolder();
            holder.tv1 = view.findViewById(R.id.textView2);
            holder.tv2 = view.findViewById(R.id.textView3);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        Product product = data.get(positon);
        holder.tv1.setText(product.getName());
        holder.tv2.setText(product.getPrice() + "원");
        return view;
    }
}
