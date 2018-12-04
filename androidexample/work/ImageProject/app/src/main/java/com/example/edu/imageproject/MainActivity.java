package com.example.edu.imageproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "ImageApp";
//    ImageView img;
    AQuery aq;
    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.button :
                    doAction1();
                    break;
            }
        }
    };
    void doAction1(){
        String url = "http://www.google.com/uds/GnewsSearch?q=Obama&v=1.0";

        aq.ajax(url, JSONObject.class, new AjaxCallback<JSONObject>() {

            @Override
            public void callback(String url, JSONObject json, AjaxStatus status) {


                if(json != null){

                    //successful ajax call, show status code and json content
                    Toast.makeText(aq.getContext(), status.getCode() + ":" + json.toString(), Toast.LENGTH_LONG).show();
                    Log.v(TAG, "bbb");
                }else{

                    //ajax error, show error code
                    Toast.makeText(aq.getContext(), "Error:" + status.getCode(), Toast.LENGTH_LONG).show();
                }
            }
        });
        Log.v(TAG, "aaaa");
    }

//    void doAction1(){
//        aq.id(R.id.imageView).image("https://t1.daumcdn.net/daumtop_chanel/op/20170315064553027.png");
//    }
//    void doAction1(){
//        new DownTask().execute("https://t1.daumcdn.net/daumtop_chanel/op/20170315064553027.png");
//    }
//    class DownTask extends AsyncTask<String, Void, Bitmap>{
//        @Override
//        protected void onPostExecute(Bitmap bitmap) {
//            if(bitmap != null) {
//                img.setImageBitmap(bitmap);
//            }else{
//                Toast.makeText(MainActivity.this, "이미지로딩 실패",Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        @Override
//        protected Bitmap doInBackground(String... strings) {
//            Bitmap bitmap = null;
//            String daumURL = strings[0];
//            URL url = null;
//            try{
//                url  = new URL(daumURL);
//            }catch(MalformedURLException e){
//                Log.v(TAG, "URL 오류 : " + e);
//                return null;
//            }
//            HttpsURLConnection connection = null;
//            InputStream is = null;
//            try{
//                connection = (HttpsURLConnection)url.openConnection();
//                is = connection.getInputStream();
//                int data = 0;
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                while( (data = is.read()) != -1 ){
//                    baos.write(data);
//                }
//                byte[] imgByte = baos.toByteArray();
//                bitmap = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
//                Log.v(TAG, "이미지 다운로드 성공");
//            }catch(IOException e){
//                Log.v(TAG, "e " + e);
//            }finally{
//                if(connection != null){
//                    connection.disconnect();
//                }
//            }
//
//            return bitmap;
//        }
//    }
//    void doAction1(){
//        File sd = Environment.getExternalStorageDirectory();
//        File f1 = new File(sd, "idata");
//        File fImage = new File(f1, "daum.png");
//        Bitmap bitmap = BitmapFactory.decodeFile( fImage.getAbsolutePath() );
//        img.setImageBitmap(bitmap);
//
//    }
//    void doAction1(
//        img.setImageResource(R.drawable.daum);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aq = new AQuery(this);
        setContentView(R.layout.activity_main);
//        img = findViewById(R.id.imageView);
        findViewById(R.id.button).setOnClickListener(handler);


        aq.id(R.id.editText).text("korea").textColor(0xffff0000).textSize(40);
//        EditText et = findViewById(R.id.editText);
//        et.setText("korea");
    }
}
