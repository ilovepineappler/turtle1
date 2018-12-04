package com.example.edu.iottermproject.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.edu.iottermproject.R;
import com.example.edu.iottermproject.comm.LogManager;
import com.example.edu.iottermproject.item.TempVO;

import java.util.ArrayList;


/**
 * Created by kim111 on 2016-07-06.
 */
public class TempAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<TempVO> list;

    public TempAdapter(Context context, int layout, ArrayList<TempVO> list){
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    public void setList(ArrayList<TempVO> list) {
        this.list = list;
        LogManager.print(context, "list size" + list.size());
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    class ViewHolder{
        private TextView txtLoc;
        private TextView txtTemp;
        private TextView txtDate;

    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(view == null){
            view = View.inflate(context, layout, null);
            holder = new ViewHolder();
            holder.txtDate = (TextView)view.findViewById(R.id.txtDate);
            holder.txtLoc = (TextView)view.findViewById(R.id.txtLoc);
            holder.txtTemp = (TextView)view.findViewById(R.id.txtTemp);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        holder.txtDate.setText(list.get(i).getIdate());
        holder.txtTemp.setText(list.get(i).getTemperature());
        holder.txtLoc.setText(list.get(i).getLoc());
        switch (list.get(i).getType()){
            case "L":
                holder.txtTemp.setBackgroundColor(Color.YELLOW);
                break;
            case "T":
                holder.txtTemp.setBackgroundColor(Color.GREEN);
                break;
            case "H":
                holder.txtTemp.setBackgroundColor(Color.RED);
                break;
        }
        return view;
    }
}
