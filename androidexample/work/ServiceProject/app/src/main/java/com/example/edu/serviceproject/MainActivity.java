package com.example.edu.serviceproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "ServiceApp";
    EditText et;

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            et.setText("cnt : " + intent.getIntExtra("cnt",0));
        }
    };

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
                case R.id.button4 :
                    doAction4();
                    break;
            }
        }
    };
    void doAction3(){
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.edu.serviceproject.intent.action.COUNT");
        registerReceiver(receiver, filter);
    }
    void doAction4(){
        unregisterReceiver(receiver);
    }
    void doAction2(){
//서비스 종료
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }
    void doAction1(){
//서비스 시작
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("name", "a.mp3");
        startService(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(handler);
        findViewById(R.id.button2).setOnClickListener(handler);
        findViewById(R.id.button3).setOnClickListener(handler);
        findViewById(R.id.button4).setOnClickListener(handler);

        et = findViewById(R.id.editText);
    }
}
