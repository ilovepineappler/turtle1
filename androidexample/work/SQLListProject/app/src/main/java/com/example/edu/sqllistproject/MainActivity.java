package com.example.edu.sqllistproject;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.edu.sqllistproject.adapter.PersonAdapter;
import com.example.edu.sqllistproject.helper.MyDBHelper;
import com.example.edu.sqllistproject.model.Person;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="SQLListApp";

    ListView listView;
    ArrayList<Person> data = new ArrayList<Person>();
    PersonAdapter adapter;

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button:
                    doAction1();
                    break;
            }
        }
    };
    void doAction1(){

        new ReadThread().start();
    }
    Handler uiHandler = new Handler();
    MyDBHelper helper;
    class ReadThread extends Thread{
        public void run(){
            SQLiteDatabase db = helper.getReadableDatabase();

            Cursor c = null;
            try {
                c = db.query("person", null, null, null, null, null, "_id desc");
                Person person;
                data.clear();
                while(c.moveToNext()){
                    person = new Person();
                    person.set_id( c.getInt(0) );
                    person.setName(c.getString(1));
                    person.setAge(c.getInt(2));
                    person.setAddr(c.getString(3));
                    person.setType(c.getInt(4));
                    data.add(person);
                }

                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });

            }catch(SQLException e){
                Log.v(TAG, "로딩 오류 : " + e);
            }finally {
                if(c != null){
                    c.close();
                }
            }

            db.close();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);
        findViewById(R.id.button).setOnClickListener(handler);

        adapter = new PersonAdapter(this, R.layout.person, data);
        listView.setAdapter(adapter);

        helper = new MyDBHelper(this, "person.db", null, 1);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(helper != null){
            helper.close();
            helper = null;
        }
    }
}
