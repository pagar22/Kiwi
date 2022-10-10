package com.example.aaryanpagar.drawer.Classrooms;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.aaryanpagar.drawer.Assignments.Class0Assignment;
import com.example.aaryanpagar.drawer.Assignments.Class1Assignment;
import com.example.aaryanpagar.drawer.Assignments.Class2Assignment;
import com.example.aaryanpagar.drawer.Assignments.Class3Assignment;
import com.example.aaryanpagar.drawer.Assignments.Class4Assignment;
import com.example.aaryanpagar.drawer.Assignments.Class5Assignment;
import com.example.aaryanpagar.drawer.R;

import java.util.ArrayList;

public class ClassMain extends Fragment{
    View view;
    /*ListView listView;
    static ArrayAdapter arrayAdapter;
    static ArrayList<String> classList= new ArrayList<>();
    */
    FloatingActionButton addClass;
    ListView listView;
    static ViewAdapter viewAdapter;
    public static final ArrayList<String> classList=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.class_fragment_main, container, false);
        listView= (ListView) view.findViewById(R.id.classList);
        if(classList.size()<=5) {
            classList.add("NEW CLASS");
            classList.add("NEW CLASS");
            classList.add("NEW CLASS");
            classList.add("NEW CLASS");
            classList.add("NEW CLASS");
            classList.add("NEW CLASS");
        }

        viewAdapter= new ViewAdapter();
        listView.setAdapter(viewAdapter);
        //To open a class
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {

                    case 0:
                        //check if its a new class
                        if(classList.get(0).equals("NEW CLASS"))
                        {
                            Intent intent= new Intent(getActivity().getApplicationContext(),
                                    ClassSet.class);
                            intent.putExtra("classId", position);
                            startActivity(intent);
                        }
                        else {
                            //open created class
                            Intent intent = new Intent(getActivity().getApplicationContext(),
                                    Class0Assignment.class);
                            intent.putExtra("classId", position);
                            startActivity(intent);
                        }
                    break;
                    case 1:
                        if(classList.get(1).equals("NEW CLASS"))
                        {
                            Intent intent= new Intent(getActivity().getApplicationContext(),
                                    ClassSet.class);
                            intent.putExtra("classId", position);
                            startActivity(intent);
                        }
                        else {
                            Intent intent = new Intent(getActivity().getApplicationContext(),
                                    Class1Assignment.class);
                            intent.putExtra("classId", position);
                            startActivity(intent);
                        }
                        break;
                    case 2:
                        if(classList.get(2).equals("NEW CLASS"))
                        {
                            Intent intent= new Intent(getActivity().getApplicationContext(),
                                    ClassSet.class);
                            intent.putExtra("classId", position);
                            startActivity(intent);
                        }
                        else {
                            Intent intent = new Intent(getActivity().getApplicationContext(),
                                    Class2Assignment.class);
                            intent.putExtra("classId", position);
                            startActivity(intent);
                        }
                        break;
                    case 3:
                        if(classList.get(3).equals("NEW CLASS"))
                        {
                            Intent intent= new Intent(getActivity().getApplicationContext(),
                                    ClassSet.class);
                            intent.putExtra("classId", position);
                            startActivity(intent);
                        }
                        else {
                            Intent intent = new Intent(getActivity().getApplicationContext(),
                                    Class3Assignment.class);
                            intent.putExtra("classId", position);
                            startActivity(intent);
                        }
                        break;
                    case 4:
                        if(classList.get(4).equals("NEW CLASS"))
                        {
                            Intent intent= new Intent(getActivity().getApplicationContext(),
                                    ClassSet.class);
                            intent.putExtra("classId", position);
                            startActivity(intent);
                        }
                        else {
                            Intent intent = new Intent(getActivity().getApplicationContext(),
                                    Class4Assignment.class);
                            intent.putExtra("classId", position);
                            startActivity(intent);
                        }
                        break;
                    case 5:
                    if(classList.get(5).equals("NEW CLASS"))
                    {
                        Intent intent= new Intent(getActivity().getApplicationContext(),
                                ClassSet.class);
                        intent.putExtra("classId", position);
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(getActivity().getApplicationContext(),
                                Class5Assignment.class);
                        intent.putExtra("classId", position);
                        startActivity(intent);
                    }
                }
            }
        });

        //To delete a class
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(ClassMain.this.getActivity())
                        .setIcon(R.drawable.warning)
                        .setTitle("RESET CLASSROOM?")
                        .setMessage("The classroom will be permanently RESET")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                classList.set(position, "NEW CLASS");
                                if(position==0)
                                    Class0Assignment.assignmentsList.clear();
                                else if(position==1)
                                    Class1Assignment.assignmentsList.clear();
                                else if(position==2)
                                    Class2Assignment.assignmentsList.clear();
                                else if(position==3)
                                    Class3Assignment.assignmentsList.clear();
                                else if(position==4)
                                    Class4Assignment.assignmentsList.clear();
                                else if(position==5)
                                    Class5Assignment.assignmentsList.clear();

                                viewAdapter.notifyDataSetChanged();
                                listView.setAdapter(viewAdapter);
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
    public class ViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return classList.size();
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
            View v= LayoutInflater.from(getActivity().getApplicationContext())
                    .inflate(R.layout.card_view_classes, null);
            TextView className= v.findViewById(R.id.class_name_card);
            className.setText(classList.get(position));
            return v;
        }
    }

}

