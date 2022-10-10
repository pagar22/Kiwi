package com.example.aaryanpagar.drawer.Assignments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.aaryanpagar.drawer.Classrooms.ClassMain;
import com.example.aaryanpagar.drawer.R;

import java.util.ArrayList;

public class Class4Assignment extends AppCompatActivity {
    static TextView className;
    int classId;
    FloatingActionButton fab;
    ListView listView;
    public static final ArrayList<String> assignmentsList=  new ArrayList<>();
    static ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_ass_4);
        className= (TextView) findViewById(R.id.class_ass_4_name);
        fab= (FloatingActionButton) findViewById(R.id.class_ass_4_add);
        Intent intent= getIntent();
        classId= intent.getIntExtra("classId",-1);
        className.setText(ClassMain.classList.get(classId));

        //listview
        listView= (ListView) findViewById(R.id.class_ass_4_list);
        arrayAdapter= new ArrayAdapter(Class4Assignment.this,
                android.R.layout.simple_list_item_1, assignmentsList);
        listView.setAdapter(arrayAdapter);
        //add ass
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1= new Intent(Class4Assignment.this, SetAssignments.class);
                intent1.putExtra("classId", classId);
                startActivity(intent1);
                listView.setAdapter(arrayAdapter);
            }
        });
        //open ass
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s= (String) listView.getItemAtPosition(position);
                new AlertDialog.Builder(Class4Assignment.this)
                        .setTitle("ASSIGNMENT")
                        .setMessage(s)
                        .setIcon(R.drawable.classes)
                        .setNeutralButton("Done", null)
                        .show();
            }
        });
        //delete ass
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(Class4Assignment.this)
                        .setIcon(R.drawable.warning)
                        .setTitle("DELETE ASSIGNMENT?")
                        .setMessage("The assignment will be permanently deleted")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                assignmentsList.remove(position);
                                arrayAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No",null)
                        .show();
                return true;
            }
        });
    }
}
