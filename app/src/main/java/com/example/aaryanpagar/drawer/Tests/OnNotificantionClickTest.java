package com.example.aaryanpagar.drawer.Tests;

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

public class OnNotificantionClickTest extends AppCompatActivity {
    //int notId;
    TextView date;
    TextView testText;
    TextView bench;
    TextView topic;
    Button done;
    private Context context;
    public void onRecieve(Context c, Intent i){
        context=c;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.on_notification_click_tests);
        Intent intent= getIntent();
        //Declaring
        testText= (TextView) findViewById(R.id.notifClickSub);
        bench= findViewById(R.id.notifClickBench);
        topic= findViewById(R.id.notifClickTopic);
        date= findViewById(R.id.notifClickDate);
        //setting values
        testText.setText("Subject: "+intent.getStringExtra("subject"));
        bench.setText("Benchmark: "+intent.getStringExtra("bench"));
        topic.setText("Topic: "+intent.getStringExtra("topic"));
        date.setText("Date: "+intent.getStringExtra("datepass"));
        done= (Button) findViewById(R.id.notifClickDoneTest);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
