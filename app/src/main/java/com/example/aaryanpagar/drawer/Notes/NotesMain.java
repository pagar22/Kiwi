package com.example.aaryanpagar.drawer.Notes;

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
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aaryanpagar.drawer.R;

import java.util.ArrayList;
import java.util.HashSet;

public class NotesMain extends Fragment {
    View view;
    FloatingActionButton fab;
    ListView listView;
    static TextView noItemText;
    static ArrayList<String> notesList=new ArrayList<>();
    static ArrayAdapter arrayAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.notes_fragment_main,null);
        fab= (FloatingActionButton) view.findViewById(R.id.notesFab); //Add note
        listView= (ListView) view.findViewById(R.id.notesList); //Notes List
        noItemText= (TextView) view.findViewById(R.id.notesText);
        SharedPreferences sharedPreferences =
                getActivity().getApplicationContext().getSharedPreferences
                        ("com.example.aaryanpagar.drawer.Notes", Context.MODE_PRIVATE);
        HashSet<String> set= (HashSet<String>) sharedPreferences.getStringSet("notesList", null);
        if(set!=null)
            notesList= new ArrayList<>(set);
        arrayAdapter= new ArrayAdapter(NotesMain.this.getActivity(),
                android.R.layout.simple_list_item_1, notesList);
        listView.setAdapter(arrayAdapter);

        if(notesList.size()==0)
            noItemText.setText("It's pretty empty in here. Add a note now!");
        //To edit a note
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent= new Intent(getActivity().getApplicationContext(),NotesEdit.class);
                intent.putExtra("noteId", position);
                startActivity(intent);
            }
        });

        //To add a new note
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent= new Intent(getActivity().getApplicationContext(),NotesEdit.class);
               startActivity(intent);
            }
        });

        //To delete a note
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(NotesMain.this.getActivity())
                        .setIcon(R.drawable.warning)
                        .setTitle("DELETE NOTE?")
                        .setMessage("The note will be permanently deleted")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                notesList.remove(position);
                                arrayAdapter.notifyDataSetChanged();
                                if(notesList.size()==0)
                                    noItemText.setText("It's pretty empty in here. Add a note now!");
                                //Shared preferences for permanent storage after deleting a note
                                SharedPreferences sharedPreferences =
                                        getActivity().getApplicationContext().getSharedPreferences
                                        ("com.example.aaryanpagar.drawer.Notes", Context.MODE_PRIVATE);
                                HashSet<String> set = new HashSet<>(NotesMain.notesList);
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
}
