package com.example.edu.asyncproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class SubActivity extends AppCompatActivity {
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        et = findViewById(R.id.editText2);

        Intent intent = getIntent();
        Person p = (Person) intent.getSerializableExtra("person");
        et.setText( p.toString() );
    }
}
