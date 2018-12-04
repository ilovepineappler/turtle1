package com.example.edu.recyclerproject;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Context mContext;

    RecyclerView recyclerView;
    RecyclerView.Adapter Adapter;
    ArrayList items = null;
    RecyclerView.LayoutManager layoutManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);


        // Item 리스트에 아이템 객체 넣기
        items = new ArrayList<>();

        items.add(new Item(R.drawable.a, "미키마우스"));
        items.add(new Item(R.drawable.b, "인어공주"));
        items.add(new Item(R.drawable.c, "디즈니공주"));
        items.add(new Item(R.drawable.d, "토이스토리"));
        items.add(new Item(R.drawable.e, "니모를 찾아서"));

        // StaggeredGrid 레이아웃을 사용한다
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        layoutManager = new LinearLayoutManager(this);
//        layoutManager = new GridLayoutManager(this,3);

        // 지정된 레이아웃매니저를 RecyclerView에 Set 해주어야한다.
        recyclerView.setLayoutManager(layoutManager);

        Adapter = new MyAdpater(items, mContext);
        recyclerView.setAdapter(Adapter);
    }
}
