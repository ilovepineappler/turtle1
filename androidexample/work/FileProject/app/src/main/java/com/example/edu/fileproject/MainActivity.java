package com.example.edu.fileproject;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "FileApp";
    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.button:
//                    파일명, 데이터 전송
                    doAction1("aaa.txt", "korea 한글1");
                    doAction1("bbb.txt", "korea 한글2");
                    doAction1("ccc.txt", "korea 한글3");
                    doAction1("ddd.txt", "korea 한글4");
                    doAction1("eee.txt", "korea 한글5");
                    doAction1("fff.txt", "korea 한글6");
                    break;
                case R.id.button2:
                    doAction2();
                    break;
                case R.id.button3:
                    doAction3("bbb.txt");
                    break;
                case R.id.button4:
                    doAction4("ddd.txt");  //삭제
                    break;
                case R.id.button5:
                    doAction5();
                    break;
                case R.id.button6:
                    doAction6("sam", "data", "aaa.txt");
                    break;
                case R.id.button7:
                    doAction7("sam", "data", "aaa.txt", "write 데이터 1234!@#$");
                    break;
                case R.id.button8:
                    doAction8("sam", "data", "aaa.txt");
                    break;

            }
        }
    };
    void doAction8(String dir1, String dir2, String fName){
        File sd = Environment.getExternalStorageDirectory();
        File f1 = new File(sd, dir1);
        File f2 = new File(f1, dir2);
        File f3 = new File(f2, fName);
        DataInputStream dis = null;
        try{
            dis = new DataInputStream( new FileInputStream(f3) );
            String s = dis.readUTF();
            Log.v(TAG, "sdcard read OK" + s);
        }catch(IOException e){
            Log.v(TAG, "sdcard read e : " + e);
        }finally {
            if(dis != null){
                try{
                    dis.close();
                }catch(IOException e){}
            }
        }

    }
    void doAction7(String dir1, String dir2, String fName, String data){
        File sd = Environment.getExternalStorageDirectory();
        File f1 = new File(sd, dir1);
        if( !f1.exists() ){
            f1.mkdir();
        }
        File f2 = new File(f1, dir2);

        if( ! f2.exists() ){
            f2.mkdir();
        }

        File f3 = new File(f2, fName);
        DataOutputStream dos = null;
        try{
            dos = new DataOutputStream( new FileOutputStream(f3) );
            dos.writeUTF(data);
            dos.flush();
            Log.v(TAG, "sdcard write OK");
        }catch(IOException e){
            Log.v(TAG, "sdcard write e : " + e);
        }finally {
            if(dos != null){
                try{
                    dos.close();
                }catch(IOException e){}
            }
        }

    }
    void doAction6(String dir1, String dir2, String fName){
        File sd = Environment.getExternalStorageDirectory();
        File f1 = new File(sd, dir1);
        if( !f1.exists() ){
            f1.mkdir();
        }


        File f2 = new File(f1, dir2);

        if( ! f2.exists() ){
            f2.mkdir();
        }
        File f3 = new File(f2, fName);
        try {
            boolean flag = f3.createNewFile();
            Log.v(TAG, "생성 " + flag);
        }catch(IOException e){
            Log.v(TAG, "생성 오류 : " + e);
        }

    }
    void doAction5(){
        String state = Environment.getExternalStorageState();
        Log.v(TAG, "state : " + state);
        File sd = Environment.getExternalStorageDirectory();
        Log.v(TAG, "sd path : " + sd.getAbsolutePath());
        File dataDir = Environment.getDataDirectory();
        Log.v(TAG, "dataDir path : " + dataDir.getAbsolutePath());

//        File dataDir1 = getDataDir();
//        Log.v(TAG, "dataDir1 path : " + dataDir1.getAbsolutePath());
    }
    void doAction4(String fName){
        boolean flag = deleteFile(fName);
        Log.v(TAG, "삭제" + (flag ? "성공":"실패") );
    }
    void doAction3(String fName){
        DataInputStream dis = null;
        try {
            dis = new DataInputStream( openFileInput(fName) );
            String data = dis.readUTF();
            Log.v(TAG, "read data : " + data);
        }catch(FileNotFoundException e){
            Log.v(TAG, "read e:" + e);
        }catch (IOException e){
            Log.v(TAG, "read1 e " + e);
        }finally {
            if(dis != null){
                try{
                    dis.close();
                }catch (IOException e){}
            }
        }
    }
    void doAction2(){
        String[] fNames = fileList();
        for(String name : fNames){
            Log.v(TAG, "name:" + name);
        }
    }
    void doAction1(String fName, String data){
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream( openFileOutput(fName, MODE_PRIVATE) );
            dos.writeUTF(data);
            dos.flush();
            Log.v(TAG, "save OK");
        }catch(FileNotFoundException e){
            Log.v(TAG, "save e : " + e);
        }catch(IOException e1){
            Log.v(TAG, "save1 e1 : " + e1);
        }finally {
            if(dos != null){
                try{
                    dos.close();
                }catch(IOException e){}
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(handler);
        findViewById(R.id.button2).setOnClickListener(handler);
        findViewById(R.id.button3).setOnClickListener(handler);
        findViewById(R.id.button4).setOnClickListener(handler);
        findViewById(R.id.button5).setOnClickListener(handler);
        findViewById(R.id.button6).setOnClickListener(handler);
        findViewById(R.id.button7).setOnClickListener(handler);
        findViewById(R.id.button8).setOnClickListener(handler);
    }
}
