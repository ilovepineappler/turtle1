package com.example.edu.flagproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class SubActivity extends AppCompatActivity {
    private static final String TAG = "FlagApp";
    EditText et;
    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.button2 :
                    doAction1();
                    break;
                case R.id.button5 :
                    doAction5();
                    break;
            }
        }
    };
    void doAction5(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setType("aaa");
        startActivity(intent);
    }
    void doAction1(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
//        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        findViewById(R.id.button2).setOnClickListener(handler);
        findViewById(R.id.button5).setOnClickListener(handler);
        et = findViewById(R.id.editText2);
        Log.v(TAG, "Sub onCreate ");
        Intent intent = getIntent();
        int cnt = intent.getIntExtra("cnt", 100);
        et.setText("cnt : " + cnt);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int cnt = intent.getIntExtra("cnt", 100);
        et.setText("cnt : " + cnt);
        Log.v(TAG, "Sub onNewIntent ");
    }
}
