package com.example.edu.inflateproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        LinearLayout view = new LinearLayout(this);
//        view.setBackgroundColor(Color.YELLOW); //0xffffff00
//
//        TextView tv = new TextView(this);
//        tv.setText("한성대");
//        tv.setTextColor(Color.RED);
//        tv.setTextSize(50);
//
//        view.addView(tv);
//
//        setContentView(view);

//        LayoutInflater inflater = getLayoutInflater();
//        View view = inflater.inflate(R.layout.activity_main, null);

//        View view = View.inflate(this, R.layout.activity_main, null);
//        setContentView(view);

        setContentView(R.layout.activity_main);
        findViewById(R.id.textView);
        int a = R.drawable.daum;
    }
}
