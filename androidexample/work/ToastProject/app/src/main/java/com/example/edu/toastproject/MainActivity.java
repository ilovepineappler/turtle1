package com.example.edu.toastproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "ToastApp";
    int[] resId = { R.id.button , R.id.button2, R.id.button3, R.id.button4};


    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.button:
                    Toast.makeText(MainActivity.this, "토스트 문자열",Toast.LENGTH_LONG).show();
//                    Log.v(TAG, "this : " + this);
//                    Log.v(TAG, "MainActivity.this : " + MainActivity.this);
                    break;
                case R.id.button2:
                    doAction1();
                    break;
                case R.id.button3:
                    ToastManager.toast(MainActivity.this, "삭제되었습니다.");
                    break;
                case R.id.button4:
                    ToastManager.toast(MainActivity.this, "회원가입축하합니다.", R.layout.my_toast);
                    break;
            }
        }
    };
    void doAction1(){
        Toast toast  = new Toast(this);
        View view = View.inflate(this, R.layout.my_toast, null);

        TextView tv = view.findViewById(R.id.textView);
        tv.setText("수정되었습니다.");
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP|Gravity.LEFT, 30, 40);
        toast.show();
//        Toast.makeText(this, "토스트 문자열",Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toast.makeText(this, "토스트 문자열",Toast.LENGTH_LONG).show();
        for(int id : resId){
            findViewById(id).setOnClickListener(handler);
        }
    }
}
