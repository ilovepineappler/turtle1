package com.example.edu.iotledproject.comm;

import android.content.Context;
import android.util.Log;

/**
 * Created by kim111 on 2016-07-06.
 */
public class LogManager {
    private static final String TAG = "LEDOnOffProject";
    private static final boolean DEBUG = true;
    public static void print(Context context, String msg){
        if(DEBUG){
            Log.v(TAG, msg);
        }
    }
}
