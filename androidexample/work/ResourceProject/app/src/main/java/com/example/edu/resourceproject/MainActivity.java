package com.example.edu.resourceproject;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn;
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
    void doAction1(){
//        btn.setText("저장");

        Resources res = getResources();
        String title = res.getString(R.string.str_save);
//        String title = R.string.str_save;
        btn.setText(title);
        int color = res.getColor(R.color.myColor);
        btn.setTextColor(color);
        float size = res.getDimension(R.dimen.myfontsize);
//        Log.v(TAG, "size:" + size);
        btn.setTextSize(size);
        btn.setText("size:"+size);

        String[] myWeekNames = res.getStringArray(R.array.myWeek);

        btn.setText(myWeekNames[3]);

        btn.setTextColor(0xffff0000);
//        btn.setTextColor(R.color.myColor);


//        btn.setText(R.string.str_save);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(handler);
    }
}
