package com.elif.wep;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class TaskList extends AppCompatActivity {
    private ImageButton statPage;
    private ImageButton planPage;
    private ImageButton homePage;
    private ImageButton taskPage;
    private ImageButton profilePage;

    private Button createTask;
    private RecyclerView recyclerView;
    private SubItemController subItemController;
    private Button goalPage;

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

        goalPage =  findViewById(R.id.mainGoal);
        goalPage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            openGoalPage();
            }
        });




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
                       subItemController = new SubItemController();

                        Task task = new Task(titleTask, subItemController.getListOfItems());
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


            statPage = (ImageButton) findViewById(R.id.statPage);
            statPage.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    openStatsPage();

                }
            });
            planPage = (ImageButton) findViewById(R.id.planPage);
            planPage.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    openPlanPage();

                }
            });
            homePage = (ImageButton) findViewById(R.id.homePage);
            homePage.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    openHomePage();

                }
            });
            taskPage = (ImageButton) findViewById(R.id.taskPage);
            taskPage.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    openTaskPage();

                }
            });
            profilePage = (ImageButton) findViewById(R.id.profilePage);
            profilePage.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    openProfilePage();

                }
            });
        }
        public void openStatsPage() {
            Intent intent = new Intent(this, Statistics.class);
            startActivity(intent);
        }
        private void openPlanPage() {
            Intent intent = new Intent(this, Schedule.class);
            startActivity(intent);
        }
        private void openHomePage() {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        private void openTaskPage() {
            Intent intent = new Intent(this, TaskList.class);
            startActivity(intent);
        }
        private void openProfilePage() {
            Intent intent = new Intent(this, Profile.class);
            startActivity(intent);
        }
    private void openGoalPage() {
        Intent intent = new Intent(this, GoalList.class);
        startActivity(intent);
    }


    }






