package com.example.edu.helloworld.comm;

import android.util.Log;

public class LogManager {
    public static final String TAG = "HelloWorld";
    public static final boolean DEBUG = true;
    public static void print(String msg){
        if( DEBUG ) {
            Log.v(TAG, msg);
        }
    }
}
