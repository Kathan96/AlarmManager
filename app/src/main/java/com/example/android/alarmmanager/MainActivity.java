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
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void onClick(View view){
        switch (view.getId()){
            case R.id.button_newalarm:
                Intent intent=new Intent(this,SetAlarmScreen.class);
                startActivity(intent);
                break;
            case R.id.button_delete:
                Intent myIntent = new Intent(MainActivity.this, AlarmReceiver.class);
                for(int l=0;l<7;l++) {
                    pendingIntent = PendingIntent.getBroadcast(MainActivity.this, l, myIntent, 0);
                    AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    manager.cancel(pendingIntent);
                }
                Toast.makeText(MainActivity.this,"Alarm Cancelled for all Days",Toast.LENGTH_LONG).show();
                break;
            case R.id.button_exit:
                finish();
                System.exit(0);
                break;
            default:
                break;
        }
    }
}
