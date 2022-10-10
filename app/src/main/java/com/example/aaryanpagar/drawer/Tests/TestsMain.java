package com.example.aaryanpagar.drawer.Tests;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aaryanpagar.drawer.Assignments.Class0Assignment;
import com.example.aaryanpagar.drawer.Classrooms.ClassMain;
import com.example.aaryanpagar.drawer.R;
import com.example.aaryanpagar.drawer.Reminders.RemindersMain;

import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;

public class TestsMain extends Fragment {
    ListView listView;
    View view;
    static TextView noItemText;
    FloatingActionButton fab;
    public static final ArrayList<String> testList= new ArrayList<>();
    static ViewAdaptertest viewAdaptertest;
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.test_fragment_main, container, false);
        SharedPreferences sharedPreferences =
                getActivity().getApplicationContext().getSharedPreferences
                        ("com.example.aaryanpagar.drawer.Notes", Context.MODE_PRIVATE);
        HashSet<String> set = new HashSet<>(TestsMain.testList);
        sharedPreferences.edit().putStringSet("tests", set).apply();
        final Intent get= getActivity().getIntent();
        noItemText= (TextView) view.findViewById(R.id.testText);
        listView= (ListView) view.findViewById(R.id.testList);
        fab= (FloatingActionButton) view.findViewById(R.id.testFab);
        //fabStore= (FloatingActionButton) view.findViewById(R.id.testfabStore);
        viewAdaptertest= new ViewAdaptertest();
        listView.setAdapter(viewAdaptertest);
        //set noitemtext
        if(testList.size()==0)
            noItemText.setText("It's pretty empty in here. Add a test now!");
        //addTest
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(TestsMain.this.getActivity(), TestsSet.class);
                startActivity(intent);
                listView.setAdapter(viewAdaptertest);
            }
        });
        //ON TEST CLICK
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s= (String) testList.get(position);
                new AlertDialog.Builder(TestsMain.this.getActivity())
                        .setTitle("ASSIGNMENT")
                        .setMessage(s)
                        .setIcon(R.drawable.test)
                        .setNeutralButton("Done", null)
                        .show();
            }
        });
        //Delete a test
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(TestsMain.this.getActivity())
                        .setIcon(R.drawable.warning)
                        .setTitle("DELETE test?")
                        .setMessage("The test will be permanently deleted")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                testList.remove(position);
                                viewAdaptertest.notifyDataSetChanged();
                                if(testList.size()==0)
                                    noItemText.setText("It's pretty empty in here. Add a test now!");
                                //Shared preferences for permanent storage after deleting a note
                                SharedPreferences sharedPreferences =
                                        getActivity().getApplicationContext().getSharedPreferences
                                                ("com.example.aaryanpagar.drawer.Notes", Context.MODE_PRIVATE);
                                HashSet<String> set = new HashSet<>(RemindersMain.remindersList);
                                sharedPreferences.edit().putStringSet("notes", set).apply();
                            }
                        })
                        .setNegativeButton("No",null)
                        .show();
                return true;
            }
        });
        return view;
    }
    //implement card view
    public class ViewAdaptertest extends BaseAdapter {

        @Override
        public int getCount() {
            return testList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = LayoutInflater.from(getActivity().getApplicationContext())
                    .inflate(R.layout.card_view_tests, null);
            TextView testName= v.findViewById(R.id.test_sub_card);
            testName.setText(testList.get(position));
            return v;
        }
    }
}