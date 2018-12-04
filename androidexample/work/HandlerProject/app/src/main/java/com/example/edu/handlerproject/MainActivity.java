package com.example.edu.handlerproject;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "HandlerApp";
    EditText et1, et2;
    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.button :
                    doAction1();
                    break;
                case R.id.button2 :
                    doAction2();
                    break;
            }
        }
    };
    CountThread trd1 = null;
    CountThread2 trd2 = null;
    void doAction2(){
        if(trd2 == null){
            trd2 = new CountThread2();
            onAir = true;
            trd2.start();
        }
    }
    void doAction1(){
        if(trd1 == null){
            trd1 = new CountThread();
            onAir = true;
            trd1.start();
        }
    }

    void stopThread(){
        onAir = false;
        trd1 = null;
        System.gc();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopThread();
    }

    boolean onAir = false;
    class CountThread2 extends Thread{
        int cnt = 0;
        public void run(){
            while( onAir ){
                cnt++;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        et2.setText("cnt : " + cnt);
                    }
                });
                SystemClock.sleep(700);
            }
        }
    }
    Handler postHandler = new Handler();
//    class CountThread2 extends Thread{
//        int cnt = 0;
//        public void run(){
//            while( onAir ){
//                cnt++;
//                postHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        et2.setText("cnt : " + cnt);
//                    }
//                });
//                SystemClock.sleep(700);
//            }
//        }
//    }
//    class CountThread2 extends Thread{
//        public void run(){
//            int cnt = 0;
//            Message msg = null;
//            while( onAir ){
//                cnt++;
//                msg = uiHandler.obtainMessage();
//                msg.arg1 = cnt;
//                msg.what = 900;
////                msg.setArg1(cnt);
//                uiHandler.sendMessage(msg);
////                et1.setText("cnt : " + cnt);
//                SystemClock.sleep(700);
//            }
//        }
//    }

    int cnt = 0;
    class CountThread extends Thread{
        public void run(){
            while( onAir ){
                cnt++;
                uiHandler.sendEmptyMessage(500);
                SystemClock.sleep(500);
            }
        }
    }
//    class CountThread extends Thread{
//        public void run(){
//            int cnt = 0;
//            Message msg = null;
//            while( onAir ){
//                cnt++;
//                msg = uiHandler.obtainMessage();
//                msg.arg1 = cnt;
//                msg.what = 500;
////                msg.setArg1(cnt);
//                uiHandler.sendMessage(msg);
////                et1.setText("cnt : " + cnt);
//                SystemClock.sleep(500);
//            }
//        }
//    }

    UIHandler uiHandler;
    class UIHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 500:
                    et1.setText("cnt : " + cnt);
//                    et1.setText("cnt : " + msg.arg1);
                    break;
                case 900:
                    et2.setText("cnt : " + msg.arg1);
                    break;
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


        uiHandler = new UIHandler();
    }
}
