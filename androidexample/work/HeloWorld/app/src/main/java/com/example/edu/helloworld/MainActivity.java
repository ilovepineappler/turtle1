package com.example.edu.helloworld;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.edu.helloworld.comm.LogManager;

public class MainActivity extends AppCompatActivity {
//    public static final String TAG = "aaa";

    EditText et = findViewById(R.id.editText) ;

    int a = 0 + 3;

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.btn_aaa:
                    LogManager.print("aaa");
                    et.getText();
                    break;
                case R.id.btn_bbb:
                    LogManager.print("bbb");
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

//        Button btn = (Button)findViewById(R.id.btn_aaa);
        Button btn = findViewById(R.id.btn_aaa);
        et = (EditText)findViewById(R.id.editText);

        int num1 = Integer.parseInt( et.getText().toString() );
        int cnt = 100;
        if(btn != null) {
//            btn.setText("korea");
            btn.setText( cnt + "" );  //String.valueOf(cnt)
//            btn.setText(R.string.app_name);
            btn.setOnClickListener(handler);
//            btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    LogManager.print("click");
//                }
//            });
        }

//        Button btn1 = findViewById(R.id.btn_bbb);
//        btn1.setOnClickListener(handler);
        findViewById(R.id.btn_bbb).setOnClickListener(handler);
//        LogManager.print("oncreate1");
//        LogManager.print("oncreate2");
//        LogManager.print("oncreate3");
//        LogManager.print("oncreate4");
//        Log.v(TAG,"oncreate1");
//        Log.v(TAG,"oncreate2");
//        Log.v(TAG,"oncreate3");
//        Log.v(TAG,"oncreate4");
    }
}
