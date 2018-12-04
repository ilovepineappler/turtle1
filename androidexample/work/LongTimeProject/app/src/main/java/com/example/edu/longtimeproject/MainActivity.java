package com.example.edu.longtimeproject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "LongApp";
    EditText et;
    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.button2 :
                    new JobThread().start();
//                    doAction1();
                    break;
            }
        }
    };
    class JobThread extends  Thread{
        public void run(){
            doAction1();
        }
    }
    UIHandler uiHandler;
    class UIHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case 100 :
                    et.setText("작업완료");
                    break;
            }
        }
    }
    void doAction1(){
        for(int i = 0 ;i < 10; i++){
            Log.v(TAG, "i : " + i);
            SystemClock.sleep(1000);
        }
        uiHandler.sendEmptyMessage(100);
//        et.setText("작업완료");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button2).setOnClickListener(handler);
        et = findViewById(R.id.editText);
        uiHandler = new UIHandler();
    }
}
