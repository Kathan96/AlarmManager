package com.example.android.alarmmanager;

import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class Calculation extends ActionBarActivity {

    private Uri notification;
    private Ringtone r;
    private PowerManager pm;
    private PowerManager.WakeLock wl;
    private TextView number_1;
    private TextView sign;
    private TextView number_2;
    private EditText answer;
    private Integer total;
    private String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PowerManager pm = (PowerManager)getSystemService(Context.POWER_SERVICE);
        if(!pm.isScreenOn()){
            wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE
                    , "MyLock");
            wl.acquire(5000);
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        r = RingtoneManager.getRingtone(this, notification);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);
        try {
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setQuestion();

    }
    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }
    public void setQuestion(){
                Random random = new Random();
                Integer num1, num2;
                int choice = random.nextInt(2);
                number_1 = (TextView) findViewById(R.id.number_1);
                number_2 = (TextView) findViewById(R.id.number_2);
                sign = (TextView) findViewById(R.id.sign);
                num1 = random.nextInt(9000) + 1000;
                num2 = random.nextInt(9000) + 1000;
                number_1.setText(Integer.toString(num1));
                number_2.setText(Integer.toString(num2));
                if (choice == 0) {
                    sign.setText("+");
                    total = num1 + num2;
                } else {
                    sign.setText("-");
                    total = num1 - num2;
                }
                result = Integer.toString(total);

    }
    public void onClick(View view){
        EditText answer=(EditText)findViewById(R.id.answer);
        String Answer_result=answer.getText().toString();
        Intent intent=new Intent(this,GoToNews.class);
        if (Answer_result.compareTo(result)==0) {
            r.stop();
            //finish();
            //System.exit(0);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calculation, menu);
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
}
