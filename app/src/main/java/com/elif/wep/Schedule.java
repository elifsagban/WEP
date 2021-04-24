package com.elif.wep;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
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
    private ImageButton statPage;
    private ImageButton planPage;
    private ImageButton homePage;
    private ImageButton taskPage;
    private ImageButton profilePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

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

    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override
    protected void onStart() {
        super.onStart();
        scheduleAdapter.startListening();
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

}