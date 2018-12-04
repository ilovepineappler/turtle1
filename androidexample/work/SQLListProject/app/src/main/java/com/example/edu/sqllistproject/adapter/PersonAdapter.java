package com.example.edu.sqllistproject.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.edu.sqllistproject.R;
import com.example.edu.sqllistproject.model.Person;

import java.util.ArrayList;

public class PersonAdapter extends BaseAdapter {

    Context context;
    int layout;
    ArrayList<Person> data;

    int[] imgRes = {R.drawable.icon01, R.drawable.icon02, R.drawable.icon03};

    public PersonAdapter(Context context, int layout, ArrayList<Person> data) {
        this.context = context;
        this.layout = layout;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder{
        ImageView img;
        TextView tv1;
        TextView tv2;
        TextView tv3;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(view == null){
            view  = View.inflate(context, layout, null);
            holder = new ViewHolder();
            holder.img = view.findViewById(R.id.imageView);
            holder.tv1 = view.findViewById(R.id.textView);
            holder.tv2 = view.findViewById(R.id.textView2);
            holder.tv3 = view.findViewById(R.id.textView3);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        Person person = data.get(position);
        holder.img.setImageResource(imgRes[person.getType()]);
        holder.tv1.setText(person.get_id() + " 번");
        holder.tv2.setText(person.getName());
        holder.tv3.setText(person.getAge() + "살");
        return view;
    }
}








