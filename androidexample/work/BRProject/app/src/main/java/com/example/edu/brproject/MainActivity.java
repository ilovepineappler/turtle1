package com.example.edu.brproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "BRApp";
    EditText et;

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            String data = "";
            switch(action){
                case "com.example.edu.brproject.intent.action.LOAD" :
                    data = intent.getStringExtra("fName");
                    et.setText("LOAD : " + data);
                    break;
                case "com.example.edu.brproject.intent.action.SAVE" :
                    data = intent.getStringExtra("title");
                    et.setText("SAVE : " + data);
                    break;
                case "com.example.edu.brproject.intent.action.DELETE" :
                    data = intent.getStringExtra("name");
                    et.setText("DELETE : " + data);
                    break;
            }
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.edu.brproject.intent.action.LOAD");
        filter.addAction("com.example.edu.brproject.intent.action.SAVE");
        filter.addAction("com.example.edu.brproject.intent.action.DELETE");

        registerReceiver(receiver, filter);
    }

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.button :
                    doAction1();
                    break;
                case R.id.button2 :
                    doAction2();
                    break;
                case R.id.button3 :
                    doAction3();
                    break;
            }
        }
    };
    void doAction3(){
        Intent intent = new Intent();
        intent.setAction("com.example.edu.brproject.intent.action.DELETE");
        intent.putExtra("name","test.mp3");
        sendBroadcast(intent);
    }
    void doAction2(){
        Intent intent = new Intent();
        intent.setAction("com.example.edu.brproject.intent.action.LOAD");
        intent.putExtra("fName","sam.mp3");
        sendBroadcast(intent);
    }
    void doAction1(){
        Intent intent = new Intent();
        intent.setAction("com.example.edu.brproject.intent.action.SAVE");
        intent.putExtra("title","홍길동전");
        sendBroadcast(intent);
        Log.v(TAG, "send Ok");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(handler);
        findViewById(R.id.button2).setOnClickListener(handler);
        findViewById(R.id.button3).setOnClickListener(handler);

        et = findViewById(R.id.editText);
    }
}
