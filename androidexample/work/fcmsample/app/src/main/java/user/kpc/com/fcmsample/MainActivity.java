package user.kpc.com.fcmsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {
    private static  final String TAG = "MainActivity";

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.button2 :
                    getInstanceId();
                    break;
            }
        }
    };
    void getInstanceId(){
        Log.v(TAG, "id : " + FirebaseInstanceId.getInstance().getToken());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button2).setOnClickListener(handler);
        Intent intent = getIntent();
//        data 영역 Extra 형식으로 전송됨
        Log.v(TAG, intent.getStringExtra("title") + "");
        Log.v(TAG, intent.getStringExtra("content") + "");
    }
}