package com.example.edu.viewproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Date d = new Date();
//        int year = d.getYear();
        Calendar d = Calendar.getInstance();
        int year = d.get(Calendar.YEAR);

    }
}
