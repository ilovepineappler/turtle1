package com.example.edu.toastproject;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ToastManager {
    public static void toast(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
    public  static void toast(Context context, String msg, int layout){
        Toast toast  = new Toast(context);
        View view = View.inflate(context, layout, null);

        TextView tv = view.findViewById(R.id.textView);
        tv.setText(msg);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}
