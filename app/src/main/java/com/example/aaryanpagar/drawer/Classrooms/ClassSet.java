package com.example.aaryanpagar.drawer.Classrooms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aaryanpagar.drawer.R;

public class ClassSet extends AppCompatActivity {

    EditText editText;
    static public String className;
    Button done;
    Button cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_fragment_set);
        done= (Button) findViewById(R.id.classSetDone);
        cancel= (Button) findViewById(R.id.classSetCancel);
        editText= (EditText) findViewById(R.id.classSetName);
        Intent intent= getIntent();
        final int classId= intent.getIntExtra("classId",-1 );
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                className= editText.getText().toString();
                if(className.equals(""))
                   Toast.makeText(getApplicationContext(), "Empty Class", Toast.LENGTH_SHORT).show();
                else
                {
                    ClassMain.classList.set(classId,className);
                    ClassMain.viewAdapter.notifyDataSetChanged();
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
}
