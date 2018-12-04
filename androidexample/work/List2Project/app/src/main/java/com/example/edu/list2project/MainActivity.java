package com.example.edu.list2project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "ListApp";
    EditText et;
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> data = new ArrayList<String>();

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.button:
                    doAction1();
                    break;
                case R.id.button2:
                    doAction2();
                    break;
            }
        }
    };
    void doAction1(){
        int idx = listView.getCheckedItemPosition();
        switch(idx){
            case ListView.INVALID_POSITION:
                Toast.makeText(this, "항목을 선택한 후에 삭제 버튼을 누르세요", Toast.LENGTH_SHORT).show();
                break;
                default:
                    data.remove(idx);
                    listView.clearChoices();
                    adapter.notifyDataSetChanged();
                    break;
        }
    }
    void doAction2(){
        String title = et.getText().toString();
        data.add(title);
        et.setText("");
        adapter.notifyDataSetChanged();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.editText);
        listView = findViewById(R.id.listView);

        findViewById(R.id.button).setOnClickListener(handler);
        findViewById(R.id.button2).setOnClickListener(handler);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, data);
//        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(itemClickListener);
    }

    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            Toast.makeText(MainActivity.this, data.get(position), Toast.LENGTH_SHORT).show();
        }
    };
}
