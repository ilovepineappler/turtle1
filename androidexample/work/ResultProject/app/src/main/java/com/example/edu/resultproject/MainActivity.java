package com.example.edu.resultproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG  = "ActivityApp";
    EditText et1, et2, et3;
    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.button:
                    doAction1();
                    break;
                case R.id.button3:
                    doAction2();
                    break;
                case R.id.button4:
                    doAction4();
                    break;
                case R.id.button6:
                    doAction6();
                    break;
            }
        }
    };
    int x, y;
    void doAction6(){
        x++;
        y++;
        et1.setText("x : " + x + ", y : " + y);
    }
    void doAction4(){
        Intent intent = new Intent(this, Sub2Activity.class);
        String name = et1.getText().toString();
        intent.putExtra("name", name);
        intent.putExtra("cnt",30);
        startActivityForResult(intent, Comm.REQUEST_SUB2);
    }
    void doAction2(){
        Intent intent = new Intent(this, Sub1Activity.class);
        String name = et1.getText().toString();
        intent.putExtra("name", name);
        intent.putExtra("cnt",30);
        startActivityForResult(intent, Comm.REQUEST_SUB1);
    }
    void doAction1(){
        Intent intent = new Intent(this, Sub1Activity.class);
        String name = et1.getText().toString();
        intent.putExtra("name", name);
        intent.putExtra("cnt",30);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case RESULT_OK :
                String msg = data.getStringExtra("rData");
                switch (requestCode){
                    case Comm.REQUEST_SUB1:
                        et2.setText("rData : " + msg);
                        break;
                    case Comm.REQUEST_SUB2:
                        et3.setText("rData : " + msg);
                        break;
                }
                break;
            case RESULT_CANCELED:
                Toast.makeText(this, "취소가 눌렸습니다.", Toast.LENGTH_SHORT).show();
                break;
            case Comm.RESULT_EXIT:
                setResult( Comm.RESULT_EXIT );
                finish();
                break;
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        switch(newConfig.orientation){
            case Configuration.ORIENTATION_LANDSCAPE:
                Toast.makeText(this, "현재 가로 모드", Toast.LENGTH_SHORT).show();
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                Toast.makeText(this, "현재 세로 모드", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("x", x);
    }

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null) {
            x = savedInstanceState.getInt("x");
        }

        findViewById(R.id.button).setOnClickListener(handler);
        findViewById(R.id.button3).setOnClickListener(handler);
        findViewById(R.id.button4).setOnClickListener(handler);
        findViewById(R.id.button6).setOnClickListener(handler);

        et1 = findViewById(R.id.editText);
        et2 = findViewById(R.id.editText2);
        et3 = findViewById(R.id.editText3);
        Log.v(TAG, "Main onCreate: " + savedInstanceState );
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "Main onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "Main onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "Main onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "Main onPause");
        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("y",y);
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "Main onResume");
        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
        y = sp.getInt("y",100);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v(TAG, "Main onRestart");
    }
}
