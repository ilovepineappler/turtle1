package com.example.edu.brproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "BRApp";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v(TAG, "action : " + intent.getAction());
//        Log.v(TAG, "title : " + intent.getStringExtra("title"));

    }
}
