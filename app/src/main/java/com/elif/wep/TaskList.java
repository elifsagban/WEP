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
import com.google.firebase.database.Query;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class TaskList extends AppCompatActivity {


    FirebaseAuth fAuth;
    DatabaseReference db;
    String userID;

    taskAdapter taskAdapter;

    private ImageButton createTask;

    private RecyclerView recyclerViewTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_list_with_picture);

        createTask = findViewById(R.id.addTaskList);
        recyclerViewTask = findViewById(R.id.recycleViewTask);


        fAuth = FirebaseAuth.getInstance();
        userID = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
        db = FirebaseDatabase.getInstance().getReference().child("tasks").child(userID);


        recyclerViewTask.setLayoutManager(new LinearLayoutManager(this));


        Query taskQuery = db.orderByChild("done").equalTo(false);


        FirebaseRecyclerOptions<TaskItem> options = new FirebaseRecyclerOptions.Builder<TaskItem>()
                .setQuery(taskQuery, TaskItem.class)
                .build();

        taskAdapter = new taskAdapter(options);
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

        final EditText task = view.findViewById(R.id.dialogUserTitle);
        final EditText description = view.findViewById(R.id.dialogUserDesc);

        Button save = view.findViewById(R.id.add_button);
        Button cancel = view.findViewById(R.id.cancel_button);



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mTask = task.getText().toString().trim();
                String mDescription = description.getText().toString().trim();
                String id = db.push().getKey();
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm, dd/MM/yyyy ");
                Date d = new Date();
                String date = formatter.format(d);
                ArrayList<Integer> duration = new ArrayList();
                ArrayList<Integer> breaks = new ArrayList();


                if (TextUtils.isEmpty(mTask)) {
                    task.setError("Task required");
                    return;
                }

                if (TextUtils.isEmpty(mDescription)) {
                    description.setError("Description required");
                    return;
                } else {

                    TaskItem taskItem = new TaskItem(mTask, mDescription, date, id, 0, false, breaks, duration);

                    db.child(id).setValue(taskItem).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(TaskList.this, "successfully added", Toast.LENGTH_SHORT).show();
                            } else {
                                String error = task.getException().toString();
                                Toast.makeText(TaskList.this, "error message: " + error, Toast.LENGTH_SHORT).show();


                            }
                        }
                    });

                }

                addDialog.dismiss();
            }

        });

        addDialog.show();


    }

    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override
    protected void onStart() {
        super.onStart();
        taskAdapter.startListening();
    }


}
