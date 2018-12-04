package com.example.edu.resultproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Sub2Activity extends AppCompatActivity {
    EditText et1;
    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.button2:
                    doAction1();
                    break;
            }
        }
    };
    void doAction1(){
        Intent data = new Intent();
        data.putExtra("rData", et1.getText().toString());
        setResult(RESULT_OK, data);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);

        et1 = findViewById(R.id.editText);

        findViewById(R.id.button2).setOnClickListener(handler);

        Intent intent = getIntent();
        String name  = intent.getStringExtra("name");
        if(name != null) {
            name = name.toUpperCase();
        }
        int cnt = intent.getIntExtra("cnt",100);
        et1.setText(name + cnt);
    }
}
