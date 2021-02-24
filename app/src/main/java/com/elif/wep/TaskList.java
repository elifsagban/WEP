package com.elif.wep;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class TaskList extends AppCompatActivity {

    private Button createTask;
    private RecyclerView recyclerView;
    private String titleTask;

    private final ArrayList<Task> tasks = new ArrayList<>();

    private LinearLayoutManager linearLayoutManager;
    TaskRecyclerViewAdapter taskRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        createTask = findViewById(R.id.addTaskList);

        recyclerView = findViewById(R.id.recycleViewTask);
        recyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        taskRecyclerViewAdapter = new TaskRecyclerViewAdapter(tasks);
        recyclerView.setAdapter(taskRecyclerViewAdapter);


        createTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder taskNameDialog = new AlertDialog.Builder(TaskList.this);
                taskNameDialog.setTitle("Enter Task Name");

                final EditText taskName = new EditText(TaskList.this);

                taskName.setInputType(InputType.TYPE_CLASS_TEXT);
                taskNameDialog.setView(taskName);

                taskNameDialog.setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        titleTask = taskName.getText().toString();
                        Toast.makeText(TaskList.this, titleTask + " is created", Toast.LENGTH_LONG).show();
                        Task task = new Task(titleTask, "item list gelecek");
                        tasks.add(task);

                    }
                });

                taskNameDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                taskNameDialog.show();
            }


        });


    }

}




