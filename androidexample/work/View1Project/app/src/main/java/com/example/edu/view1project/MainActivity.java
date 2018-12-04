package com.example.edu.view1project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "ViewApp";
    EditText et;
    CheckBox cbx;
    RadioGroup radioGroup ;

    RadioGroup.OnCheckedChangeListener checkedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int changedId) {
            RadioButton radioButton = radioGroup.findViewById(changedId);
            et.setText( radioButton.getText().toString() );
        }
    };

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.button :
                    doAction2();
//                    doAction1();
                    break;
                case R.id.checkBox:
                    et.setText("체크박스 눌림");
                    break;
            }

        }
    };
    void doAction2() {
        int id = radioGroup.getCheckedRadioButtonId();
        switch(id){
            case R.id.radioButton1:
                et.setText("대");
                break;
            case R.id.radioButton2:
                et.setText("중");
                break;
            case R.id.radioButton3:
                et.setText("소");
                break;
        }
    }
    void doAction1(){
        boolean flag = cbx.isChecked();
        et.setText(flag ? "선택됨" : "해제됨");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(handler);
        findViewById(R.id.checkBox).setOnClickListener(handler);

        et = findViewById(R.id.editText);
        cbx = findViewById(R.id.checkBox);
        radioGroup = findViewById(R.id.size_option);
        radioGroup.setOnCheckedChangeListener(checkedChangeListener);
    }
}
