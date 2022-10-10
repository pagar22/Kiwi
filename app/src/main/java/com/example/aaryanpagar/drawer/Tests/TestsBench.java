package com.example.aaryanpagar.drawer.Tests;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aaryanpagar.drawer.R;

import java.net.Inet4Address;

public class TestsBench extends AppCompatActivity {
    private Context context;
    public void onRecieve(Context c, Intent i){
        context=c;
    }
    EditText editText;
    Button done;
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tests_bench);
        final Intent intent= getIntent();
        final Intent intent1= new Intent(TestsBench.this, TestsMain.class);
        editText= findViewById(R.id.testBench);
        done= findViewById(R.id.testBenchDone);
        name= findViewById(R.id.testBenchName);
        name.setText(intent.getStringExtra("test name"));
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int poi= intent.getIntExtra("poi",0 );
                int bench= intent.getIntExtra("benchmark2", 0);
                int benchGot= Integer.parseInt(editText.getText().toString());
                if(Integer.toString(benchGot).equals(""))
                    Toast.makeText(getApplicationContext(), "Invalid benchmark", Toast.LENGTH_SHORT).show();
                else
                if(bench<benchGot){
                    poi+=(benchGot-bench);
                    Toast.makeText(getApplicationContext(), Integer.toString(bench), Toast.LENGTH_LONG).show();
                    intent1.putExtra("points", poi);
                }
                else
                    Toast.makeText(getApplicationContext(), "Better luck next time :)", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
