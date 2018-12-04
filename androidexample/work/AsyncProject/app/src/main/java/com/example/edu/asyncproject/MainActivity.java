package com.example.edu.asyncproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "AsyncApp";
    EditText et;
    ProgressBar progressBar;
    ProgressDialog pDialog;
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
    void doAction2(){
        Intent intent = new Intent(this, SubActivity.class);

        Person p = new Person("홍길동",30, true);


        intent.putExtra("person", p);
        startActivity(intent);
    }
    void doAction1(){
        new JobTask().execute(30, 80, 2);
    }

    class JobTask extends AsyncTask<Integer, Integer, String>{
        @Override
        protected String doInBackground(Integer... values) {
            int start  = values[0];
            int end = values[1];
            int step = values[2];

            int num = start;
            while(num <= end){
                num += step;
                Log.v(TAG, "num : " + num);
                if( num % 10 == 0 ){
                    publishProgress(num);
                }
                SystemClock.sleep(200);
            }
            return "작업 성공";
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(pDialog != null){
                if(pDialog.isShowing()){
                    pDialog.cancel();
                }
            }
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int data = values[0];
            et.setText("num : " + data);
            progressBar.setProgress(data);
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = ProgressDialog.show(MainActivity.this, "", "작업중...");
        }






    }

//    void aa(){
//        bb();
//        bb(1);
//        bb(1, 2);
//        bb(1, 2, 3);
//    }
//    void bb( int... a){
//        int tot = 0;
//        for(int i = 0 ; i < a.length; i++){
//            tot += a[i];
//        }
//    }
//    void bb(int a){}
//    void bb(int a, int b){}
//    void bb(int a, int b, int c){}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.editText);
        progressBar = findViewById(R.id.progressBar2);

        findViewById(R.id.button).setOnClickListener(handler);
        findViewById(R.id.button2).setOnClickListener(handler);
    }
}
