package com.example.aaryanpagar.drawer.Tests;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.method.CharacterPickerDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aaryanpagar.drawer.Assignments.Class0Assignment;
import com.example.aaryanpagar.drawer.Assignments.SetAssignments;
import com.example.aaryanpagar.drawer.Main.NotificationBuilder;
import com.example.aaryanpagar.drawer.R;

import java.util.Calendar;

public class TestsSet extends AppCompatActivity {
    DatePicker datePicker;
    Button add;
    Button cancel;
    EditText editText1;
    String test;
    EditText editText2;
    String bench;
    EditText editText3;
    String topic;
    String date;
    int notify=-1;
    int testId;
    private Context context;
    public void onRecieve(Context c,Intent i){
        context=c;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_fragment_set);
        //INITIALIZE
        datePicker= findViewById(R.id.testDate);
        add= findViewById(R.id.testAdd);
        cancel= findViewById(R.id.testCancel);
        editText1= findViewById(R.id.testName);
        editText2= findViewById(R.id.testBench);
        editText3= findViewById(R.id.testTopic);
        Intent intent= getIntent();
        testId= intent.getIntExtra("testId", TestsMain.testList.size());
        //Initialize Date
        Calendar now= Calendar.getInstance();
        datePicker.init(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH),
                null);
        //Add test
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test= editText1.getText().toString();
                bench= editText2.getText().toString();
                topic= editText3.getText().toString();
                Calendar current = Calendar.getInstance();
                Calendar cal = Calendar.getInstance();
                cal.clear();
                cal.set(datePicker.getYear(),
                        datePicker.getMonth(),
                        datePicker.getDayOfMonth()-1,
                        19, 00, 0); //start time of a study hall previous day
                date= String.valueOf(datePicker.getDayOfMonth())+" of "+ month(datePicker.getMonth())
                        +", "+String.valueOf(datePicker.getYear());
                if (cal.compareTo(current) <= 0)
                    Toast.makeText(getApplicationContext(), "INVALID", Toast.LENGTH_SHORT).show();
                else
                    if(test.equals("")||topic.equals("")||bench.equals(""))
                        Toast.makeText(getApplicationContext(), "Empty Field", Toast.LENGTH_SHORT).show();
                else
                {
                    notify++;
                    test= editText1.getText().toString();
                    TestsMain.noItemText.setText("");
                    Intent intent = new Intent(TestsSet.this, NotificationBuilder.class);
                    intent.putExtra("who's notify", "tests");
                    intent.putExtra("reminder", test);
                    intent.putExtra("topic", topic);
                    intent.putExtra("bench", bench);
                    intent.putExtra("dateTest", date);
                    intent.putExtra("testId", notify);

                    PendingIntent alarmIntent = PendingIntent.getBroadcast(TestsSet.this, 0,
                            intent, PendingIntent.FLAG_CANCEL_CURRENT);
                    AlarmManager alarmManager = (AlarmManager) getSystemService(context.ALARM_SERVICE);
                    long startTime = cal.getTimeInMillis();
                    //create alarm
                    alarmManager.set(AlarmManager.RTC_WAKEUP, startTime, alarmIntent);
                    Toast.makeText(getApplicationContext(), "SET!", Toast.LENGTH_SHORT).show();

                    //redirect to device's calendar
                    Intent intent1 = new Intent(Intent.ACTION_INSERT)
                            .setData(CalendarContract.Events.CONTENT_URI)
                            .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime-600000)//ten minutes before
                            .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, startTime)
                            .putExtra(CalendarContract.Events.TITLE, test+ " Test")
                            .putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true)
                            .putExtra(CalendarContract.Events.DESCRIPTION, topic)
                            .putExtra(CalendarContract.Events.AVAILABILITY,
                                    CalendarContract.Events.AVAILABILITY_BUSY)
                            .putExtra(Intent.EXTRA_EMAIL, "com.example.aaryanpagar.drawer");
                    startActivity(intent1);

                    //Update testList
                    TestsMain.testList.add("");
                    TestsMain.testList.set(testId, topic);
                    TestsMain.viewAdaptertest.notifyDataSetChanged();
                    finish();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "CANCELLED", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    String month(int x) {
        switch (x+1) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return null;
        }
    }
}
