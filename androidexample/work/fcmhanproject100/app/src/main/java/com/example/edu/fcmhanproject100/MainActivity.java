package com.example.edu.fcmhanproject100;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "FCMApp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Log.v(TAG, "id : " + FirebaseInstanceId.getInstance().getId());
//        Log.v(TAG, "token: " + FirebaseInstanceId.getInstance().getToken());
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        if(title == null){
            Log.v(TAG, "런처 스타트");
        }else{
            Log.v(TAG, "노티 스타드");
        }
    }
}
