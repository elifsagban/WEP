package com.elif.wep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class TaskTimer extends AppCompatActivity {
    private int seconds = 0;

    // Is the stopwatch running?
    private boolean running;
    private boolean wasRunning;
    private Button startButton;
    private Button pauseButton;
    private Button saveButton;

    private TaskItem taskItem;


    FirebaseAuth fAuth;
    DatabaseReference db;
    String userID;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_timer);


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
    public void onSaveInstanceState (Bundle savedInstanceState){
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
                chronometer.onClickSave();
                saveSeconds(chronometer);
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

    }

}