package com.example.edu.intentproject;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "IntentApp";
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
//        특정사용자 전화 걸기
        String tel = "01037926917";
//        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tel));
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tel));
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            try {
                startActivity(intent);
            }catch(ActivityNotFoundException e) {
                Log.v(TAG, "지정된 Activity가 없습니다.");
            }
        }else{
            Toast.makeText(this, "전화걸기를 허용해셔야 합니다.", Toast.LENGTH_SHORT).show();
        }
    }
    void doAction1(){
//        특정 사이트 보여주세요 네이버사이트 접속
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(handler);
        findViewById(R.id.button2).setOnClickListener(handler);
    }
}
