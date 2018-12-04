package com.example.edu.touchproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TouchApp";

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.v(TAG, "취소버튼이 눌렸습니다.");
    }

    View.OnTouchListener handler = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch(view.getId()){
                case R.id.textview :
                    switch(motionEvent.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            int count  = motionEvent.getPointerCount();
                            Log.v(TAG, "터치 눌림 갯수 : " + count);
                            break;
                        case MotionEvent.ACTION_MOVE:
//                            int count  = motionEvent.getPointerCount();
                            int x = (int)motionEvent.getX(0);
                            int y = (int)motionEvent.getY(0);
                            Log.v(TAG, "터치 이동됨 x : " + x + " , y : " + y);
                            break;
                        case MotionEvent.ACTION_UP:
                            Log.v(TAG, "터치 뗌");
                            break;
                    }
                    break;
            }
            return true;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.textview).setOnTouchListener(handler);
    }
}
