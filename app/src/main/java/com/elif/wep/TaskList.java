package com.elif.wep;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;

public class TaskList extends AppCompatActivity {


    FirebaseAuth fAuth;
    DatabaseReference db;
    String userID;

    taskAdapter taskAdapter;

    private ImageButton createTask;
    private ImageButton goalPage;

    private RecyclerView recyclerViewTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        createTask = findViewById(R.id.addTaskList);
        recyclerViewTask = findViewById(R.id.recycleViewTask);

//        goalPage =  findViewById(R.id.mainGoal);
//        goalPage.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                openGoalPage();
//            }
//        });

        fAuth = FirebaseAuth.getInstance();
        userID = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
        db = FirebaseDatabase.getInstance().getReference().child("tasks").child(userID);


        // To display the Recycler view linearly
        recyclerViewTask.setLayoutManager(new LinearLayoutManager(this));

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<TaskItem> options = new FirebaseRecyclerOptions.Builder<TaskItem>()
                .setQuery(db, TaskItem.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        taskAdapter = new taskAdapter(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerViewTask.setAdapter(taskAdapter);



        createTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTask();
            }
        });
    }


    private void openGoalPage() {
        Intent intent = new Intent(this, GoalList.class);
        startActivity(intent);
    }

    private void addTask() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(TaskList.this);
        LayoutInflater layoutInflater = LayoutInflater.from(this);

        View view = layoutInflater.inflate(R.layout.input_alertdialog, null);
        alertDialog.setView(view);

        AlertDialog addDialog = alertDialog.create();
        addDialog.setCancelable(false);

        final EditText task = view.findViewById(R.id.dialogUserTitle);
        final EditText description = view.findViewById(R.id.dialogUserDesc);

        Button save = view.findViewById(R.id.add_button);
        Button cancel = view.findViewById(R.id.cancel_button);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDialog.dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mTask = task.getText().toString().trim();
                String mDescription = description.getText().toString().trim();
                String id = db.push().getKey();
                String date = DateFormat.getDateInstance().format(new Date());


                if(TextUtils.isEmpty(mTask)) {
                    task.setError("Task required");
                    return;
                }

                if(TextUtils.isEmpty(mDescription)) {
                    description.setError("Description required");
                    return;
                } else {

                    TaskItem taskItem = new TaskItem(mTask, mDescription, date, id, false, null);

                    db.child(id).setValue(taskItem).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(TaskList.this, "successfully added", Toast.LENGTH_SHORT).show();
                            } else {
                                String error = task.getException().toString();
                                Toast.makeText(TaskList.this, "error message: " + error, Toast.LENGTH_SHORT).show();


                            }
                        }
                    });

                }

                addDialog.dismiss();;
            }

        });

        addDialog.show();



    }

    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override protected void onStart()
    {
        super.onStart();
        taskAdapter.startListening();
    }


}
