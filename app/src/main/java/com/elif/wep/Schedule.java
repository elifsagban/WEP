package com.elif.wep;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Objects;

public class Schedule extends AppCompatActivity {

    FirebaseAuth fAuth;
    DatabaseReference db;
    String userID;
    scheduleAdapter scheduleAdapter;
    SwitchCompat switchCompat;
    SwitchCompat switchCompatMinutes;
    SwitchCompat switchCompatHour;
    Boolean switchCheckPom;
    Boolean switchCheckMin;
    Boolean switchCheckHr;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        fAuth = FirebaseAuth.getInstance();
        switchCheckHr = false;
        switchCheckMin = false;
        switchCheckPom = false;
        switchCompat = findViewById(R.id.pomodoroSwitch);
        SharedPreferences sharedPreferences = getSharedPreferences("savePom", Context.MODE_PRIVATE);
        switchCompat.setChecked(sharedPreferences.getBoolean("valuePom", false));

        switchCompat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switchCompat.isChecked() && ((switchCheckHr || switchCheckMin) == false)){
                    switchCheckPom = true;
                    SharedPreferences.Editor editor = getSharedPreferences("savePom", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("valuePom", switchCompat.isChecked());
                    editor.apply();
                    switchCompat.setChecked(true);
                    Toast.makeText(Schedule.this, "Pomodoro Plan is enabled!",
                            Toast.LENGTH_LONG).show();
                    System.out.println("Pomodoro Plan is enabled!");

                }else{
                    if(!switchCheckPom){
                        Toast.makeText(Schedule.this, "Please choose only 1 plan!",
                                Toast.LENGTH_LONG).show();
                    }
                    switchCheckPom = false;
                    SharedPreferences.Editor editor= getSharedPreferences("savePom", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("valuePom", false);
                    editor.apply();
                    switchCompat.setChecked(false);
                }
            }
        });
        switchCompatMinutes = findViewById(R.id.minutesSwitch);
        SharedPreferences sharedPreferencesMin = getSharedPreferences("saveMin", Context.MODE_PRIVATE);
        switchCompatMinutes.setChecked(sharedPreferencesMin.getBoolean("valueMin", false));

        switchCompatMinutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switchCompatMinutes.isChecked() && ((switchCheckHr || switchCheckPom) == false)){
                    switchCheckMin = true;
                    SharedPreferences.Editor editor = getSharedPreferences("saveMin", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("valueMin", switchCompatMinutes.isChecked());
                    editor.apply();
                    switchCompatMinutes.setChecked(true);
                    Toast.makeText(Schedule.this, "40 Minutes Plan is enabled!",
                            Toast.LENGTH_LONG).show();
                    System.out.println("40 Minutes Plan is enabled!");

                }else{
                    if(!switchCheckMin){
                        Toast.makeText(Schedule.this, "Please choose only 1 plan!",
                                Toast.LENGTH_LONG).show();
                    }
                    switchCheckMin = false;
                    SharedPreferences.Editor editor= getSharedPreferences("saveMin", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("valueMin", false);
                    editor.apply();
                    switchCompatMinutes.setChecked(false);

                }
            }
        });
        switchCompatHour = findViewById(R.id.hourSwitch);
        SharedPreferences sharedPreferencesHour = getSharedPreferences("saveHour", Context.MODE_PRIVATE);
        switchCompatHour.setChecked(sharedPreferencesHour.getBoolean("valueHour", false));

        switchCompatHour.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (switchCompatHour.isChecked() && ((switchCheckMin || switchCheckPom) == false)){
                    switchCheckHr = true;
                    SharedPreferences.Editor editor = getSharedPreferences("saveHour", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("valueHour", switchCompatHour.isChecked());
                    editor.apply();
                    switchCompatHour.setChecked(true);
                    Toast.makeText(Schedule.this, "1 Hour Plan is enabled!",
                            Toast.LENGTH_LONG).show();
                    System.out.println("1 Hour Plan is enabled!");

                }else{
                    if(!switchCheckHr){
                        Toast.makeText(Schedule.this, "Please choose only 1 plan!",
                                Toast.LENGTH_LONG).show();
                    }
                    switchCheckHr = false;
                    SharedPreferences.Editor editor= getSharedPreferences("saveHour", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("valueHour", false);
                    editor.apply();
                    switchCompatHour.setChecked(false);
                }
            }
        });
        // navigation Menu
        navigationMenu();

        if (fAuth.getCurrentUser() != null) {
            firebaseAdapter();
        }
        else {
            Toast.makeText(Schedule.this, "You need to register!", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    protected void onStart() {

        if (fAuth.getCurrentUser() != null) {
            super.onStart();
            scheduleAdapter.startListening();
        } else {
            super.onStart();

        }
    }


    private void navigationMenu() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.plan_item);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            private MenuItem item;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                this.item = item;
                switch (item.getItemId()) {
                    case R.id.home_item:
                        startActivity(new Intent(getApplicationContext()
                                , MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.task_item:
                        startActivity(new Intent(getApplicationContext()
                                , TaskList.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.plan_item:
                        return true;
                    case R.id.stat_item:
                        startActivity(new Intent(getApplicationContext()
                                , Statistics.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile_item:
                        startActivity(new Intent(getApplicationContext()
                                , Profile.class));
                        overridePendingTransition(0, 0);
                        return true;

                }
                return false;
            }
        });
    }

    private void firebaseAdapter() {
        userID = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
        db = FirebaseDatabase.getInstance().getReference().child("tasks").child(userID);

        RecyclerView recyclerViewSchedule = findViewById(R.id.doneTaskList);

        Query taskQuery = db.orderByChild("done").equalTo(true);

        recyclerViewSchedule.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<TaskItem> options = new FirebaseRecyclerOptions.Builder<TaskItem>()
                .setQuery(taskQuery, TaskItem.class)
                .build();

        scheduleAdapter = new scheduleAdapter(options);
        recyclerViewSchedule.setAdapter(scheduleAdapter);
    }


}