package com.example.edu.notifcationproject;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "NotiApp";
    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.button :
                    doAction1();
                    break;
                case R.id.button2 :
                    doAction2();
                    break;
            }
        }
    };
    void doAction2(){
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(this, "chApp")
                .setContentTitle("타이틀")
                .setContentText("body")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setAutoCancel(true);

        notificationManager.notify(333, builder.build());

//        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
//
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.putExtra("data", "sam테스트");
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//
////        Notification.Builder builder = new Notification.Builder(this, "Sam");
////
////        builder.setSmallIcon(R.mipmap.ic_launcher)
////                .setContentTitle("Noti 알림")
////                .setContentText("알림테스트입니다.")
////                .setContentIntent(pendingIntent)
////                .setAutoCancel(true);
//        NotificationCompat.Builder mNotificationBuilder = new NotificationCompat.Builder( this)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle( "noti 알림" )
//                .setContentText("알림테스트입니다")
//                .setAutoCancel( true )
//                .setContentIntent(pendingIntent);
//
//
//        Notification notification = mNotificationBuilder.build();
//
//
//        notificationManager.notify(555, notification);
        Log.v(TAG, "aaa");
    }
    void doAction1(){
//        진동
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
//        vibrator.vibrate(2000);

        long[] vtimes = {500,500,200,500,200,500,200};
        vibrator.vibrate(vtimes, -1);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(handler);
        findViewById(R.id.button2).setOnClickListener(handler);
    }
}
