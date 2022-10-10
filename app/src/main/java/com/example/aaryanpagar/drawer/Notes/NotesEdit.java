package com.example.aaryanpagar.drawer.Notes;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aaryanpagar.drawer.Main.MainActivity;
import com.example.aaryanpagar.drawer.R;

import java.util.HashSet;

public class NotesEdit extends AppCompatActivity {

    int noteId;
    Button done;
    Button cancel;
    EditText contentet;
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_fragment_edit);
        contentet= (EditText) findViewById(R.id.notesEditContent);
        Intent intent= getIntent();
        noteId= intent.getIntExtra("noteId", -1);
        cancel= (Button) findViewById(R.id.notesEditCancel);
        if(noteId!=-1) {
            contentet.setText(NotesMain.notesList.get(noteId));
            done= (Button) findViewById(R.id.notesEditSet);
            done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s= contentet.getText().toString();
                    NotesMain.notesList.set(noteId, s);
                    NotesMain.arrayAdapter.notifyDataSetChanged();
                    SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences
                            ("com.example.aaryanpagar.drawer.Notes", Context.MODE_PRIVATE);
                    HashSet<String> set = new HashSet<>(NotesMain.notesList);
                    sharedPreferences.edit().putStringSet("notes", set).apply();
                    finish();
                }
            });
        }

        else{
            NotesMain.noItemText.setText("");
                done = (Button) findViewById(R.id.notesEditSet);
                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s= contentet.getText().toString();
                        if(s.equals(""))
                            Toast.makeText(getApplicationContext(), "Empty Note", Toast.LENGTH_SHORT).show();
                        else {
                            NotesMain.notesList.add("");
                            noteId = NotesMain.notesList.size() - 1;
                            NotesMain.notesList.set(noteId, s);
                            NotesMain.arrayAdapter.notifyDataSetChanged();
                            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences
                                    ("com.example.aaryanpagar.drawer.Notes", Context.MODE_PRIVATE);
                            HashSet<String> set = new HashSet<>(NotesMain.notesList);
                            sharedPreferences.edit().putStringSet("notes", set).apply();
                            finish();
                        }
                    }
                });
            }
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "CANCELLED", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    /*
    public  void hidekeyboard() {
        try{
            InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);

            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        catch (Exception e){}
    }*/
    /*
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.notes_fragment_edit, container, false);
        titleet = (EditText) view.findViewById(R.id.notesEditTitle);
        contentet = (EditText) view.findViewById(R.id.notesEditContent);
        Intent intent = getActivity().getIntent();
        noteId = intent.getIntExtra("noteId", -1);
        if (noteId != -1) {
            contentet.setText(NotesMain.notesList.get(noteId));
        }
        return view;
        View view;
    EditText titleet;
    EditText contentet;
    int noteId;
     */
    /*protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_fragment_edit);
        /*
        }
    }
    }*/
}
