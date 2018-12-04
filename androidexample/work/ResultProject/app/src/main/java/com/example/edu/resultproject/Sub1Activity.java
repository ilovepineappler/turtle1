package com.example.edu.resultproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Sub1Activity extends AppCompatActivity {
    private static final String TAG  = "ActivityApp";
    EditText et1;
    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.button2:
                    doAction1();
                    break;
                case R.id.button5:
                    doAction5();
                    break;
            }
        }
    };
    void doAction5(){
        setResult( Comm.RESULT_EXIT );
        finish();
    }
    void doAction1(){
        Intent data = new Intent();
        data.putExtra("rData", et1.getText().toString());
        setResult(RESULT_OK, data);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub1);

        et1 = findViewById(R.id.editText);

        findViewById(R.id.button2).setOnClickListener(handler);
        findViewById(R.id.button5).setOnClickListener(handler);

        Intent intent = getIntent();
        String name  = intent.getStringExtra("name");
        if(name != null) {
            name = name.toUpperCase();
        }
        int cnt = intent.getIntExtra("cnt",100);
        et1.setText(name + cnt);
        Log.v(TAG, "Sub onCreate: " + savedInstanceState );
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "Sub onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "Sub onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "Sub onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "Sub onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "Sub onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v(TAG, "Sub onRestart");
    }

}
