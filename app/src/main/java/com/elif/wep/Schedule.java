package com.elif.wep;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        switchCompat = findViewById(R.id.pomodoroSwitch);
        SharedPreferences sharedPreferences = getSharedPreferences("save", Context.MODE_PRIVATE);
        switchCompat.setChecked(sharedPreferences.getBoolean("value", true));

        switchCompat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switchCompat.isChecked()){
                    SharedPreferences.Editor editor = getSharedPreferences("save", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("value", switchCompat.isChecked());
                    editor.apply();
                    switchCompat.setChecked(true);
                    System.out.println("Pomodoro is enabled!");

                }else{
                    SharedPreferences.Editor editor= getSharedPreferences("save", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("value", false);
                    editor.apply();
                    switchCompat.setChecked(false);
                }
            }
        });
        // navigation Menu
        navigationMenu();

        firebaseAdapter();

    }

    @Override
    protected void onStart() {
        super.onStart();
        scheduleAdapter.startListening();
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
        fAuth = FirebaseAuth.getInstance();
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