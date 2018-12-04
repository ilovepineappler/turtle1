package com.example.edu.frameproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout view1, view2, view3;
    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            hideView();
            switch(view.getId()){
                case R.id.textView1:
                    view1.setVisibility(View.VISIBLE);
                    break;
                case R.id.textView2:
                    view2.setVisibility(View.VISIBLE);
                    break;
                case R.id.textView3:
                    view3.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };
    void hideView(){
        view1.setVisibility(View.GONE);
        view2.setVisibility(View.GONE);
        view3.setVisibility(View.GONE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.textView1).setOnClickListener(handler);
        findViewById(R.id.textView2).setOnClickListener(handler);
        findViewById(R.id.textView3).setOnClickListener(handler);

        view1 =findViewById(R.id.view1);
        view2 =findViewById(R.id.view2);
        view3 =findViewById(R.id.view3);
    }
}
