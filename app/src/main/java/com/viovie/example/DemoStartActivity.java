package com.viovie.example;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.viovie.example.receiver.AlarmReceiver;

import java.util.Calendar;

public class DemoStartActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_start);

        startActivity(new Intent(this, com.viovie.example.landingslide.MainActivity.class));

        TextView rotateTest = (TextView) findViewById(R.id.rotate_text);
        rotateTest.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate));

        TextView cardListText = (TextView) findViewById(R.id.card_example_text);
        cardListText.setOnClickListener(this);

        TextView alarmText = (TextView) findViewById(R.id.alarm_text);
        alarmText.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_example_text: {
                startActivity(new Intent(this, com.viovie.example.cardlist.ListActivity.class));
                break;
            }
            case R.id.alarm_text: {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MINUTE, 3);

                Intent intent = new Intent(this, AlarmReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_ONE_SHOT);

                AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                break;
            }
        }
    }
}
