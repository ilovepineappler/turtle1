package com.example.edu.recyclerproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by user on 2017-10-06.
 */

public class MyAdpater extends RecyclerView.Adapter {
    private Context context;
    private ArrayList mItems;
    // Allows to remember the last item shown on screen
    private int lastPosition = -1;

    public MyAdpater(ArrayList items, Context mContext) {
        mItems = items;
        context = mContext;
    }
    // 필수로 Generate 되어야 하는 메소드 1 : 새로운 뷰 생성
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 새로운 뷰를 만든다
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    // 필수로 Generate 되어야 하는 메소드 2 : ListView의 getView 부분을 담당하는 메소드
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.v("MainActivity", "aaa");
        final Item mItem = (Item)mItems.get(position);
        MyViewHolder myViewHolder = (MyViewHolder)holder;
        myViewHolder.imageView.setImageResource(mItem.getImage());
        myViewHolder.textView.setText(mItem.getImagetitle());
        setAnimation(myViewHolder.imageView, position);
        myViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, mItem.getImagetitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // // 필수로 Generate 되어야 하는 메소드 3
    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public MyViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.imageView2);
            textView = (TextView) view.findViewById(R.id.textView2);
        }
    }
    private void setAnimation(View viewToAnimate, int position) {
        // 새로 보여지는 뷰라면 애니메이션을 해줍니다
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}

