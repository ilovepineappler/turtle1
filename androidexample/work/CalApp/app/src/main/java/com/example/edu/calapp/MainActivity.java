package com.example.edu.calapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "CalApp";
    EditText et1, et2, et3;

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//            String aa = "dd";
            switch(view.getId()){
                case R.id.button :   // byte, short, char,int,    //long,float, double, boolean
                    doAction1();
                    break;
                case R.id.button2 :   // byte, short, char,int,    //long,float, double, boolean
                    doAction2();
                    break;
                case R.id.button3 :   // byte, short, char,int,    //long,float, double, boolean
                    doAction3();
                    break;
                case R.id.button4 :   // byte, short, char,int,    //long,float, double, boolean
                    doAction4();
                    break;
                case R.id.button5 :   // byte, short, char,int,    //long,float, double, boolean
                    doAction5();
                    break;
            }
        }
    };
    void doAction5(){
        et1.setText("");
        et2.setText("");
        if(et3 != null) {
            et3.setText("");
        }
    }
    void doAction4(){
        String st1 = et1.getText().toString();
        String st2 = et2.getText().toString();
        int num1 = Integer.parseInt(st1);
        int num2 = Integer.parseInt(st2);
        int result = num1 / num2;
        et3.setText(result + "");
    }
    void doAction3(){
        String st1 = et1.getText().toString();
        String st2 = et2.getText().toString();
        int num1 = Integer.parseInt(st1);
        int num2 = Integer.parseInt(st2);
        int result = num1 * num2;
        et3.setText(result + "");
    }
    void doAction2(){
        String st1 = et1.getText().toString();
        String st2 = et2.getText().toString();
        int num1 = Integer.parseInt(st1);
        int num2 = Integer.parseInt(st2);
        int result = num1 - num2;
        et3.setText(result + "");
    }
    void doAction1(){
        String st1 = et1.getText().toString();
        String st2 = et2.getText().toString();
        int num1 = 0;
        try {
            num1 = Integer.parseInt(st1);
        }catch(NumberFormatException e){
            Log.v(TAG, "데이터 오류");
        }
        int num2 = Integer.parseInt(st2);
        int result = num1 + num2;
        et3.setText(result + "");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(handler);
        findViewById(R.id.button2).setOnClickListener(handler);
        findViewById(R.id.button3).setOnClickListener(handler);
        findViewById(R.id.button4).setOnClickListener(handler);
        findViewById(R.id.button5).setOnClickListener(handler);

        et1 = findViewById( R.id.editText );
        et2 = findViewById( R.id.editText2 );
        et3 = findViewById( R.id.editText3 );

    }
}
