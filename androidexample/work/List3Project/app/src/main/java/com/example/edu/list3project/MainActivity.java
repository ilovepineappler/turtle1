package com.example.edu.list3project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.example.edu.list3project.adapter.MyAdapter;
import com.example.edu.list3project.model.MyItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "ListApp";
//    ListView listView;
    GridView gridView;
    ArrayList<MyItem> data = new ArrayList<MyItem>();
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);
//        listView = findViewById(R.id.listView);

//        MyItem item = new MyItem(R.drawable.icon01, "친구찾기", "유틸리티", 900);
        data.add( new MyItem(R.drawable.icon01, "친구찾기1", "유틸리티", 900) );
        data.add( new MyItem(R.drawable.icon02, "친구찾기2", "유틸리티", 1900) );
        data.add( new MyItem(R.drawable.icon01, "친구찾기3", "유틸리티", 900) );
        data.add( new MyItem(R.drawable.icon03, "친구찾기4", "유틸리티", 3900) );
        data.add( new MyItem(R.drawable.icon04, "친구찾기5", "유틸리티", 900) );
        data.add( new MyItem(R.drawable.icon05, "친구찾기6", "유틸리티", 7900) );
        data.add( new MyItem(R.drawable.icon06, "친구찾기7", "유틸리티", 900) );
        data.add( new MyItem(R.drawable.icon01, "친구찾기8", "유틸리티", 4900) );
        data.add( new MyItem(R.drawable.icon04, "친구찾기9", "유틸리티", 800) );
        data.add( new MyItem(R.drawable.icon01, "친구찾기10", "유틸리티", 0) );
        data.add( new MyItem(R.drawable.icon03, "친구찾기11", "유틸리티", 900) );
        data.add( new MyItem(R.drawable.icon02, "친구찾기12", "유틸리티", 1200) );
        data.add( new MyItem(R.drawable.icon05, "친구찾기13", "유틸리티", 7900) );
        data.add( new MyItem(R.drawable.icon06, "친구찾기14", "유틸리티", 200) );
        data.add( new MyItem(R.drawable.icon01, "친구찾기15", "유틸리티", 2900) );


        adapter = new MyAdapter(this, R.layout.item, data);

        gridView.setAdapter(adapter);
//        listView.setAdapter(adapter);
    }
}
