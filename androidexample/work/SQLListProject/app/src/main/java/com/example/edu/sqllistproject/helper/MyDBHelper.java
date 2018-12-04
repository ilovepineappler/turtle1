package com.example.edu.sqllistproject.helper;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHelper extends SQLiteOpenHelper {
    private static final String TAG = "SQLApp";
    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Log.v(TAG, "DB OPen");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.v(TAG, "DB onCreate");
//        테이블 사람 (_id, 이름, 나이, 주소, 성별)
//        String s = "(";
        String sql = "create table person(" +
                " _id integer primary key autoincrement " +
                " , name varchar(20)" +
                ",age integer" +
                ",addr varchar(50)" +
                ",type Integer ); ";


        try {
            sqLiteDatabase.execSQL(sql);
            Log.v(TAG, "create1111 success");
            sqLiteDatabase.execSQL("insert into person(name, age, addr, type) values('kim', 30, 'seoul', 1)");
            sqLiteDatabase.execSQL("insert into person(name, age, addr, type) values('lee', 22, 'pusan', 1)");
            sqLiteDatabase.execSQL("insert into person(name, age, addr, type) values('park', 30, 'seoul', 2)");
            sqLiteDatabase.execSQL("insert into person(name, age, addr, type) values('kim', 25, 'jeju', 0)");
            sqLiteDatabase.execSQL("insert into person(name, age, addr, type) values('lee', 34, 'seoul', 1)");
            sqLiteDatabase.execSQL("insert into person(name, age, addr, type) values('kang', 20, 'sung', 2)");
            sqLiteDatabase.execSQL("insert into person(name, age, addr, type) values('kim1', 25, 'jeju', 0)");
            sqLiteDatabase.execSQL("insert into person(name, age, addr, type) values('lee2', 34, 'seoul', 1)");
            sqLiteDatabase.execSQL("insert into person(name, age, addr, type) values('kang3', 20, 'sung', 0)");
            Log.v(TAG, "insert  success");
        }catch(SQLException e){
            Log.v(TAG, "create e : " + e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.v(TAG, "DB onUpgrade oldVersion : " + oldVersion + " , newVersion" + newVersion);
    }
}
