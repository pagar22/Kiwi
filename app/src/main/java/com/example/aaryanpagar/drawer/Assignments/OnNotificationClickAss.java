package com.example.aaryanpagar.drawer.Assignments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aaryanpagar.drawer.Classrooms.ClassMain;
import com.example.aaryanpagar.drawer.Main.MainActivity;
import com.example.aaryanpagar.drawer.R;

public class OnNotificationClickAss extends AppCompatActivity {
    TextView assName;
    TextView date;
    TextView assSub;
    Button done;
    private Context context;
    public void onRecieve(Context c, Intent i){
        context=c;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.on_notification_click_ass);
        final Intent intent= getIntent();
        done= (Button) findViewById(R.id.notifClickAssDone);
        assName= (TextView) findViewById(R.id.notifClickAss);
        assSub= (TextView) findViewById(R.id.notifClickAssSub);
        date= (TextView) findViewById(R.id.notifClickAssDate);
        assName.setText("Assignment: "+intent.getStringExtra("assi"));
        assSub.setText("Subject: "+intent.getStringExtra("subject2"));
        date.setText("Date: "+intent.getStringExtra("datepass2"));
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
