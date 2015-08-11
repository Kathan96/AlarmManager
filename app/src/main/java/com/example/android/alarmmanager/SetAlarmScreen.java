package com.example.android.alarmmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class SetAlarmScreen extends ActionBarActivity {
    TimePicker timePicker;
    Button set_alarm;
    private PendingIntent pendingIntent;
    CheckBox monday;
    CheckBox tuesday;
    CheckBox wednesday;
    CheckBox thursday;
    CheckBox friday;
    CheckBox saturday;
    CheckBox sunday;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm_screen);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_set_alarm_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void setAlarm(View view){
        i=0;
        deleteAll();
        monday=(CheckBox)findViewById(R.id.monday);
        tuesday=(CheckBox)findViewById(R.id.tuesday);
        wednesday=(CheckBox)findViewById(R.id.wednesday);
        thursday=(CheckBox)findViewById(R.id.thursday);
        friday=(CheckBox)findViewById(R.id.friday);
        saturday=(CheckBox)findViewById(R.id.saturday);
        sunday=(CheckBox)findViewById(R.id.sunday);
        timePicker=(TimePicker)findViewById(R.id.timePicker);
        if(monday.isChecked()){
            alarmSet(2);
        }
        if(tuesday.isChecked()){
            alarmSet(3);
        }
        if(wednesday.isChecked()){
            alarmSet(4);
        }
        if(thursday.isChecked()){
           alarmSet(5);
        }
        if(friday.isChecked()){
           alarmSet(6);
        }
        if(saturday.isChecked()){
            alarmSet(7);
        }
        if(sunday.isChecked()){
           alarmSet(1);
        }
    }
    public void alarmSet(int week){
        Calendar calendar=Calendar.getInstance();
        Intent myIntent = new Intent(SetAlarmScreen.this, AlarmReceiver.class);
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if(week>calendar.get(Calendar.DAY_OF_WEEK)){
            calendar.set(Calendar.DAY_OF_WEEK,week);
            calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
            calendar.set(Calendar.SECOND,0);
            calendar.set(Calendar.MILLISECOND,0);
            pendingIntent = PendingIntent.getBroadcast(SetAlarmScreen.this,i,myIntent, 0);
            i++;
            manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntent);
        }
        else{
            if(week<calendar.get(Calendar.DAY_OF_WEEK)){
                calendar.set(Calendar.DAY_OF_WEEK, week);
                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
                calendar.set(Calendar.SECOND,0);
                calendar.set(Calendar.MILLISECOND,0);
                pendingIntent = PendingIntent.getBroadcast(SetAlarmScreen.this,i,myIntent, 0);
                i++;
                manager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis()+AlarmManager.INTERVAL_DAY*7,AlarmManager.INTERVAL_DAY*7, pendingIntent);
            }
            else{
                if((timePicker.getCurrentHour()< calendar.get(Calendar.HOUR_OF_DAY)) || ((timePicker.getCurrentHour()==calendar.get(Calendar.HOUR_OF_DAY)) && (timePicker.getCurrentMinute() < calendar.get(Calendar.MINUTE)))){
                    calendar.set(Calendar.DAY_OF_WEEK, week);
                    calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                    calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
                    calendar.set(Calendar.SECOND,0);
                    calendar.set(Calendar.MILLISECOND,0);
                    pendingIntent = PendingIntent.getBroadcast(SetAlarmScreen.this,i,myIntent, 0);
                    i++;
                    manager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis()+AlarmManager.INTERVAL_DAY*7,AlarmManager.INTERVAL_DAY*7, pendingIntent);
                }
                else{
                    calendar.set(Calendar.DAY_OF_WEEK,week);
                    calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                    calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
                    calendar.set(Calendar.SECOND,0);
                    calendar.set(Calendar.MILLISECOND,0);
                    pendingIntent = PendingIntent.getBroadcast(SetAlarmScreen.this,i,myIntent, 0);
                    i++;
                    manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntent);
                }
            }
        }
    }
    public void deleteAll(){
        for(int l=0;l<7;l++) {
            Intent myIntent1 = new Intent(SetAlarmScreen.this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(SetAlarmScreen.this, l, myIntent1, 0);
            AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            manager.cancel(pendingIntent);
        }
    }
}
