package com.elif.wep;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

public class TaskTimer extends AppCompatActivity {
    FirebaseAuth fAuth;
    DatabaseReference db;
    DatabaseReference dbTasks;
    String userID;
    String id;
    private int seconds = 0;
    // Is the stopwatch running?
    private boolean running;
    private boolean wasRunning;
    private Button startButton;
    private Button pauseButton;
    private Button saveButton;
    private TaskItem taskItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_design);


        Chronometer chronometer = new Chronometer();
        TextView time_view = findViewById(R.id.time_view);
        TextView selectedTask = findViewById(R.id.selectedTask);


        seconds = chronometer.getSeconds();
        running = chronometer.isRunning();
        wasRunning = chronometer.isWasRunning();

        startButton = findViewById(R.id.start_button);
        pauseButton = findViewById(R.id.pause_button);
        saveButton = findViewById(R.id.save_button);


        fAuth = FirebaseAuth.getInstance();
        userID = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
        db = FirebaseDatabase.getInstance().getReference().child("tasks").child(userID);
        dbTasks = FirebaseDatabase.getInstance().getReference().child("doneTasks").child(userID);

        if (getIntent().getExtras() != null) {
            taskItem = (TaskItem) getIntent().getSerializableExtra("task");
            assert taskItem != null;
            selectedTask.setText(taskItem.getTitle());
            startTimer(savedInstanceState, chronometer, time_view);


        }
    }

    // Save the state of the stopwatch
    // if it's about to be destroyed.
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState
                .putInt("seconds", seconds);
        savedInstanceState
                .putBoolean("running", running);
        savedInstanceState
                .putBoolean("wasRunning", wasRunning);
    }


    private void startTimer(Bundle savedInstanceState, Chronometer chronometer, TextView time_view) {
        if (savedInstanceState != null) {
            // Get the previous state of the stopwatch
            // if the activity has been
            // destroyed and recreated.
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        chronometer.runTimer(time_view);
        startChronometer(chronometer);
        pauseChronometer(chronometer);
        saveChronometer(chronometer);
    }


    private void startChronometer(Chronometer chronometer) {
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.onClickStart();
            }
        });
    }

    private void pauseChronometer(Chronometer chronometer) {
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.onClickStop();

            }
        });
    }

    private void saveChronometer(Chronometer chronometer) {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishTask(chronometer);
                chronometer.onPause();


            }
        });
    }

    private void saveSeconds(Chronometer chronometer) {

        int custom_seconds = chronometer.getSeconds();
        ArrayList items_break = chronometer.getItemsBreak();
        ArrayList avg_duration = chronometer.getItemsDuration();

        id = taskItem.getId();
        db.child(id).child("seconds").setValue(custom_seconds);
        db.child(id).child("breaks").setValue(items_break);
        db.child(id).child("duration").setValue(avg_duration);
        db.child(id).child("done").setValue(true);


    }


    private void finishTask(Chronometer chronometer) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(TaskTimer.this);
        LayoutInflater layoutInflater = LayoutInflater.from(this);

        View view = layoutInflater.inflate(R.layout.save_task_dialog, null);
        alertDialog.setView(view);

        AlertDialog saveDialog = alertDialog.create();
        Button continueBtn = view.findViewById(R.id.continueBtn);
        Button saveBtn = view.findViewById(R.id.saveButton);


        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDialog.dismiss();
                chronometer.onResume();

            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                chronometer.onClickSave();
                saveSeconds(chronometer);
                returnHomePage();
                Toast.makeText(TaskTimer.this, "well done!", Toast.LENGTH_SHORT).show();
                saveDialog.dismiss();


            }


        });

        saveDialog.show();


    }


    private void returnHomePage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}