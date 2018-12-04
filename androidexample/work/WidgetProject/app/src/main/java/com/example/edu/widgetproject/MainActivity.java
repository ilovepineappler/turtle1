package com.example.edu.widgetproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
//    View.OnClickListener handler = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//
//        }
//    };

    public void doAction1(View view){
        Log.v("Widget","테스트" );
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        findViewById(R.id.button).setOnClickListener(handler);
//        findViewById(R.id.button2).setOnClickListener(handler);
//        findViewById(R.id.button3).setOnClickListener(handler);

//        View view = findViewById(R.id.button3);
//        if(view != null) {
//            view.setOnClickListener(handler);
//        }

    }
}
