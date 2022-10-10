package com.example.aaryanpagar.drawer.Reminders;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.aaryanpagar.drawer.Main.NotificationBuilder;
import com.example.aaryanpagar.drawer.R;

import java.util.Calendar;
import java.util.TimeZone;


public class RemindersEdit extends AppCompatActivity {
    Button set;
    Button can;
    TimePicker timePicker;
    DatePicker datePicker;
    EditText reminder;
    static public String reminderTask;
    static int notify=-1;
    int remId;
    private Context context;
    public void onRecieve(Context c,Intent i){
        context=c;
    }
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminders_fragment_set);
        Intent intent= getIntent();
        remId= intent.getIntExtra("remId", RemindersMain.remindersList.size());
        reminder= (EditText) findViewById(R.id.remindersTask);
        set= (Button) findViewById(R.id.rem_set);
        can= (Button) findViewById(R.id.rem_can);
        timePicker= (TimePicker) findViewById(R.id.rem_tp);
        datePicker= (DatePicker) findViewById(R.id.remindersDate);
        final Calendar now= Calendar.getInstance();
        datePicker.init(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH),
                null);
        set.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                reminderTask= reminder.getText().toString();
                Calendar current = Calendar.getInstance();
                Calendar cal = Calendar.getInstance();
                cal.clear();
                cal.set(datePicker.getYear(),
                        datePicker.getMonth(),
                        datePicker.getDayOfMonth(),
                        timePicker.getCurrentHour(),
                        timePicker.getMinute(),
                        0);
               if (cal.compareTo(current) <= 0)
                    Toast.makeText(getApplicationContext(), "Invalid Date or Time", Toast.LENGTH_SHORT).show();
               else
                   if(reminderTask.equals(""))
                       Toast.makeText(getApplicationContext(), "Empty Reminder", Toast.LENGTH_SHORT).show();
                else {
                    notify++;
                    reminderTask= reminder.getText().toString();
                    RemindersMain.noItemText.setText("");
                    Intent intent = new Intent(RemindersEdit.this, NotificationBuilder.class);
                    intent.putExtra("who's notify", "rem");
                    intent.putExtra("remId", notify);
                    intent.putExtra("reminder", reminderTask);
                    //intent.putExtra("todo", "notified");
                    PendingIntent alarmIntent = PendingIntent.getBroadcast(RemindersEdit.this, 0,
                            intent, PendingIntent.FLAG_CANCEL_CURRENT);
                    AlarmManager alarmManager = (AlarmManager) getSystemService(context.ALARM_SERVICE);
                    long startTime = cal.getTimeInMillis();
                    //create alarm
                    alarmManager.set(AlarmManager.RTC_WAKEUP, startTime, alarmIntent);
                   Toast.makeText(getApplicationContext(), "SET!", Toast.LENGTH_SHORT).show();
                   //Update remindersList
                   RemindersMain.remindersList.add("");
                   RemindersMain.remindersList.set(remId, reminderTask);
                   RemindersMain.arrayAdapter.notifyDataSetChanged();
                   finish();
                }
            }
        });
        can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
