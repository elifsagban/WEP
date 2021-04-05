package com.elif.wep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;
import java.util.Objects;

public class TaskTimer extends AppCompatActivity {
    private int seconds = 0;

    // Is the stopwatch running?
    private boolean running;
    private boolean wasRunning;
    private Button startButton;
    private Button pauseButton;
    private Button resetButton;
    private TaskItem taskItem;


    FirebaseAuth fAuth;
    DatabaseReference db;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_timer);
        if(getIntent().getExtras() != null) {
            TaskItem taskItem = (TaskItem) getIntent().getSerializableExtra("task");

        }

        Chronometer chronometer = new Chronometer();
        TextView time_view = findViewById(R.id.time_view);
        TextView selectedTask = findViewById(R.id.selectedTask);


        seconds = chronometer.getSeconds();
        running = chronometer.isRunning();
        wasRunning = chronometer.isWasRunning();

        startButton = findViewById(R.id.start_button);
        pauseButton = findViewById(R.id.stop_button);
        resetButton = findViewById(R.id.reset_button);




        fAuth = FirebaseAuth.getInstance();
        userID = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
        db = FirebaseDatabase.getInstance().getReference().child("tasks").child(userID);

        if (getIntent().getExtras() != null) {
            taskItem = (TaskItem) getIntent().getSerializableExtra("task");
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
        stopChronometer(chronometer);
        resetChronometer(chronometer);
    }


    private void startChronometer(Chronometer chronometer) {
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.onClickStart();
                savetoFirebase(chronometer);

            }
        });
    }

    private void stopChronometer(Chronometer chronometer) {
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.onClickStop();
                savetoFirebase(chronometer);

            }
        });
    }

    private void resetChronometer(Chronometer chronometer) {
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.onClickReset();
            }
        });
    }

    private void savetoFirebase(Chronometer chronometer) {


        db.child(taskItem.getId()).child("seconds").setValue(chronometer.getSeconds());
        db.child(taskItem.getId()).child("title").setValue("they saaaay");

    }

}