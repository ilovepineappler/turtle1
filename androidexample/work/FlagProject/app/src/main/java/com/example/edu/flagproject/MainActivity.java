package com.example.edu.flagproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "FlagApp";
    EditText et;
    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.button :
                    doAction1();
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



    void doAction4(){
        new Thread(){
            public void run(){
                for(int i = 0 ; i < 4; i++) {
                    Intent intent = new Intent(MainActivity.this, SubActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.putExtra("cnt", i);
                    startActivity(intent);
                    SystemClock.sleep(5000);
                }
            }
        }.start();
    }
    int cnt = 0;
    void doAction3(){
        cnt++;
        et.setText("cnt:" + cnt);
    }
    void doAction1(){
        Intent intent = new Intent(this, SubActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(handler);
        findViewById(R.id.button3).setOnClickListener(handler);
        findViewById(R.id.button4).setOnClickListener(handler);
        et = findViewById(R.id.editText);
        Log.v(TAG, "Main onCreate ");
    }
}
