package com.example.edu.serviceproject;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "ServiceApp";
    int cnt = 0;

    boolean onAir = false;
    class JobThread extends  Thread{
        public void run(){
            while( onAir ){
                cnt++;
                Log.v(TAG, "loop cnt : " + cnt);
//                3의 배수가되면 UI 변경이 필요하다
                if(cnt % 3 == 0){
                    Intent intent = new Intent();
                    intent.setAction("com.example.edu.serviceproject.intent.action.COUNT");
                    intent.putExtra("cnt", cnt);
                    sendBroadcast(intent);
                }
//                Toast.makeText(MyService.this, "ssss", Toast.LENGTH_SHORT).show();
                SystemClock.sleep(500);
            }
        }
    }
    @Override
    public void onCreate() {
        super.onCreate();
        cnt++;
        onAir = true;
        new JobThread().start();

        Log.v(TAG, "create cnt : " + cnt);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        cnt++;
        Log.v(TAG, "onStartCommand cnt : " + cnt);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        cnt++;
        onAir = false;
        Log.v(TAG, "onDestroy cnt : " + cnt);
        super.onDestroy();
    }









    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
