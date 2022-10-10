package com.example.aaryanpagar.drawer.Assignments;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aaryanpagar.drawer.Main.NotificationBuilder;
import com.example.aaryanpagar.drawer.R;
import com.example.aaryanpagar.drawer.Reminders.RemindersEdit;
import com.example.aaryanpagar.drawer.Reminders.RemindersMain;

import java.io.BufferedReader;
import java.util.Calendar;
import java.util.Set;

public class SetAssignments extends AppCompatActivity {
    DatePicker datePicker;
    Button set;
    Button cancel;
    EditText assignmentName;
    static public String assignment;
    String date;
    String subject;
    static int notify=-1;
    int assId;
    int classId;
    private Context context;
    public void onRecieve(Context c,Intent i){
        context=c;
    }
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_fragment_assignments_set);
        //INTENT
        Intent intent= getIntent();
        classId= intent.getIntExtra("classId",-1);
        //INITIALIZE
        datePicker= (DatePicker) findViewById(R.id.assignmentDate);
        set= (Button) findViewById(R.id.assignmentCreate);
        cancel= (Button) findViewById(R.id.assignmentCancel);
        assignmentName= (EditText) findViewById(R.id.assignmentName);
        //DATE INITIALIZE
        final Calendar now= Calendar.getInstance();
        datePicker.init(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH),
                null);
        //CREATE ASSIGNMNET
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assignment= assignmentName.getText().toString();
                Calendar current = Calendar.getInstance();
                Calendar cal = Calendar.getInstance();
                cal.clear();
                cal.set(datePicker.getYear(),
                        datePicker.getMonth(),
                        datePicker.getDayOfMonth()-1,
                        19, 30,0); //Start time of study hall the previous day
                date= String.valueOf(datePicker.getDayOfMonth())+" of "+ month(datePicker.getMonth())
                        +", "+String.valueOf(datePicker.getYear());
                if (cal.compareTo(current) <= 0)
                    Toast.makeText(getApplicationContext(), "Invalid date", Toast.LENGTH_SHORT).show();
                else
                    if(assignment.equals(""))
                        Toast.makeText(getApplicationContext(), "Empty assignment", Toast.LENGTH_SHORT).show();
                    else
                    {
                        switch (classId) {
                            case 0:
                                assId= Class0Assignment.assignmentsList.size();
                                subject= Class0Assignment.className.getText().toString();
                            Class0Assignment.assignmentsList.add("");
                            Class0Assignment.assignmentsList.set(assId, assignment);
                            Class0Assignment.arrayAdapter.notifyDataSetChanged();
                            finish();
                            break;
                            case 1:
                                assId= Class1Assignment.assignmentsList.size();
                                subject= Class1Assignment.className.getText().toString();
                                Class1Assignment.assignmentsList.add("");
                                Class1Assignment.assignmentsList.set(assId, assignment);
                                Class1Assignment.arrayAdapter.notifyDataSetChanged();
                                finish();
                                break;
                            case 2:
                                assId= Class2Assignment.assignmentsList.size();
                                subject= Class2Assignment.className.getText().toString();
                                Class2Assignment.assignmentsList.add("");
                                Class2Assignment.assignmentsList.set(assId, assignment);
                                Class2Assignment.arrayAdapter.notifyDataSetChanged();
                                finish();
                                break;
                            case 3:
                                assId= Class3Assignment.assignmentsList.size();
                                subject= Class3Assignment.className.getText().toString();
                                Class3Assignment.assignmentsList.add("");
                                Class3Assignment.assignmentsList.set(assId, assignment);
                                Class3Assignment.arrayAdapter.notifyDataSetChanged();
                                finish();
                                break;
                            case 4:
                                assId= Class4Assignment.assignmentsList.size();
                                subject= Class4Assignment.className.getText().toString();
                                Class4Assignment.assignmentsList.add("");
                                Class4Assignment.assignmentsList.set(assId, assignment);
                                Class4Assignment.arrayAdapter.notifyDataSetChanged();
                                finish();
                                break;
                            case 5:
                                assId= Class5Assignment.assignmentsList.size();
                                subject= Class5Assignment.className.getText().toString();
                                Class5Assignment.assignmentsList.add("");
                                Class5Assignment.assignmentsList.set(assId, assignment);
                                Class5Assignment.arrayAdapter.notifyDataSetChanged();
                                finish();
                                break;
                        }
                        notify++;
                        assignment= assignmentName.getText().toString();
                        Intent intent = new Intent(SetAssignments.this, NotificationBuilder.class);
                        intent.putExtra("who's notify", "ass");
                        intent.putExtra("subject", subject);
                        intent.putExtra("reminder", assignment);
                        intent.putExtra("assId", notify);
                        intent.putExtra("dateAss", date);
                        //intent.putExtra("todo", "notified");
                        PendingIntent alarmIntent = PendingIntent.getBroadcast(SetAssignments.this, 0,
                                intent, PendingIntent.FLAG_CANCEL_CURRENT);
                        AlarmManager alarmManager = (AlarmManager) getSystemService(context.ALARM_SERVICE);
                        long startTime = cal.getTimeInMillis();
                        //create alarm
                        alarmManager.set(AlarmManager.RTC_WAKEUP, startTime, alarmIntent);
                        Toast.makeText(getApplicationContext(), "ADDED!", Toast.LENGTH_SHORT).show();

                        //redirect to device calendar
                        Intent intent1 = new Intent(Intent.ACTION_INSERT)
                                .setData(CalendarContract.Events.CONTENT_URI)
                                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime-600000)
                                //ten minutes before
                                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, startTime)
                                .putExtra(CalendarContract.Events.TITLE, subject+ " Assignment")
                                .putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true)
                                .putExtra(CalendarContract.Events.DESCRIPTION, assignment)
                                .putExtra(CalendarContract.Events.AVAILABILITY,
                                        CalendarContract.Events.AVAILABILITY_BUSY)
                                .putExtra(Intent.EXTRA_EMAIL, "com.example.aaryanpagar.drawer");
                        startActivity(intent1);
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "CANCELLED",Toast.LENGTH_SHORT).show();
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
