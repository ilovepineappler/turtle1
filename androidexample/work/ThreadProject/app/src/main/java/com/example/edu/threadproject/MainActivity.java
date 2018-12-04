package com.example.edu.threadproject;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "ThreadApp";
    EditText et1, et2;
    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.button:
                    doAction1();
                    break;
                case R.id.button2:
                    doAction2();
                    break;
            }
        }
    };
    void doAction2(){
        onAir = false;
        trd1 = null;
        System.gc();
    }

    @Override
    protected void onStop() {
        super.onStop();
        doAction2();
    }

    //    void aa(){
//        int[] arr = {1,2,3,4,5};
//        int[] brr = {10,20,30,40,50};
////        brr = arr;
////        for(int i = 0; i< 3 ; i++){
////            brr[i+2] = arr[i];
////        }
//        System.arraycopy(arr, 0, brr, 0, 5);
//    }
    Ani1Thread trd1 = null;
    void doAction1(){
        if(trd1 == null) {
            trd1 = new Ani1Thread();
            onAir = true;
            trd1.start();
        }
    }
    boolean onAir = false;
    class Ani1Thread extends Thread{
        public void run(){
            int cnt = 0;
            while( onAir ){
                cnt++;
                Log.v(TAG, "cnt : " + cnt);
                et1.setText("cnt : " + cnt);
                SystemClock.sleep(500);
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(handler);
        findViewById(R.id.button2).setOnClickListener(handler);


        et1 = findViewById(R.id.editText);
        et2 = findViewById(R.id.editText2);
    }
}
