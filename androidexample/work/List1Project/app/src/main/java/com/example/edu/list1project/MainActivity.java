package com.example.edu.list1project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> data = new ArrayList<String>();

    ArrayAdapter adapter = null;
//    String[] da = new String[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview);

        data.add("홍길동1");
        data.add("홍길동2");
        data.add("홍길동3");
        data.add("홍길동4");
        data.add("홍길동1");
        data.add("홍길동2");
        data.add("홍길동3");
        data.add("홍길동4");
        data.add("홍길동1");
        data.add("홍길동2");
        data.add("홍길동3");
        data.add("홍길동4");
        data.add("홍길동1");
        data.add("홍길동2");
        data.add("홍길동3");
        data.add("홍길동4");
        data.add("홍길동1");
        data.add("홍길동2");
        data.add("홍길동3");
        data.add("홍길동4");
        data.add("홍길동1");
        data.add("홍길동2");
        data.add("홍길동3");
        data.add("홍길동4");
        data.add("홍길동1");
        data.add("홍길동2");
        data.add("홍길동3");
        data.add("홍길동4");
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data );
        listView.setAdapter(adapter);
    }
}
