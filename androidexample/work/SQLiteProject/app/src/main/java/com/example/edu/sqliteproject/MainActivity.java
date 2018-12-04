package com.example.edu.sqliteproject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.edu.sqliteproject.helper.MyDBHelper;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "SQLApp";
    int[] resId = {R.id.button, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6};

    MyDBHelper helper;
    SQLiteDatabase db;
    String tableName="person";
    View.OnClickListener handler  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.button:
                    doAction1();
                    break;
                case R.id.button2:
                    doAction2("kang",27, "mokpo",1);
                    break;
                case R.id.button3:
                    doAction3();
                    break;
                case R.id.button4:
//                    String[] colNames = new String[]{"name", "age", "addr"};
                    doAction4( new String[]{"name", "age", "addr"} , "seoul");
                    break;
                case R.id.button5:
                    doAction5("newKim", "newSeoul", 3);
                    break;
                case R.id.button6:
                    doAction6("new");
                    break;
            }
        }
    };
    void doAction6(String wname){
        openDB();
//        String sql = "delete from person where name like '%new%'";
        try{
            int cnt = db.delete(tableName, "name like ?", new String[]{ "%" + wname + "%" } );
            Log.v(TAG, "삭제된 레코드 갯수 : " + cnt);
        }catch(SQLException e){
            Log.v(TAG, "delete e : " + e);
        }
        closeDB();
    }
    void doAction5(String nName, String nAddr, int wid){
        openDB();
//        String sql = "update person set name='newKim', addr='newSeoul' where _id > 3";
        ContentValues values = new ContentValues();
        values.put("name", nName);
        values.put("addr", nAddr);
        try {
            int cnt = db.update(tableName, values, " _id > ?", new String[]{wid + ""});
            Log.v(TAG, "수정된 레코드 갯수 : " + cnt);
        }catch(SQLException e){
            Log.v(TAG, "update e : " + e);
        }
        closeDB();
    }
    void doAction4(String[] colnames, String waddr){
        openDB();

        String sql = "select %s, %s, %s from person where addr = '%s' order by _id limit 3";
        String sql1 = String.format(sql, colnames[0], colnames[1],colnames[2], waddr);
        Cursor cursor = null;
        try{
            cursor = db.query("person", colnames,"addr = ? and sex = ?", new String[]{waddr, 1+""}, null,null,"_id","3");
//            cursor = db.rawQuery( sql1 , null);
            String name;
            int age;
            String addr;
            while(cursor.moveToNext()){
                name = cursor.getString(0);
                age = cursor.getInt(1);
                addr = cursor.getString(2);
                Log.v(TAG, String.format("data name : %s, age : %d, addr : %s", name, age, addr));
            }
        }catch(SQLException e){
            Log.v(TAG, "read e : " + e);
        }finally {
            cursor.close();
        }


        closeDB();
    }
//    void doAction4(String[] colnames, String waddr){
//        openDB();
//
//        String sql = "select %s, %s, %s from person where addr = '%s' order by _id limit 3";
//        String sql1 = String.format(sql, colnames[0], colnames[1],colnames[2], waddr);
//        Cursor cursor = null;
//        try{
//            cursor = db.rawQuery( sql1 , null);
//            String name;
//            int age;
//            String addr;
//            while(cursor.moveToNext()){
//                name = cursor.getString(0);
//                age = cursor.getInt(1);
//                addr = cursor.getString(2);
//                Log.v(TAG, String.format("data name : %s, age : %d, addr : %s", name, age, addr));
//            }
//        }catch(SQLException e){
//            Log.v(TAG, "read e : " + e);
//        }finally {
//            cursor.close();
//        }
//
//
//        closeDB();
//    }
    void doAction3(){
        openDB();

//        String sql = "select * from person ";
        Cursor cursor = null;
        try{
            cursor = db.query("person", null,null,null,null,null,null);
            int id;
            String name;
            int sex;
            while(cursor.moveToNext()){
                id = cursor.getInt(0);
                name = cursor.getString(1);
                sex = cursor.getInt(4);
                Log.v(TAG, String.format("data id : %d, name : %s, sex : %d", id, name, sex));
            }
        }catch(SQLException e){
            Log.v(TAG, "read e : " + e);
        }finally {
            cursor.close();
        }


        closeDB();
    }

//    void doAction3(){
//        openDB();
//
//        String sql = "select _id, name, age, addr, sex from person where 1 = 1 order by _id limit 3";
//        Cursor cursor = null;
//        try{
//            cursor = db.rawQuery( sql , null);
//            int id;
//            String name;
//            int sex;
//            while(cursor.moveToNext()){
//                id = cursor.getInt(0);
//                name = cursor.getString(1);
//                sex = cursor.getInt(4);
//                Log.v(TAG, String.format("data id : %d, name : %s, sex : %d", id, name, sex));
//            }
//        }catch(SQLException e){
//            Log.v(TAG, "read e : " + e);
//        }finally {
//            cursor.close();
//        }
//
//
//        closeDB();
//    }
    void doAction2(String name, int age, String addr, int sex){
        openDB();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("age", age);
        values.put("addr", addr);
        values.put("sex", sex);
        long id = db.insert("person", null, values);
        Log.v(TAG, "insert " + (id > 0? "성공": "실패"));

        closeDB();
    }
//    void doAction2(String name, int age, String addr, int sex){
//        openDB();
//        String sql = "insert into person1(name, age, addr, sex) values('"+name+"', "+age+", '"+addr+"', "+sex+")";
//        try {
//            db.execSQL(sql);
//            Log.v(TAG, "insert success");
//        }catch(SQLException e){
//            Log.v(TAG, "insert error : " + e);
//        }
//
//        closeDB();
//    }
    void doAction1(){
        openDB();
        closeDB();
    }

    int version = 2;
    void openDB(){
        helper = new MyDBHelper(this, "sam.db", null, version);
        db = helper.getWritableDatabase();

    }

    void closeDB(){
        if(db != null) {
            db.close();
        }
        if(helper != null) {
            helper.close();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int id : resId){
            findViewById(id).setOnClickListener(handler);
        }
    }
}
