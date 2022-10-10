package com.example.aaryanpagar.drawer.Reminders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aaryanpagar.drawer.Assignments.OnNotificationClickAss;
import com.example.aaryanpagar.drawer.Main.MainActivity;
import com.example.aaryanpagar.drawer.R;

public class OnNotificationClickRem extends AppCompatActivity {
    //int notId;
    TextView textView;
    String reminder;
    Button done;
    private Context context;
    public void onRecieve(Context c, Intent i){
        context=c;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.on_notification_click_rem);
        Intent intent= getIntent();
        //notId= intent.getIntExtra("remId",0);
        reminder= intent.getStringExtra("reminderName");
       // reminder= intent.getStringExtra("reminder");
        done= (Button) findViewById(R.id.notifClickDone);
        textView= (TextView) findViewById(R.id.notifClickText);
        textView.setText(reminder);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
