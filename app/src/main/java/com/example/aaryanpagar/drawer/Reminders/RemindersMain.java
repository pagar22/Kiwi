package com.example.aaryanpagar.drawer.Reminders;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aaryanpagar.drawer.Notes.NotesMain;
import com.example.aaryanpagar.drawer.R;
import java.util.ArrayList;
import java.util.HashSet;

public class RemindersMain extends Fragment{
    View view;
    ListView listView;
    static TextView noItemText;
    static ArrayAdapter arrayAdapter;
    static public ArrayList<String> remindersList= new ArrayList<>();
    FloatingActionButton fab;
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.reminders_fragment_main, container, false);
        listView= (ListView) view.findViewById(R.id.remindersList);
        noItemText= (TextView) view.findViewById(R.id.remindersText);
        arrayAdapter= new ArrayAdapter(RemindersMain.this.getActivity(),
                android.R.layout.simple_list_item_1, remindersList);
        listView.setAdapter(arrayAdapter);
        if(remindersList.size()==0)
            noItemText.setText("It's pretty empty in here. Add a reminder now!");
        else
            noItemText.setText("");
        fab= (FloatingActionButton) view.findViewById(R.id.remindersFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RemindersMain.this.getActivity(), RemindersEdit.class);
                //intent.putExtra("remId", remindersList.size());
                startActivity(intent);
                listView.setAdapter(arrayAdapter);
            }
        });
        //To open a reminder
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s= (String) listView.getItemAtPosition(position);
                new AlertDialog.Builder(RemindersMain.this.getActivity())
                        .setTitle("REMINDER")
                        .setMessage(s)
                        .setIcon(R.drawable.reminder_dia)
                        .setNeutralButton("Done", null)
                        .show();
            }
        });
        //To delete a reminder
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(RemindersMain.this.getActivity())
                        .setIcon(R.drawable.warning)
                        .setTitle("DELETE REMINDER?")
                        .setMessage("The reminder will be permanently deleted")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                remindersList.remove(position);
                                arrayAdapter.notifyDataSetChanged();
                                if(remindersList.size()==0)
                                noItemText.setText("It's pretty empty in here. Add a reminder now!");
                                //Shared preferences for permanent storage after deleting a note
                                SharedPreferences sharedPreferences =
                                        getActivity().getApplicationContext().getSharedPreferences
                                                ("com.example.aaryanpagar.drawer.Notes", Context.MODE_PRIVATE);
                                HashSet<String> set = new HashSet<>(RemindersMain.remindersList);
                                sharedPreferences.edit().putStringSet("reminders", set).apply();
                            }
                        })
                        .setNegativeButton("No",null)
                        .show();
                return true;
            }
        });



        return view;
    }

}