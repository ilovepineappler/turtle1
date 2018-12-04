package com.example.edu.list3project.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edu.list3project.R;
import com.example.edu.list3project.model.MyItem;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private static final String TAG = "ListApp";
    Context context;
    int layout;
    ArrayList<MyItem> data;

    public MyAdapter(Context context, int layout, ArrayList<MyItem> data) {
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
        TextView tv1 ;
        TextView tv2;
        TextView tv3 ;
    }

    @Override
    public View getView( int position, View currentView, ViewGroup viewGroup) {
        Log.v(TAG, "getView position : " + position);
        ViewHolder holder = null;
        if(currentView == null) {
            currentView = View.inflate(context, layout, null);
            ImageView img = currentView.findViewById(R.id.imageView);
            TextView tv1 = currentView.findViewById(R.id.textView);
            TextView tv2 = currentView.findViewById(R.id.textView2);
            TextView tv3 = currentView.findViewById(R.id.textView3);
            holder = new ViewHolder();
            holder.img = img;
            holder.tv1 = tv1;
            holder.tv2 = tv2;
            holder.tv3 = tv3;
            currentView.setTag(holder);
            Log.v(TAG, "getView inflate position : " + position);
        }else{
            holder = (ViewHolder) currentView.getTag();
        }
//        ImageView img = currentView.findViewById(R.id.imageView);
//        TextView tv1 = currentView.findViewById(R.id.textView);
//        TextView tv2 = currentView.findViewById(R.id.textView2);
//        TextView tv3 = currentView.findViewById(R.id.textView3);


        final MyItem item = data.get(position);
        holder.img.setImageResource(item.imgRes);
        holder.tv1.setText(item.title);
        holder.tv2.setText(item.type);
        holder.tv3.setText(item.price + "원");
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, item.title, Toast.LENGTH_SHORT ).show();
//                Toast.makeText(context, data.get(position).title, Toast.LENGTH_SHORT ).show();
//                Intent intent = new Intent();
//                intent.putExtra("item", item);
//                context.startActivity(intent);
            }
        });


        if(position % 4 == 0){
            holder.tv1.setTextColor(0xff00ff00);
        }else {
            holder.tv1.setTextColor(0xff000000);
        }

        return currentView;
    }
}
