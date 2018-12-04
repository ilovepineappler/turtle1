package com.example.edu.iotledproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.example.edu.iotledproject.comm.LogManager;


public class MainActivity extends AppCompatActivity {

    AQuery aq = null;
    boolean flag1 = false;
    boolean flag2 = false;
    boolean flag3 = false;
    String data = "";

    ImageView img1, img2, img3;
    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.img1:
                    if(flag1){
                        data = "H";
                    }else{
                        data = "L";
                    }
                    flag1 = !flag1;
                    doLEDOnOff("pos1", data, 1);
                    break;
                case R.id.img2:
                    if(flag2){
                        data = "K";
                    }else{
                        data = "O";
                    }
                    flag2 = !flag2;
                    doLEDOnOff("pos1", data, 2);
                    break;
                case R.id.img3:
                    if(flag3){
                        data = "S";
                    }else{
                        data = "A";
                    }
                    flag3 = !flag3;
                    doLEDOnOff("pos1", data, 3);
                    break;
            }
        }
    };
    String getTel(){
        //실제 전화번호 얻어오기
//        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
//        String tel =  tm.getLine1Number();
//        return tel;
        return "0102220202";
    }
    void doLEDOnOff(String loc, String data, final int type) {
        String tel = getTel();
        //IP 주소 실제값으로 변경 필요함
        String url = "http://192.168.120.22/iot1/ledonoff?loc="+loc+"&tel=" + tel + "&data=" + data;
        aq.ajax(url, String.class, new AjaxCallback<String>() {
            @Override
            public void callback(String url, String object, AjaxStatus status) {
                LogManager.print(MainActivity.this, object.toString());
                doChangeImage(object.substring(0,1), type);
            }
        });
    }
    void doChangeImage(String data, int type){
        ImageView tempImage = null;
        switch(type){
            case 1 :
                tempImage = img1;
                break;
            case 2 :
                tempImage = img2;
                break;
            case 3 :
                tempImage = img3;
                break;
        }
        switch(data){
            case "H" :case "K" :case "S" :
                tempImage.setImageResource(R.drawable.on);
                break;
            case "L" :case "O" :case "A" :
                tempImage.setImageResource(R.drawable.off);
                break;
            default:
                Toast.makeText(this, "오류가 발생함 : " + data, Toast.LENGTH_SHORT).show();
                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aq = new AQuery(this);
        img1 = (ImageView)findViewById(R.id.img1);
        img2 = (ImageView)findViewById(R.id.img2);
        img3 = (ImageView)findViewById(R.id.img3);
        img1.setOnClickListener(handler);
        img2.setOnClickListener(handler);
        img3.setOnClickListener(handler);
    }
}