package com.example.android.alarmmanager;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.view.WindowManager;
import android.widget.Toast;

public class AlarmReceiver extends WakefulBroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1=new Intent(context,Calculation.class);
        intent1.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);
    }
}
