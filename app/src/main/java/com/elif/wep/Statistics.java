package com.elif.wep;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Objects;

public class Statistics extends AppCompatActivity {

    FirebaseAuth fAuth;
    DatabaseReference db;
    String userID;
    RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerViewStatistics;
    private statisticsAdapter statisticsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.stat_item);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            private MenuItem item;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                this.item = item;
                switch (item.getItemId()){
                    case R.id.home_item:
                        startActivity(new Intent(getApplicationContext()
                                , MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.task_item:
                        startActivity(new Intent(getApplicationContext()
                                , TaskList.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.plan_item:
                        startActivity(new Intent(getApplicationContext()
                                , Schedule.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.stat_item:
                        return true;

                }
                return false;
            }
        });
        fAuth = FirebaseAuth.getInstance();
        userID = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
        db = FirebaseDatabase.getInstance().getReference().child("tasks").child(userID);

        recyclerViewStatistics = findViewById(R.id.statisticsRecyclerView);
        recyclerViewStatistics.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 1);


        Query taskQuery = db.orderByChild("done").equalTo(true);


        recyclerViewStatistics.setLayoutManager(layoutManager);

        FirebaseRecyclerOptions<TaskItem> options = new FirebaseRecyclerOptions.Builder<TaskItem>()
                .setQuery(taskQuery, TaskItem.class)
                .build();

        statisticsAdapter = new statisticsAdapter(options);
        recyclerViewStatistics.setAdapter(statisticsAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        statisticsAdapter.startListening();
    }


}