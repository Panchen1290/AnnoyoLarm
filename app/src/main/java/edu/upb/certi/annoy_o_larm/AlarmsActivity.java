package edu.upb.certi.annoy_o_larm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Random;

public class AlarmsActivity extends AppCompatActivity {

    private AlarmManager alarmManager;
    private TimePicker timePicker;
    private TextView textView;
    private Context context;
    private Button turnOn, turnOff;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarms);

        context = this;

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        timePicker = (TimePicker) findViewById(R.id.alarmTimePicker);
        textView = (TextView) findViewById(R.id.alarmTextView);

        final Intent intent = new Intent(context, AlarmReceiver.class);

        final Calendar calendar = Calendar.getInstance();

        turnOn = (Button) findViewById(R.id.alarmOnButton);
        turnOn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                calendar.add(Calendar.SECOND, 3);

                final int hour = timePicker.getCurrentHour();
                final int minute = timePicker.getCurrentMinute();;

                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                calendar.set(Calendar.MINUTE, timePicker.getMinute());

                intent.putExtra("extra", "yes");
                pendingIntent = PendingIntent.getBroadcast(AlarmsActivity.this, 0,
                        intent, PendingIntent.FLAG_UPDATE_CURRENT);

                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

                setAlarmText("Alarma configurada para: " + hour + ":" + minute);
            }
        });

        turnOff = (Button) findViewById(R.id.alarmOffButton);
        turnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int min = 1;
                int max = 9;

                Random r = new Random();
                int random_number = r.nextInt(max - min + 1) + min;
                Log.e("random number is ", String.valueOf(random_number));

                intent.putExtra("extra", "no");
                sendBroadcast(intent);



                alarmManager.cancel(pendingIntent);

                setAlarmText("Alarma apagada");
            }
        });
    }

    private void setAlarmText(String s) {
        textView.setText(s);
    }
}
