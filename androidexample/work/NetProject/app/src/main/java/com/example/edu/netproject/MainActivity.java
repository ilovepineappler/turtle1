package com.example.edu.netproject;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "NetApp";
    TextView tv;


//    ListView listView;
    ArrayList<Product> data = new ArrayList<Product>();
    ProductAdapter adapter = null;

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.button:
                    doAction1();
                    break;
            }
        }
    };

    ProgressDialog progressDialog;
    void doAction1(){
        progressDialog = ProgressDialog.show(this,"","다운로드중...");
        new NetThread().start();

    }
    Handler uihandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(progressDialog != null){
                progressDialog.cancel();
            }
            switch (msg.what){
                case 888:
                    Toast.makeText(MainActivity.this, "주소가 정확하지 않습니다.", Toast.LENGTH_SHORT).show();
                    break;
                case 555:
                    Toast.makeText(MainActivity.this, "응답코드 오류 코드값 : " + msg.arg1, Toast.LENGTH_SHORT).show();
                    break;
                case 333:
//                    tv.setText(msg.obj.toString());
                    adapter.notifyDataSetChanged();
                    break;

            }


        }
    };
//    String mURL = "http://www.seoul.go.kr/main/index.html";
    String mURL = "http://192.168.120.22/plistjson/";
//    String mURL = "http://192.168.120.22/pjson/";
    class NetThread extends  Thread{
        public void run(){
            URL url = null;
            try{
                url = new URL(mURL);
            }catch(MalformedURLException e){
                Log.v(TAG, "형식 오류:" + e);
                uihandler.sendEmptyMessage(888);
                return;
            }
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                connection = (HttpURLConnection)url.openConnection();

                int code = connection.getResponseCode();
                Message msg = null;
                switch (code){
                    case HttpURLConnection.HTTP_OK :   // 200
//                      정상적인 데이터 로딩
                        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                        reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"euc-kr"));
                        String s = reader.readLine();
                        Log.v(TAG, "s : " + s);
//                        Product  product = ProductJSONParser.parser(s);
                        ProductList productList = ProductJSONParser.listParser(s);
                        msg = uihandler.obtainMessage();
                        msg.what = 333;
                        adapter.setData( productList.getList() );
//                        msg.obj = productList.toString();
//                        msg.obj = product.toString();
//                        uihandler.sendMessage(msg);
                        break;
                    default:
                        msg = uihandler.obtainMessage();
                        msg.what = 555;
                        msg.arg1 = code;
//                        uihandler.sendMessage(msg);
                        break;
                }
                uihandler.sendMessage(msg);
            }catch(IOException e){
                uihandler.sendEmptyMessage(777);
                return;
            }finally {
                if(reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                    }
                }
                connection.disconnect();
            }
        }
    }
//    class NetThread extends  Thread{
//        public void run(){
//            URL url = null;
//            try{
//                url = new URL(mURL);
//            }catch(MalformedURLException e){
//                Log.v(TAG, "형식 오류:" + e);
//                uihandler.sendEmptyMessage(888);
//                return;
//            }
//            HttpURLConnection connection = null;
//            BufferedReader reader = null;
//            try {
//                connection = (HttpURLConnection)url.openConnection();
//
//                int code = connection.getResponseCode();
//                Message msg = null;
//                switch (code){
//                    case HttpURLConnection.HTTP_OK :   // 200
////                      정상적인 데이터 로딩
//                        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
////                        reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"euc-kr"));
//                        String s = "";
//                        StringBuilder builder = new StringBuilder();
//                        while( (s = reader.readLine()) != null){
////                            s = reader.readLine();
////                            if(s == null){
////                                break;
////                            }
////                            작업
//                            builder.append(s).append("\n");
//                        }
//                        msg = uihandler.obtainMessage();
//                        msg.what = 333;
//                        msg.obj = builder.toString();
////                        uihandler.sendMessage(msg);
//                        break;
//                    default:
//                        msg = uihandler.obtainMessage();
//                        msg.what = 555;
//                        msg.arg1 = code;
////                        uihandler.sendMessage(msg);
//                        break;
//                }
//                uihandler.sendMessage(msg);
//            }catch(IOException e){
//                uihandler.sendEmptyMessage(777);
//                return;
//            }finally {
//                if(reader != null) {
//                    try {
//                        reader.close();
//                    } catch (IOException e) {
//                    }
//                }
//                connection.disconnect();
//            }
//        }
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        tv = findViewById(R.id.textView);
        findViewById(R.id.button).setOnClickListener(handler);

        ListView listView = findViewById(R.id.listView);

        adapter = new ProductAdapter(this, R.layout.product, data);
        listView.setAdapter(adapter);
    }

    void aa(){

    }
}
