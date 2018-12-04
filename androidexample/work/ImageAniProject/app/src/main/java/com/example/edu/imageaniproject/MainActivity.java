package com.example.edu.imageaniproject;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "AniApp";
    ImageView img;
    AnimationDrawable animationDrawable;
    Animation animation = null;
    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            view.startAnimation(animation);
            switch (view.getId()){
                case R.id.button:
                    doAction1();
                    break;
                case R.id.button2:
                    doAction2();
                    break;
                case R.id.button3:
                    doAction3();
                    break;
                case R.id.button5:
                    doAction5();
                    break;
            }
        }
    };
    void doAction5(){
        img.startAnimation(animation);
    }
    void doAction2(){
        animationDrawable.start();
    }
    void doAction3(){
        animationDrawable.stop();
    }
    ArrayList<Drawable> imageList = new ArrayList<Drawable>();
    void doAction1(){
        if(trd == null){
            trd = new AniThread();
            onAir = true;
            trd.start();
        }
    }
    boolean onAir = false;
    UIHandler uiHandler;
    AniThread trd;
    class AniThread extends  Thread{
        int idx = 0;
        public void run(){
            Message msg = null;
            while( onAir ){
                idx++;
                if(idx > 5){
                    idx = 0;
                }
                msg = uiHandler.obtainMessage();
                msg.what = 100;
                msg.arg1 = idx;
                uiHandler.sendMessage(msg);
                SystemClock.sleep(500);
            }
        }
    }
    class UIHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
           switch (msg.what){
               case 100 :
                   img.setImageDrawable(imageList.get(msg.arg1));
                   break;
           }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        onAir = false;
//        try {
//            trd.join();
//        }catch(InterruptedException e){}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.imageView);
        findViewById(R.id.button).setOnClickListener(handler);
        findViewById(R.id.button2).setOnClickListener(handler);
        findViewById(R.id.button3).setOnClickListener(handler);
        findViewById(R.id.button5).setOnClickListener(handler);

        imageList.add(getResources().getDrawable(R.drawable.icon01));
        imageList.add(getResources().getDrawable(R.drawable.icon02));
        imageList.add(getResources().getDrawable(R.drawable.icon03));
        imageList.add(getResources().getDrawable(R.drawable.icon04));
        imageList.add(getResources().getDrawable(R.drawable.icon05));
        imageList.add(getResources().getDrawable(R.drawable.icon06));
        uiHandler = new UIHandler();
        animationDrawable = (AnimationDrawable) findViewById(R.id.button4).getBackground();

//        animationDrawable.start();
        animation = AnimationUtils.loadAnimation(this, R.anim.ani6);
//        animation = AnimationUtils.loadAnimation(this, R.anim.ani5);
//        animation = AnimationUtils.loadAnimation(this, R.anim.ani4);
//        animation = AnimationUtils.loadAnimation(this, R.anim.ani3);
//        animation = AnimationUtils.loadAnimation(this, R.anim.ani2);
//        animation = AnimationUtils.loadAnimation(this, R.anim.ani1);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            animationDrawable.start();
        }else{
            animationDrawable.stop();
        }
    }
}
